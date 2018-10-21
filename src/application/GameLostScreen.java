package application;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameLostScreen {
    private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;
   
    public GameLostScreen(Stage stage) {
        this.stage = stage;
        this.screenTitle = "Game Lost";
       this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/GameLost.fxml"));
    }
    
    public void start() {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(new GameLostController(stage));
        try {  
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500,300);
            stage.setScene(sc);
            stage.centerOnScreen();
            stage.show();
            sc.getRoot().requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
