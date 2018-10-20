package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // set the stage height to be 400
        // set the stage width to be 600
           
        primaryStage.setHeight(1000);
        primaryStage.setWidth(900);
        primaryStage.centerOnScreen();
        //primaryStage.setMaximized(true);
       //GameScreen gameScreen = new GameScreen(primaryStage);
       
       //gameScreen.start();
          //DesignScreen designScreen = new DesignScreen(primaryStage);
          //designScreen.start();
          
          //change this to start with Main menu screen
          
          //implement play game to launch Game Screen
          
          MenuScreen mainMenuScreen = new MenuScreen(primaryStage);
          //MainMenuController mainMenuController = new MainMenuController(primaryStage);
          mainMenuScreen.start();
         
          
          //GameScreen gameScreen = new GameScreen(primaryStage);
         // gameScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}