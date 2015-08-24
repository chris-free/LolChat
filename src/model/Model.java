package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.github.theholywaffle.lolchatapi.ChatServer;
import com.github.theholywaffle.lolchatapi.FriendRequestPolicy;
import com.github.theholywaffle.lolchatapi.LolChat;
import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendRequestListener;
import com.github.theholywaffle.lolchatapi.riotapi.RateLimit;
import com.github.theholywaffle.lolchatapi.riotapi.RiotApiKey;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class Model {

	   private  LolChat api;
	   
	   public void addChatListener(ChatListener chatListener) {
		   api.addChatListener(chatListener);
	   }
	   
	   public void addFriendListener(FriendListener friendListener) {
		   api.addFriendListener(friendListener);
		   
	   }
	   
	   public ObservableList<Friend> getOnlineFriends() {
		   return FXCollections.observableArrayList(api.getFriends());
	   }
	   
	   public boolean login(String userName, String password) {
			if (api.login(userName, password)) {
				return true;
			} else {
				return false;
			}
	   }
	   
	   public Model() {
		    api = new LolChat(ChatServer.EUW, FriendRequestPolicy.MANUAL, new RiotApiKey("RIOT-API-KEY", RateLimit.DEFAULT));
		    
			api.setFriendRequestListener(new FriendRequestListener() {

				public boolean onFriendRequest(String userId, String name) {
					System.out.println(userId + " wants to add me. Yes or no?");
					return true; // Accept user
				}
			});
	   }
}