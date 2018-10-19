package Model;


import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import Controller.Direction;
import Model.ComputerAgent.Boulder;
import Model.ComputerAgent.ComputerAgent;
import Model.Item.Item;
import Model.Item.Key;
import Model.Tile.DefaultTile;
import java.util.Map.Entry;

import Controller.Direction;
import Model.Item.Item;
import Model.Item.Potion;
import Model.Tile.Door;
import Model.Tile.EntityType;
import Model.Tile.FunctionalTile;
import Model.Tile.Tile;
import Model.Tile.Type;
import Model.Tile.Wall;
import Model.Tile.Switch;
import javafx.scene.image.Image;

public class Dungeon {

	public final int MAX_SIZE = 20;

    private Point topLeft;
    private Point bottomRight;

    private Map<Point, Item> itemGrid;
    private Map<Point, Tile> tileGrid;
    private Map<Point, ComputerAgent> agentGrid;
    private Point playerPosition;
    private Player player;
    private Queue<Integer> doorCode;
    private ArrayList<Switch> switches;
    private WinCondition winCheck;
    private int savesize;


    public Dungeon(int size) throws IllegalArgumentException{
    	playerPosition = null;
    	agentGrid = new HashMap<Point, ComputerAgent>();
    	itemGrid = new HashMap<Point, Item>();
    	savesize = size;
    	winCheck = new DefaultNoWinCondition();
    	
    	
        if (size > MAX_SIZE || size < 1) {
            throw new IllegalArgumentException("Dungeon constructor size param 1-20. Received " + size);
        }

        this.tileGrid = initTileGrid(size);

        this.topLeft = new Point(0, 0);
        this.bottomRight = new Point(size+1, size+1);
        this.switches = new ArrayList<>();
        this.doorCode = new ArrayDeque<>();
    }
   

    public Map<Point, Tile> getTileGrid() {
        return tileGrid;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }


    /**
     * Generate a HashMap of Tiles, keyed by Point location
     *
     * The Map is a (size) by (size) grid of no tiles, ringed by
     * a double-wall of INVINCIBLE_TILES.
     *
     * @param (size > 0 && size <= MAX_SIZE)
     * @return A default empty dungeon
     */

    private HashMap<Point, Tile> initTileGrid(int size) {

        if (size < 1) {
            // TODO: refine this error checking
            System.err.println("tried to make a dungeon grid size less than 1");
            System.exit(1);
        }

        HashMap<Point, Tile> ret = new HashMap<Point, Tile>();
        int[] edges = {-1, 0, size+1, size+2};
        // Set the dungeons walls on left/right sides
        for(int i = -1; i <= size + 2; i++) {
            for (int j : edges) {
                ret.put(new Point(i, j), new Wall());
            }
        }

        // Set the dungeons walls on remaining top/bottom sides
        for (int i : edges) {
            for (int j = 1; j <= size; j++) {
                ret.put(new Point(i, j), new Wall());
            }
        }
        //Set rest of tiles to default tiles that allow for free movement
        for(int i = 1; i <= size; i++) {
        	for(int j = 1; j <= size; j++) {
        		ret.put(new Point(i,j), new DefaultTile());
        	}
        }

        return ret;
    }

    /**
     * Default tile generator.
     *
     * Makes a Tile Grid of MAX_SIZE
     * @return A default empty dungeon size MAX_SIZE
     */
    private HashMap<Point, Tile> initTileGrid() {
        return initTileGrid(this.MAX_SIZE);
    }

//    /**
//     * Exposes the type of tile at a location
//     * @param location
//     * @return Tile.Type
//     */
//    public Type pointTileType(Point location) {
//        Tile local = tileGrid.get(location);
//        if (local == null) {
//            return null;
//        }
//
//        return local.getType();
//    }
    
    public Tile getTile(Point point) {
    	return tileGrid.get(point);
    }

    /**
     * Adds a tile to a location
     * @param tileType The Tile type that is to be placed
     * @param myPoint The location to place the Tile
     * @return true if Tile placed
     * @throws IllegalArgumentException when myPoint outside topLeft and bottomRight defined boundaries
     */
    public boolean placeTile(Tile tile, Point myPoint) throws IllegalArgumentException {

        // Out of Bound Check
        if (outOfBound(myPoint)) return false;

        // Additional Operation : to keep track of tiles in Dungeon
        if (tile.isType(Type.Switch)) addSwitch(tile, myPoint);
        else if (tile.isType(Type.ClosedDoor)) addDoorCode(tile);

        if (tileGrid.get(myPoint) == null) {
            tileGrid.put(myPoint, tile);
            return true;
        }
        //If tile already exists, simply switch type!
        else if (tileGrid.get(myPoint) != null) {
        	tileGrid.replace(myPoint, tile);
        	return true;
        }

        return false;
    }

    /**
     * Inserts a new ComputerAgent object into the agentGrid
     * @param a agent to be placed
     * @param agentPoint Location to be placed
     * @TODO Ensure that bad placedment attempt throuws Exception
     * @TODO Ensure that agent can be placed.
     */
    public void placeComputerAgent(ComputerAgent a, Point agentPoint) {
    	agentGrid.put(agentPoint, a);
    	a.setPos(agentPoint);
    	// if Boulder is on Switch, trigger
    	//if (a.isMoveable() == true) triggerSwitch(agentPoint);
    }
    /**
     * Inserts a new Player object into the dungeon
     * @param p Player to be placed
     * @param playerStart Location to be placed
     * @TODO Ensure that bad placement attempt throuws Exception
     * @TODO Ensure that Player can be placed.
     */
    public void placePlayer(Player p, Point playerStart) {
    	playerPosition = playerStart;
    	player = p;
    }
    public Player getPlayer() {
    	return player;
    }

    /**
     * Utilises entrySet iterator
     * Iterates over agentGrid to move agents
     * Grabs new position
     * Deletes old entry in agent hashmap
     * Enters new entry
     */
//    public void updateAgents() {
//
//    	for(Map.Entry<Point,ComputerAgent> entry : agentGrid.entrySet()) {
//    		
//    		Point updatePos = entry.getValue().move(this);
//    		agentGrid.remove(entry.getKey());
//    		agentGrid.put(updatePos, entry.getValue()); //Give new position, otherwise removed forever
//            triggerAgentAction(updatePos);
//    	}
//    }
    public void updateAgents() {
    	
    	ArrayList<ComputerAgent> alreadyMoved = new ArrayList<ComputerAgent>();   	
    	for(int x = 0; x<this.savesize+2; x++) {
    		for(int y = 0; y<this.savesize+2; y++) {
    			Point check = new Point(x,y);
    			ComputerAgent agent = this.agentGrid.get(check);
    			if(agent != null && !alreadyMoved.contains(agent)) {
    				Point updatePos = agent.move(this);
    				agentGrid.remove(check);
    				agentGrid.put(updatePos,  agent);
    				alreadyMoved.add(agent);
    				triggerAgentAction(updatePos);
    			}
    		}
    	}
    }

    public void updatePlayer(Direction dir) {
    	int x = playerPosition.x;
    	int y = playerPosition.y;
    	Point desireDir;
    	switch (dir) {
    		case LEFT:
    			desireDir = new Point(x-1, y);	
    			this.player.setDirection(Direction.LEFT);
    			break;
			case DOWN:
				desireDir = new Point(x, y+1);
				this.player.setDirection(Direction.DOWN);
    			break;
    		case RIGHT:
    			desireDir = new Point(x+1, y);
    			this.player.setDirection(Direction.RIGHT);
    			break;
    		case UP:
    			desireDir = new Point(x, y-1);
    			this.player.setDirection(Direction.UP);
    			break;
    		default:
    			return;
        };
        triggerTileAction(desireDir);
        if (isValidMove(desireDir)) this.playerPosition = desireDir;
        triggerPlayerAction(playerPosition);
    }

    public Point getPlayerPos() {
    	return this.playerPosition;
    }
    
    public void removeAgent(Point p) {
    	this.agentGrid.remove(p);
    }
    
    /**
     * Checks if tile to be moved on is valid to move on by Player.
     * @param check
     * @return
     */
    public boolean isValidMove(Point check) {
    	if (!tileIsReachable(check, EntityType.Default)) {
    		return false;
    	}
    	ComputerAgent temp = agentGrid.get(check);
    	if (temp != null && temp.isMoveable()) {
    		Direction dir = player.getDirection();
    		Point bol;
    		switch (dir) {
			case LEFT:
				bol = new Point(check.x-1, check.y);
				if (!isValidMoveBoulder(bol)) return false;
				break;
			case RIGHT:
				bol = new Point(check.x+1, check.y);
				if (!isValidMoveBoulder(bol)) return false;
				break;
			case UP:
				bol = new Point(check.x, check.y-1);
				if (!isValidMoveBoulder(bol)) return false;
				break;
			case DOWN:
				bol = new Point(check.x, check.y+1);
				if (!isValidMoveBoulder(bol)) return false;
				break;
			default:
				break;
			}
    	}
    	return true;
    }

    public boolean tileIsReachable(Point point, EntityType type) {
    	if (point == null) return false;
    	Tile tile = tileGrid.get(point);
    	if (tile == null || !tile.isReachable(type)) return false;
    	return true;
    }
    
    public boolean isValidMoveBasic(Point check) {
    	return tileIsReachable(check, EntityType.Default);
    }
  
    public boolean isValidMoveAgent(Point check) {
    	return tileIsReachable(check, EntityType.Computer) && !isAgentExist(check);
    }
    
    public boolean isValidMoveBoulder(Point check) {
    	return isValidMoveBasic(check) && !isAgentExist(check);
    }
    
    public boolean isValidMoveArrow(Point point) {
    	if (!isValidMoveBasic(point)) {
    		return false;
    	}
    	if(agentGrid.get(point) != null && agentGrid.get(point).isMoveable()) {
    		return false;
    	}
    	return true;
    }


    /**
     * Typically called after isValidMove(Point) to further verify for
     * agents, so agents do not overlap
     * Player object do not call this, as they can fight agents, agents can't
     * fight other agents
     * @param check
     * @return
     */
    public boolean isAgentExist(Point check) {
    	//If agent already on that spot
    	if(agentGrid.containsKey(check)) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Place item on specified point. If item already on point, simply remove then place
     * TODO: invalid coordinates, add exception for invalid item placement!
     * @param i Item
     * @param pos Position in Point form
     */
    public void placeItem(Item i, Point pos) {
    	if (i instanceof Key) setKeyCode((Key )i);
    	if (isItemExist(pos)) removeItem(pos);
    	itemGrid.put(pos, i);
    }
    
    private void setKeyCode(Key key) {
    	key.setCode(doorCode.poll());
    }

    public boolean isItemExist(Point check) {
    	if(itemGrid.containsKey(check)) {
    		return true;
    	}
    	return false;
    }
    public void removeItem(Point pos) {
    	if(isItemExist(pos)) {
    		itemGrid.remove(pos);
    	}
    }
    
    public boolean outOfBound(Point toCheck) throws IllegalArgumentException {

        if (toCheck.x < topLeft.x || toCheck.x > bottomRight.x ||
            toCheck.y > bottomRight.y || toCheck.y < topLeft.y) {
            throw new IllegalArgumentException("Placement out of bounds");
        }
    	return false;
    }

    //TODO: Is it bad to put so many if statements? probably a better way
    private void triggerPlayerAction(Point point) {	
    	ComputerAgent temp = agentGrid.get(point);
    	if (temp != null) {
    		if (temp.isMoveable()) {
    			Point newPos = ((Boulder) temp).push(player.getDirection());
    			agentGrid.remove(point);
    			if (!tileGrid.get(newPos).isType(Type.Pit)) {
    				agentGrid.put(newPos, temp);
    			}	
    		}
    		else {
    			// fight
    			this.player.fight(this);
    		}
    	}
    	// If item, attempt to pickup the item
    	if (itemGrid.get(point) != null) {
    		if (!itemGrid.get(point).isLitBomb()) {
    			this.player.pickup(itemGrid.get(point));
    			this.itemGrid.remove(point);
    		}
    	}
    	
    	for (int i = 0; i < this.player.getStatus().size(); i++) {
    		Potion curr = this.player.getStatus().get(i);
    		if (curr.isInvinc()) {
    			if (curr.getDuration() == 1) {
    				this.player.getStatus().remove(i);
    				i--;
    			} else {
    				this.player.getStatus().get(i).reduceDuration();
    			}
    		}
    	}

    }

    /**
     * Trigger Agent Fight Action
     * @param point Agent's Position
     */
    private void triggerAgentAction(Point point) {
    	if (playerPosition.equals(point)) {
    		player.fight(this);
    	}
    }
    
    /**
     * Get agent on a specific point
     * @param point Point
     * @return Computer Agent on given Point
     */
    public ComputerAgent getAgent(Point point) {
    	return agentGrid.get(point);
    }

    /**
     * Win condition for switches
     * @return true if all switches is triggered false otherwise
     */
    public boolean winConditionSwitch() {
    	for (Switch sw : switches) {
    		if (sw.isActivated() == false) return false;
    	}
    	return true;
    }
    
    /**
     * Assign additional records required for Switch
     * @param tile
     * @param point
     */
    private void addSwitch(Tile tile, Point point) {
    	if (tile.isType(Type.Switch)) {
    		Switch sw = (Switch )tile;
    		switches.add(sw);
    		sw.setPoint(point);
    	}
    }
    
    private void addDoorCode(Tile tile) {
    	if (tile.isType(Type.ClosedDoor)) {
    		Door door = (Door )tile;
    		doorCode.add(door.getCode());
    	}
    }

    /**
     * Pass in the agent on the same grid to trigger or not trigger switch
     */
    public void updateTile() {
    	for (Switch switch1 : switches) {
    		Point point = switch1.getPoint();
    		switch1.update(agentGrid.get(point));
    	}
    }
    
    /**
     * Trigger any Tile Action (like unlockDoor, fall into pit ..)
     * @param point
     */
    public void triggerTileAction(Point point) {
    	Tile tile = tileGrid.get(point);
    	if (tile instanceof FunctionalTile) {
    		((FunctionalTile )tile).doOperation(player);
    	}
    }
    
    /**
     * Return true if Tile on given Point is not null
     * @param point Point to Check 
     * @return true if the point given is a tile
     */
    public boolean hasTile(Point point) {
    	return tileGrid.get(point) != null;
    }
    
    /**
     * Function to check tileType on a given point
     * @param point Point to check
     * @param type Tile Type
     * @return true if given point is a given type
     */
    // TODO: Rename
    public boolean isPointTileType(Point point, Type type) {
    	return (hasTile(point) && getTile(point).isType(type));
    }
    
    // TODO: Give some comment on this function: good? bad?
    public void endTurn() {
    	updateAgents();
    	updateTile();
    }
  
    public int numEnemies() {
    	return this.agentGrid.size();
    }
    public boolean hasTreasure() {
    	for(Map.Entry<Point,Item> entry : itemGrid.entrySet()) {
    		if(entry.getValue().isTreasure()) {
    			return true;
    		}
    	}
    	return false;
    }
    public boolean hasWon() {
    	return winCheck.hasWon(this);
    }
    public boolean hasLost() {
    	return player.deathStatus();
    }
    /**
     * Proxy method, gets tile image at location.
     * Maybe we can specify it can take in any HashMap with point?
     * Then its a generic method!
     * We could also do this to refactor incredibly similar reptitive methods.
     * @return
     */
    public Image proxygettiles(Point point, Map<Point, ? extends Paintable> map) {
    	Paintable p = map.get(point);
    	if(p != null) {
    		return p.getImage();
    	}
    	return null;
    }
    
    public int getSize() {
    	return savesize;
    }
    public Map<Point, ComputerAgent> getAgentGrid() {
    	return agentGrid;
    }
    public Map<Point, Item> getItemGrid() {
    	return itemGrid;
    }

    public Item selectItemSlot(int index) {
    	return player.selectItem(index);
    }
    public void setWinCondition(WinCondition wc) {
    	winCheck = wc;
    }
    public void playerUseItem() {
    	player.useItem(this);
    }
}
