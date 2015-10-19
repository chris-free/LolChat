package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import com.github.theholywaffle.lolchatapi.ChatMode;
import com.github.theholywaffle.lolchatapi.LolStatus;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;
import com.github.theholywaffle.lolchatapi.wrapper.FriendGroup;

public class Summoner {

	protected Friend friend;
	private List <Runnable> presenceConsumers = Collections.synchronizedList(new ArrayList<Runnable>());

	public Summoner(Friend friend) {
		this.friend = friend;
	}

	public void notifyPresenceObservers() {
		presenceConsumers.stream()
						 .collect(Collectors.toList())
						 .forEach(Runnable::run);
	}

	public void registerPresenceObserver(Runnable o) {
		presenceConsumers.add(o);
	}

	public void registerChatObserver(BiConsumer<Summoner, String> listener) {
		friend.setChatListener((Friend friend, String message) ->  listener.accept(this, message));
	}

	public void removePresenceObserver(Runnable o) {
		presenceConsumers.remove(o);
	}

	public void sendMessage(String message) {
		friend.sendMessage(message);
		System.out.println("sending " + message);
	}

	public Friend getFriend() {
		return friend;
	}
	public String getGroupName() {
		FriendGroup group = friend.getGroup();
		return group.getName();
	}
	
	public String getUserId() {
		return friend.getUserId();
	}

	public String getName() {
		return friend.getName();
	}

	public boolean isOnline() {
		return friend.isOnline();
	}

	public LolStatus getStatus() {
		return friend.getStatus();
	}

	public ChatMode getChatMode() {
		return friend.getChatMode();
	}

}
