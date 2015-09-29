package view;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Summoner;

import com.github.theholywaffle.lolchatapi.LolStatus;
import com.github.theholywaffle.lolchatapi.LolStatus.GameStatus;

class SummonerCell extends ListCell<Summoner> {
	
	private Text textName = new Text();
	
	private Text textStatus = new Text();
	
	public void updatePresence(Summoner summoner) {
		LolStatus lolStatus = summoner.getStatus();
		GameStatus queue = lolStatus.getGameStatus();
		if(summoner.isOnline() && queue != null) {
			System.out.println(summoner.getName() + queue.toString() + " " + queue.internal());
			if (queue == GameStatus.IN_GAME) {
			} 
			textName.setText(summoner.getName() + " - " + queue.internal());
		}
	}
	
	@Override
	public void updateItem(Summoner summoner, boolean empty) {
		super.updateItem(summoner, empty);
		if (summoner != null) {
			System.out.println("list updateItem" + summoner.getName());
			summoner.registerPresenceObserver(() -> updatePresence(summoner));
			HBox box = new HBox();
			textName.setText(summoner.getName());
			box.getChildren().add(textName);
			box.getChildren().add(textStatus);
			setGraphic(textName);
		}
	}
}