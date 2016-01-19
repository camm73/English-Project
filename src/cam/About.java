package cam;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class About {
	
	public final int WIDTH = 540;
	public final int HEIGHT = 480;
	
	public JFrame frame;
	public JPanel panel;
	public JTextArea text = new JTextArea();
	public JScrollPane textScroll = new JScrollPane(text);
	public JButton next = new JButton("Next");
	
	public About(){
		content();
		createFrame();
	}
	
	public void content(){
		panel = new JPanel(new BorderLayout());
		text.setEditable(false);
		text.setFont(new Font("Arial", Font.PLAIN, 18));
		text.setText("Welcome to Time Crisis! \n \nSummary: \nYou will play as Thomas Paine as he travels through different technological eras and distributes 'The Crisis'. You will start out in the Revolutionary Era leading up to the war by handing out 'The Crisis' pamphlets. After that you will skip ahead to the era of the telephone where you will call fellow colonists about 'The Crisis'. Finally you will be put in the modern era where you will tweet about 'The Crisis' and watch it go viral. \n \n Controls: \n Level 1: "
				+ "\n W:	Up \n S:	Down \n A:	Left \n D:	Right \n X:	Interact \n \n Level 2: \n Click the 'Make Call' Button. Then click 'Call' next to one of the names and listen to the conversation. Once the call ends the 'Back' button will be enabled again. Click that button, then call someone else. \n \n Level 3: \n Click the 'Upload and Tweet' Button. Then watch as the tweet goes viral. You can scroll down the feed of popular tweets on the right side.");
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(textScroll, BorderLayout.CENTER);
		text.setCaretPosition(0); 
		
		panel.add(next, BorderLayout.SOUTH);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				Menu menu = new Menu();
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
