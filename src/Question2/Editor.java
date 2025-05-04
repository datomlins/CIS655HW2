package Question2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Editor 
{
	private Interpreter interp;
	private Hardware cpu;
	
	JFrame frame;
	
	public Editor()
	{
		interp = new Interpreter();
		cpu = new Hardware();
		
		// make the frame
		frame = new JFrame("This is a test because I don't remember how to GUI");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// add a space to write code
		JTextField codeSpace = new JTextField();
		
		// add a space to show results
		JLabel label = new JLabel("Current Register State: " + cpu.toString());
		
		// add a button to execute code
		JButton executeButton = new JButton("Execute");
		
		frame.add(codeSpace);
		frame.add(label);
		frame.add(executeButton);
		
		// display our panel 
		frame.setVisible(true);
	}
}
