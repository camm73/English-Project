package cam;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {

	public static int width = 500;
	public static int height = 420;

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JLabel title = new JLabel("Travel to Corinth");
	private JLabel instruct = new JLabel("You will travel back in time to Corinth and ask the Corinthians");
	private JLabel instruct2 = new JLabel("about the letters they received from Paul");
	private JButton play = new JButton("Play");

	public Menu() {
		frame = new JFrame();
		createFrame();
		content();
	}

	public void createFrame() {
		frame.setTitle(Game.title);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void content() {
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1.0;

		title.setForeground(Color.BLUE);
		title.setFont(new Font("Stencil", Font.PLAIN, 42));
		panel.add(title, c);

		c.gridy++;

		instruct.setForeground(Color.BLACK);
		instruct.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(instruct, c);
		c.weighty = 0;
		c.gridy++;
		instruct2.setForeground(Color.BLACK);
		instruct2.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(instruct2, c);
		c.weighty = 1.0;

		c.gridy++;

		panel.add(play, c);

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game game = new Game();
				game.begin();
				frame.dispose();
			}
		});

	}

}
