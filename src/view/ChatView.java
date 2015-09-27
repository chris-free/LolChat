package view;

import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Api;
import model.Summoner;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ChatView {

	private ListWrapper listWrapper;

	private TabWrapper tabWrapper;

	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	static class ColorRectCell extends ListCell<ListFriend> {
		@Override
		public void updateItem(ListFriend item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				HBox box = new HBox();
				Text t = new Text(item.getFriend().getName());
				item.getFriend().getName();
				box.getChildren().add(t);
				setGraphic(t);
			}
		}
	}

	public ChatView(Api lolApi) {
		TabPane tabPane = new TabPane();
		ListView<ListFriend> listView = new ListView<ListFriend>();

		this.listWrapper = new ListWrapper(listView);
		this.tabWrapper = new TabWrapper(tabPane);

		List <ListFriend> listFriends = lolApi
				.getOnlineFriends()
				.stream()
				.map(i -> new ListFriend(i))
				.collect(Collectors.toList());

		List <Summoner> summoners = lolApi.getSummoners();

		for (Summoner sum : summoners) {
			sum.registerChatObserver(new com.github.theholywaffle.lolchatapi.listeners.ChatListener(){
				public void onMessage(Friend friend, String message) {
					if (message != null) {
						System.out.println(message);
						Platform.runLater(() -> {
							TabFriend tab = tabWrapper.get(friend);
							tab.incomingMessage(friend.getName(), message);
						});
					}
				}});
		};

		listView.setItems(FXCollections.observableArrayList(listFriends));
		listView.setPrefSize(200, 250);
		listView.setCellFactory(new Callback<ListView<ListFriend>, ListCell<ListFriend>>() {
			@Override 
			public ListCell<ListFriend> call(ListView<ListFriend> list) {
				return new ColorRectCell();
			}});

		Group root = new Group();
		scene = new Scene(root, 400, 250, Color.WHITE);

		listView.getSelectionModel()
		.selectedItemProperty()
		.addListener((ObservableValue<? extends ListFriend> oValue, ListFriend previousSelected, ListFriend selected)  -> {
			if (selected != null) {
				Friend selectedFriend = selected.getFriend();
				tabWrapper.select(selectedFriend);
			}
		});

		tabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>() {
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
						if (t1 != null) {
							Friend selectedFriend =  ((TabFriend) t1).getFriend();
							listWrapper.select(selectedFriend);
						}

					}
				}
				);


		BorderPane borderPane = new BorderPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		borderPane.setCenter(tabPane);
		borderPane.setRight(listView);      

		root.getChildren().add(borderPane);
	}

}
