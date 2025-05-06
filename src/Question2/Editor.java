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
		JFrame frame = new JFrame("Instruction Set Interpreter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 600));

		// set up the content pane
		addComponentsToGUI(frame.getContentPane());
		
		// display our panel 
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addComponentsToGUI(Container pane)
	{
		pane.setPreferredSize(new Dimension(1000, 600));
		pane.setBackground(Color.LIGHT_GRAY);

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
		JScrollPane scrollPane = new JScrollPane(codeSpace);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 5;  // leave space for the button
		c.fill = GridBagConstraints.BOTH;
		scrollPane.setBorder(BorderFactory.createTitledBorder("Code Input"));
		pane.add(scrollPane, c);


		// add a space to show results
		JTextArea outputArea = new JTextArea(25, 60);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		outputArea.setEditable(false);
		outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

		JScrollPane outputScrollPane = new JScrollPane(outputArea);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 6;
		c.fill = GridBagConstraints.BOTH;
		outputScrollPane.setBorder(BorderFactory.createTitledBorder("Execution Output"));
		pane.add(outputScrollPane, c);
		
		// add a button to execute code
		JButton executeButton = new JButton("Execute");
		executeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					String code = codeSpace.getText();
					String[] lines = code.split("\\n");
					StringBuilder output = new StringBuilder();

					for (String line : lines) {
						String result = cpu.executeAndReport(line.trim(), interp);
						output.append(result).append("\n\n");
					}

					String formatted = "Output:\n"
							+ output.toString()
							+ "\n\nCurrent Register State:\n"
							+ cpu.toString();

					outputArea.setText(formatted);
				} catch (Exception e) {
					outputArea.setText("Error: " + e.getMessage());
				}
			}
		});

		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(executeButton, c);
	}
}
