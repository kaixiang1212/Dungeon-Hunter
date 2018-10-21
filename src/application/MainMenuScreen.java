package application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;

public class MainMenuScreen {  
    private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;
    
    public MainMenuScreen(Stage stage) {
        this.stage = stage;
        this.screenTitle = "Main Menu";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/MainMenu.fxml"));
    }
    
    public void start() {
        stage.setTitle(screenTitle);
        fxmlLoader.setController(new MainMenuController(stage));
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
