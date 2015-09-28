package model;

import java.util.Map;

import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class SummonerFriendListener implements FriendListener {
	private Map <Friend, Summoner> summoners;
	
	public SummonerFriendListener(Map <Friend, Summoner> summoners) {
		System.out.println("reset?");
		this.summoners = summoners;
	}
	
	@Override
	public void onFriendAvailable(Friend friend) {
		System.out.println(friend.getName() + " is available");
		summoners.get(friend).notifyPresenceObservers();
	}

	@Override
	public void onFriendAway(Friend friend) {
		System.out.println(friend.getName() + " is away ");
		summoners.get(friend).notifyPresenceObservers();
	}

	@Override
	public void onFriendBusy(Friend friend) {
		System.out.println(friend.getName()+ " is busy");
		summoners.get(friend).notifyPresenceObservers();
		
	}

	@Override
	public void onFriendJoin(Friend friend) {
		System.out.println(friend.getName() + " is join");
		summoners.get(friend).notifyPresenceObservers();
	}

	@Override
	public void onFriendLeave(Friend friend) {
		System.out.println(friend.getName() + " is leave");
		summoners.get(friend).notifyPresenceObservers();
	}

	@Override
	public void onFriendStatusChange(Friend friend) {
		System.out.println(friend.getName() + " is statuscahnge");
		summoners.get(friend).notifyPresenceObservers();
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
