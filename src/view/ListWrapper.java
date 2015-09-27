package view;


import javafx.scene.control.ListView;
import model.Summoner;

public class ListWrapper {

	private ListView <Summoner> listView;

	public ListWrapper (ListView<Summoner> listView) {
		this.listView = listView;
	}

	public void select(Summoner summoner) {

		listView.getSelectionModel().select(summoner);
	}
}
