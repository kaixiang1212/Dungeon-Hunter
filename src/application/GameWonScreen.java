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
    private String screenTitle;
    private FXMLLoader fxmlLoader;

    
    public GameWonScreen(Stage stage) {
        this.stage = stage;
        this.screenTitle = "GameWon";
       this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/GameWon.fxml"));
    }
    
    
    
    public void start() {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(new GameWonController(stage));
        try {  
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500,300);
            stage.setScene(sc);   
            stage.show();
            sc.getRoot().requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
