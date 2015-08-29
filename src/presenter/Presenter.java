package presenter;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import model.Api;
import model.MockApi;
import view.LoginSceneFactory;
import view.ViewChat;

public class Presenter {
	private Stage primaryStage;
	private Api model;
	
	public Presenter(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.model = new MockApi();
	}
	
	public void launch () {
		primaryStage.setTitle("LoL Chat");
		LoginSceneFactory loginFactory = new LoginSceneFactory((userName, password) -> login(userName, password));
		Scene loginScene = loginFactory.create();
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}
	
	public boolean login(String userName, String password) {
		if (model.login(userName, password)) {
			ViewChat viewChat = new ViewChat(model);
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
