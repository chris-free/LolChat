package model;

import java.util.List;

import javafx.collections.ObservableList;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public interface Api {

	   public void setChatListener(ChatListener chatListener);
	   public void setFriendListener(FriendListener friendListener) ;	   
	   public ObservableList<Friend> getOnlineFriends();
	   public boolean login(String userName, String password);
	   public List <Summoner> getSummoners();
}
