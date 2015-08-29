package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import presenter.Presenter;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Presenter presenter = new Presenter(primaryStage);
		presenter.launch();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
