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
	public int talkingTime = 3;
	public static int talkingTimeLeft;
	public boolean moving = false;
	public Thread tmpThread;

	private int time = 0;
	
	public Commoner(){
		
	}

	public Commoner(Game game, int x, int y, int id, int type, boolean received) {
		this.game = game;
		this.x = x * 16;
		this.y = y * 16;
		this.id = id;
		this.received = received;
		this.type = type;
		
		keys = game.keys;

		player = Game.player;
	}

	long now;
	int anim = 0;
	public void update() {
		time++;
		checkInteraction();
		
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
		
		if (!interacting) {
			if (time % (random.nextInt(40) + 30) == 0) {
				xa = random.nextInt(3) - 1;
				ya = random.nextInt(3) - 1;
				if (random.nextInt(5) == 0) {
					xa = 0;
					ya = 0;
					moving = false;
				}
			}

			if (xa != 0 || ya != 0) {
				move(xa, 0);
				move(0, ya);
				moving = true;
			}
		} else {
			xa = 0;
			ya = 0;
			moving = false;
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

	public void render(Screen screen) {
		now = System.currentTimeMillis();
		
		int xx = x - 16;
		int yy = y - 16;

		if (type == 1) {

			switch (dir) {
			case 0:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man1_up;
					}else{
						sprite = Sprite.man1_up_1;
					}
				}else{
					sprite = Sprite.man1_up;
				}
				break;
			case 1:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man1_right;
					}else{
						sprite = Sprite.man1_right_1;
					}
				}else{
					sprite = Sprite.man1_right;
				}

				break;
			case 2:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man1_down;
					}else{
						sprite = Sprite.man1_down_1;
					}
				}else{
					sprite = Sprite.man1_down;
				}
				break;
			case 3:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man1_left;
					}else{
						sprite = Sprite.man1_left_1;
					}
				}else{
					sprite = Sprite.man1_left;
				}
				break;
			default:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man1_down;
					}else{
						sprite = Sprite.man1_down_1;
					}
				}else{
					sprite = Sprite.man1_down;
				}
				break;
			}

		} else if (type == 0) {

			switch (dir) {
			case 0:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man2_up;
					}else{
						sprite = Sprite.man2_up_1;
					}
				}else{
					sprite = Sprite.man2_up;
				}
				break;
			case 1:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man2_right;
					}else{
						sprite = Sprite.man2_right_1;
					}
				}else{
					sprite = Sprite.man2_right;
				}

				break;
			case 2:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man2_down;
					}else{
						sprite = Sprite.man2_down_1;
					}
				}else{
					sprite = Sprite.man2_down;
				}
				break;
			case 3:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man2_left;
					}else{
						sprite = Sprite.man2_left_1;
					}
				}else{
					sprite = Sprite.man2_left;
				}
				break;
			default:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man2_down;
					}else{
						sprite = Sprite.man2_down_1;
					}
				}else{
					sprite = Sprite.man2_down;
				}
				break;
			}
		} else if (type == 2) {
			switch (dir) {
			case 0:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man3_up;
					}else{
						sprite = Sprite.man3_up_1;
					}
				}else{
					sprite = Sprite.man3_up;
				}
				break;
			case 1:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man3_right;
					}else{
						sprite = Sprite.man3_right_1;
					}
				}else{
					sprite = Sprite.man3_right;
				}

				break;
			case 2:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man3_down;
					}else{
						sprite = Sprite.man3_down_1;
					}
				}else{
					sprite = Sprite.man3_down;
				}
				break;
			case 3:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man3_left;
					}else{
						sprite = Sprite.man3_left_1;
					}
				}else{
					sprite = Sprite.man3_left;
				}
				break;
			default:
				if(moving){
					if(anim % 20 < 5){
						sprite = Sprite.man3_down;
					}else{
						sprite = Sprite.man3_down_1;
					}
				}else{
					sprite = Sprite.man3_down;
				}
				break;
			}
		}

		screen.renderPlayer(xx, yy, sprite);
	}
	
	private void checkInteraction() {
		double distance = Math.sqrt(Math.pow(x - Game.player.getX(), 2) - Math.pow(y - Game.player.getY(), 2));
		double radius = 12;
		

		if(distance < radius){
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
		}
		
		
		if (distance < (radius)) {
			interacting = true;
			if (Keys.interact && !keys.disabled) {
				
				switch (id) {
				case 0:
					if (!received && !keys.letGo) {
						say("Thanks you fine sir!", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 1:
					if (!received && !keys.letGo) {
						say("Words of wisdom these are indeed Thomas.", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 2:
					if (!received && !keys.letGo) {
						say("My friend Thomas, thank you for this masterpiece", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 3:
					if (!received && !keys.letGo) {
						say("Thank you!", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 4:
					if (!received && !keys.letGo) {
						say("I agree. I am fed up with the tyranny of England!", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
					
				case 5:
					if(!received && !keys.letGo){
						say("I'll make sure I read this as soon as possible.", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 6:
					if(!received && !keys.letGo){
						say("We're need more men like you if we are ever gonna leave England's rule.", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 7:
					if(!received && !keys.letGo){
						say("You're absolutely right. We need to break free of this tyrannical government", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 8:
					if(!received && !keys.letGo){
						say("Finally! Someone who is speaking the truth about this.", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				case 9:
					if(!received && !keys.letGo){
						say("May God guide us in our path to freedom.", 1);
						Game.distributions1++;
						received = true;
						keys.letGo = true;
						
						pauseControls();
					}else{
						if(received && !keys.letGo){
							say("Thank you again, but I've already gotten one!", 1);
						}
					}
					break;
				}
			}
		} else {
			interacting = false;
		}

	}
	
	long prev;
	public void pauseControls(){
		prev = System.currentTimeMillis();
		tmpThread = new Thread(new Runnable(){
			public void run(){
				while(((now - prev)/1000) < talkingTime){
					talkingTimeLeft = talkingTime - (int) ((now - prev) / 1000);
					keys.disabled = true;
					System.out.println("timeLeft: " + talkingTimeLeft);
				}
				keys.disabled = false;
			}
		});
		tmpThread.start();
	}
	
	public synchronized void stop(){
		try {
			tmpThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
