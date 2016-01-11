package cam.level2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cam.Game;
import cam.Sound;
import cam.level3.ThirdMain;

public class SecondMain {

	public final int WIDTH = Game.WIDTH * Game.SCALE;
	public final int HEIGHT = Game.HEIGHT * Game.SCALE;
	public JFrame frame;

	public BufferedImage openBookImage;
	public BufferedImage oldDeskImage;
	public BufferedImage callBackgroundImage;
	public JLabel openBookLabel;
	public JLabel oldDeskLabel;
	public JLabel callBackgroundLabel;
	public JLabel callerNameLabel = new JLabel();
	public JLabel distLabel = new JLabel();
	public Insets insets;

	public boolean showDesk = true;
	public boolean showBook = false;
	public boolean showCalling = false;
	public boolean makingHashmap = true;
	
	public JButton phoneButton = new JButton("Make a call");
	public JButton backButton = new JButton("Back");
	public JButton nextLevel = new JButton("Next Level");
	public HashMap<String, JLabel> nameLabelLeft = new HashMap<String, JLabel>();
	public HashMap<String, JButton> callButtonsLeft = new HashMap<String, JButton>();
	public HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	public ArrayList<Sound> playingSounds = new ArrayList<Sound>();

	public JPanel leftPage = new JPanel(new GridBagLayout());
	public JPanel callingPage = new JPanel(new GridBagLayout());
	public JPanel mainPanel;
	
	public int distributions = 0;
	public int multiplier = 0;
	public int callsMade = 0;
	
	public Thread thread;
	public boolean running = false;
	public boolean soundsExist = false;

	public SecondMain() {
		frame = new JFrame();
		start();
		loadImages();
		loadHashmaps();

		while (makingHashmap) {
			System.out.println("Busy filling name map");
		}
		mainPanel = new MainPanel();
		createFrame();
	}

	public void createFrame() {
		frame.setTitle(Game.title);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	
	double delta = 0;
	long prev;
	public synchronized void start(){
		running = true;
		prev = System.nanoTime();
		final double limit = 1000000000.0 / 60.0;
		thread = new Thread(new Runnable(){
			public void run(){
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
		});
		thread.start();
	}
	
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getLeftPage() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.WEST;
		
		for (int i = 0; i < nameLabelLeft.size(); i++) {
			leftPage.add(nameLabelLeft.get(String.valueOf(i)), c);
			c.gridy++;
		}

		c.gridx++;
		c.gridy =0;
		c.anchor = GridBagConstraints.EAST;
		for (int i = 0; i < callButtonsLeft.size(); i++) {
			leftPage.add(callButtonsLeft.get(String.valueOf(i)), c);
			c.gridy++;
		}

	}

	public void loadImages() {
		try {
			openBookImage = ImageIO.read(SecondMain.class.getResource("/openBookBackground.png"));
			oldDeskImage = ImageIO.read(SecondMain.class.getResource("/oldDeskImage.png"));
			callBackgroundImage = ImageIO.read(SecondMain.class.getResource("/callBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		openBookLabel = new JLabel(new ImageIcon(openBookImage));
		oldDeskLabel = new JLabel(new ImageIcon(oldDeskImage));
		callBackgroundLabel = new JLabel(new ImageIcon(callBackgroundImage));

	}
	
	double time = 0;
	public void update(){
		time++;
		int secs = 11;
		if(callsMade < 8){
			if((time % (secs*60)) == 0){
				distributions += callsMade + (multiplier*distributions)/3;
			}
		}
		
		distLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		distLabel.setText("Distributions: "+ distributions + "             ");
		distLabel.repaint();
		
		if(soundsExist){
			for(int i = 0; i < sounds.size(); i++){
				if(sounds.get(String.valueOf(i)).playing){
					if(!playingSounds.contains(sounds.get(String.valueOf(i)))){
						playingSounds.add(sounds.get(String.valueOf(i)));
					}
				}else{
					if(playingSounds.contains(sounds.get(String.valueOf(i)))){
						playingSounds.remove(sounds.get(String.valueOf(i)));
					}
				}
			}
			
			if(playingSounds.size() > 0){
				backButton.setEnabled(false);
			}else{
				backButton.setEnabled(true);
			}
		}
		
		if(callsMade == 8){
			nextLevel.setVisible(true);
		}
	
	}

	public void loadHashmaps() {
		nameLabelLeft.put("0", new JLabel("Phillip Abel"));
		nameLabelLeft.put("1", new JLabel("James Edmund"));
		nameLabelLeft.put("2", new JLabel("Dave Mann"));
		nameLabelLeft.put("3", new JLabel("Braxton Westerberg"));
		nameLabelLeft.put("4", new JLabel("Joffrey Sudworth"));
		nameLabelLeft.put("5", new JLabel("Nikolas Schmitt"));
		nameLabelLeft.put("6", new JLabel("Alfred Montagne"));
		nameLabelLeft.put("7", new JLabel("Kyle Wittenberg"));

		for (int i = 0; i < nameLabelLeft.size(); i++) {
			JLabel label = nameLabelLeft.get(String.valueOf(i));
			label.setForeground(Color.black);
			label.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
		}
		
		for(int i = 0; i < nameLabelLeft.size(); i++){
			callButtonsLeft.put(String.valueOf(i), new JButton("Call"));
		}
		
		for(int i = 0;i < 8; i++){
			sounds.put(String.valueOf(i), new Sound("/convo" + String.valueOf(i+1) + ".wav"));
		}
		soundsExist = true;
		for(int i = 0; i < callButtonsLeft.size(); i++){
			JButton btn = callButtonsLeft.get(String.valueOf(i));
			btn.setForeground(new Color(0x228B22));
			btn.setOpaque(false);
			int tmp = i;
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					showBook = false;
					showCalling = true;
					callerNameLabel.setText("Speaking with: " + nameLabelLeft.get(String.valueOf(tmp)).getText());
					sounds.get(String.valueOf(tmp)).play();
					sounds.get(String.valueOf(tmp)).playing = true;
					distLabel.setForeground(Color.black);
					btn.setEnabled(false);
					callingPage.repaint();
					callingPage.revalidate();
					leftPage.setVisible(false);
					callingPage.setVisible(true);
					mainPanel.repaint();
					backButton.setEnabled(false);
				}
			});
		}
		
		makingHashmap = false;
	}

	class MainPanel extends JPanel {
		private static final long serialVersionUID = -3841288405434640751L;

		public MainPanel() {
			setLayout(null);
			callerNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
			callerNameLabel.setForeground(Color.black);
			content();
		}

		public void content() {
			insets = getInsets();

			Rectangle leftBound = new Rectangle(110, 60, 320, 470);
			leftPage.setBounds(leftBound);
			leftPage.setVisible(false);
			leftPage.setOpaque(false);
			getLeftPage();
			add(leftPage);
			
			Rectangle callingBound = new Rectangle(194, 60, 500, 300);
			callingPage.setBounds(callingBound);
			callingPage.setVisible(false);
			callingPage.setOpaque(false);
			getCallingPage();
			add(callingPage);

			Dimension phoneButtonSize = phoneButton.getPreferredSize();
			phoneButton.setBounds(insets.left + 30, insets.top + 400, phoneButtonSize.width, phoneButtonSize.height);
			phoneButton.setForeground(Color.RED);
			phoneButton.setBackground(Color.black);
			add(phoneButton);
			phoneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showDesk = false;
					showBook = true;
					phoneButton.setVisible(false);
					leftPage.setVisible(true);
					repaint();
				}
			});
			
			Dimension distSize = new Dimension(600, 100);
			distLabel.setBounds(insets.left + 600, insets.top + 15, distSize.width, distSize.height);
			distLabel.setForeground(Color.white);
			add(distLabel);
			
			
			Dimension nextSize = nextLevel.getPreferredSize();
			nextLevel.setBounds(insets.left + 400, insets.top + 300, nextSize.width, nextSize.height);
			nextLevel.setForeground(Color.RED);
			nextLevel.setVisible(false);
			nextLevel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ThirdMain tm = new ThirdMain();
					frame.dispose();
				}
			});
			
		}
		
		public void getCallingPage(){
			//TODO add name, call time, back button to this panel
			Insets callingInsets = callingPage.getInsets();
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.weighty = 1.0;
			
			Dimension backSize = backButton.getPreferredSize();
			backButton.setBounds(callingInsets.left, callingInsets.top, backSize.width, backSize.height);
			backButton.setForeground(Color.WHITE);
			callingPage.add(backButton, c);
			backButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					distributions++;
					callsMade++;
					multiplier++;
					time = 0;
					System.out.println("distributions: " + distributions + " callsMade: " + callsMade + " multiplier: " + multiplier);
					showCalling = false;
					showBook = true;
					distLabel.setForeground(Color.white);
					leftPage.setVisible(true);
					callingPage.setVisible(false);
					mainPanel.repaint();
					mainPanel.revalidate();
				}
			});
			
			c.gridy++;
			c.anchor = GridBagConstraints.NORTH;
			callingPage.add(callerNameLabel, c);
		}

		public void paintComponent(Graphics g) {
			if (showDesk) {
				g.drawImage(oldDeskImage, 0, 0, null);
			} else if (showBook){
				g.drawImage(openBookImage, 0, 0, null);
			}else if(showCalling){
				g.drawImage(callBackgroundImage, 0, 0, null);
			}
		}
	}

}
