package application;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;



public class MainMenuController extends Controller {
    
   // private String statement;
    
    //private Label statementUI;
    
    @FXML
    private AnchorPane anchor;
    
    public MainMenuController(Stage s) {
        super(s);  
    }

    @FXML
    public void initialize() {
        //statementUI = new Label(statement);
        //anchor.getChildren();
        //statementUI.sesetLayoutY(100);
        //statementUI.setFont(new Font("Arial", 17));
    }
    
    @FXML 
    public void handleLevelSelectionButton() {
        MenuScreen levelSelectionScreen = new MenuScreen(super.getS(), "Level Selection", "application/LevelSelectionMenu.fxml");
        LevelSelectionController levelSelectionController = new LevelSelectionController(super.getS());
        
        levelSelectionScreen.start(levelSelectionController);
    }
    
    public void handleQuitMainMenuButton() {
        System.exit(1);
    }
    
  
}
