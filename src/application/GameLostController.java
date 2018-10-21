package application;

import javafx.stage.Stage;
import Model.Dungeon;
import javafx.fxml.FXML;


public class GameLostController {

    @FXML
    private Stage stage;
    private Dungeon savedState;
   
    public GameLostController(Stage s, Dungeon d) {
    	this.stage = s;
    	this.savedState = d;
             
    }
    
    @FXML 
    public void handleMainMenuButton() {
        MainMenuScreen menuScreen = new MainMenuScreen(stage);
        // MainMenuController mainMenuController = new MainMenuController(stage);
        menuScreen.start();
    }
    
    @FXML
    public void restartGame() {
    	GameScreen gs = new GameScreen(stage);
    	gs.start(savedState);
    }
    
    @FXML
    public void handleQuitGame() {
        System.exit(1);
    }
    
   
}
