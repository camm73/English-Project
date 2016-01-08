package cam.entities.people;

import java.util.Random;

import cam.Game;
import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.input.Keys;

public class Commoner extends People {

	private boolean interacting = false;
	private Random random = new Random();
	private Player player;
	long timer;
	int xa = 0, ya = 0;

	private int time = 0;

	public Commoner(int x, int y, int id, boolean spoken) {
		this.x = x * 16;
		this.y = y * 16;
		this.id = id;
		this.spoken = spoken;
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
		double radius = 16;

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
					if (!spoken) {
						say("Thanks you fine sir!", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 1:
					if (!spoken) {
						say("Words of wisdom these are indeed Thomas.", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 2:
					if (!spoken) {
						say("My friend Thomas, thank you for this masterpiece", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 3:
					if (!spoken) {
						say(">>Paul told us to pursue love and strive for spiritual gifts.<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 4:
					if (!spoken) {
						say(">>Paul told us to gloify our God in our bodies and souls<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 5:
					if (!spoken) {
						say(">>Paul told us that we have received the Holy Spirit to help us understand God's gifts.<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 6:
					if (!spoken) {
						say(">>Paul reminded us of how God wasn't please with the Israelites and how what", 1);
						say(" happened to them is an example for us to not desire evil.", 2);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 7:
					if (!spoken) {
						say(">>Paul told us that we must forgive others so that we aren't outwitted by Satan.<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 8:
					if (!spoken) {
						say(">>Paul told us to be generous in order to test our genuineness of our love.<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 9:
					if (!spoken) {
						say(">>Paul told us that anyone who eats the body and blood of christ must be worthy.<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 10:
					if (!spoken) {
						say(">>Paul told us that all of the spiritual gifts given to them have all come from the same God.<<", 1);
						Game.distributions++;
						spoken = true;
					}
					break;
				case 11:
					if (!spoken) {
						say("The form criticism of Corithians is how Paul use metaphors to address the divisions and", 1);
						say("disunity of the church. Paul says they are many parts that must make one body.", 2);
						Game.distributions++;
						spoken = true;

						//TODO literary criticism of Corinthians 12:12
						
						// >>The form criticism of Corinthians shows that the purpose behind both letters is to show the immorality of the Corithians and to challenge them to live holy lives and combat their impure ways.<<
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
