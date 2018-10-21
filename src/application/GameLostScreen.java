package application;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameLostScreen {
    private Stage stage;
    private FXMLLoader fxmlLoader;
   
    public GameLostScreen(Stage stage) {
        this.stage = stage;
       this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/GameLost.fxml"));
    }
    
    public void start() {
        fxmlLoader.setController(new GameLostController(stage));
        try {  
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500, 500);
            Stage newWindow = new Stage();
            newWindow.setTitle("You Lost");
            newWindow.setScene(sc);
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
        
    }
}
