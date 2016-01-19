package cam;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cam.level2.SecondMain;
import cam.level3.ThirdMain;

public class Results {

	public final int WIDTH = 540;
	public final int HEIGHT = 480;
	
	public JFrame frame;
	public JPanel panel;
	public JTextArea text = new JTextArea();
	public JScrollPane textScroll = new JScrollPane(text);
	public JButton next = new JButton("Back to Menu");
	
	
	public Results(){
		content();
		createFrame();
	}
	
	public void content(){
		panel = new JPanel(new BorderLayout());
		text.setEditable(false);
		text.setFont(new Font("Arial", Font.PLAIN, 18));
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text.setText("Congratulations! Your scores by level were: \n \n Level 1: " + Game.distributions1 + "\n Level 2: " + SecondMain.distributions2 + "\n Level 3: " + ThirdMain.distributions3 + "\n \n Please press the button below to return to the menu.");
		panel.add(textScroll, BorderLayout.CENTER);
		
		panel.add(next, BorderLayout.SOUTH);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				Menu menu = new Menu();
				Game.distributions1 = 0;
				SecondMain.distributions2 = 0;
				ThirdMain.distributions3 = 0;
			}
		});
	}
	
	public void createFrame(){
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setTitle("About");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.setVisible(true);
	}
}
