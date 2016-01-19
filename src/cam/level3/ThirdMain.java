package cam.level3;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import cam.Game;
import cam.Results;

public class ThirdMain {
	
	public final int WIDTH = 900;
	public final int HEIGHT = 600;
	
	public JFrame frame;
	public static long distributions3 = 0;
	
	public ThirdMain(){
		frame = new JFrame();
		createFrame();
	}
	
	public void createFrame(){
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setTitle(Game.title);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ThirdPanel());
		frame.setVisible(true);
	}
	
	
	class ThirdPanel extends JPanel implements Runnable{
		
		private static final long serialVersionUID = 1998269598443270286L;
		public Insets insets = getInsets();
		public JPanel feedPanel = new JPanel(new GridBagLayout());
		public JScrollPane feedScroll = new JScrollPane(feedPanel);
		
		public BufferedImage bbcImage;
		public BufferedImage cnnImage;
		public BufferedImage foxImage;
		public BufferedImage franklinImage;
		public BufferedImage jeffersonImage;
		public BufferedImage nbcImage;
		public BufferedImage nytimeImage;
		public BufferedImage washingtonImage;
		public BufferedImage coloniesImage;
		
		public JLabel bbcLabel;
		public JLabel cnnLabel;
		public JLabel foxLabel;
		public JLabel franklinLabel;
		public JLabel jeffersonLabel;
		public JLabel nbcLabel;
		public JLabel nytimeLabel;
		public JLabel washingtonLabel;
		public JLabel coloniesLabel;
		
		public long multiplier = 0;
		
		public boolean tweetsExist = false;
		public boolean distExists = false;
		public boolean done = false;
		
		public ArrayList<JLabel> tweets = new ArrayList<JLabel>();
		
		public JLabel distLabel;
		public boolean running = false;
		public Thread thread;
		long onTweet = 0;
		
		public ThirdPanel(){
			distLabel = new JLabel("Distributions:");
			distLabel.setFont(new Font("Arial", Font.PLAIN, 18));
			distLabel.setAlignmentY(SwingConstants.CENTER);
			start();
			setLayout(new BorderLayout());
			loadImages();
			content();
		}
		
		public synchronized void start(){
			running = true;
			thread = new Thread(this);
			thread.start();
		}
		
		public synchronized void stop(){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void content(){
			
			add(getFeed(), BorderLayout.EAST);
			add(coloniesLabel, BorderLayout.CENTER);
			add(distLabel, BorderLayout.SOUTH);
		}
		
		GridBagConstraints c = new GridBagConstraints();
		public JComponent getFeed(){
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.CENTER;
			feedScroll.setViewportView(feedPanel);
			feedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			feedPanel.add(tweets.get((int)onTweet), c);
			onTweet++;
			c.gridy++;
			
			return feedScroll;
		}
		
		
		public void drawComponent(Graphics g){
			
		}
		
		int time = 0;
		public void update(){
			time++;
			int secs = 5;
			if(!done){
				distributions3 += 1 + ((multiplier*distributions3)/3000) + (onTweet*2)/10000;
			}
			distLabel.setText("Distributions: " + distributions3);
			if(distExists){
				distLabel.repaint();
			}
			if(time%(120) == 0){
				multiplier++;
			}
			
			if(tweetsExist){
				if(onTweet < 8){
					if(time %(secs*60) == 0){
						feedPanel.add(tweets.get((int) onTweet), c);
						c.gridy++;
						onTweet++;
						feedPanel.repaint();
						feedPanel.revalidate();
					}
				}
			}
			System.out.println(onTweet);
			
			if(onTweet == 8){
				done = true;
			}
			
			if(done){
				frame.dispose();
				new Results();
				stop();
			}
		}
		
		public void loadImages() {
			try {
				bbcImage = ImageIO.read(ThirdMain.class.getResource("/Bbc.png"));
				cnnImage = ImageIO.read(ThirdMain.class.getResource("/Cnn.png"));
				foxImage = ImageIO.read(ThirdMain.class.getResource("/Fox.png"));
				nbcImage = ImageIO.read(ThirdMain.class.getResource("/NBC.png"));
				franklinImage = ImageIO.read(ThirdMain.class.getResource("/Franklin.png"));
				jeffersonImage = ImageIO.read(ThirdMain.class.getResource("/Jefferson.png"));
				washingtonImage = ImageIO.read(ThirdMain.class.getResource("/Washington.png"));
				nytimeImage = ImageIO.read(ThirdMain.class.getResource("/Nytimes.png"));
				coloniesImage = ImageIO.read(ThirdMain.class.getResource("/colonies.gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			bbcLabel = new JLabel(new ImageIcon(bbcImage));
			cnnLabel = new JLabel(new ImageIcon(cnnImage));
			foxLabel = new JLabel(new ImageIcon(foxImage));
			nbcLabel = new JLabel(new ImageIcon(nbcImage));
			franklinLabel = new JLabel(new ImageIcon(franklinImage));
			jeffersonLabel = new JLabel(new ImageIcon(jeffersonImage));
			washingtonLabel = new JLabel(new ImageIcon(washingtonImage));
			nytimeLabel = new JLabel(new ImageIcon(nytimeImage));
			coloniesLabel = new JLabel(new ImageIcon(coloniesImage));

			tweets.add(nbcLabel);
			tweets.add(cnnLabel);
			tweets.add(jeffersonLabel);
			tweets.add(foxLabel);
			tweets.add(bbcLabel);
			tweets.add(franklinLabel);
			tweets.add(washingtonLabel);
			tweets.add(nytimeLabel);
			
			tweetsExist = true;
		}

		long prev;
		@Override
		public void run() {
			prev = System.nanoTime();
			double delta = 0.0;
			final double limit = 1000000000.0 / 60.0;
			while(running){
				long now = System.nanoTime();
				delta += ((now-prev) / limit);
				prev = now;
				if(delta >= 1){
					update();
					delta--;
				}
			}
		}
	}

}
