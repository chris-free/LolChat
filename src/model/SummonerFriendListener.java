package model;

import java.util.List;

import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class SummonerFriendListener implements FriendListener{
	private List <Summoner> summoners;
	
	public SummonerFriendListener(List <Summoner> summoners) {
		this.summoners = summoners;
	}
	
	@Override
	public void onFriendAvailable(Friend friend) {
		
	}

	@Override
	public void onFriendAway(Friend friend) {
		// TODO Auto-generated method stub
		System.out.println(friend.getName() + " is away ");
	}

	@Override
	public void onFriendBusy(Friend friend) {
		// TODO Auto-generated method stub
		System.out.println(friend.getName()+ " is busy");
		
	}

	@Override
	public void onFriendJoin(Friend friend) {
		// TODO Auto-generated method stub
		System.out.println(friend.getName() + " is join");
	}

	@Override
	public void onFriendLeave(Friend friend) {
		// TODO Auto-generated method stub
		System.out.println(friend.getName() + " is leave");
	}

	@Override
	public void onFriendStatusChange(Friend friend) {
		// TODO Auto-generated method stub
		System.out.println(friend.getName() + " is statuscahnge");
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
