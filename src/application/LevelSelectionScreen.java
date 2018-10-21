package application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelSelectionScreen {
    private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;
    
    public LevelSelectionScreen(Stage stage) {
        this.stage = stage;
        this.screenTitle = "Level Selection Menu";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/LevelSelectionMenu.fxml"));
    }
    
    public void start() {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(new LevelSelectionController(stage));
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

    
