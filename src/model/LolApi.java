package model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.theholywaffle.lolchatapi.ChatServer;
import com.github.theholywaffle.lolchatapi.FriendRequestPolicy;
import com.github.theholywaffle.lolchatapi.LolChat;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendRequestListener;
import com.github.theholywaffle.lolchatapi.riotapi.RateLimit;
import com.github.theholywaffle.lolchatapi.riotapi.RiotApiKey;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class LolApi implements Api{

	private  LolChat api;
	
	private FriendListener viewFriendListener;
	
	private Map<String, Summoner> summoners;

	public void reset() {
		api.removeFriendListener(viewFriendListener);
		summoners = api.getFriends().stream().map(i -> new Summoner(i)).collect(Collectors.toMap(Summoner::getUserId, Function.identity()));
		viewFriendListener = new SummonerFriendListener(summoners);
		api.addFriendListener(viewFriendListener);
	}
	
	@Override
	public List<Summoner> getSummoners() {
		return summoners.values().stream().collect(Collectors.toList());
	}
	
	public boolean login(String userName, String password) {
		if (api.login(userName, password)) {
			summoners = api.getFriends().stream().map(i -> new Summoner(i)).collect(Collectors.toMap(Summoner::getUserId, Function.identity()));
			viewFriendListener = new SummonerFriendListener(summoners);
			api.addFriendListener(viewFriendListener);
			return true;
		} else {
			return false;
		}
	}

	public LolApi() {
		api = new LolChat(ChatServer.EUW, FriendRequestPolicy.MANUAL, new RiotApiKey("RIOT-API-KEY", RateLimit.DEFAULT));

		api.setFriendRequestListener(new FriendRequestListener() {

			public boolean onFriendRequest(String userId, String name) {
				System.out.println(userId + " wants to add me. Yes or no?");
				return true; // Accept user
			}
		});
	}

}