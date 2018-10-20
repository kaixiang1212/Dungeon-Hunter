package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        // set the stage height to be 400
        // set the stage width to be 600          
        
        //primaryStage.setHeight(400);
// set the stage width to be 600
        //primaryStage.setWidth(800);

        primaryStage.setHeight(1000);
        primaryStage.setWidth(900);
        primaryStage.centerOnScreen();       
  
         
          //GAME MODE (Direct)
        
          //GameScreen gameScreen = new GameScreen(primaryStage);
          // gameScreen.start();
                
        
          //DESIGN MODE (Direct)
        
          //DesignScreen designScreen = new DesignScreen(primaryStage);
          //designScreen.start(null);
          
        
          //Main Menu -> Level Selection Menu -> game
        
          MainMenuScreen mainMenuScreen = new MainMenuScreen(primaryStage);
          //MainMenuController mainMenuController = new MainMenuController(primaryStage);
          mainMenuScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}