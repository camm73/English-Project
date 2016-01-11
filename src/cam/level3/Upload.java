package cam.level3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cam.Game;
import cam.level2.SecondMain;

public class Upload {
	
	public JFrame frame;
	
	public Upload(){
		frame = new JFrame();
		createFrame();
	}
	
	public void createFrame(){
		frame.setSize(500, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Game.title);
		frame.setLocationRelativeTo(null);
		frame.add(new UploadPanel());
		frame.setVisible(true);
	}
	
	class UploadPanel extends JPanel{
		public JLabel tweet;
		public BufferedImage tweetImage;
		public JButton uploadButton = new JButton("Upload and Tweet");
		public JLabel uploadedLabel = new JLabel("Uploaded and Tweeted!");
		
		public UploadPanel(){
			setLayout(new BorderLayout());
			setBackground(Color.white);
			loadImages();
			content();
		}
		
		public void content(){
			add(tweet, BorderLayout.CENTER);
			uploadedLabel.setFont(new Font("Arial", Font.PLAIN, 18));
			uploadedLabel.setVisible(false);
			add(uploadedLabel, BorderLayout.SOUTH);
			add(uploadButton, BorderLayout.EAST);
			uploadButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					uploadedLabel.setVisible(true);
					new ThirdMain();
					frame.dispose();
				}
			});
			
		}
		
		public void loadImages() {
			try {
				tweetImage = ImageIO.read(Upload.class.getResource("/tweetImage.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			tweet = new JLabel(new ImageIcon(tweetImage));

		}
	}

}
