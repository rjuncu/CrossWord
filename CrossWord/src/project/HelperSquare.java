package project;

import java.awt.*;

public class HelperSquare extends Square {
	
	private ButtonListener listen = new ButtonListener(this);
	
	public HelperSquare() {
		super();
		this.button.addActionListener(listen);
		Color customColor = new Color(173, 216, 230);
		this.button.setBackground(customColor);
	}

}
