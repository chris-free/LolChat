package view;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ViewChat {
	
	private TabPane tabPane;
	
	private ListView<ListFriend> listView;
	
	private Scene scene;
	
	private ChatListener chatListener;
	
	private FriendListener friendListener;
	
	public void selectFriend(Friend friend) {
		
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public ChatListener getChatListener() {
		return chatListener;
	}
	
	public FriendListener getFriendListener() {
		return friendListener;
	}
	
	public ViewChat() {
		this.tabPane = new TabPane();
		this.listView = new ListView<ListFriend>();
		SceneFactory chatSceneFactory = new ChatSceneFactory(tabPane, listView);
		scene = chatSceneFactory.create();
	}

}
