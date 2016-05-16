package view;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Api;
import presenter.Chat;

public class ResetView {
	
	private Runnable reset;
	
	private Scene scene;
	
	private Stage stage;
	
	public ResetView (Stage primaryStage, Api model) {
		
		reset = () -> {
			model.reset();
			Chat viewChat = new Chat(model);
			Scene scene = viewChat.getScene();
			primaryStage.close(); // Needs otherwise doesn't switch properly, instead can remove/add from the root
			primaryStage.setScene(scene);
			primaryStage.show();
		};
		
		Button resetButton = new Button("Reset!");
		resetButton.setOnAction((ActionEvent e) -> reset.run());
		
        scene = new Scene(resetButton, 500, 400);
        stage = new Stage();
        stage.setScene(scene);
	}

	public void show() {
		stage.show();
	}
}
