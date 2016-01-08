package cam.level1.entities.people;

import java.util.Random;

import cam.Game;
import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.input.Keys;

public class Commoner extends People {

	private boolean interacting = false;
	private Random random = new Random();
	private Player player;
	private Game game;
	private Keys keys;
	long timer;
	int xa = 0, ya = 0;

	private int time = 0;

	public Commoner(Game game, int x, int y, int id, boolean received) {
		this.game = game;
		this.x = x * 16;
		this.y = y * 16;
		this.id = id;
		this.received = received;
		
		keys = game.keys;
		
		if (random.nextInt(2) == 1 && id != 11) {
			gender = 1;
		} else if (id == 11) {
			gender = 2;
		} else {
			gender = 0;
		}

		player = Game.player;
	}

	public void update() {
		time++;
		checkInteraction();

		if (!interacting) {
			if (time % (random.nextInt(40) + 30) == 0) {
				xa = random.nextInt(3) - 1;
				ya = random.nextInt(3) - 1;
				if (random.nextInt(5) == 0) {
					xa = 0;
					ya = 0;
				}
			}

			if (xa != 0 || ya != 0) {
				move(xa, 0);
				move(0, ya);
			}
		} else {
			xa = 0;
			ya = 0;
		}
	}

	public void say(String saying, int text) {
		if (text == 1) {
			Game.commonerText = saying;
			Game.commonerText2 = "";
		} else if (text == 2) {
			Game.commonerText2 = saying;
		}
	}

	private void checkInteraction() {
		double distance = Math.sqrt(Math.pow(x - Game.player.getX(), 2) - Math.pow(y - Game.player.getY(), 2));
		double radius = 12;

		switch(Game.player.getDir()){
		case 0:
			dir = 0;
			break;
		case 1:
			dir = 3;
			break;
		case 2:
			dir = 2;
			break;
		case 3:
			dir = 1;
			break;
		}
		
		
		if (distance < (radius)) {
			interacting = true;
			if (Keys.interact) {

				switch (id) {
				case 0:
					if (!received && !keys.letGo) {
						say("Thanks you fine sir!", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
				case 1:
					if (!received && !keys.letGo) {
						say("Words of wisdom these are indeed Thomas.", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
				case 2:
					if (!received && !keys.letGo) {
						say("My friend Thomas, thank you for this masterpiece", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
				case 3:
					if (!received && !keys.letGo) {
						say("Thank you!", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
				case 4:
					if (!received && !keys.letGo) {
						say("I agree. I am fed up with the tyranny of England!", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
					
				case 5:
					if(!received && !keys.letGo){
						say("I'll make sure I read this as soon as possible.", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
				case 6:
					if(!received && !keys.letGo){
						say("We're need more men like you if we are ever gonna leave England's rule.", 1);
						Game.distributions++;
						received = true;
						keys.letGo = true;
					}
					break;
				
				}
			}
		} else {
			interacting = false;
		}

	}

	public void render(Screen screen) {

		int xx = x - 16;
		int yy = y - 16;

		if (gender == 1) {

			switch (dir) {
			case 0:
				sprite = Sprite.man_up;
				break;
			case 1:
				sprite = Sprite.man_right;

				break;
			case 2:
				sprite = Sprite.man_down;
				break;
			case 3:
				sprite = Sprite.man_left;
				break;
			default:
				sprite = Sprite.man_down;
				break;
			}

		} else if (gender == 0) {

			switch (dir) {
			case 0:
				sprite = Sprite.woman_up;
				break;
			case 1:
				sprite = Sprite.woman_right;
				break;
			case 2:
				sprite = Sprite.woman_down;

				break;
			case 3:
				sprite = Sprite.woman_left;
				break;
			default:
				sprite = Sprite.woman_down;
				break;
			}
		} else if (gender == 2) {// old man
			switch (dir) {
			case 0:
				sprite = Sprite.oldman_up;
				break;
			case 1:
				sprite = Sprite.oldman_right;

				break;
			case 2:
				sprite = Sprite.oldman_down;
				break;
			case 3:
				sprite = Sprite.oldman_left;
				break;
			default:
				sprite = Sprite.oldman_down;
				break;
			}
		}

		screen.renderPlayer(xx, yy, sprite);
	}
}
