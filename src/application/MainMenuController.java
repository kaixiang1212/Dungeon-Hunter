package application;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;



public class MainMenuController {
      
    @FXML
    private AnchorPane anchor;
    private Stage stage;
    
    public MainMenuController(Stage s) {
        this.stage = s;
    }
    
    @FXML 
    public void handleLevelSelectionButton() {
        LevelSelectionScreen levelSelectionScreen = new LevelSelectionScreen(stage);       
        levelSelectionScreen.start();
    }
    
    @FXML
    public void handleQuitMainMenuButton() {
        System.exit(1);
    } 
}
