package application;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Controller.Direction;
import Controller.NoMoveBehaviour;
import Model.DefaultWinCondition;
import Model.Dungeon;
import Model.EnemiesKilledDecorator;
import Model.ExitWinDecorator;
import Model.Paintable;
import Model.Player;
import Model.SwitchWinDecorator;
import Model.TreasureCollectedDecorator;
import Model.WinCondition;
import Model.ComputerAgent.Boulder;
import Model.ComputerAgent.Coward;
import Model.ComputerAgent.Hound;
import Model.ComputerAgent.Hunter;
import Model.ComputerAgent.Strategist;
import Model.Item.Arrow;
import Model.Item.Hover;
import Model.Item.Invincibility;
import Model.Item.Item;
import Model.Item.Key;
import Model.Item.Sword;
import Model.Item.Treasure;
import Model.Tile.Door;
import Model.Tile.Exit;
import Model.Tile.Pit;
import Model.Tile.Switch;
import Model.Tile.Tile;
import View.AgentRenderer;
import View.DungeonRenderer;
import View.PlayerRenderer;
import View.Renderer;
import View.TileRenderer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController {

	private Stage stage;
	private Dungeon d;
	private Item selected;
	private DungeonRenderer renderer;

	@FXML
	private Pane mainPane;
	@FXML
	private Label promptLabel;
	@FXML
	private Pane inventoryPane;
	@FXML
	private Label inventoryContents;
	
	public GameController(Stage s, Dungeon d) {
		this.stage = s;
		this.d = d;
		this.renderer = new DungeonRenderer(d);
		
	}
	
	@FXML 
	public void initialize()  {
		if(d.getPlayer() != null) {
			inventoryContents.setText(d.getInventoryDescription());
		}
		WinCondition testwc = new DefaultWinCondition();
		testwc = new SwitchWinDecorator(testwc);
		this.d.setWinCondition(testwc);
		//Temporary setup we actually want to pass a dungeon
		if(this.d == null) {
			int size = 8;
			Dungeon test = new Dungeon(size);
			this.d = test;


			testwc = new DefaultWinCondition();
			testwc = new EnemiesKilledDecorator(testwc);
			test.setWinCondition(testwc);
//			test.placeComputerAgent(new Hunter(), new Point(2,1));
//			test.placeComputerAgent(new Hunter(), new Point(1,1));
//			test.placeComputerAgent(new Hunter(), new Point(17,1));
//			test.placeComputerAgent(new Hunter(), new Point(12,1));
//			test.placeComputerAgent(new Hunter(), new Point(14,1));
//			test.placeComputerAgent(new Hunter(), new Point(15,1));
			test.placeComputerAgent(new Hound(), new Point(13,12));
			test.placeComputerAgent(new Boulder(new NoMoveBehaviour()), new Point(2,2));
			test.placeItem(new Treasure(), new Point(2,3));
			test.placeItem(new Sword(), new Point(2,1));
			test.placeItem(new Invincibility(), new Point(3,1));
			test.placeItem(new Hover(), new Point(4,1));
			test.placeItem(new Sword(), new Point(5,1));
			test.placeItem(new Arrow(), new Point(6,2));
			test.placePlayer(new Player(), new Point(4,4));
//			test.placeComputerAgent(new Hunter(), new Point(18,1));
			test.placeTile(new Exit(), new Point(8,8));
			test.placeTile(new Pit(), new Point(6,8));
			test.placeTile(new Door(), new Point(6,6));
			test.placeTile(new Switch(), new Point(7,7));
			test.placeItem(new Key(), new Point(5,6));
		}
		mainPane.setMaxHeight((d.getSize()+2) * 32);
		mainPane.setMaxWidth((d.getSize()+2) * 32);
	    stage.setMaximized(true);
	
		render();
	}


	/**
	 * Calls renderUtil to render multiple grids
	 * Encapsulated away from initialize so it can be called again on every
	 * move.
	 * @param dungeon
	 */
	public void render() {
		renderer.render(mainPane);
	}

	/**
	 * 
	 * @param dungeon Dungeon reference, for size and to utilise check method
	 * @param map Reference of grid to paint
	 * @param pane Pane to paint onto
	 * Note plus 2, as size specifies walkable area, we need to include the invincible walls as possible area for placement
	 */

//	public void renderUtil(Map<Point, ? extends Paintable> map, Pane pane) {
//		for(int y = 0; y < d.getSize() + 2; y++) {
//			for(int x = 0; x < d.getSize() + 2; x++) {
//				Image check = d.proxygettiles(new Point(x, y), map);
//				if(check != null) {
//					ImageView insertview = new ImageView(check);
//					insertview.setFitHeight(32);
//					insertview.setFitWidth(32);
//					insertview.setLayoutX(x * 32);
//					insertview.setLayoutY(y * 32);
//					pane.getChildren().add(insertview);
//				}
//			}
//		}
//	}

	//Temporary solution
	//Ideally some game loop with threading and animation
	
	@FXML
	public void playerMovement(KeyEvent key) {
		System.out.print(key.getCode() + "\n");
		switch (key.getCode()) {
		case A:
			d.updatePlayer(Direction.LEFT);
			endTurn();
			break;
		case S:
			d.updatePlayer(Direction.DOWN);
			endTurn();
			break;
		case D:
			d.updatePlayer(Direction.RIGHT);
			endTurn();
			break;
		case W:
			d.updatePlayer(Direction.UP);
			endTurn();
			break;
		case E:
			d.playerUseItem();
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
		inventoryContents.setText(d.getInventoryDescription());
		System.out.println(d.getInventoryDescription());
		render();
		checkDungeonState();
	}
	
	/**
	 * TODO: Add loading of win or loss screen, which allows exit of game or restart?
	 */
	
	public void checkDungeonState() {
		if(d.hasLost()) {
		    //System.out.println("You have Lost\n");
		    GameLostScreen gameLostScreen = new GameLostScreen(stage);
		    gameLostScreen.start();
						
		}
		else if(d.hasWon()) {
			System.out.println("You have Won\n");
			GameWonScreen gameWonScreen = new GameWonScreen(stage);
            gameWonScreen.start();
		}
	}
	@FXML
	public void hotSwitch() {
		DesignScreen ds = new DesignScreen(this.stage);
		ds.start(this.d);
	}
	
	public void endTurn() {
		d.updateAgents();
		d.updateTile();
	}

}

