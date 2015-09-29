package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Summoner;

public class TabWrapper {

	private TabPane tabPane;

	public TabWrapper (TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public SummonerTab get(Summoner f) {
		return contains(f) ? find(f) : create(f);
	}

	private SummonerTab create(Summoner f) {
		SummonerTab t = new SummonerTab(f);
		tabPane.getTabs().add(t);
		return t;
	}

	private SummonerTab find(Summoner f) {
		return (SummonerTab) tabPane
				.getTabs()
				.stream()
				.filter(t -> ((SummonerTab) t).getSummoner().getUserId() == f.getUserId())
				.findFirst()
				.get();
	}

	public boolean contains(Summoner f) {
		return tabPane
				.getTabs()
				.stream()
				.anyMatch(t -> ((SummonerTab) t).getSummoner().getUserId() == f.getUserId());
	}

	public void select(Summoner f) {
		Tab tab = get(f);
		tabPane.getSelectionModel().select(tab);
	}
}
