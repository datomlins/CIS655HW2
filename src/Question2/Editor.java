package Question2;

import javax.swing.JFrame;

public class Editor 
{
	private Interpreter interp;
	private Hardware cpu;
	
	JFrame frame;
	
	public Editor()
	{
		interp = new Interpreter();
		cpu = new Hardware();
		
		frame = new JFrame("This is a test because I don't remember how to GUI");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
