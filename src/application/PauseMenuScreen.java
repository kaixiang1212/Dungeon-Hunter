package application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;
public class PauseMenuScreen {
        
        private Stage stage;
        private String screenTitle;
        private FXMLLoader fxmlLoader;
        
        public PauseMenuScreen(Stage stage) {
            this.stage = stage;
            this.screenTitle = "Main Menu";
            this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/PauseMenu.fxml"));
        }
        
        
        public Stage getStage() {
            return stage;
        }
        
        public void start() {
            stage.setTitle(screenTitle);
            fxmlLoader.setController(new PauseMenuController(stage));
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
