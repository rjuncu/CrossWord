package project;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;

public class MakePuzzle {
	//arguments passed to constructor
	private ArrayList<String> list;
	private ArrayList<String> clues;
	private String solution;

	private JPanel innerPanel;
	private JFrame outerFrame;
	private JPanel displayPanelGrid;
	private JPanel displayPanelClues;
	private JPanel displayPanelBottom;

	private JTextPane sideClues;
	private BottomButtonListener wordChecker;
	private JButton button;
	private ArrayList<JButton> specialWord;

	public MakePuzzle(ArrayList<String> list, ArrayList<String> clues, String solution) {
		this.list = list;
		this.clues = clues;
		this.solution = solution;

		this.innerPanel = new JPanel();
		this.outerFrame = new JFrame("Swedish-style crossword puzzle");
		this.displayPanelGrid = new JPanel();
		this.displayPanelClues = new JPanel();
		this.displayPanelBottom = new JPanel();

		this.sideClues = new JTextPane();
		this.wordChecker = new BottomButtonListener();

		int temp = makeSquares();
		makeClues();
		makeBottom(temp);

		innerPanel.setLayout(new GridBagLayout());
		GridBagConstraints master = new GridBagConstraints();
		master.fill = GridBagConstraints.BOTH;
		master.gridy = 0;
		master.gridx = 0;
		innerPanel.add(this.displayPanelGrid, master);
		master.gridy = 1;
		master.insets = new Insets(5,0,0,0);
		innerPanel.add(this.displayPanelBottom, master);

		outerFrame.add(this.displayPanelClues, BorderLayout.LINE_START);
		outerFrame.add(innerPanel, BorderLayout.LINE_END);
		outerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		outerFrame.pack();
		outerFrame.setVisible(true);
	}

	public int makeSquares() {
		int i = 0;
		int numSpecialSquare = 0;
		this.displayPanelGrid.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		for(String row : this.list) {
			int j = 0;
			String[] noSpace = row.split(" ");

			for(int k = 0; k<noSpace.length; k++) {
				switch(noSpace[k].charAt(0)) {
				case 'O':
					WhiteSquare white = new WhiteSquare();
					this.button = white.getButton();
					break;
				case 'X': 
					BlackSquare black = new BlackSquare();
					this.button = black.getButton();
					break;
				case 'S':
					SpecialSquare special = new SpecialSquare();
					this.button = special.getButton();
					numSpecialSquare ++;
					break;
				case 'H':
					HelperSquare help = new HelperSquare();
					if (noSpace[k].charAt(2)=='H') {
						help.setHint(Hint.H);
					}else if(noSpace[k].charAt(2)=='C') {
						help.setHint(Hint.C);
					}else if(noSpace[k].charAt(2)=='Y') {
						help.setHint(Hint.Y);
					}
					this.button = help.getButton();
					break;
				}
				c.gridx = j;
				c.gridy = i; 
				this.displayPanelGrid.add(button, c);
				j++;
			}
			i++;
		}
		return numSpecialSquare;
	}

	public void makeClues() {
		Pattern pattern1 = Pattern.compile("[A-Z]{3,}");
		Pattern pattern2 = Pattern.compile("\\d");
		Matcher matcher1;
		Matcher matcher2;

		for(int line = 0; line<this.clues.size(); line++) {			
			if(this.clues.get(line) != null) {
				matcher1 = pattern1.matcher(this.clues.get(line));
				matcher2 = pattern2.matcher(this.clues.get(line));
				boolean allCaps = matcher1.find();
				boolean numbers = matcher2.find();
				
				if(allCaps) {
					addToPane(this.sideClues, this.clues.get(line), Color.MAGENTA, true);
				}else {
					if(numbers) {
						String beginning = ""+this.clues.get(line).charAt(0)+this.clues.get(line).charAt(1);
						String end = this.clues.get(line).replaceFirst("([0-9]*\\.)", "");
						addToPane(this.sideClues, beginning, Color.MAGENTA, false);
						addToPane(this.sideClues, end, Color.BLACK, true);
					}
				}
			}
		}
		this.sideClues.setEditable(false);
		this.displayPanelClues.add(this.sideClues);
	}
	
	//addToPane method is credited to https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
	public void addToPane(JTextPane tp, String msg, Color c, boolean newLineFlag) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.Bold, true);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		if(newLineFlag) {
			tp.replaceSelection(msg+"\n");
		}else {
			tp.replaceSelection((msg));
		}
	}


	public void makeBottom(int numSpecialSquare) {
		this.displayPanelBottom.setLayout(new GridBagLayout());
		GridBagConstraints bottom = new GridBagConstraints();
		bottom.fill = GridBagConstraints.BOTH;
		this.specialWord = new ArrayList();

		for (int k = 0; k < numSpecialSquare; k++) {
			SpecialSquare special = new SpecialSquare();
			this.button = special.getButton();
			bottom.gridy = 1;
			bottom.gridx = k;
			this.displayPanelBottom.add(this.button, bottom);
			this.specialWord.add(this.button);
		}
		this.button = new JButton("Check");
		//this.button.setPreferredSize(new Dimension(100,20));
		this.button.addActionListener(this.wordChecker);
		bottom.gridy = 1;
		bottom.gridx = numSpecialSquare;
		bottom.insets = new Insets(10,5,10,5);
		this.displayPanelBottom.add(this.button, bottom);
	}

	class BottomButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String answer = "";
			for(int i = 0; i<specialWord.size(); i++) {
				answer = answer + (specialWord.get(i).getText());
			}
			if(answer.equals(solution)) {
				new Celebration();
			}


		}

	}
}

