package view;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;

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

				Tab tab = tabWrapper.get(friend);

				Text ChatText = (Text) tab.getContent().lookup(
						"#tab-text");

				ChatText.setText(ChatText.getText() + "\n"
						+ friend.getName() + ": " + message);
			});
		}
	}
}
