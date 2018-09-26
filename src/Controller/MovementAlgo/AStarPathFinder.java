package Controller.MovementAlgo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import Model.Dungeon;

public class AStarPathFinder implements PathFinder {
	
	private ArrayList<Node> closed;
	private PriorityQueue<Node> open;
	private Node[][] nodes;

	public AStarPathFinder() {
		closed = new ArrayList<>();
		open = new PriorityQueue<>(12, new Comparator<Node>() {

			@Override
			public int compare(Node arg0, Node arg1) {
				return (int) (arg0.getCost() - arg1.getCost());
			}
		});
	}
	
	private void init(Dungeon map) {
		nodes = new Node[map.MAX_SIZE][map.MAX_SIZE];
		for (int x = 0;x < map.MAX_SIZE; x++) {
			for (int y = 0; y < map.MAX_SIZE; y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}

	@Override
	public Point findPath(Dungeon map, Point current, Point target) {
		
		init(map);
		
		nodes[current.x][current.y].setCost(0);
		closed.clear();
		open.clear();
		open.add(nodes[current.x][current.y]);
		
		while (open.size() != 0) {
			Node currentNode = open.peek();
			if (currentNode.x == target.x && currentNode.y == target.y) {
				break;
			}
			open.remove(currentNode);
			closed.add(currentNode);
			
			for (int x = -1; x<2; x++) {
				for (int y = -1; y<2; y++) {
					if ((x == 0 && y == 0) || (x != 0 && y != 0)) continue;
					
					int dx = x + currentNode.x;
					int dy = y + currentNode.y;
					
					if (map.isValidMoveAgent(new Point(dx, dy))) {
						double nextStepCost = currentNode.getCost() + 1;
						Node neighbour = nodes[dx][dy];
						
						if (nextStepCost < neighbour.getCost()) {
							if (open.contains(neighbour)) {
								open.remove(neighbour);
							}
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}
						if (!open.contains(neighbour) && !closed.contains(neighbour)) {
							neighbour.setCost(nextStepCost);
							neighbour.setHeuristic(getHeuristicCost(dx, dy, target.x, target.y));
							neighbour.setParent(currentNode);
							open.add(neighbour);
						}
						
					}
				}
			}
		}
		if (nodes[target.x][target.y].getParent() == null) return null;
		
		Node targetNode = nodes[target.x][target.y];
		while (targetNode.getParent() != nodes[current.x][current.y]) {
			targetNode = targetNode.getParent();
		}
		
		return new Point(targetNode.x, targetNode.y);
	}
	
	private double getHeuristicCost(int targetX, int targetY, int currentX, int currentY) {
		return Math.abs(targetX - currentX) + Math.abs(currentY - targetY);
	}

}
