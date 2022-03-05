package project;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class Celebration {
	private JFrame frame;
	private JPanel panel;
	
	public Celebration() {
		this.frame = new JFrame();
		this.panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 300));
		
		JTextArea congrats = new JTextArea();
		String message = "..... (¯`v´¯)♥"+"\n"+".......•.¸.•´"+"\n"+"....¸.•´"+"\n"+"... ("+"\n"+"☻/"+"\n"+"/|♥♥"+"\n"+"/ \\ ♥♥"+"\n";
		congrats.setText(message);
		congrats.append("Good job!");
		congrats.setFont(new Font("Arial Black", Font.BOLD, 20));
		congrats.setEditable(false);
		panel.add(congrats);
		frame.add(panel);
		frame.dispose();
		frame.pack();
		frame.setVisible(true);
	}
	
}
