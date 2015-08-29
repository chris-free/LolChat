package model;

import javafx.collections.ObservableList;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public interface Api {

	   public void addChatListener(ChatListener chatListener);
	   public void addFriendListener(FriendListener friendListener) ;	   
	   public ObservableList<Friend> getOnlineFriends();
	   public boolean login(String userName, String password);
	   
}