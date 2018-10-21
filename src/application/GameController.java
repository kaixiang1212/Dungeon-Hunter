package application;

import Controller.Direction;
import Model.Dungeon;
import Model.Item.Item;
import View.DungeonRenderer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController {

	private Stage stage;
	private Dungeon d;
	private Dungeon savedState;
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
	public void initialize() throws CloneNotSupportedException  {
		if(d.getPlayer() != null) {
			inventoryContents.setText(d.getInventoryDescription());
		}
		savedState = (Dungeon) d.clone();
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
			endTurn();
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
		updateDisplay();
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
			//System.out.println("You have Won\n");
			GameWonScreen gameWonScreen = new GameWonScreen(stage);
            gameWonScreen.start();
		}
	}
	@FXML
	public void hotSwitch() {
		DesignScreen ds = new DesignScreen(this.stage);
		ds.start(this.savedState);
	}
	@FXML
	public void mainMenu() {
		MainMenuScreen mms = new MainMenuScreen(stage);
		mms.start();
	}
	
	@FXML
	public void exitGame() {
        Platform.exit();
        System.exit(0);
	}
	public void endTurn() {
		d.updateAgents();
		d.updateTile();
		checkDungeonState();
	}
	
	public void updateDisplay() {
		if(selected != null) {
			promptLabel.setText(selected.toString());
		}
		inventoryContents.setText(d.getInventoryDescription());
		render();
	}

}

