package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Summoner;

public class TabWrapper {

	private TabPane tabPane;

	public TabWrapper (TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public TabFriend get(Summoner f) {
		return contains(f) ? find(f) : create(f);
	}

	private TabFriend create(Summoner f) {
		TabFriend t = new TabFriend(f);
		tabPane.getTabs().add(t);
		return t;
	}

	private TabFriend find(Summoner f) {
		return (TabFriend) tabPane
				.getTabs()
				.stream()
				.filter(t -> ((TabFriend) t).getSummoner().getUserId() == f.getUserId())
				.findFirst()
				.get();
	}

	public boolean contains(Summoner f) {
		return tabPane
				.getTabs()
				.stream()
				.anyMatch(t -> ((TabFriend) t).getSummoner().getUserId() == f.getUserId());
	}

	public void select(Summoner f) {
		Tab tab = get(f);
		tabPane.getSelectionModel().select(tab);
	}
}
