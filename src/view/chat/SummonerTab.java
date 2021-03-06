package view.chat;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.Summoner;

import com.github.theholywaffle.lolchatapi.ChatMode;

public class SummonerTab extends Tab {

	private final Summoner summoner;

	private final Text messageList;

	private final Circle circleGraphic;

	private final Text textGraphic;

	public Summoner getSummoner() {
		return summoner;
	}

	public void incomingMessage(String userName, String message) {
		if (!isSelected()) {
			textGraphic.setFill(Color.ORANGE);
		}

		messageList.setText(messageList.getText() 
				+ "\n"
				+ userName
				+ ": " 
				+ message); 
	}

	public void clearMessageNotification() {
		textGraphic.setFill(Color.BLACK);
	}

	public void updatePresence() {
		System.out.println(summoner.getName() + " is PRESENCED" + summoner.isOnline());
		if (summoner.isOnline()) {
			ChatMode chatMode = summoner.getChatMode();
			if (chatMode != null) {
				if (chatMode == ChatMode.AVAILABLE) 
					circleGraphic.setFill(Color.GREEN);
				else if (chatMode == ChatMode.AWAY) 
					circleGraphic.setFill(Color.YELLOW);
				else if (chatMode == ChatMode.BUSY) 
					circleGraphic.setFill(Color.RED);
				else 
					circleGraphic.setFill(Color.GRAY);
			}
			else {
				circleGraphic.setFill(Color.GRAY);
			}
		} else{
			circleGraphic.setFill(Color.GREY);
		}
	}

	public SummonerTab(Summoner summoner) {
		this.summoner = summoner;	

		setOnSelectionChanged((Event value) 
				-> ((SummonerTab) value.getTarget()).clearMessageNotification());						

		messageList = new Text(10, 20, "");

		summoner.registerPresenceObserver(this::updatePresence);

		circleGraphic = new Circle(0, 0, 5, Color.GREY);
		updatePresence();

		textGraphic = new Text(" " + summoner.getName());
		HBox graphicBox = new HBox();
		graphicBox.getChildren().add(circleGraphic);
		graphicBox.getChildren().add(textGraphic);
		setGraphic(graphicBox);


		HBox chatBox = new HBox();

		TextArea chatTextArea = new TextArea();
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				String message = chatTextArea.getText();
				messageList.setText(messageList.getText() 
						+ "\n" 
						+ "You: " 
						+ message); 
				chatTextArea.setText("");
				summoner.sendMessage(message);
			}
		});

		chatBox.getChildren().add(chatTextArea);
		chatBox.getChildren().add(submitButton);

		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(chatBox);
		borderPane.setCenter(messageList);

		this.setContent(borderPane);
	}
}
