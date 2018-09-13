package itemDesign;

import java.awt.Point;
import java.util.Map;

public interface MoveBehaviour {
	
	public void move(Point playerPos, Map<Point, ComputerAgent> agentMap);
	
}
