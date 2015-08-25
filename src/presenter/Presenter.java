package presenter;

import javafx.scene.Scene;
import model.Model;
import view.LoginSceneFactory;
import view.View;
import view.ViewChat;

public class Presenter {
	private View view;
	private Model model;
	
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	public void launch () {
		LoginSceneFactory loginFactory = new LoginSceneFactory(this);
		Scene loginScene = loginFactory.create();
		view.primaryStage.setScene(loginScene);
		view.primaryStage.show();
	}
	
	public boolean login(String userName, String password) {
		if (model.login(userName, password)) {
			ViewChat viewChat = new ViewChat();
			Scene scene = viewChat.getScene();
			
			view.primaryStage.setScene(scene);
			return true;
		} else {
			return false;
		}
	}
}
