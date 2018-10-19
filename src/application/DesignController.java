package application;

import java.awt.Point;

import Model.Dungeon;
import View.DungeonRenderer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DesignController {

	
	private Stage stage;
	private Dungeon d;
	private DungeonRenderer drender;
	
	@FXML
	private Pane mainPane;
	
	public DesignController(Stage s) {
		this.stage = s;
	}

	
	@FXML 
	public void initialize() {
		int size = 9;
		Dungeon customDungeon = new Dungeon(size);
		mainPane.setMaxHeight((size+2) * 32);
		mainPane.setMaxWidth((size+2) * 32);
		this.drender = new DungeonRenderer(customDungeon);
		this.drender.render(mainPane);
		stage.setMaximized(true);

	}
	
}