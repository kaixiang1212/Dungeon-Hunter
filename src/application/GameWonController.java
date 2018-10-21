package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;

public class GameWonController {

    @FXML
    private Stage stage;
   
    public GameWonController(Stage s) {
     this.stage = s;       
    }
    
    @FXML 
    public void handleMainMenuButton() {
        MainMenuScreen menuScreen = new MainMenuScreen(stage);
        // MainMenuController mainMenuController = new MainMenuController(stage);
        menuScreen.start();
    }
    
    public void handleQuitGame() {
        System.exit(1);
    }
    
   
}
