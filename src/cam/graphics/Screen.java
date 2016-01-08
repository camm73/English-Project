package cam.graphics;

import java.util.Random;

import cam.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;

	public final int mapSize = 64;

	public int xOffset, yOffset;

	public int[] tiles = new int[mapSize * mapSize];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		for (int i = 0; i < mapSize * mapSize; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset;
		yPos -= yOffset;

		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yPos;

			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xPos;

				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				
				if (xa <0){
					xa = 0;
				}
				
				pixels[xa + ya * width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
			}
		}
	}
	
	
	public void renderPlayer(int xPos, int yPos, Sprite sprite){
		xPos -= xOffset;
		yPos -= yOffset;
		
		for(int y = 0; y < sprite.SIZE; y++){
			int ya = y + yPos;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xPos;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;

				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col != 0xffff00ff){
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
