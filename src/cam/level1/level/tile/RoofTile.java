package cam.level1.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;

public class RoofTile extends Tile {

	public RoofTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}

}
