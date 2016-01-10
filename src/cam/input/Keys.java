package cam.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cam.Game;

public class Keys implements KeyListener{

	public boolean[] key = new boolean[65536];
	public static boolean up, down, left, right, escape, interact;
	public boolean disabled = false;
	public boolean letGo = false;
	
	
	
	public void update(){
		if(!disabled){
			up = key[KeyEvent.VK_W];
			down = key[KeyEvent.VK_S];
			left = key[KeyEvent.VK_A];
			right = key[KeyEvent.VK_D];
		}else{
			up = false;
			down = false;
			left = false;
			right = false;
		}
		escape = key[KeyEvent.VK_ESCAPE];
		interact = key[KeyEvent.VK_X];
		if(key[KeyEvent.VK_7]){
			Game.levelNumber = 2;
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()] = false;
		
		if(!interact){
			letGo = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
