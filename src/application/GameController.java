package application;

import java.awt.Point;

import Model.Dungeon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController {

	private Stage stage;
	

	@FXML
	private Pane mainPane;
	
	public GameController(Stage s) {
		this.stage = s;
	}
	
	@FXML 
	public void initialize() {
		
		Dungeon test = new Dungeon(3);
//		Image insert = new Image("application/defaulttile.png");
//		ImageView insertview = new ImageView(insert);
//		insertview.setFitHeight(32);
//		insertview.setFitWidth(32);
//		insertview.setLayoutX(32);
//		insertview.setLayoutY(32);
//		
//		ImageView insertview2 = new ImageView(insert);
//		insertview2.setFitHeight(32);
//		insertview2.setFitWidth(32);
//		insertview2.setLayoutX(0);
//		insertview2.setLayoutY(0);
//
//		
//		
//		mainPane.getChildren().add(insertview);
//		mainPane.getChildren().add(insertview2);

		//Loops through all possible points on dungeon tiles
		//Possible to refactor into reusable method! Only thing changing is
		//What method is being called to retrieve image at a location
		//Func pointers? Maybe we can violate law of demeter
		//Get grid (of any type), then pass into renderGrid() function?
		int size = test.getSize();
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				Image check = test.proxygettiles(new Point(x, y));
				if(check != null) {
					ImageView insertview = new ImageView(check);
					insertview.setFitHeight(32);
					insertview.setFitWidth(32);
					insertview.setLayoutX(x * 32);
					insertview.setLayoutY(y * 32);
					mainPane.getChildren().add(insertview);
				}
			}
		}
	}
//	@FXML
//	public void playerMovement(KeyEvent key) {
//		double x = jj.getX();
//		double y = jj.getY();
//		System.out.print(key.getCode());
//		switch (key.getCode()) {
//		case A:
//			jj.setX(x-32);
//			break;
//		case S:
//			jj.setY(y+32);
//			break;
//		case D:
//			jj.setX(x+32);
//			break;
//		case W:
//			jj.setY(y-32);
//			
//		}
//	}
}
