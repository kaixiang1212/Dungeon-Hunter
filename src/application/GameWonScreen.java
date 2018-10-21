package application;


import java.io.IOException;

import Model.Dungeon;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GameWonScreen {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    
    public GameWonScreen(Stage stage) {
        this.stage = stage;
       this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/GameWon.fxml"));
    }
    
    public void start() {
        fxmlLoader.setController(new GameLostController(stage));
        try {  
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 650, 700);
            Stage newWindow = new Stage();
            newWindow.setTitle("You Won");
            newWindow.setScene(sc);
            newWindow.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        
    }
}
