package cam.level1.entities.people;

import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.input.Keys;
import cam.level1.level.Level;

public class Player extends People{

	private Keys key;
	private Sprite sprite;
	private Level level;
	private int anim = 2;
	private int speed = 2;
	private boolean moving = false;

	public Player(Keys key) {
		this.key = key;
	}

	public Player(int x, int y, Keys key) {
		this.key = key;
		this.x = x * 16;
		this.y = y * 16;
	}

	public void update() {
		int xa = 0, ya = 0;

		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}

		if (key.up)
			ya -= speed;
		if (key.down)
			ya += speed;
		if (key.left)
			xa -= speed;
		if (key.right)
			xa += speed;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
		}
	}

	public void render(Screen screen) {
		int xx = x - 16;
		int yy = y - 16;

		switch (dir) {
		case 0:
			sprite = Sprite.player_up;

			/**if (moving) {
				if (anim % 20 > 5) {
					sprite = Sprite.player_up_1;
				} else {
					sprite = Sprite.player_up_2;
				}
			}**/
			break;
		case 1:
			sprite = Sprite.player_right;

			/**if (moving) {
				if (anim % 20 > 5) {
					sprite = Sprite.player_right_1;
				} else {
					sprite = Sprite.player_right_2;
				}
			}**/
			break;
		case 2:
			sprite = Sprite.player_down;

			/**if (moving) {
				if (anim % 20 > 5) {
					sprite = Sprite.player_down_1;
				} else {
					sprite = Sprite.player_down_2;
				}
			}**/
			break;
		case 3:
			sprite = Sprite.player_left;

			/**if (moving) {
				if (anim % 20 > 5) {
					sprite = Sprite.player_left_1;
				} else {
					sprite = Sprite.player_left_2;
				}
			}**/
			break;
		default:
			sprite = Sprite.player_down;

			/**if (moving) {
				if (anim % 20 > 5) {
					sprite = Sprite.player_down_1;
				} else {
					sprite = Sprite.player_down_2;
				}
			}**/
			break;
		}
		screen.renderPlayer(xx, yy, sprite);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getDir(){
		return dir;
	}
}
