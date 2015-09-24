package view;

import javafx.application.Platform;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ViewChatListener implements ChatListener {

	private TabWrapper tabWrapper;
	
	public ViewChatListener(TabWrapper tabWrapper) {
		this.tabWrapper = tabWrapper;
	}
	
	@Override
	public void onMessage(Friend friend, String message) {
		if (message != null) {
			Platform.runLater(() -> {

				TabFriend tab = tabWrapper.get(friend);
				
				tab.incomingMessage(friend.getName(), message);
			});
		}
	}
}
