package application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;

public class MenuScreen {
    
    private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;
    private String fxmlFile;
    
    public MenuScreen(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public void start(Controller c) {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(c);
        try {
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500,300);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
