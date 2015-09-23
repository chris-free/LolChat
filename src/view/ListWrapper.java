package view;

import java.util.Optional;

import javafx.scene.control.ListView;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class ListWrapper {

	private ListView <ListFriend> listView;

	public ListWrapper (ListView<ListFriend> listView) {
		this.listView = listView;
	}

	public void select(Friend friend) {

		Optional opt = listView.getItems()
				.stream()
				.filter(x -> (x.getFriend() == friend))
				.findFirst();

		if (opt.isPresent()) {
			ListFriend listFriend = (ListFriend) opt.get();
			listView.getSelectionModel().select(listFriend);
		}
	}
}
