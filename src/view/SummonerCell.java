package view;


import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.Summoner;

import com.github.theholywaffle.lolchatapi.ChatMode;
import com.github.theholywaffle.lolchatapi.LolStatus;
import com.github.theholywaffle.lolchatapi.LolStatus.GameStatus;

public class SummonerCell extends ListCell<Summoner> {

	private Text textName;

	private Circle statusCircle;
	
	private Summoner summoner;
	
	private Runnable listener;

	public void updatePresence() {
		if(this.summoner.isOnline()) {
				ChatMode chatMode = this.summoner.getChatMode();
				if (chatMode != null) {
					if (chatMode == ChatMode.AVAILABLE) 
						statusCircle.setFill(Color.GREEN);
					else if (chatMode == ChatMode.AWAY) 
						statusCircle.setFill(Color.YELLOW);
					else if (chatMode == ChatMode.BUSY) 
						statusCircle.setFill(Color.RED);
					else 
						statusCircle.setFill(Color.GRAY);
				}
				else {
					statusCircle.setFill(Color.GRAY);
				}

 			LolStatus lolStatus = this.summoner.getStatus();
			GameStatus queue = lolStatus.getGameStatus();
			if(queue != null) {
				System.out.println(this.summoner.getName() + queue.toString() + " " + queue.internal());
				textName.setText(this.summoner.getName() + " - " + queue.internal());
			}
		} else {
			statusCircle.setFill(Color.GRAY);
		}
	}

	@Override
	public void updateItem(Summoner summoner, boolean empty) {
		super.updateItem(summoner, empty);
		
	    if (empty) {
	    	if (listener != null) {
				this.summoner.removePresenceObserver(listener);
				listener = null;
			}
	    	this.summoner = null;
	        setGraphic(null);
	        setText(null);
	      }
	    else if (this.summoner == summoner) {
			return;
		}
		else if (this.summoner != summoner) {
	    	if (listener != null) {
				this.summoner.removePresenceObserver(listener);
				listener = null;
			}
			this.summoner = summoner;
			listener = this::updatePresence;
			summoner.registerPresenceObserver(listener);
			HBox box = new HBox();
			textName = new Text();
			textName.setText(summoner.getName());
			statusCircle = new Circle(0, 0, 5, Color.GREY);
			box.getChildren().add(statusCircle);
			box.getChildren().add(textName);
			setGraphic(box);
			updatePresence();
		}
	    
	}
}