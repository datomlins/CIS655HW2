package Question2;

import java.util.HashMap;

public class Interpreter 
{

	private HashMap<String, String> opcodes;
	
	private static final int NORM_REG_SIZE = 4;
	private static final int NORM_OP_SIZE = 12;
	private static final int STORE_OP_SIZE = 14;
	
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
		opcodes.put("weave",  "1010");
	}
	
	/**
	 * Returns a bit representation of the input instruction.
	 * 
	 * The first 4 bits are the opcode. For most instructions, the next four bits
	 * index the destination register. The next 12 bits and the final twelve bits 
	 * encode the two operands whose result of the operation will be in the result
	 * register. The first bit is a 0 if the operand is a register, and a 1 if it
	 * is a literal. 
	 * 
	 * For the "load" and "store" instruction, there are some differences. The first
	 * four bits are still the opcode, but the remaining bits are "split" in half - 
	 * 14 to the first operand and 14 to the second. Like in the other instructions, 
	 * the first bit is reserved to flag whether the operand is a literal (1) or a 
	 * register (0)
	 * 	  
	 * @param assembly The given assembly representation of the instruction
	 * 
	 * @return The bit representation of the instruction; returns null if invalid
	 */
	public String interpret(String assembly)
	{
		String[] components = assembly.split("\\s+");
		StringBuilder result = new StringBuilder(32);
		
		String opBits = opcodes.get(components[0]);
		
		if (opBits == null)
		{
			return null;
		}
		else
		{
			result.append(opBits);
		}
		
		// if the operator is "store" or "load" there are only two operands
		if (components[0].equals("store") || components[0].equals("load"))
		{
			if (components.length < 3)
			{
				return null;
			}
			
			String op1 = parseOperand(components[1], STORE_OP_SIZE);
			String op2 = parseOperand(components[2], STORE_OP_SIZE);
			
			if (op1 == null || op2 == null)
			{
				return null;
			}
			
			result.append(op1);
			result.append(op2);			
		}
		else
		{
			if (components.length < 4)
			{
				return null;
			}
			String op1 = parseOperand(components[1], NORM_REG_SIZE);
			String op2 = parseOperand(components[2], NORM_OP_SIZE);
			String op3 = parseOperand(components[3], NORM_OP_SIZE);
			
			result.append(op1);
			result.append(op2);	
			result.append(op3);
			
		}
		
		return (result.toString());
	}
	
	/**
	 * Returns a binary representation of the provided string if possible
	 * with the correct length given by targetLength
	 * @param str
	 * @param targetLength
	 * @return
	 */
	private String parseOperand(String str, int targetLength)
	{
		int num; 
		boolean register = false;
		
		// if it starts with an r, then we try and parse it as a register
		if (str.substring(0, 1).equals("r"))
		{
			register = true;
			try
			{
				num = Integer.parseInt(str.substring(1));
			}
			catch (Exception e)
			{
				return null;
			}
		}
		else // otherwise we try to parse it as an integer
		{
			try
			{
				num = Integer.parseInt(str);
			}
			catch (Exception e)
			{
				return null;
			}
		}
		
		String binValue = Integer.toBinaryString(num).toString();
		
		StringBuilder result = padLeft(binValue, (targetLength-1));
		
		if (register)
		{
			result.insert(0, 0);
		}
		else
		{
			result.insert(0, 1);
		}
		
		return result.toString();
	}
	
	
	/**
	 * Pads the given string with 0s until the target size
	 * @param str
	 * @param targetSize
	 * @return
	 */
	private StringBuilder padLeft(String str, int targetSize)
	{
		StringBuilder result = new StringBuilder(targetSize+1);
		
		if (str.length() >= targetSize)
		{
			return new StringBuilder(str);
		}
		
		int reqZeros = targetSize - str.length();
		
		int zeroCount = 0;
		while (zeroCount < reqZeros)
		{
			result.append("0");
			zeroCount++;
		}
		
		result.append(str);
		
		return result;
	}
	
}
