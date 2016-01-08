package cam.entities;

import java.util.Random;

import cam.graphics.Screen;
import cam.level.Level;

public abstract class Entity {
	
	public int x, y;
	protected Level level;
	public int id;
	public int gender;
	public boolean removed = false;
	public boolean spoken = false;
	protected final Random random = new Random();
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
	public void init(Level level){
		this.level = level;
	}
	
	public int getId(){
		return id;
	}

	public boolean hasSpoken(){
		return spoken;
	}
	
	public int getGender(){
		return gender;
	}

}
