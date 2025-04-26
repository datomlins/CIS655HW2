package Question2;

import java.util.HashMap;

public class Interpreter 
{

	private HashMap<String, String> opcodes;
		
	public Interpreter()
	{
		// initialize opcodes
		opcodes = new HashMap<String, String>();
		
		opcodes.put("load",   "0001");
		opcodes.put("store",  "0010");
		opcodes.put("add",    "0011");
		opcodes.put("sub",    "0100");
		opcodes.put("shiftl", "0101");
		opcodes.put("shiftr", "0110");
		opcodes.put("or",     "0111");
		opcodes.put("and",    "1000");
		opcodes.put("xor",    "1001");
		opcodes.put("crazy",  "1010");
	}
	
	/**
	 * Returns a bit representation of the input instruction.
	 * 
	 * @param assembly The given assembly representation of the instruction
	 * 
	 * @return The bit representation of the instruction
	 */
	public String interpret(String assembly)
	{
		return ("TODO");
	}
	
	
	
	
}
