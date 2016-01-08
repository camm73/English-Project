package cam.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainLevel extends Level {

	public MainLevel(String path) {
		super(path);
	}

	public void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(MainLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height =  image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	protected void generateLevel(){
		
	}

}
