package View;

import java.util.ArrayList;

import Model.Dungeon;
import javafx.scene.layout.Pane;

public class DungeonRenderer {
	ArrayList<Renderer> renderers;
	
	public DungeonRenderer(Dungeon dungeon) {
		renderers = new ArrayList<>();
		renderers.add(new TileRenderer(dungeon));
		renderers.add(new PlayerRenderer(dungeon));
		renderers.add(new AgentRenderer(dungeon));
		renderers.add(new ItemRenderer(dungeon));
	}
	
	public void render(Pane pane) {
		for (Renderer renderer : renderers) {
			renderer.render(pane);
		}
	}
}
