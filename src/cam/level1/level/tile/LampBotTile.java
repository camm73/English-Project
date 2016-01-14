package cam.level1.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;

public class LampBotTile extends Tile {

	public LampBotTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}

}
