package model;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.github.theholywaffle.lolchatapi.ChatServer;
import com.github.theholywaffle.lolchatapi.FriendRequestPolicy;
import com.github.theholywaffle.lolchatapi.LolChat;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendRequestListener;
import com.github.theholywaffle.lolchatapi.riotapi.RateLimit;
import com.github.theholywaffle.lolchatapi.riotapi.RiotApiKey;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class LoLApi implements Api{

	private  LolChat api;

	@Override
	public List<Summoner> getSummoners() {
		return api.getFriends().stream().map(i -> new Summoner(i)).collect(Collectors.toList());
	}
	
	public boolean login(String userName, String password) {
		if (api.login(userName, password)) {
			FriendListener viewFriendListener = new SummonerFriendListener(getSummoners());
			api.addFriendListener(viewFriendListener);
			return true;
		} else {
			return false;
		}
	}

	public LoLApi() {
		api = new LolChat(ChatServer.EUW, FriendRequestPolicy.MANUAL, new RiotApiKey("RIOT-API-KEY", RateLimit.DEFAULT));

		api.setFriendRequestListener(new FriendRequestListener() {

			public boolean onFriendRequest(String userId, String name) {
				System.out.println(userId + " wants to add me. Yes or no?");
				return true; // Accept user
			}
		});
	}

}