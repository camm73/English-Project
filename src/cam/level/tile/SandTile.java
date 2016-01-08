package cam.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;

public class SandTile extends Tile {

	public SandTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
}
