package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public class TabFriend extends Tab{
	
	private final Friend friend;
	
	public Friend getFriend() {
		return friend;
	}
	
	// private final GRAPHIC
	
	private final Text messageList;

	public TabFriend(Friend friend) {
		
		this.friend = friend;	
		
		this.setId("abc");
		this.setText(friend.getName());
		
		BorderPane borderPane = new BorderPane();
		this.messageList = new Text(10, 20, "");
		
		HBox hbox = new HBox();
		
		TextArea chatTextArea = new TextArea();
		
		hbox.getChildren().add(chatTextArea);
		
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	String message = chatTextArea.getText();
                messageList.setText(messageList.getText() + "\n" + "You: " + message); 
                chatTextArea.setText("");
            }
        });
        
        hbox.getChildren().add(submitButton);
        borderPane.setBottom(hbox);
        borderPane.setCenter(messageList);
        
        this.setContent(borderPane);
	}
}
