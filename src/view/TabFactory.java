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

public class TabFactory {

	public Tab create(Friend friend) {
		
		Tab tab = new Tab();
		tab.setId("abc");
		tab.setText(friend.getName());
		
		BorderPane borderPane = new BorderPane();
		Text t = new Text(10, 20, "");
		
		HBox hbox = new HBox();
		
		TextArea chatTextArea = new TextArea();
		
		hbox.getChildren().add(chatTextArea);
		
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	String message = chatTextArea.getText();
                t.setText(t.getText() + "\n" + "You: " + message); 
                chatTextArea.setText("");
            }
        });
        
        hbox.getChildren().add(submitButton);
        borderPane.setBottom(hbox);
        borderPane.setCenter(t);
        
        tab.setContent(borderPane);
		return tab;
	}
}
