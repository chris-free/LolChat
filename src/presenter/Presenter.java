package presenter;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Api;
import model.LoLApi;
import view.ChatView;
import view.LoginView;
import view.ResetView;

public class Presenter {
	private Stage primaryStage;
	private Api model;
	
	public Presenter(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.model = new LoLApi();
	}
	
	public void launch () {
		primaryStage.setTitle("LoL Chat");
		LoginView loginFactory = new LoginView(this::login);
		Scene loginScene = loginFactory.getScene();
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}
	
	public boolean login(String userName, String password) {
		if (model.login(userName, password)) {
			ChatView viewChat = new ChatView(model);
			Scene scene = viewChat.getScene();
			primaryStage.close(); // Needs otherwise doesn't switch properly, instead can remove/add from the root
			primaryStage.setScene(scene);
			primaryStage.show();
			
			ResetView reset = new ResetView(primaryStage, model);
			reset.show();
			
			return true;
		} else {
			return false;
		}
	}
}
