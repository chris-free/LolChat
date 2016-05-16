package presenter;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Api;
import model.Summoner;
import view.SummonerCell;
import view.chat.SummonerTab;

public class Chat {

	private TabPane tabPane;

	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	private SummonerTab tabGet(Summoner f) {
		return tabContains(f) ? tabFind(f) : tabCreate(f);
	}

	private SummonerTab tabCreate(Summoner f) {
		SummonerTab t = new SummonerTab(f);
		tabPane.getTabs().add(t);
		return t;
	}

	private SummonerTab tabFind(Summoner f) {
		return (SummonerTab) tabPane
				.getTabs()
				.stream()
				.filter(t -> ((SummonerTab) t).getSummoner().getUserId() == f.getUserId())
				.findFirst()
				.get();
	}

	private boolean tabContains(Summoner f) {
		return tabPane
				.getTabs()
				.stream()
				.anyMatch(t -> ((SummonerTab) t).getSummoner().getUserId() == f.getUserId());
	}

	private void tabSelect(Summoner f) {
		Tab tab = tabGet(f);
		tabPane.getSelectionModel().select(tab);
	}
	
	public Chat(Api lolApi) {
		tabPane = new TabPane();
		ListView<Summoner> listView = new ListView<Summoner>();

		List <Summoner> summoners = lolApi.getSummoners();

		for (Summoner sum : summoners) {
			sum.registerChatObserver((Summoner summoner, String message)
					-> {
						if (message != null) {
							System.out.println(message);
							Platform.runLater(() -> {
								SummonerTab tab = tabGet(summoner);
								tab.incomingMessage(summoner.getName(), message);
							});
						}});
			
			sum.registerPresenceObserver(()
					-> { 
						java.util.Collections.sort(listView.getItems(), new java.util.Comparator<Summoner>() {
						    @Override
						    public int compare(Summoner o1, Summoner o2) {
						    	if (o1.isOnline() == o2.isOnline()) {
						    		return o1.getName().compareTo(o2.getName());
						    	} else {
						    		if (o1.isOnline()) {
						    			return -1;
						    		} else{
						    			return 1;
						    		}
						    	}
						    }
						});
					});
		};

		listView.setItems(FXCollections.observableArrayList(summoners));
		listView.setPrefSize(200, 250);
		listView.setCellFactory(new Callback<ListView<Summoner>, ListCell<Summoner>>() {
			@Override 
			public ListCell<Summoner> call(ListView<Summoner> list) {
				return new SummonerCell();
			}});

		Group root = new Group();
		scene = new Scene(root, 400, 250, Color.WHITE);

		listView.getSelectionModel()
		.selectedItemProperty()
		.addListener((ObservableValue<? extends Summoner> oValue, Summoner previousSelected, Summoner selected) 
				-> {
					if (selected != null) {
						tabSelect(selected);
					}
				});

		tabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>() {
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
						if (t1 != null) {
							Summoner selectedFriend =  ((SummonerTab) t1).getSummoner();
							listView.getSelectionModel().select(selectedFriend);
						}

					}
				}
				);


		BorderPane borderPane = new BorderPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		borderPane.setCenter(tabPane);
		/*
		GridPane grid = new GridPane();
		int rowIndex = 0;
		for (Entry<String, List<Summoner>> entry: lolApi.getSummonersByGroup().entrySet()) {
			String groupName = entry.getKey();
			List <Summoner> listFriend = entry.getValue();
			
			TitledPane t = new TitledPane(groupName, new Text(listFriend.get(0).getName()));
			grid.add(t, 0, rowIndex);
			rowIndex++;
		}
*/		
		borderPane.setRight(listView);      

		root.getChildren().add(borderPane);
	}

}
