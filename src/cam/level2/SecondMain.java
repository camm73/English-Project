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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cam.Game;

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
	public Insets insets;

	public boolean showDesk = true;
	public boolean showBook = false;
	public boolean showCalling = false;
	public boolean makingHashmap = true;

	public JButton phoneButton = new JButton("Make a call");
	public HashMap<String, JLabel> nameLabelLeft = new HashMap<String, JLabel>();
	public HashMap<String, JButton> callButtonsLeft = new HashMap<String, JButton>();

	public JPanel leftPage = new JPanel(new GridBagLayout());
	public JPanel callingPage = new JPanel(new GridBagLayout());
	public JPanel mainPanel;

	public SecondMain() {
		frame = new JFrame();
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

	public void loadHashmaps() {
		nameLabelLeft.put("0", new JLabel("Phillip Abel"));
		nameLabelLeft.put("1", new JLabel("James Edmund"));
		nameLabelLeft.put("2", new JLabel("Dave Mann"));
		nameLabelLeft.put("3", new JLabel("Braxton Westerberg"));
		nameLabelLeft.put("4", new JLabel("Joffrey Sudworth"));
		nameLabelLeft.put("5", new JLabel("Nikolas Schmitt"));
		nameLabelLeft.put("6", new JLabel("Alfred Montagne"));

		for (int i = 0; i < nameLabelLeft.size(); i++) {
			JLabel label = nameLabelLeft.get(String.valueOf(i));
			label.setForeground(Color.black);
			label.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
		}
		
		for(int i = 0; i < nameLabelLeft.size(); i++){
			callButtonsLeft.put(String.valueOf(i), new JButton("Call"));
		}
		
		for(int i = 0; i < callButtonsLeft.size(); i++){
			JButton btn = callButtonsLeft.get(String.valueOf(i));
			btn.setForeground(new Color(0x228B22));
			btn.setOpaque(false);
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					showBook = false;
					showCalling = true;
					leftPage.setVisible(false);
					callingPage.setVisible(true);
					mainPanel.repaint();
				}
			});
		}
		
		makingHashmap = false;
	}

	class MainPanel extends JPanel {
		private static final long serialVersionUID = -3841288405434640751L;

		public MainPanel() {
			setLayout(null);
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
			
			Rectangle callingBound = new Rectangle(100, 60, 500, 300);
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
		}
		
		public void getCallingPage(){
			//TODO add name, call time, back button to this panel
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
