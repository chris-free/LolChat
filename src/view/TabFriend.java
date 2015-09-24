package view;

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

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class TabFriend extends Tab{

	private final Friend friend;

	private final Text messageList;

	private final Circle circleGraphic;
	
	private final Text textGraphic;
	
	public Friend getFriend() {
		return friend;
	}
	
	public void incomingMessage(String userName, String message) {
		if (!isSelected()) {
			circleGraphic.setFill(Color.RED);
		}
		
		messageList.setText(messageList.getText() 
				+ "\n"
				+ userName
				+ ": " 
				+ message); 
	}
	
	public void clearTabNotification() {
		textGraphic.setFill(Color.BLACK);
	}

	public TabFriend(Friend friend) {
		this.friend = friend;	

		this.setOnSelectionChanged((Event value) 
				-> (((TabFriend) value.getTarget()).clearTabNotification()));						
		
		this.setId("abc");

		this.messageList = new Text(10, 20, "");


		circleGraphic = new Circle(0, 0, 5, Color.YELLOW);
		textGraphic = new Text(" " + friend.getName());
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
