package application;

import java.awt.Point;
import java.util.Map;

import Model.Dungeon;
import Model.Hunter;
import Model.Paintable;
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
	@FXML
	private Pane agentPane;
	
	public GameController(Stage s) {
		this.stage = s;
	}
	
	@FXML 
	public void initialize() {
		
		Dungeon test = new Dungeon(8);
		test.placeComputerAgent(new Hunter(), new Point(1,1));
		//Loops through all possible points on dungeon tiles
		//Possible to refactor into reusable method! Only thing changing is
		//What method is being called to retrieve image at a location
		//Func pointers? Maybe we can violate law of demeter
		//Get grid (of any type), then pass into renderGrid() function?
		int size = test.getSize();
		renderUtil(test, test.getTileGrid(), mainPane);
		renderUtil(test, test.getAgentGrid(), mainPane);
	}
	
	/**
	 * 
	 * @param d Dungeon reference, for size and to utilise check method
	 * @param map Reference of grid to paint
	 * @param pane Pane to paint onto
	 */
	public void renderUtil(Dungeon d, Map<Point, ? extends Paintable> map, Pane pane) {
		for(int y = 0; y < d.getSize(); y++) {
			for(int x = 0; x < d.getSize(); x++) {
				Image check = d.proxygettiles(new Point(x, y), map);
				if(check != null) {
					ImageView insertview = new ImageView(check);
					insertview.setFitHeight(32);
					insertview.setFitWidth(32);
					insertview.setLayoutX(x * 32);
					insertview.setLayoutY(y * 32);
					pane.getChildren().add(insertview);
				}
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
//
