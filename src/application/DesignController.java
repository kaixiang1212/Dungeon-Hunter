package application;

import java.awt.Point;

import Controller.NoMoveBehaviour;
import Model.Dungeon;
import Model.Paintable;
import Model.ComputerAgent.Boulder;
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
	
	public DesignController(Stage s) {
		this.stage = s;
	}

	
	@FXML 
	public void initialize() {
		int size = 9;
		Dungeon customDungeon = new Dungeon(size);
		this.d = customDungeon;
		mainPane.setMaxHeight((size+2) * 32);
		mainPane.setMaxWidth((size+2) * 32);
		this.drender = new DungeonRenderer(customDungeon);
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
		System.out.println("Boulder has been selected");

	}
	
}