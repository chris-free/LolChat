package view;

import java.util.List;

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

import com.github.theholywaffle.lolchatapi.LolStatus;
import com.github.theholywaffle.lolchatapi.LolStatus.GameStatus;
import com.github.theholywaffle.lolchatapi.LolStatus.Queue;

public class ChatView {

	private ListWrapper listWrapper;

	private TabWrapper tabWrapper;

	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	public ChatView(Api lolApi) {
		TabPane tabPane = new TabPane();
		ListView<Summoner> listView = new ListView<Summoner>();

		this.listWrapper = new ListWrapper(listView);
		this.tabWrapper = new TabWrapper(tabPane);

		List <Summoner> summoners = lolApi.getSummoners();

		for (Summoner sum : summoners) {
			sum.registerChatObserver((Summoner summoner, String message)
					-> {
						if (message != null) {
							System.out.println(message);
							Platform.runLater(() -> {
								SummonerTab tab = tabWrapper.get(summoner);
								tab.incomingMessage(summoner.getName(), message);
							});
						}});
			
			sum.registerPresenceObserver(()
					-> { 
						java.util.Collections.sort(listView.getItems(), new java.util.Comparator<Summoner>() {
						    @Override
						    public int compare(Summoner o1, Summoner o2) {
						    	if ((o1.isOnline() && o2.isOnline()) || (!o1.isOnline() && !o2.isOnline())) {
						    		return o1.getName().compareTo(o2.getName());
						    	} else {
						    		if (o1.isOnline()) {
						    			return -1;
						    		} else{
						    			return 1;
						    		}
						    	}
						    }
						});
					});
		};

		listView.setItems(FXCollections.observableArrayList(summoners));
		listView.setPrefSize(200, 250);
		listView.setCellFactory(new Callback<ListView<Summoner>, ListCell<Summoner>>() {
			@Override 
			public ListCell<Summoner> call(ListView<Summoner> list) {
				return new SummonerCell();
			}});

		Group root = new Group();
		scene = new Scene(root, 400, 250, Color.WHITE);

		listView.getSelectionModel()
		.selectedItemProperty()
		.addListener((ObservableValue<? extends Summoner> oValue, Summoner previousSelected, Summoner selected) 
				-> {
					if (selected != null) {
						tabWrapper.select(selected);
					}
				});

		tabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>() {
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
						if (t1 != null) {
							Summoner selectedFriend =  ((SummonerTab) t1).getSummoner();
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
