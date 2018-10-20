package application;

import java.awt.Point;

import Controller.NoMoveBehaviour;
import Model.Dungeon;
import Model.Paintable;
import Model.Player;
import Model.ComputerAgent.Boulder;
import Model.ComputerAgent.Coward;
import Model.ComputerAgent.Hound;
import Model.ComputerAgent.Hunter;
import Model.ComputerAgent.Strategist;
import Model.Tile.*;
import View.DungeonRenderer;
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
	private Pane mainPane;
	
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
			int size = 9;
			Dungeon customDungeon = new Dungeon(size);
			this.d = customDungeon;
		}
		mainPane.setMaxHeight((d.getSize()+2) * 32);
		mainPane.setMaxWidth((d.getSize()+2) * 32);
		this.drender = new DungeonRenderer(this.d);
		this.drender.render(mainPane);
		stage.setMaximized(true);

	}
	
	@FXML
	public void handlePlacement(MouseEvent event) {
		int x = (int) event.getX()/32;
		int y = (int) event.getY()/32;
		System.out.println(x);
		System.out.println(y);
		currSelection.place(d, new Point(x,y));
		this.drender.render(mainPane);
	}
	@FXML
	public void boulderSelected() {
		this.currSelection = new Boulder(new NoMoveBehaviour());
	}
	@FXML
	public void hunterSelected() {
		this.currSelection = new Hunter();
	}
	@FXML
	public void houndSelected() {
		this.currSelection = new Hound();
	}
	@FXML
	public void strategistSelected() {
		this.currSelection = new Strategist();
	}
	@FXML
	public void cowardSelected() {
		this.currSelection = new Coward();
	}
	@FXML
	public void playerSelected() {
		this.currSelection = new Player();
	}
	@FXML
	public void defaultSelected() {
		this.currSelection = new DefaultTile();
	}
	@FXML
	public void wallSelected() {
		this.currSelection = new Wall();
	}
	@FXML
	public void switchSelected() {
		this.currSelection = new Switch();
	}
	@FXML
	public void pitSelected() {
		this.currSelection = new Pit();
	}
	@FXML
	public void openDoorSelected() {
		this.currSelection = new OpenedDoor();
	}
	@FXML
	public void exitSelected() {
		this.currSelection = new Exit();
	}
	@FXML
	public void hotSwitch() {
		GameScreen gs = new GameScreen(this.stage);
		gs.start(this.d);
	}
	
}