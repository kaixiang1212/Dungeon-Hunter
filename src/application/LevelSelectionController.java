package application;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;



public class LevelSelectionController extends Controller {
    
  
    
    @FXML
    private AnchorPane anchor;
    
    public LevelSelectionController(Stage s) {
        super(s);
        //this.statement = statement;
        
        //this.statement = statement;    
    }
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML 
    public void handleBackButton() {
        MenuScreen menuScreen = new MenuScreen(super.getS(), "Main Menu", "application/MainMenu.fxml");
        MainMenuController mainMenuController = new MainMenuController(super.getS());
        menuScreen.start(mainMenuController);
    }
    
    @FXML
    public void handlePlayLevel1Button() {
        GameScreen playLevel1Screen = new GameScreen(super.getS());
        //GameLaunchController level1Controller = new GameLaunchController(super.getS());
        playLevel1Screen.start();
    }
   
    
  
}
