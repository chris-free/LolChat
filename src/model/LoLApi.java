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
	
	private FriendListener viewFriendListener;
	
	private List<Summoner> summoners;

	public void reset() {
		api.removeFriendListener(viewFriendListener);
		summoners = api.getFriends().stream().map(i -> new Summoner(i)).collect(Collectors.toList());
		viewFriendListener = new SummonerFriendListener(summoners);
		api.addFriendListener(viewFriendListener);
	}
	
	@Override
	public List<Summoner> getSummoners() {
		return this.summoners;
	}
	
	public boolean login(String userName, String password) {
		if (api.login(userName, password)) {
			summoners = api.getFriends().stream().map(i -> new Summoner(i)).collect(Collectors.toList());
			viewFriendListener = new SummonerFriendListener(summoners);
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