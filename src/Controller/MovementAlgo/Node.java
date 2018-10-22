package Controller.MovementAlgo;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	int x;
	int y;
	private double cost;
	private Node parent;
	private double heuristic;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * @return the heuristic
	 */
	public double getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	
}
