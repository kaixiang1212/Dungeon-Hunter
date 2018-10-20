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
    private String fxmlFile;

    
    public GameWonScreen(Stage stage) {
        this.stage = stage;
        this.screenTitle = "Game Lost";
       this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/GameLost.fxml"));
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public void start() {
        //FXMLLoader fxml = new FXMLoader("application/GameLost.fxml");
        //Label secondLabel = new Label("I'm a Label on new Window");          
        //StackPane secondaryLayout = new StackPane();
        
        //secondaryLayout.getChildren().add(secondLabel);
        fxmlLoader.setController(new GameLostController(stage));
        try {  
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500, 500);
            Stage newWindow = new Stage();
            newWindow.setTitle("You Won");
            newWindow.setScene(sc);
            newWindow.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        
    }
}
