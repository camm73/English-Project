package cam.level1.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;

public class SandTile extends Tile {

	public SandTile(Sprite sprite) {
		super(sprite);
	}
	
	public void renderTile(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}

}
