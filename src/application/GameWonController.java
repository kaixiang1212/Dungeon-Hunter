package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import Model.Dungeon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;

public class GameWonController {

    @FXML
    private Stage stage;
    private Dungeon savedState;
    
    public GameWonController(Stage s, Dungeon d) {
    	this.stage = s;       
    	this.savedState = d;
    }
    
    @FXML 
    public void handleMainMenuButton() {
        MainMenuScreen menuScreen = new MainMenuScreen(stage);
        menuScreen.start();
    }
    @FXML
    public void restartGame() {
    	GameScreen gs = new GameScreen(stage);
    	gs.start(savedState);
    }
    public void handleQuitGame() {
        System.exit(1);
    }
    
   
}
