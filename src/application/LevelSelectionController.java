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
    public void initialize() {
        //statementUI = new Label(statement);
        //anchor.getChildren();
        //statementUI.sesetLayoutY(100);
        //statementUI.setFont(new Font("Arial", 17));
        //this.setupStageDimensions();
    }
         
    public void setupStageDimensions() {
        stage.setMaximized(true);
        //stage.centerOnScreen();      
    }
   
    
    @FXML 
    public void handleBackButton() {
        MainMenuScreen menuScreen = new MainMenuScreen(stage);
        // MainMenuController mainMenuController = new MainMenuController(stage);
        menuScreen.start();
    }
    
    @FXML
    public void handlePlayLevel1Button() {
        DesignScreen designScreen = new DesignScreen(stage);
        //GameLaunchController level1Controller = new GameLaunchController(super.getS());
        designScreen.start(null);
    }
    
    
  
}
