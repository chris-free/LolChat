package presenter;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import model.Api;
import model.MockApi;
import view.LoginView;
import view.ChatView;

public class Presenter {
	private Stage primaryStage;
	private Api model;
	
	public Presenter(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.model = new MockApi();
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
			

			new Thread() {
				public void run() {
					try {
						Thread.sleep(10000);
						System.out.println(scene.lookup("#abc"));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print(1);
				}
			}.start();
			
			return true;
		} else {
			return false;
		}
	}
}
