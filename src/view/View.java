package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {
	
	public Stage primaryStage;
	
	public View (Stage primaryStage) {
		primaryStage.setTitle("LoL Chat");
		this.primaryStage = primaryStage;
	}

}
