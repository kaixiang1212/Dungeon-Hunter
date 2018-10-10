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
	private Label label1;
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
	public void keyPressed(KeyEvent key) {
		System.out.println("Tony gay");
		switch (key.getCode()) {
		case LEFT:
			System.out.println("Tony gay");
			jj.setX(1);
			jj.setY(1);
		default:
			System.out.println("Tony gay");
		}
	}
	@FXML
	public void mouseClick(MouseEvent mouse) {
		System.out.println("Tony gay");
		double x = jj.getX();
		double y = jj.getY();
		jj.setX(x+1);
		jj.setY(x+1);
	}
}
