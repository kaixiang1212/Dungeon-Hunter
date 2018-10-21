package application;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;



public class MainMenuController {
      
    @FXML
    private AnchorPane anchor;
    private Stage stage;
    
    public MainMenuController(Stage s) {
        this.stage = s;
    }
    @FXML
    public void initialize() {
    	stage.setMaximized(true);

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
