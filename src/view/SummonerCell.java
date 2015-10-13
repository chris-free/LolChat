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

class SummonerCell extends ListCell<Summoner> {

	private Text textName;

	private Circle statusCircle;
	
	private Summoner sum;
	
	private Runnable listener;

	public void updatePresence(Summoner summoner) {
		if (!sum.getName().equals(summoner.getName())) {
			System.out.println(sum.getName() + " given when registered as " + summoner.getName());
			return;
		}
		if(summoner.isOnline()) {
				ChatMode chatMode = summoner.getChatMode();
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

 			LolStatus lolStatus = summoner.getStatus();
			GameStatus queue = lolStatus.getGameStatus();
			if(queue != null) {
				System.out.println(summoner.getName() + queue.toString() + " " + queue.internal());
				textName.setText(summoner.getName() + " - " + queue.internal());
			}
		} else {
			statusCircle.setFill(Color.GRAY);
		}
	}

	@Override
	public void updateItem(Summoner summoner, boolean empty) {
		super.updateItem(summoner, empty);
		
	    if (empty) {
//	    	System.out.println("empty" + sum.getName() + " : " + summoner.getName());
	        setGraphic(null);
	        setText(null);
	      }
	    else if (this.sum == summoner) {
			return;
		}
		else if (this.sum != summoner) {
			if (sum != null) {
	    	System.out.println("creating new cell" + sum.getName() + " : " + summoner.getName());
			} else if (summoner != null){
		    	System.out.println("creating new cell" +  " : " + summoner.getName());
			} else{
				System.out.println("new cell");
			}
	    	if (listener != null) {
				this.sum.removePresenceObserver(listener);
				listener = null;
			}
			this.sum = summoner;
			listener = () -> updatePresence(summoner);
			summoner.registerPresenceObserver(listener);
			HBox box = new HBox();
			textName = new Text();
			textName.setText(summoner.getName());
			statusCircle = new Circle(0, 0, 5, Color.GREY);
			box.getChildren().add(statusCircle);
			box.getChildren().add(textName);
			setGraphic(box);
			updatePresence(summoner);
			
		}
	    
	}
}