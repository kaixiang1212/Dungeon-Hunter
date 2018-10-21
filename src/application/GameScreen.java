package application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

import Model.Dungeon;

public class GameScreen {

    private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;

    public GameScreen(Stage s) {
        this.s = s;
        this.title = "Game";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/test2.fxml"));
    }

    public void start(Dungeon d) {
        s.setTitle(title);
        fxmlLoader.setController(new GameController(s, d));
        try {
            Parent root = fxmlLoader.load();  
            Scene sc = new Scene(root, 500, 300);
            s.setScene(sc);
            s.show();
            sc.getRoot().requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
  