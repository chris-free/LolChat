package view;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class TabWrapper {
	
	private TabPane tabPane;
	
	public TabWrapper (TabPane tabPane) {
		this.tabPane = tabPane;
	}
	
	// Contains/ getTab/ addTab/
	public Tab getTab(Friend f) {
		return tabPane.getTabs().stream().filter(t -> ((TabFriend) t).getFriend().getUserId() == f.getUserId()).findFirst().get();
	}
	
	public boolean contains(Friend f) {
		return tabPane
				.getTabs()
				.stream()
				.anyMatch(t -> ((TabFriend) t).getFriend().getUserId() == f.getUserId());
	}
	
	public void add(Tab t) {
	       tabPane.getTabs().add(t);
	}
	
}
