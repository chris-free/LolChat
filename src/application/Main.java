package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import presenter.Presenter;
import view.View;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		View view = new View(primaryStage);
		Model model = new Model();

		Presenter presenter = new Presenter(view, model);
		presenter.launch();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
