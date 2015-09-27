package model;

import java.util.ArrayList;
import java.util.List;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class MockSummoner extends Summoner {

	public Friend getFriend() {
		return friend;
	}
	public MockSummoner(Friend friend) {
		super(friend);
		// TODO Auto-generated constructor stub
	}
	
	private List<ChatListener> chatListenerList = new ArrayList<ChatListener>();

	@Override
	public void registerChatObserver(ChatListener listener) {
        chatListenerList.add(listener);
	}

	public void onMessage(Friend friend, String message) {
		for (ChatListener listener: chatListenerList) {
			listener.onMessage(friend, message);
		}
	}
	
}
