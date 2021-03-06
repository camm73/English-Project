package cam.level1.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.level1.level.tile.GrassTile;

public abstract class Tile {

	public Sprite sprite;
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile test = new TestTile(Sprite.test);
	public static Tile grassTile = new GrassTile(Sprite.grass);
	public static Tile stoneTile = new StoneTile(Sprite.stone);
	public static Tile woodTile = new WoodTile(Sprite.wood);
	public static Tile waterTile = new WaterTile(Sprite.water);
	public static Tile darkWoodTile = new DarkWoodTile(Sprite.darkWood);
	public static Tile glassTile = new GlassTile(Sprite.glass);
	public static Tile brickTile = new BrickTile(Sprite.brick);
	public static Tile roofTile = new RoofTile(Sprite.roof);
	public static Tile sandTile = new SandTile(Sprite.sandSprite);
	
	public static final int col_grass = 0xff0FD63D;
	public static final int col_stone = 0xffD6690F;
	public static final int col_water = 0xff0026FF;
	public static final int col_wood = 0xff663300;
	public static final int col_darkWood = 0xff000000;
	public static final int col_glass =  0xffB4CDCD;
	public static final int col_brick = 0xffff0000;
	public static final int col_roof = 0xffd2b48c;
	public static final int col_sand = 0xffFFFFFF;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}
}
