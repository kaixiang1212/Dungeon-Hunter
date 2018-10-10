package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameController {

	private Stage stage;
	
	@FXML
	private ImageView jj;
	
	public GameController(Stage s) {
		this.stage = s;
	}
	
	@FXML 
	public void initialize() {
		//label1.setText("Hi");
	}
	@FXML
	public void playerMovement(KeyEvent key) {
		double x = jj.getX();
		double y = jj.getY();
		System.out.print(key.getCode());
		switch (key.getCode()) {
		case A:
			jj.setX(x-32);
			break;
		case S:
			jj.setY(y+32);
			break;
		case D:
			jj.setX(x+32);
			break;
		case W:
			jj.setY(y-32);
			
		}
	}
}
