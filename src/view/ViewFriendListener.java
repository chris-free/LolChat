package view;

import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ViewFriendListener implements FriendListener{

	private TabWrapper tabWrapper;
	
	private ListWrapper ListWrapper;
	
	public ViewFriendListener(ListWrapper listWrapper, TabWrapper tabWrapper) {
		this.ListWrapper = listWrapper;
		this.tabWrapper = tabWrapper;
	}
	
	@Override
	public void onFriendAvailable(Friend friend) {
	}

	@Override
	public void onFriendAway(Friend friend) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFriendBusy(Friend friend) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFriendJoin(Friend friend) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFriendLeave(Friend friend) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFriendStatusChange(Friend friend) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNewFriend(Friend friend) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onRemoveFriend(String userId, String name) {
		// TODO Auto-generated method stub
	}

}
