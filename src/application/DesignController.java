package application;

import java.awt.Point;

import Controller.NoMoveBehaviour;
import Model.Dungeon;
import Model.Paintable;
import Model.Player;
import Model.Tile.*;
import Model.Item.*;
import Model.ComputerAgent.*;
import View.DungeonRenderer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DesignController {

	
	private Stage stage;
	private Dungeon d;
	private DungeonRenderer drender;
	private Paintable currSelection;
	
	@FXML
	private Label selectPrompt;
	@FXML
	private Pane mainPane;
	@FXML
	private Pane frontPane;
	@FXML
	private Label errorMessage;
	
	public DesignController(Stage s, Dungeon d) {
		this.stage = s;
		this.currSelection = new DefaultTile();
		this.d = d;
	}

	
	@FXML 
	public void initialize() {
		/**
		 * Todo find a way to pass size the person wants :)
		 */
		if(this.d == null) {
			int size = 14;
			Dungeon customDungeon = new Dungeon(size);
			this.d = customDungeon;
		}
		this.mainPane.setMaxHeight((d.getSize()+2) * 32);
		this.mainPane.setMaxWidth((d.getSize()+2) * 32);
		this.drender = new DungeonRenderer(this.d);
		this.drender.render(mainPane);
		this.stage.setMaximized(true);

	}
	public void updatePrompts() {
		this.selectPrompt.setText(currSelection.toString());
	}
	/**
	 * Gets coordinates of click on dungeon, divides by num pixels width of each tile [32]
	 * Rounds down to get correct coordinates for placement on Dungeon type
	 * Clones current selection so players can place subsequent unique objects (in reference) on Dungeon
	 * Re-renders to provide visual feedback on placement
	 * @param event Containing information about mouse event (ie. click)
	 * @throws CloneNotSupportedException
	 */
	@FXML
	public void handlePlacement(MouseEvent event) throws CloneNotSupportedException {
		int x = (int) event.getX()/32;
		int y = (int) event.getY()/32;
		try {
			currSelection.place(d, new Point(x,y));
		} catch (Exception e) {
			errorMessage.setText(e.getMessage());
		}
		Paintable clone = (Paintable) this.currSelection.clone();
		this.currSelection = clone;
		this.drender.render(mainPane);
	}
	@FXML
	public void boulderSelected() {
		this.currSelection = new Boulder(new NoMoveBehaviour());
		this.updatePrompts();
	}
	@FXML
	public void hunterSelected() {
		this.currSelection = new Hunter();
		this.updatePrompts();
	}
	@FXML
	public void houndSelected() {
		this.currSelection = new Hound();
		this.updatePrompts();
	}
	@FXML
	public void strategistSelected() {
		this.currSelection = new Strategist();
		this.updatePrompts();
	}
	@FXML
	public void cowardSelected() {
		this.currSelection = new Coward();
		this.updatePrompts();
	}
	@FXML
	public void playerSelected() {
		this.currSelection = new Player();
		this.updatePrompts();
	}
	@FXML
	public void defaultSelected() {
		this.currSelection = new DefaultTile();
		this.updatePrompts();
	}
	@FXML
	public void wallSelected() {
		this.currSelection = new Wall();
		this.updatePrompts();
	}
	@FXML
	public void switchSelected() {
		this.currSelection = new Switch();
		this.updatePrompts();
	}
	@FXML
	public void pitSelected() {
		this.currSelection = new Pit();
		this.updatePrompts();
	}
	@FXML
	public void openDoorSelected() {
		this.currSelection = new OpenedDoor();
		this.updatePrompts();
	}
	@FXML
	public void exitSelected() {
		this.currSelection = new Exit();
		this.updatePrompts();
	}
	@FXML
	public void treasureSelected() {
		this.currSelection = new Treasure();
		this.updatePrompts();
	}
	@FXML
	public void invincibilitySelected() {
		this.currSelection = new Invincibility();
		this.updatePrompts();
	}
	@FXML
	public void hoverSelected() {
		this.currSelection = new Hover();
		this.updatePrompts();
	}
	@FXML
	public void arrowSelected() {
		this.currSelection = new Arrow();
		this.updatePrompts();
	}
	@FXML
	public void swordSelected() {
		this.currSelection = new Sword();
		this.updatePrompts();
	}
	@FXML
	public void keySelected() {
		this.currSelection = new Key();
		this.updatePrompts();
	}
	@FXML
	public void closedDoorSelected() {
		this.currSelection = new Door();
		this.updatePrompts();
	}
	@FXML
	public void unlitBombSelected() {
		this.currSelection = new Bomb();
		this.updatePrompts();
	}
	@FXML
	public void hotSwitch() {
		GameScreen gs = new GameScreen(this.stage);
		gs.start(this.d);
	}
	@FXML
	public void handleExit() {
        Platform.exit();
        System.exit(0);
	}
	
}