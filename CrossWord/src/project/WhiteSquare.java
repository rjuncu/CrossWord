package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WhiteSquare extends Square{

	private ButtonListener listen = new ButtonListener(this);

	public WhiteSquare() {
		super();
		this.button.addActionListener(listen);
		this.button.setBackground(Color.WHITE);
	}
}


