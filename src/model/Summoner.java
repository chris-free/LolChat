package model;

import java.util.List;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class Summoner {

	protected Friend friend;
	private List <Runnable> presenceConsumers;
	
	public Summoner(Friend friend) {
		this.friend = friend;
	}
	
	public void notifyPresenceObservers() {
		for (Runnable statusObserver : presenceConsumers) {
			statusObserver.run();
		}
	}
	
	public void registerPresenceObserver(Runnable o) {
		presenceConsumers.add(o);
	}

	public void registerChatObserver(ChatListener listener) {
		friend.setChatListener(listener);
	}
	
}
