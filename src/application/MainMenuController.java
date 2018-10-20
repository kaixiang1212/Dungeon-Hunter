package application;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;



public class MainMenuController {
    
   // private String statement;
    
    //private Label statementUI;
    
    @FXML
    private AnchorPane anchor;
    private Stage stage;
    
    public MainMenuController(Stage s) {
        this.stage = s;
    }

    @FXML
    public void initialize() {
        //statementUI = new Label(statement);
        //anchor.getChildren();
        //statementUI.sesetLayoutY(100);
        //statementUI.setFont(new Font("Arial", 17));
       //this.setupStageDimensions(); 
    }
         
    public void setupStageDimensions() {
        stage.setMaximized(true);
    }
    
    
    @FXML 
    public void handleLevelSelectionButton() {
        LevelSelectionScreen levelSelectionScreen = new LevelSelectionScreen(stage);
        //LevelSelectionController levelSelectionController = new LevelSelectionController(stage);
        
        levelSelectionScreen.start();
    }
    
    public void handleQuitMainMenuButton() {
        System.exit(1);
    }
    
  
}
