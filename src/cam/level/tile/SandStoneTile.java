package cam.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;

public class SandStoneTile extends Tile {

	public SandStoneTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}

}
