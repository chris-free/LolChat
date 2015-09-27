package view;

import java.util.Optional;

import javafx.scene.control.ListView;
import model.Summoner;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ListWrapper {

	private ListView <Summoner> listView;

	public ListWrapper (ListView<Summoner> listView) {
		this.listView = listView;
	}

	public void select(Friend friend) {

		Optional opt = listView.getItems()
				.stream()
				.filter(x -> (x.getFriend() == friend))
				.findFirst();

		if (opt.isPresent()) {
			Summoner summoner = (Summoner) opt.get();
			listView.getSelectionModel().select(summoner);
		}
	}
}
