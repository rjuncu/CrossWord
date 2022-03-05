package project;

import java.awt.*;

public class SpecialSquare extends Square{

	private ButtonListener listen = new ButtonListener(this);
	
	public SpecialSquare() {
	
		this.button.addActionListener(listen);
		this.button.setBackground(Color.GRAY);
	}	

}


