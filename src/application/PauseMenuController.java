package application;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;


public class PauseMenuController {
    
        
       // private String statement;
        
        //private Label statementUI;
        
        @FXML
        private AnchorPane anchor;
        private Stage stage;
        
        public PauseMenuController(Stage s) {
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
        
        public void handleQuitPauseMenuButton() {
            
        }
        
      
}
