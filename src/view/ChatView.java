package view;

import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.Api;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ChatView {
	
	private ListWrapper listWrapper;

	private TabWrapper tabWrapper;
	
	private Scene scene;
	
	private ChatListener chatListener;
	
	private FriendListener friendListener;
	
	public Scene getScene() {
		return scene;
	}
	
	public ChatListener getChatListener() {
		return chatListener;
	}
	
	public FriendListener getFriendListener() {
		return friendListener;
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
		
		listView.setItems(FXCollections.observableArrayList(listFriends));
		
		
		Group root = new Group();
		scene = new Scene(root, 400, 250, Color.WHITE);
		
		listView.setPrefSize(200, 250);
		listView.getSelectionModel()
				.selectedItemProperty()
				.addListener((ObservableValue<? extends ListFriend> oValue, ListFriend previousSelected, ListFriend selected)  -> {
					if (selected != null) {
						Friend selectedFriend = selected.getFriend();
						if (!tabWrapper.contains(selectedFriend)) {
						  TabFriend tab = new TabFriend(selectedFriend);
						  tabWrapper.add(tab);
						}
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
