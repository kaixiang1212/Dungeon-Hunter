package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // set the stage height to be 400
        primaryStage.setHeight(400);
        // set the stage width to be 600
        primaryStage.setWidth(800);
        
        //change this to start with Main menu screen
        
        //implement play game to launch Game Screen
        
        MenuScreen mainMenuScreen = new MenuScreen(primaryStage, "Main Menu", "application/MainMenu.fxml");
        MainMenuController mainMenuController = new MainMenuController(primaryStage);
        mainMenuScreen.start(mainMenuController);
        
        //GameScreen gameScreen = new GameScreen(primaryStage);
       // gameScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}