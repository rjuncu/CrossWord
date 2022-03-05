package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

public class Keyboard {
	private JFrame frame;
	private JPanel keyPanel;
	private KeyboardButtonListener listen;

	private java.util.List<String> master = Arrays.asList("A", "Z", "E", "R", "T", "Y", "U", "I", "O", "P", "Q", "S", "D", "F", "G", "H", "J", "K", "L", "M", "W", "X", "C", "V", "B", "N");
	private JButton button;
	private Square square;

	public Keyboard(String title, JButton button, Square square) {
		this.frame = new JFrame(title);
		this.button = button;
		this.square = square;
		this.keyPanel = new JPanel();
		this.keyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		this.listen = new KeyboardButtonListener();
		ArrayList<JButton>buttonList = new ArrayList<JButton>();

		int alphabet = 0;

		for(int counter = 0; counter<2; counter++) {
			for(int vertCounter = 0; vertCounter<10; vertCounter++) {
				JButton key = new JButton(master.get(alphabet));
				buttonList.add(key);
				alphabet ++; 
				key.addActionListener(listen);
				c.gridx = vertCounter;
				c.gridy = counter;
				this.keyPanel.add(key,c);
			}
		}
		int counter = 3;
		for(int vertCounter = 2; vertCounter<8; vertCounter++) {
			JButton key = new JButton(master.get(alphabet));
			buttonList.add(key);
			alphabet ++;
			key.addActionListener(listen);
			c.gridx = vertCounter;
			c.gridy = counter;
			this.keyPanel.add(key, c);
		}

		if(square.getHint()!=null) {
			int visibleKeys[] = new int[5];
			visibleKeys = getRandomInts(visibleKeys, master.indexOf(square.getHint()));

			for(int i = 0; i<buttonList.size(); i++) {
				buttonList.get(i).setBackground(Color.GRAY);
				buttonList.get(i).removeActionListener(listen);
				for(int j = 0; j<visibleKeys.length; j++) {
					if(master.get(visibleKeys[j]).contains(buttonList.get(i).getText())) {
						buttonList.get(i).setBackground(Color.WHITE);
						buttonList.get(i).addActionListener(listen);
					}
				}
			}
		}



		frame.add(this.keyPanel, BorderLayout.PAGE_START);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}


	public int[] getRandomInts(int[] randIntArray, int end) {
		ArrayList<Integer> alphabetList = new ArrayList<Integer>();
		for(int i = 0; i<25; i++) {
			if(i != end) {
				alphabetList.add(i);
			}
		}

		Collections.shuffle(alphabetList);

		for(int i = 0; i<(randIntArray.length-1); i++) {
			randIntArray[i]=alphabetList.get(i);
		}
		randIntArray[randIntArray.length-1]=end;
		return randIntArray;
	}

	class KeyboardButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = ((JButton)e.getSource()).getText();
			button.setText(action);
			frame.dispose();
		}
	}

}



