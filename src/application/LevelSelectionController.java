package application;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class LevelSelectionController {

    @FXML
    private AnchorPane anchor;
    private Stage stage;
   
    public LevelSelectionController(Stage s) {
     this.stage = s;
             
    }
 
    @FXML 
    public void handleBackButton() {
        MainMenuScreen menuScreen = new MainMenuScreen(stage);
        menuScreen.start();
    }
    
    @FXML
    public void handlePlayLevel1Button() {
        DesignScreen designScreen = new DesignScreen(stage);
        designScreen.start(null);
    }
    
    
  
}
