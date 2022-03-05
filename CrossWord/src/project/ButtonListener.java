package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener {
	private Square square;
	ButtonListener(Square square){
		this.square = square; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		new Keyboard("Enter A Character", button, square);
	}
}

