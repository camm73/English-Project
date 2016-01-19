package cam;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.input.Keys;
import cam.level1.entities.people.Commoner;
import cam.level1.entities.people.Player;
import cam.level1.level.Level;
import cam.level1.level.Map;
import cam.level2.SecondMain;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2946129672622533874L;
	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	public static final int SCALE = 3;
	public static final int WIDTH = 340;
	public static final int HEIGHT = 200;
	public static final String title = "Time Crisis -- By Cameron Miller and Brian Harrison";
	public static int levelNumber = 1;

	private Screen screen;
	public Keys keys;
	private Level level;
	private Map map;
	private int fps;
	public static Player player;
	public boolean first = true;
	public BufferedImage woodBar;

	private int entityNum = 60;
	public int levelTime = 120;
	public static int distributions1 = 0;
	public static String commonerText = "";
	public static String commonerText2 = "";

	public Sound music = new Sound("/levelSong.wav");

	public BufferedImage mainLevelImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) mainLevelImage.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);

		loadImage();
		frame = new JFrame(title);
		keys = new Keys();
		screen = new Screen(WIDTH, HEIGHT);
		level = Level.main;
		map = new Map();
		player = new Player(18, 40, keys);
		player.init(level);
		addKeyListener(keys);

		startMusic();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			music.stop();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	int time = 0;

	public void update() {
		time++;
		keys.update();
		player.update();
		level.update();

		if (Keys.mute && music.playing && !keys.muteLetGo) {
			keys.muteLetGo = true;
			music.stop();
		} else if (Keys.mute && !music.playing && !keys.muteLetGo) {
			keys.muteLetGo = true;
			music.loop();
		} else if (!Keys.mute) {
			keys.muteLetGo = false;
		}

		if (first) {
			addEntity();
			first = false;
		}

		if (time % 60 == 0) {
			levelTime--;
		}

		if (levelTime == 0) {
			levelNumber = 2;
		}

		if (levelNumber == 2) {
			SecondMain main = new SecondMain();
			frame.dispose();
			stop();
		}
	}

	public void addEntity() {
		for (int i = 0; i < entityNum; i++) {
			Random random = new Random();
			if (!level.getTile(i * 48, i * 48).solid()) {
				level.add(new Commoner(this, i * 2 + 25, 20, random.nextInt(10), random.nextInt(3), false));
			} else {
				int tmp = 0;
				while (level.getTile((i * 3) + tmp, (i * 2) + tmp).solid()) {
					tmp += 5;
				}
				level.add(new Commoner(this, (i * 3) + tmp, (i * 2) + tmp, random.nextInt(10), random.nextInt(3), false));
			}
		}
	}

	public void loadImage() {
		try {
			woodBar = ImageIO.read(Game.class.getResource("/woodBar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();

		if (levelNumber == 1) {

			g.drawImage(mainLevelImage, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.black);
			g.drawImage(woodBar, 0, Game.HEIGHT * SCALE - 60, null);
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 18));
			g.drawString(new String(String.valueOf(fps)), 15, 25);
			g.drawString("Pamphlets distributed: " + distributions1, ((WIDTH * SCALE) / 2) - 20, 25);
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.setColor(Color.BLACK);
			g.drawString(commonerText, 10, (HEIGHT * SCALE) - 35);
			g.drawString(commonerText2, 10, (HEIGHT * SCALE) - 15);
			if (keys.disabled) {
				g.drawString("Time left speaking with colonist: " + Commoner.talkingTimeLeft, 240, (HEIGHT * SCALE - 15));
			}
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Time Left: " + levelTime + " secs", (WIDTH * SCALE) - 200, (HEIGHT * SCALE) - 20);

			map.render(g, level);
		} else if (levelNumber == 2) {
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = 0;
			}
			g.drawImage(mainLevelImage, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.black);
			g.fillRect(0, HEIGHT * SCALE - 60, WIDTH * SCALE, 60);
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 18));
			g.drawString(new String(String.valueOf(fps)), 15, 25);
			g.drawString("Pamphlets distributed: " + distributions1, (WIDTH * SCALE) / 2, 25);
		}

		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		long prev = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - prev) / ns;
			prev = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frame.setTitle(title + " UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

		stop();
	}

	public void begin() {

		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		start();
	}

	public void startMusic() {
		music.loop();
		music.playing = true;
	}

}
