package view;

import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;



public class ChatSceneFactory implements SceneFactory {

	private TabPane tabPane;
	private ListView<ListFriend> listView;
	
	@Override
	public Scene create() {
		
		Group root = new Group();
		Scene scene = new Scene(root, 400, 250, Color.WHITE);
		
		listView.setPrefSize(200, 250);
		listView.getSelectionModel()
				.selectedItemProperty()
				.addListener((ObservableValue<? extends ListFriend> oValue, ListFriend previousSelected, ListFriend selectedFriend)  -> {
					if (selectedFriend != null) {
						TabFactory tabFactory = new TabFactory();
						Tab t = tabFactory.create(selectedFriend.getFriend());
						tabPane.getTabs().add(t);
						
					}
				});
		
		
		BorderPane borderPane = new BorderPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		borderPane.setCenter(tabPane);
		borderPane.setRight(listView);      


		root.getChildren().add(borderPane);
		return scene;
	}

	public ChatSceneFactory(TabPane tabPane, ListView<ListFriend> listView) {
		this.tabPane = tabPane;
		this.listView = listView;
	}
}
