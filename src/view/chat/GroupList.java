package view.chat;

import java.util.List;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Summoner;

public class GroupList extends TitledPane {

	public GroupList(GridPane groupGrid, String groupName, List <Summoner> friendList) {
		super(groupName, new Text(friendList.get(0).getName()));
	}
}
