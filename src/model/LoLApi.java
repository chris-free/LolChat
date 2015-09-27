package model;

import java.util.List;
import java.util.stream.Collectors;

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

public class LoLApi implements Api{

	private  LolChat api;

	public void setChatListener(ChatListener chatListener) {
	}

	public void setFriendListener(FriendListener friendListener) {
	}

	@Override
	public List<Summoner> getSummoners() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ObservableList<Friend> getOnlineFriends() {
		List <Friend> onlineFriends = api.getFriends((Friend f) -> f.isOnline());
		return FXCollections.observableArrayList(onlineFriends);
	}

	public boolean login(String userName, String password) {
		if (api.login(userName, password)) {
			List <Summoner> summoners = api.getFriends()
					.stream()
					.map(i -> new Summoner(i))
					.collect(Collectors.toList());

			FriendListener viewFriendListener = new SummonerFriendListener(summoners);
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