package application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScreen {

    private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;

    public GameScreen(Stage s) {
        this.s = s;
        this.title = "Rental Screen";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/test2.fxml"));
    }

    public void start()  {
        s.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new GameController(s));
        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();        
            Scene sc = new Scene(root, 500, 300);
            s.setScene(sc);
            s.show();
            root.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

}