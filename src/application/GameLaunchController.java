package application;

import java.awt.Point;
import java.util.Map;

import Controller.Direction;
import Model.Coward;
import Model.Dungeon;
import Model.Hunter;
import Model.Paintable;
import Model.Player;
import Model.Strategist;
import Model.Item.Hover;
import Model.Item.Invincibility;
import Model.Item.Sword;
import Model.Item.Treasure;
import Model.Tile.Type;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameLaunchController extends Controller{

    private Stage stage;
    private Dungeon d;

    @FXML
    private Pane mainPane;
    
    @FXML
    private Pane inventoryPane;
    
    public GameLaunchController(Stage s) {
        super(s);
    }
    
    @FXML 
    public void initialize() {
        //Temporary setup
        Dungeon test = new Dungeon(8);
        //test.placeComputerAgent(new Hunter(), new Point(1,1));
        //test.placeComputerAgent(new Hunter(), new Point(2,1));
        test.placeComputerAgent(new Strategist(), new Point(2,2));
        //test.placeComputerAgent(new Coward(), new Point(2,3));
        test.placeItem(new Treasure(), new Point(2,3));
        test.placeItem(new Sword(), new Point(2,1));
        test.placeItem(new Invincibility(), new Point(3,1));
        test.placeItem(new Hover(), new Point(4,1));
        test.placeItem(new Sword(), new Point(5,1));
        test.placePlayer(new Player(), new Point(4,4));
        test.placeTile(Type.EXIT, new Point(8,8));
        test.placeTile(Type.PIT, new Point(6,8));
        test.placeTile(Type.CLOSED_DOOR, new Point(6,6));
        test.placeTile(Type.OPEN_DOOR, new Point(7,7));
        
        this.d = test;
        //Starts game
        ImageView insertview = new ImageView(d.getPlayerImage());
        insertview.setFitHeight(32);
        insertview.setFitWidth(32);
        insertview.setLayoutX(0 * 32);
        insertview.setLayoutY(0 * 32);  
        inventoryPane.getChildren().add(insertview);
        render();
    }
    /**
     * Calls renderUtil to render multiple grids
     * Encapsulated away from initialize so it can be called again on every
     * move.
     * @param d
     */
    public void render() {
        int size = d.getSize();
        renderUtil(d.getTileGrid(), mainPane);
        renderUtil(d.getItemGrid(), mainPane);
        renderUtil(d.getAgentGrid(), mainPane);
        renderPlayer(mainPane);
        
    }
//  public void startGame(Dungeon d) {
        //Initial rendering of game
//      render(d);

        //d.updateAgents();
        //render(d);
//      int maxmoves = 100;
//      for(int i=0;i<maxmoves;i++) {
//          
//          d.updateAgents();
//          render(d);
//          
//      }
//  }
    /**
     * 
     * @param d Dungeon reference, for size and to utilise check method
     * @param map Reference of grid to paint
     * @param pane Pane to paint onto
     * Note plus 2, as size specifies walkable area, we need to include the invincible walls as possible area for placement
     */

    public void renderUtil(Map<Point, ? extends Paintable> map, Pane pane) {
        for(int y = 0; y < d.getSize() + 2; y++) {
            for(int x = 0; x < d.getSize() + 2; x++) {
                Image check = d.proxygettiles(new Point(x, y), map);
                if(check != null) {
                    ImageView insertview = new ImageView(check);
                    insertview.setFitHeight(32);
                    insertview.setFitWidth(32);
                    insertview.setLayoutX(x * 32);
                    insertview.setLayoutY(y * 32);
                    pane.getChildren().add(insertview);
                }
            }
        }
    }
    //Issue is player is no part of grids, done separately in this case.
    public void renderPlayer(Pane pane) {
        Point playerPos = d.getPlayerPos();
        int x = playerPos.x;
        int y = playerPos.y;
        ImageView insertview = new ImageView(d.getPlayerImage());
        insertview.setFitHeight(32);
        insertview.setFitWidth(32);
        insertview.setLayoutX(x * 32);
        insertview.setLayoutY(y * 32);  
        pane.getChildren().add(insertview);
    }

    //Temporary solution
    //Ideally some game loop with threading and animation
    @FXML
    public void playerMovement(KeyEvent key) {
        System.out.print(key.getCode());
        switch (key.getCode()) {
        case A:
            d.updatePlayer(Direction.LEFT);
            break;
        case S:
            d.updatePlayer(Direction.DOWN);
            break;
        case D:
            d.updatePlayer(Direction.RIGHT);
            break;
        case W:
            d.updatePlayer(Direction.UP);
        }
        d.updateAgents();
        render();
    }
}

