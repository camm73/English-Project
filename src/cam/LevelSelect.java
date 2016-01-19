package cam;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cam.level2.SecondMain;
import cam.level3.Upload;

public class LevelSelect {

	public final int WIDTH = 480;
	public final int HEIGHT = 320;

	public JFrame frame;
	public JPanel panel;
	public JPanel mainPanel;

	public JLabel instruct = new JLabel();
	public JButton level1 = new JButton("Level 1: Revolutionary Era");
	public JButton level2 = new JButton("Level 2: Era of the Telephone");
	public JButton level3 = new JButton("Level 3: Era of Modern Technology");
	public JButton back = new JButton("Return to Main Menu");

	public LevelSelect() {
		contentPanel();
		mainPanel();
		createFrame();
	}

	public void mainPanel() {
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(instruct, BorderLayout.NORTH);
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(back, BorderLayout.SOUTH);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu();
				frame.dispose();
			}

		});
	}

	public void contentPanel() {
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;

		panel.add(level1, c);
		level1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game();
				game.begin();
				frame.dispose();
			}
		});

		c.gridy++;

		panel.add(level2, c);
		level2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondMain main = new SecondMain();
				frame.dispose();
			}
		});

		c.gridy++;

		panel.add(level3, c);
		level3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upload up = new Upload();
				frame.dispose();
			}
		});

	}

	public void createFrame() {
		frame = new JFrame("Level Select");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
}
