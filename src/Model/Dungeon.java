package Model;


import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import Controller.Direction;
import Model.Item.Item;
import Model.Tile.Door;
import Model.Tile.Tile;
import Model.Tile.Type;

public class Dungeon {
    public final int MAX_SIZE = 20;


    private Point topLeft;
    private Point bottomRight;

    private Map<Point, Item> itemGrid;
    private Map<Point, Tile> tileGrid;
    private Map<Point, ComputerAgent> agentGrid;
    private Point playerPosition;
    private Player player;
    private int doorCode = -1;


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
                ret.put(new Point(i, j), new Tile(Type.INVINCIBLE_WALL));
            }
        }

        // Set the dungeons walls on remaining top/bottom sides
        for (int i : edges) {
            for (int j = 1; j <= size; j++) {
                ret.put(new Point(i, j), new Tile(Type.INVINCIBLE_WALL));
            }
        }
        //Set rest of tiles to default tiles that allow for free movement
        for(int i = 1; i <= size; i++) {
        	for(int j = 1; j <= size; j++) {
        		ret.put(new Point(i,j), new Tile(Type.DEFAULT));
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

    /**
     * Exposes the type of tile at a location
     * @param location
     * @return Tile.Type
     */
    public Type pointTileType(Point location) {
        Tile local = tileGrid.get(location);
        if (local == null) {
            return null;
        }

        return local.getType();
    }
    
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
    public boolean placeTile(Type tileType, Point myPoint) throws IllegalArgumentException {

        // Cannot place invincible wall
        if (tileType == Type.INVINCIBLE_WALL) {
            return false;
        }

        // Out of Bound Check
        if (outOfBound(myPoint)) return false;

        if (tileGrid.get(myPoint) == null) {
            tileGrid.put(myPoint, new Tile(tileType));
            return true;
        }
        //If tile already exists, simply switch type!
        if(tileGrid.get(myPoint) != null) {
        	tileGrid.get(myPoint).setType(tileType);
        	return true;
        }

        return false;
    }
    
    /**
     * Create a new door and return the key to this door
     * @param doorPoint Point to create a closed door
     * @return a key to the door
     * @throws IllegalArgumentException
     */
    public boolean placeDoorKey(Point doorPoint, Point keyPoint) throws IllegalArgumentException {

    	if (outOfBound(doorPoint) || outOfBound(keyPoint)) return false;

    	if (tileGrid.get(doorPoint).isType(Type.DEFAULT)) {
    		Door newDoor = new Door(doorCode++);
    		tileGrid.put(doorPoint, newDoor);
    		// place key on map (Not yet implemented)
    		placeItem(newDoor.generateKey(), keyPoint);
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
    	switch (dir) {
    		case LEFT:
    			Point left = new Point(x-1, y);
    			if (isValidMove(left)) {
    				this.playerPosition = left;
    				this.player.setDirection(Direction.LEFT);
    			}
    			break;
			case DOWN:
    			Point down = new Point(x, y+1);
    			if (isValidMove(down)) {
    				this.playerPosition = down;
    				this.player.setDirection(Direction.DOWN);
    			}
    			break;
    		case RIGHT:
    			Point right = new Point(x+1, y);
    			if (isValidMove(right)) {
    				this.playerPosition = right;
    				this.player.setDirection(Direction.RIGHT);
    			}
    			break;
    		case UP:
    			Point up = new Point(x, y-1);
    			if (isValidMove(up)) {
    				this.playerPosition = up;
    				this.player.setDirection(Direction.UP);
    			}
    			break;
        }
        triggerPlayerAction(playerPosition);
    }

    public Point getPlayerPos() {
    	return this.playerPosition;
    }
    
    public void removeAgent(Point p) {
    	this.agentGrid.remove(p);
    }
    
    /**
     * Checks if tile to be moved on is valid to move on by players.
     * @param check
     * @return
     */
    public boolean isValidMove(Point check) {
    	//Checks cases for types of tiles that can't be moved on
    	if(!isValidMoveBasic(check)) {

    		return false;
    	}
    	//Checks if movable agent, ie. boulder can't move depending on player push direction
    	ComputerAgent temp = agentGrid.get(check);
    	if(temp != null && temp.isMoveable()) {

    		Direction dir = player.getDirection();
    		int x = (int) check.getX();
    		int y = (int) check.getY();
    		switch(dir) {
    			case LEFT:
    				if(!isValidMoveAgent(new Point(x-1, y))) {
    					return false;
    				}
    				break;
    			case RIGHT:
    				if(!isValidMoveAgent(new Point(x+1, y))) {
    					return false;
    				}
    				break;
    			case UP:
    				if(!isValidMoveAgent(new Point(x, y-1))) {
    					return false;
    				}
    				break;
    			case DOWN:
    				if(!isValidMoveAgent(new Point(x, y+1))) {
    					return false;
    				}
    				break;
    		}
    	}

    	return true;  	
    }
  
    public boolean isValidMoveBasic(Point check) {
    	//Checks cases for types of tiles that can't be moved on

    	if (check == null) return false;

    	Tile tileA = tileGrid.get(check);
    	if (tileA != null) {

    		Type type = tileA.getType();
    		switch (type) {
    		case INVINCIBLE_WALL:
    			return false;
    		case CLOSED_DOOR:
    			return false;
    		case DESTRUCTIBLE_WALL:
    			return false;
    		}
    	}
    	return true;
    }
    public boolean isValidMoveArrow(Point check) {
    	if (!isValidMoveBasic(check)) {
    		return false;
    	}
    	if(agentGrid.get(check) != null && agentGrid.get(check).isMoveable()) {
    		return false;
    	}
    	return true;
    }
    public boolean isValidMoveAgent(Point check) {
    	if (!isValidMoveArrow(check)) {
    		return false;
    	}
    	ComputerAgent temp = agentGrid.get(check);
    	if(tileGrid.get(check).getType() == Type.PIT && temp != null && !temp.isMoveable()) {
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
     	// Grid is a PIT
    	if(tileGrid.get(point).getType() == Type.PIT) {
    		if (!this.player.isHover()) {
    			this.player.die();
    		}
    	}
    	// Grid holds an Agent
    	ComputerAgent temp = agentGrid.get(point);
		if (temp != null) {
			if(temp.isMoveable()) {
				Point newPos = ((Boulder) temp).push(player.getDirection());
				agentGrid.remove(point);
				if(tileGrid.get(newPos).getType() != Type.PIT) {
					agentGrid.put(newPos, temp);
				}	
			}
			else {
    		// fight
				this.player.fight(this);
			}
    	}
/*     	// The next Grid is Door
    	if (tileGrid.get(point).getType() == Type.CLOSED_DOOR) {
    		// unlock door
    		Door door = (Door )tileGrid.get(point);
    		door.unlockDoor(player.getKeys());
    	}*/

		//Grid is a EXIT
    	if(tileGrid.get(point).getType() == Type.EXIT) {
    		//Win?
    	}
    	    	
    	// If item, attempt to pickup the item
    	if (itemGrid.get(point) != null) {
    		if (!itemGrid.get(point).isLitBomb()) {
    			this.player.pickup(itemGrid.get(point));
    			this.itemGrid.remove(point);
    		}
    	}
    	

    }
    private void triggerAgentAction(Point point) {
    	
    	if(playerPosition.equals(point)) {
    		player.fight(this);
    	}
    }
    public ComputerAgent getAgent(Point point) {
    	return agentGrid.get(point);
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
}
