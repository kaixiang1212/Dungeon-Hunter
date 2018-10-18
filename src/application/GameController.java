package application;

import java.awt.Point;
import java.util.Map;

import Controller.Direction;
import Controller.NoMoveBehaviour;
import Model.Boulder;
import Model.Coward;
import Model.Dungeon;
import Model.Hunter;
import Model.Paintable;
import Model.Player;
import Model.Strategist;
import Model.Item.Arrow;
import Model.Item.Hover;
import Model.Item.Invincibility;
import Model.Item.Item;
import Model.Item.Sword;
import Model.Item.Treasure;
import Model.Tile.Type;
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
	private Dungeon d;
	private Item selected;

	@FXML
	private Pane mainPane;
	@FXML
	private Label promptLabel;
	@FXML
	private Pane inventoryPane;
	
	public GameController(Stage s) {
		this.stage = s;
	}
	
	@FXML 
	public void initialize() {

		//Temporary setup
		Dungeon test = new Dungeon(17);
		this.d = test;
		this.setupStageDimensions();
		//int fullscreensize = test.getSize() + 2;
		//int bytepertile = 32;
        //stage.setMaxWidth(fullscreensize * bytepertile);
        //stage.setMaxHeight(fullscreensize * bytepertile + bytepertile);
		

		test.placeComputerAgent(new Hunter(), new Point(1,1));
		test.placeComputerAgent(new Hunter(), new Point(17,1));
		test.placeComputerAgent(new Hunter(), new Point(12,1));
		test.placeComputerAgent(new Hunter(), new Point(14,1));
		test.placeComputerAgent(new Hunter(), new Point(15,1));
		//test.placeComputerAgent(new Boulder(new NoMoveBehaviour()), new Point(2,2));
		test.placeComputerAgent(new Coward(), new Point(13,12));
		test.placeItem(new Treasure(), new Point(2,3));
		test.placeItem(new Sword(), new Point(2,1));
		test.placeItem(new Invincibility(), new Point(3,1));
		test.placeItem(new Hover(), new Point(4,1));
		test.placeItem(new Sword(), new Point(5,1));
		test.placeItem(new Arrow(), new Point(6,2));
		test.placePlayer(new Player(), new Point(4,4));
		test.placeTile(Type.EXIT, new Point(8,8));
		test.placeTile(Type.PIT, new Point(6,8));
		test.placeTile(Type.CLOSED_DOOR, new Point(6,6));
		test.placeTile(Type.OPEN_DOOR, new Point(7,7));
		
		render();
	}
	public void setupStageDimensions() {

		double centreX = 977.0;
		double centreY = 576.0;
		double increment = (this.d.getSize() + 3)/2;
		mainPane.setLayoutX(centreX - increment*32);
		mainPane.setLayoutY(centreY - increment*32);
		
        stage.setMaximized(true);
	}
	/**
	 * Calls renderUtil to render multiple grids
	 * Encapsulated away from initialize so it can be called again on every
	 * move.
	 * @param d
	 */
	public void render() {
		int size = d.getSize();
		renderUtil(d.getTileGrid(), mainPane);
		renderUtil(d.getItemGrid(), mainPane);
		renderUtil(d.getAgentGrid(), mainPane);
		renderPlayer(mainPane);
		
	}

	/**
	 * 
	 * @param d Dungeon reference, for size and to utilise check method
	 * @param map Reference of grid to paint
	 * @param pane Pane to paint onto
	 * Note plus 2, as size specifies walkable area, we need to include the invincible walls as possible area for placement
	 */

	public void renderUtil(Map<Point, ? extends Paintable> map, Pane pane) {
		for(int y = 0; y < d.getSize() + 2; y++) {
			for(int x = 0; x < d.getSize() + 2; x++) {
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
	//Issue is player is no part of grids, done separately in this case.
	public void renderPlayer(Pane pane) {
		Point playerPos = d.getPlayerPos();
		int x = playerPos.x;
		int y = playerPos.y;
		ImageView insertview = new ImageView(d.getPlayerImage());
		insertview.setFitHeight(32);
		insertview.setFitWidth(32);
		insertview.setLayoutX(x * 32);
		insertview.setLayoutY(y * 32);	
		pane.getChildren().add(insertview);
	}

	//Temporary solution
	//Ideally some game loop with threading and animation
	@FXML
	public void playerMovement(KeyEvent key) {
		System.out.print(key.getCode());
		switch (key.getCode()) {
		case A:
			d.updatePlayer(Direction.LEFT);
			d.updateAgents();
			break;
		case S:
			d.updatePlayer(Direction.DOWN);
			d.updateAgents();
			break;
		case D:
			d.updatePlayer(Direction.RIGHT);
			d.updateAgents();
			break;
		case W:
			d.updatePlayer(Direction.UP);
			d.updateAgents();
			break;
		case DIGIT1:
			selected = d.selectItemSlot(0);
			break;
		case DIGIT2:
			selected = d.selectItemSlot(1);
			break;
		case DIGIT3:
			selected = d.selectItemSlot(2);
			break;
		}
		if(selected != null) {
			promptLabel.setText(selected.toString());
		}
		render();
	}
}

