package Model;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Controller.Direction;
import Model.Item.Item;
import Model.Tile.DefaultTile;
import Model.Tile.Door;
import Model.Tile.EntityType;
import Model.Tile.FunctionalTile;
import Model.Tile.Tile;
import Model.Tile.Type;
import Model.Tile.Wall;
import Model.Tile.Switch;

public class Dungeon {

	public final int MAX_SIZE = 20;

    private Point topLeft;
    private Point bottomRight;

    private Map<Point, Item> itemGrid;
    private Map<Point, Tile> tileGrid;
    private Map<Point, ComputerAgent> agentGrid;
    private Point playerPosition;
    private Player player;
    // TODO: Easier to track win condition
    private ArrayList<Switch> switches;


    public Dungeon(int size) throws IllegalArgumentException{
    	playerPosition = null;
    	agentGrid = new HashMap<Point, ComputerAgent>();
    	itemGrid = new HashMap<Point, Item>();
    	
        if (size > MAX_SIZE || size < 1) {
            throw new IllegalArgumentException("Dungeon constructor size param 1-20. Received " + size);
        }

        this.tileGrid = initTileGrid(size);

        this.topLeft = new Point(0, 0);
        this.bottomRight = new Point(size+1, size+1);
        this.switches = new ArrayList<>();
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

        addSwitch(tile, myPoint);
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
    	return this.player;
    }

    /**
     * Utilises entrySet iterator
     * Iterates over agentGrid to move agents
     * Grabs new position
     * Deletes old entry in agent hashmap
     * Enters new entry
     */
    public void updateAgents() {
    	for(Map.Entry<Point,ComputerAgent> entry : agentGrid.entrySet()) {
    		Point updatePos = entry.getValue().move(this);
    		agentGrid.remove(entry.getKey());
    		agentGrid.put(updatePos, entry.getValue()); //Give new position, otherwise removed forever
            triggerAgentAction(updatePos);
    	}
    }

    public void updatePlayer(Direction dir) {
    	int x = (int) this.playerPosition.getX();
    	int y = (int) this.playerPosition.getY();
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
        }
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
    	// Checks cases for types of tiles that can't be moved on
    	if(!isValidMoveBasic(check)) {
    		return false;
    	}
    	//Checks if movable agent, ie. boulder can't move depending on player push direction
    	ComputerAgent temp = agentGrid.get(check);
    	if (temp != null && temp.isMoveable()) {

    		Direction dir = player.getDirection();
    		int x = (int) check.getX();
    		int y = (int) check.getY();
    		switch(dir) {
    			case LEFT:
    				if(!isValidMoveBasic(new Point(x-1, y))) {
    					return false;
    				}
    				break;
    			case RIGHT:
    				if(!isValidMoveBasic(new Point(x+1, y))) {
    					return false;
    				}
    				break;
    			case UP:
    				if(!isValidMoveBasic(new Point(x, y-1))) {
    					return false;
    				}
    				break;
    			case DOWN:
    				if(!isValidMoveBasic(new Point(x, y+1))) {
    					return false;
    				}
    				break;
    		}
    	}

    	return true;  	
    }

    public boolean tileIsReachable(Point point, EntityType type) {
    	if (point == null) return false;
    	Tile tile = tileGrid.get(point);
    	if (tile != null && !tile.isReachable(type)) return false;
    	return true;
    }
    
    public boolean isValidMoveBasic(Point check) {
    	return tileIsReachable(check, EntityType.Default);
    }
  
    public boolean isValidMoveAgent(Point check) {
    	return tileIsReachable(check, EntityType.Computer);
    }
    
    public boolean isValidMoveArrow(Point point) {
    	return isAgentExist(point) && (!getAgent(point).getClass().getSimpleName().equals("Boulder"));
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
    	if(isItemExist(pos)) {
    		removeItem(pos);
    	}
    	itemGrid.put(pos, i);
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
    	// If item, attempt to pickup the item
    	if (itemGrid.get(point) != null) {
    		if (!itemGrid.get(point).isLitBomb()) {
    			this.player.pickup(itemGrid.get(point));
    			this.itemGrid.remove(point);
    		}
    	}
    }

    private void triggerAgentAction(Point point) {
    	if (playerPosition.equals(point)) {
    		player.fight(this);
    	}
    }
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
    public void addSwitch(Tile tile, Point point) {
    	if (tile instanceof Switch) {
    		Switch sw = (Switch )tile;
    		switches.add(sw);
    		sw.setPoint(point);
    		
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
    
    public void triggerTileAction(Point point) {
    	Tile tile = tileGrid.get(point);
    	if (tile instanceof FunctionalTile) {
    		((FunctionalTile )tile).doOperation(player);
    	}
    }
}
