package cam.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{

	public boolean[] key = new boolean[65536];
	public static boolean up, down, left, right, escape, interact;
	
	
	
	public void update(){
		up = key[KeyEvent.VK_W];
		down = key[KeyEvent.VK_S];
		left = key[KeyEvent.VK_A];
		right = key[KeyEvent.VK_D];
		escape = key[KeyEvent.VK_ESCAPE];
		interact = key[KeyEvent.VK_X];
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
