package Question2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Editor 
{
	private Interpreter interp;
	private Hardware cpu;
	
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	public Editor()
	{
		interp = new Interpreter();
		cpu = new Hardware();
				
		createAndShowGUI();		
	}
	
	private void createAndShowGUI()
	{
		// make the frame
		JFrame frame = new JFrame("This is a test because I don't remember how to GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up the content pane
		addComponentsToGUI(frame.getContentPane());
		
		// display our panel 
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addComponentsToGUI(Container pane)
	{
		if (RIGHT_TO_LEFT)
		{
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill)
		{
			c.fill = GridBagConstraints.HORIZONTAL; 
		}
		
		// add a space to write code
		JTextArea codeSpace = new JTextArea(10, 20);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(codeSpace, c);
		
		
		// add a space to show results
		JLabel registers = new JLabel("Current Register State: " + cpu.toString());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		pane.add(registers, c);
		
		// add a button to execute code
		JButton executeButton = new JButton("Execute");
		executeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					// TODO - execute whatever is in the codeSpace
					
					// TODO - set the text in label
				} catch (Exception e) {
					registers.setText("Error");
				}
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		pane.add(executeButton, c);
	}
}
