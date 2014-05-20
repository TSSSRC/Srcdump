package net.util;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
public class KeyPad extends JPanel

{	
	
	public KeyPad(int xPos, int yPos, int width, int height)
	{
		this.setBounds(xPos, yPos, width, height);
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnOne = new JButton("1");
		JButton btnTwo = new JButton("2");	
		JButton btnThree = new JButton("3");
		JButton btnFour = new JButton("4");
		JButton btnFive = new JButton("5");
		JButton btnSix = new JButton("6");
		JButton btnSeven = new JButton("7");
		JButton btnEight = new JButton("8");
		JButton btnNine = new JButton("9");
		JButton btnZero = new JButton("0");
		
		//LHS
		btnOne.setBounds(5, 5, (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnThree.setBounds(5, (10+(this.getBounds().height-30)/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnFive.setBounds(5, (15+(this.getBounds().height-30)*2/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnSeven.setBounds(5, (20+(this.getBounds().height-30)*3/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnNine.setBounds(5, (25+(this.getBounds().height-30)*4/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		//RHS
		btnTwo.setBounds((10+(this.getBounds().width-15)/2), 5, (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnFour.setBounds((10+(this.getBounds().width-15)/2), (10+(this.getBounds().height-30)/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnSix.setBounds((10+(this.getBounds().width-15)/2), (15+(this.getBounds().height-30)*2/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnEight.setBounds((10+(this.getBounds().width-15)/2), (20+(this.getBounds().height-30)*3/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		btnZero.setBounds((10+(this.getBounds().width-15)/2), (25+(this.getBounds().height-30)*4/5), (this.getBounds().width-15)/2, (this.getBounds().height-30)/5);
		
		this.add(btnOne);
		this.add(btnTwo);
		this.add(btnThree);
		this.add(btnFour);
		this.add(btnFive);
		this.add(btnSix);
		this.add(btnSeven);
		this.add(btnEight);
		this.add(btnNine);
		this.add(btnZero);
		
		
		
	}	

}
	
