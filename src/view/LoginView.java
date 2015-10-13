package view;

import java.util.function.BiFunction;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView {
	
	private PasswordField pwBox;
	
	private TextField userTextField;
	
	private Text actiontarget;
	
	private BiFunction <String, String, Boolean> loginFunction;
	
	public LoginView(BiFunction <String, String, Boolean> loginFunction) {
		this.loginFunction = loginFunction;
	}
	
	private void login() {
		actiontarget.setText("Authenticating");
    	if (!loginFunction.apply(userTextField.getText(), pwBox.getText())) {	    		
    		actiontarget.setFill(Color.FIREBRICK);
    		actiontarget.setText("Login failed");
    	}
	}
	
	public Scene getScene() {
		Scene scene;
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		scene = new Scene(grid, 400, 250, Color.WHITE);
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		 userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		pwBox.setOnKeyPressed((KeyEvent ke) -> {
			if (ke.getCode().equals(KeyCode.ENTER)) 
            	login();
			});
		
	
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        btn.setOnAction((ActionEvent e) -> login());
		

		
		return scene;
	}

}
