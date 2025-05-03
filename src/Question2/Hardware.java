package Question2;

import java.util.HashMap;

public class Hardware 
{
	private HashMap<String, Integer> registerMap;
	private static final String[] registerNames = {"r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8"};
	private int[] memory; // array representing random access memory/cache
	private int[] registers; // array representing the values in different registers
		
	private static final int REGISTER_SIZE = 32; // TODO - maybe just get rid of this
	private static final int MEMORY_SIZE = 1024;
	
	public Hardware()
	{
		registerMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < registerNames.length; i++)
		{
			registerMap.put(registerNames[i], i);
		}
		
		registers = new int[registerNames.length];
		memory = new int[MEMORY_SIZE];
	}
	
	
	/**
	 * Adds the contents of r2 and r3 and puts them in r1. 
	 * @param r1 The destination register
	 * @param r2 The first register being added
	 * @param r3 The second register being added
	 * @return 1 if successful, else 0
	 */
	private int addRegisters(int r1_idx, int r2_idx, int r3_idx)
	{
		try 
		{
			registers[r1_idx] = registers[r2_idx] + registers[r3_idx];
			return 1;
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	/**
	 * Subtracts the contents of r2 from r1.
	 * @param r1
	 * @param r2
	 * @return the content of r2 subtracted from the content of r1
	 */
	private int subRegisters(String r1, String r2)
	{
		int r1_num = registerMap.get(r1);
		int r2_num = registerMap.get(r2);
		
		return (registers[r1_num] - registers[r2_num]);
	}

	/**
	 * Shifts the contents of register r to the left one and then trims 
	 * the first digit if it would cause the number of bits in the result
	 * to exceed the max register size.
	 * 
	 * @param r the register begin modified
	 * @return the resultant integer
	 */
	private int shiftl(String r)
	{
		int num = registers[registerMap.get(r)];
		// TODO - add trim
		return (num << 1);
	}
	
	/**
	 * Shifts the contents of register r to the right one and then trims 
	 * the last digit if it would cause the number of bits in the result
	 * to exceed the max register size.
	 * 
	 * @param r the register begin modified
	 * @return the resultant integer
	 */
	private int shiftr(String r)
	{
		int num = registers[registerMap.get(r)];
		
		// TODO - add trim
		return (num >>> 1);
	}
	
	/** 
	 * Performs bitwise or betweent the contents of the registers and returns
	 * the results
	 * @param r1 the string name for the first register
	 * @param r2 the string name for the second register
	 * @return the result of the bitwise operation
	 */
	private int or(String r1, String r2)
	{
		int num1 = registers[registerMap.get(r1)];
		int num2 = registers[registerMap.get(r2)];
		
		return (num1 | num2);
	}
	
	private int and(String r1, String r2)
	{
		int num1 = registers[registerMap.get(r1)];
		int num2 = registers[registerMap.get(r2)];
		
		return (num1 & num2);
	}
	
	private int xor(String r1, String r2)
	{
		int num1 = registers[registerMap.get(r1)];
		int num2 = registers[registerMap.get(r2)];
		
		return (num1 ^ num2);
	}
	
	/**
	 * Interleaves the contents of the registers
	 * @param r1
	 * @param r2
	 * @return
	 */
	private int crazy(String r1, String r2)
	{
		String snum1 = Integer.toBinaryString(registers[registerMap.get(r1)]);
		String snum2 = Integer.toBinaryString(registers[registerMap.get(r2)]);
		
		// if one of the strings is shorter, left pad it with 0s
		if (snum1.length() < snum2.length())
		{
			// pad snum1
			while (snum1.length() < snum2.length())
			{
				snum1 = "0".concat(snum1);
			}
		} else {
			// pad snum2 until lengths match
			while (snum2.length() < snum1.length())
			{
				snum2 = "0".concat(snum2);
			}
		}
		
		String mergedString = "";
		
		for (int i = 0; i < snum1.length(); i++)
		{
			mergedString = mergedString.concat(snum1.substring(i,i+1) + snum2.substring(i,i+1));
		}
		
		int result = Integer.parseInt(mergedString, 2);
		
		return result;
	}
	
	private int store(String r, String location)
	{
		
		
		return 0;
	}
	
	public int executeInstruction(String[] instString)
	{
		// Check the command and execute appropriately
		String op = instString[0];
		int[] registerIndices;
		
		if (!op.equals("store") && !op.equals("load"))
		{
			registerIndices = new int[instString.length-1];
			for (int i = 1; i < instString.length; i++)
			{
				registerIndices[i-1] = registerMap.get(instString[i]);
			}
			
			// execute the op
			
		} else {
			registerIndices = new int[1];
			registerIndices[0] = registerMap.get(instString[1]);
			
			// execute the store or load
		}
		
		return 0;
	}
	
	public void testOr()
	{
		registers[0] = 4;
		registers[1] = 1;
		System.out.println("4 | 1 = " + this.or("r1", "r2"));
		
		registers[2] = 16;
		System.out.println("4 | 16 = " + this.or("r1", "r3"));
		
		System.out.println("? | ?? = " + this.or("r4", "r5"));
		
		System.out.println("Not an or:");
		registers[0] = 7;
		registers[1] = 0;
		System.out.println("7 `crazy` 0 = " + Integer.toBinaryString(this.crazy("r1", "r2")));
	}
}
