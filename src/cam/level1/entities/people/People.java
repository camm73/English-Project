package cam.level1.entities.people;

import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.level1.entities.Entity;
import cam.level1.level.Level;

public abstract class People extends Entity{
	
	protected Sprite sprite;
	protected int dir = 2; // 0 - up(back), 1 right(right side), 2 down(front),
							// 3 left(left side)

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (ya < 0)
			dir = 2;
		if (ya > 0)
			dir = 0;
		if (xa < 0)
			dir = 3;
		if (xa > 0)
			dir = 1;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {
	}

	public void render(Screen screen) {

	}

	public boolean collision(int xa, int ya) {
		boolean hitting = false;
		for (int d = 0; d < 4; d++) {
			int xd = 0, yd = 0;
			if ((x * 16) < Level.width) {
				xd = ((x + xa) - 16) / 16;
			} else {
				xd = ((x + xa) - 4) / 16;
			}
			
			if((y * 16) < Level.height){
				yd = ((y + ya)  - 15) / 16;
			}else{
				yd = ((y + ya) + 6) / 16;
			}
			
			if (level.getTile(xd, yd).solid()) {
				hitting = true;
			}
		}

		return hitting;
	}
}
