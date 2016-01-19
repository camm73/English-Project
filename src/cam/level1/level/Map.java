package cam.level1.level;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import cam.Game;
import cam.level1.entities.Entity;

public class Map extends Canvas {

	int width = MainLevel.width;
	int height = MainLevel.height;
	int scale = 2;

	BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt) map.getRaster().getDataBuffer()).getData();

	public void render(Graphics g, Level level) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
		for (int i = 0; i < level.entities.size(); i++) {
			Entity e = level.entities.get(i);
			int x = e.x >> 4;
			int y = e.y >> 4;
			int px = Game.player.getX() >> 4;
			int py = Game.player.getY() >> 4;

			if (!e.received) {
				if (e.getId() == 11) {
					pixels[x + y * width] = 0xffffff;
					pixels[(x + 1) + y * width] = 0xffffff;
					pixels[(x + 2) + y * width] = 0xffffff;
				} else {
					pixels[x + y * width] = 0xff0000;
					pixels[(x + 1) + y * width] = 0xff0000;
					pixels[(x + 2) + y * width] = 0xff0000;
				}
			} else {
				pixels[x + y * width] = 0;
				pixels[(x + 1) + y * width] = 0;
				pixels[(x + 2) + y * width] = 0;
			}

			pixels[(px) + py * width] = 0xffff00;
			pixels[(px + 1) + py * width] = 0xffff00;
			pixels[(px + 2) + py * width] = 0xffff00;
		}

		g.drawImage(map, (Game.WIDTH*Game.SCALE)- width, 0, width, height, null);
	}

}
