package model;

import java.util.Map;
import java.util.stream.Collectors;

import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class SummonerFriendListener implements FriendListener {
	private Map <String, Summoner> summoners;
	
	public SummonerFriendListener(Map <String, Summoner> summoners) {
		System.out.println("reset?");
		this.summoners = summoners;
	}
	
	public void notifyPresence(Friend friend) {
		summoners.get(friend.getUserId()).notifyPresenceObservers();
	}
	
	@Override
	public void onFriendAvailable(Friend friend) {
		System.out.println(friend.getName() + " is available");
		notifyPresence(friend);
	}

	@Override
	public void onFriendAway(Friend friend) {
		System.out.println(friend.getName() + " is away ");
		notifyPresence(friend);
	}

	@Override
	public void onFriendBusy(Friend friend) {
		System.out.println(friend.getName()+ " is busy");
		notifyPresence(friend);		
	}

	@Override
	public void onFriendJoin(Friend friend) {
		System.out.println(friend.getName() + " is join");
		notifyPresence(friend);		
	}

	@Override
	public void onFriendLeave(Friend friend) {
		System.out.println(friend.getName() + " is leave");
		notifyPresence(friend);
	}

	@Override
	public void onFriendStatusChange(Friend friend) {
		System.out.println(friend.getName() + " is statuscahnge");
		notifyPresence(friend);
	}

	@Override
	public void onNewFriend(Friend friend) {
		// TODO Auto-generated method stub
		System.out.println(friend.getName() + " is new");
	}

	@Override
	public void onRemoveFriend(String userId, String name) {
		// TODO Auto-generated method stub
	}

}
