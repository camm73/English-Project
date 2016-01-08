package cam.level1.level;

import java.util.ArrayList;
import java.util.List;

import cam.graphics.Screen;
import cam.level1.entities.Entity;
import cam.level1.entities.people.Player;
import cam.level1.level.tile.Tile;

public abstract class Level {
	
	public int[] tiles;
	public static int width, height;
	
	public List<Entity> entities = new ArrayList<Entity>();
	public static Level main = new MainLevel("/map.png");
	protected Player player;
	
	public Level(String path){
		loadLevel(path);
		generateLevel();
	}
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width*height];
		generateLevel();
	}
	
	public void add(Entity e){
		e.init(this);
		entities.add(e);
	}
	
	public Entity getEntity(int x, int y){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).getX() == x && entities.get(i).getY() == y){
				return entities.get(i);
			}
		}
		
		return null;
	}
	
	public void remove(Entity e, int id){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).getId() == id){
				entities.remove(i);
			}
		}
	}
	
	public boolean getSpoken(int id){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).getId() == id){
				return entities.get(i).hasSpoken();
			}
		}
		
		return false;
	}
	
	public void removeAllEntities(){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved()){
				entities.remove(i);
			}
		}
	}
	
	protected void generateLevel(){
		
	}
	
	protected void loadLevel(String path){
		
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x< x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
					
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = entity.getX();
			int y = entity.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);

			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) {
				result.add(entity);
			}
		}
		return entities;
	}

	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if(tiles[x+y*width] == Tile.col_grass) return Tile.grassTile;
		if(tiles[x+y*width] == Tile.col_sandStone) return Tile.sandStoneTile;
		if(tiles[x+y*width] ==Tile.col_wood) return Tile.woodTile;
		if(tiles[x+y*width] == Tile.col_water) return Tile.waterTile;
		if(tiles[x+y*width] == Tile.col_sand) return Tile.sandTile;
		if(tiles[x+y*width] == Tile.col_darkWood) return Tile.darkWoodTile;
		if(tiles[x+y*width] == Tile.col_glass) return Tile.glassTile;
		
		return Tile.voidTile;
	}
}
