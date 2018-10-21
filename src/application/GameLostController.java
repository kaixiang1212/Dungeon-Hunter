package application;

import javafx.stage.Stage;
import javafx.fxml.FXML;


public class GameLostController {

    @FXML
    private Stage stage;
   
    public GameLostController(Stage s) {
     this.stage = s;
             
    }
    
    @FXML 
    public void handleMainMenuButton() {
        MainMenuScreen menuScreen = new MainMenuScreen(stage);
        // MainMenuController mainMenuController = new MainMenuController(stage);
        menuScreen.start();
    }
    
    @FXML
    public void handleQuitGame() {
        System.exit(1);
    }
    
   
}
