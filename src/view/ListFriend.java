package view;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ListFriend {
	private Friend friend;

	public ListFriend(Friend friend) {
		this.friend = friend;
	}
	public Friend getFriend() {
		return friend;
	}
	
	public String toString() {
		return friend.getName();
	}

}
