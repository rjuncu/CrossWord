package project;

import java.awt.*;
import javax.swing.*;

public abstract class Square {
	protected JButton button = new JButton(" ");
    Hint hint;
    public Square() {
    	this.button.setPreferredSize(new Dimension(45,45));
    	this.button.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public String getHint() {
    	if(hint!=null) {
    		String hintContents = hint.toString();
            return hintContents;
    	}else {
    		return null;
    	}
    }

    public void setHint(Hint hint) {
    	this.hint = hint;
    }	
	
	public JButton getButton() {
		return this.button;
	}
	
	public void setButton(JButton button) {
		this.button = button;
	}
	
}
