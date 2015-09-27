package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

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
	
	private List<BiConsumer<Summoner, String> > chatListenerList = new ArrayList<BiConsumer<Summoner, String> >();

	@Override
	public void registerChatObserver(BiConsumer<Summoner, String> listener) {
        chatListenerList.add(listener);
	}

	public void onMessage(Summoner summoner, String message) {
		for (BiConsumer<Summoner, String>  listener: chatListenerList) {
			listener.accept(summoner, message);
		}
	}
	
}
