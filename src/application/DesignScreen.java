package application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import Model.Dungeon;

public class DesignScreen {

    private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;

    public DesignScreen(Stage s) {
        this.s = s;
        this.title = "Design";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/design.fxml"));
    }

    public void start(Dungeon d)  {
        s.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new DesignController(s, d));
        try {
            // load into a Parent node called root
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
