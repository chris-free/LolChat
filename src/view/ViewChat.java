package view;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import model.Api;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ViewChat {
	
	private TabPane tabPane;
	
	private ListView<ListFriend> listView;
	
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
	
	public ViewChat(Api lolApi) {
		this.tabPane = new TabPane();
		this.listView = new ListView<ListFriend>();
		
		List <ListFriend> listFriends = lolApi
				.getOnlineFriends()
				.stream()
				.map(i -> new ListFriend(i))
				.collect(Collectors.toList());
		
		listView.setItems(FXCollections.observableArrayList(listFriends));
		
		SceneFactory chatSceneFactory = new ChatSceneFactory(tabPane, listView);
		scene = chatSceneFactory.create();
	}

}
