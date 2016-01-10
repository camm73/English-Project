package cam;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;

import cam.graphics.Screen;
import cam.input.Keys;
import cam.level1.entities.people.Commoner;
import cam.level1.entities.people.Player;
import cam.level1.level.Level;
import cam.level1.level.Map;
import cam.level2.SecondMain;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2946129672622533874L;
	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	public static final int SCALE = 3;
	public static final String title = "TEMPORARY ENGLISH PROJECT TITLE";
	public static int levelNumber = 1;

	private Screen screen;
	public Keys keys;
	private Level level;
	private Map map;
	private int fps;
	public static Player player;
	public boolean first = true;
	
	private int commonNum = 60;
	public static int distributions = 0;
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

		frame = new JFrame(title);
		keys = new Keys();
		screen = new Screen(WIDTH, HEIGHT);
		level = Level.main;
		map = new Map();
		player = new Player(5, 20, keys);
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

	public void update() {
		keys.update();
		player.update();
		level.update();
		
		if(first){
			addEntity();
			first = false;
		}
		
		if(levelNumber == 2){
			SecondMain main = new SecondMain();
			frame.dispose();
			stop();
		}
	}
	
	public void addEntity(){
		for(int i = 0; i < commonNum; i++){
			Random random = new Random();
			level.add(new Commoner(this, i*2 + 25, 20, random.nextInt(7), false)); //TODO may need to change the spawning distances
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
		//System.out.println(xScroll + "   " + yScroll);
		player.render(screen);
		//System.out.println("x: "+ player.x + " y: "+ player.y);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(levelNumber == 1){
		
			g.drawImage(mainLevelImage, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.black);
			g.fillRect(0, HEIGHT*SCALE - 60, WIDTH*SCALE, 60); //TODO replace this with a nice looking plank of wood or something of the sort
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 18));
			g.drawString(new String(String.valueOf(fps)), 15, 25);
			g.drawString("Pamphlets distributed: " + distributions, (WIDTH*SCALE)/2, 25);
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.setColor(Color.WHITE);
			g.drawString(commonerText, 10, (HEIGHT*SCALE) - 35);
			g.drawString(commonerText2, 10, (HEIGHT*SCALE) - 15);
			
			//TODO need to add some sort of time limit which will be drawn on the top of the screen
			
			map.render(g, level);
		}else if(levelNumber == 2){
			for(int i = 0; i < pixels.length;i++){
				pixels[i] = 0;
			}
			g.drawImage(mainLevelImage, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.black);
			g.fillRect(0, HEIGHT*SCALE - 60, WIDTH*SCALE, 60);
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 18));
			g.drawString(new String(String.valueOf(fps)), 15, 25);
			g.drawString("Pamphlets distributed: " + distributions, (WIDTH*SCALE)/2, 25);
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
			delta += (now-prev) / ns;
			prev = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
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
	
	public void startMusic(){
		music.play();
	}

}
