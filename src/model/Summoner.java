package model;

import java.util.List;
import java.util.function.BiConsumer;

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

	public void registerChatObserver(BiConsumer<Summoner, String> listener) {
		friend.setChatListener((Friend friend, String message) ->  listener.accept(this, message));
	}
	
	public void sendMessage(String message) {
		friend.sendMessage(message);
		System.out.println("sending " + message);
	}
	
	public Friend getFriend() {
		return friend;
	}
	
	public String getUserId() {
		return friend.getUserId();
	}
	
	public String getName() {
		return friend.getName();
	}
	
}
