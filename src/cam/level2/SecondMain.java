package cam.level2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cam.Game;

public class SecondMain {
	
	public final int WIDTH = Game.WIDTH*Game.SCALE;
	public final int HEIGHT = Game.HEIGHT*Game.SCALE;
	
	public JPanel panel;
	public JFrame frame;
	
	public BufferedImage backgroundImage;
	
	public SecondMain(){
		frame = new JFrame();
		panel = new JPanel();
		loadImages();
		content();
	}
	
	public void content(){
		panel.setLayout(null);
		
	}
	
	public void createFrame(){
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(backgroundImage);
	}
	
	public void loadImages(){
		try{
			backgroundImage = SecondMain.class.getResource("/secondBackground.png");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
