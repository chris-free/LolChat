package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ChatSceneFactory implements SceneFactory {

	@Override
	public Scene create() {
		Group root = new Group();
		HBox box = new HBox();
		Text t = new Text("abc");
		box.getChildren().add(t);
		
		Scene scene = new Scene(root, 400, 250, Color.WHITE);
		root.getChildren().add(box);
		// TODO Auto-generated method stub
		return scene;
	}
	

}
