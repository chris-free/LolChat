package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class TabWrapper {

	private TabPane tabPane;

	public TabWrapper (TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public TabFriend get(Friend f) {
		return contains(f) ? find(f) : create(f);
	}

	private TabFriend create(Friend f) {
		TabFriend t = new TabFriend(f);
		tabPane.getTabs().add(t);
		return t;
	}

	private TabFriend find(Friend f) {
		return (TabFriend) tabPane
				.getTabs()
				.stream()
				.filter(t -> ((TabFriend) t).getFriend().getUserId() == f.getUserId())
				.findFirst()
				.get();
	}

	private boolean contains(Friend f) {
		return tabPane
				.getTabs()
				.stream()
				.anyMatch(t -> ((TabFriend) t).getFriend().getUserId() == f.getUserId());
	}

	public void select(Friend f) {
		Tab tab = get(f);
		tabPane.getSelectionModel().select(tab);
	}
}
