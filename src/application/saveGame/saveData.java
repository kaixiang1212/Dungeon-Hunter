package application.saveGame;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

import Model.Dungeon;
import Model.Player;
import Model.WinCondition;
import Model.ComputerAgent.ComputerAgent;
import Model.Item.Item;
import Model.Tile.Tile;
import Model.Tile.Switch;

public class saveData implements Serializable {

	private static final long serialVersionUID = 1L;

	public Point topLeft;
    public Point bottomRight;

    public Map<Point, Item> itemGrid;
    public Map<Point, Tile> tileGrid;
    public Map<Point, ComputerAgent> agentGrid;
    public Point playerPosition;
    public Player player;
    public Queue<Integer> doorCode;
    public ArrayList<Switch> switches;
    public WinCondition winCheck;
    
    public saveData(Dungeon dungeon) {
    	topLeft = dungeon.getTopLeft();
    	bottomRight = dungeon.getBottomRight();
    	itemGrid = dungeon.getItemGrid();
    	tileGrid = dungeon.getTileGrid();
    	agentGrid = dungeon.getAgentGrid();
    	player = dungeon.getPlayer();
    	playerPosition = dungeon.getPlayerPos();
    	winCheck = dungeon.getWinCheck();
    	switches = dungeon.getSwitches();
    	doorCode = dungeon.getDoorCode();
	}
}
