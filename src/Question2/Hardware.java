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
	
	class Operand
	{
		private int value; 
		private int type; // 0 for register, 1 for literal (i.e. an int)
		
		/**
		 * Constructs an operand for use in parsing
		 * @param val The value of the operand - a literal or the register index
		 * @param type The type of the operand - 0 for a register index; 1 for a literal
		 */
		public Operand(int val, int type)
		{
			if (type != 0 && type != 1)
			{
				throw new IllegalArgumentException();
			}
			
			this.value = val;
			this.type = type;
		}
		
		/**
		 * Returns the value of "value"
		 * @return The value of the operand - an index if a register index; else a literal
		 */
		public int getValue()
		{
			return value;
		}
		
		/**
		 * Returns the type of the operand
		 * @return 0 if a register index, otherwise a 1 if a literal
		 */
		public int getType()
		{
			return type; 
		}
	}
	
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
	private int add(Operand op1, Operand op2, Operand op3)
	{
		try 
		{
			int destRegisterIdx = op1.getValue();
			int op2Val, op3Val;
			
			if (op2.getType() == 0)
			{
				op2Val = registers[op2.getValue()];
			}
			else 
			{
				op2Val = op2.getValue();
			}
			
			if (op3.getType() == 0)
			{
				op3Val = registers[op3.getValue()];
			}
			else
			{
				op3Val = op3.getValue();
			}
			
			registers[destRegisterIdx] = op2Val + op3Val;
			
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
	private int sub(Operand op1, Operand op2, Operand op3)
	{
		try 
		{
			int destRegisterIdx = op1.getValue();
			int op2Val, op3Val;
			
			if (op2.getType() == 0)
			{
				op2Val = registers[op2.getValue()];
			}
			else 
			{
				op2Val = op2.getValue();
			}
			
			if (op3.getType() == 0)
			{
				op3Val = registers[op3.getValue()];
			}
			else
			{
				op3Val = op3.getValue();
			}
			
			registers[destRegisterIdx] = op2Val - op3Val;
			
			return 1;
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	/**
	 * Shifts the contents of register r to the left one and then trims 
	 * the first digit if it would cause the number of bits in the result
	 * to exceed the max register size.
	 * 
	 * @param r the register begin modified
	 * @return the resultant integer
	 */
	private int shiftl(Operand op1, Operand op2)
	{
		int resIdx = op1.getValue();
		
		if (op2.getType() == 0)
		{
			registers[resIdx] = registers[op2.getValue()] << 1;
			
			return 1;
		}
		else
		{
			registers[resIdx] = op2.getValue() << 1;
			
			return 1;
		}
	}
	
	/**
	 * Shifts the contents of register r to the right one and then trims 
	 * the last digit if it would cause the number of bits in the result
	 * to exceed the max register size.
	 * 
	 * @param r the register begin modified
	 * @return the resultant integer
	 */
	private int shiftr(Operand op1, Operand op2)
	{
		int resIdx = op1.getValue();
		
		if (op2.getType() == 0)
		{
			registers[resIdx] = registers[op2.getValue()] >>> 1;
			
			return 1;
		}
		else
		{
			registers[resIdx] = op2.getValue() >>> 1;
			
			return 1;
		}
	}
	
	/** 
	 * Performs bitwise or between the contents of the registers and returns
	 * the results
	 * @param r1 the string name for the first register
	 * @param r2 the string name for the second register
	 * @return the result of the bitwise operation
	 */
	private int or(Operand op1, Operand op2, Operand op3)
	{
		int resIdx = op1.getValue();
		
		int num1, num2;
		
		if (op2.getType() == 0)
		{
			num1 = registers[op2.getValue()];
		}
		else
		{
			num1 = op2.getValue();
		}
		
		if (op3.getType() == 0)
		{
			num2 = registers[op3.getValue()];
		}
		else
		{
			num2 = op3.getValue();
		}
		
		registers[resIdx] = (num1 | num2);
		
		return 1;
	}
	
	private int and(Operand op1, Operand op2, Operand op3)
	{
		int resIdx = op1.getValue();
		
		int num1, num2;
		
		if (op2.getType() == 0)
		{
			num1 = registers[op2.getValue()];
		}
		else
		{
			num1 = op2.getValue();
		}
		
		if (op3.getType() == 0)
		{
			num2 = registers[op3.getValue()];
		}
		else
		{
			num2 = op3.getValue();
		}
		
		registers[resIdx] = (num1 & num2);
		
		return 1;
	}
	
	private int xor(Operand op1, Operand op2, Operand op3)
	{
		int resIdx = op1.getValue();
		
		int num1, num2;
		
		if (op2.getType() == 0)
		{
			num1 = registers[op2.getValue()];
		}
		else
		{
			num1 = op2.getValue();
		}
		
		if (op3.getType() == 0)
		{
			num2 = registers[op3.getValue()];
		}
		else
		{
			num2 = op3.getValue();
		}
		
		registers[resIdx] = (num1 ^ num2);
		
		return 1;
	}
	
	/**
	 * Interleaves the contents of the registers
	 * @param r1
	 * @param r2
	 * @return
	 */
	private int crazy(Operand op1, Operand op2, Operand op3)
	{
		int resIdx = op1.getValue();
		String snum1, snum2;
		
		if (op2.getType() == 0)
		{
			snum1 = Integer.toBinaryString(registers[op2.getValue()]);
		}
		else
		{
			snum1 = Integer.toBinaryString(op2.getValue());
		}
		
		if (op3.getType() == 0)
		{
			snum2 = Integer.toBinaryString(registers[op3.getValue()]);
		}
		else
		{
			snum2 = Integer.toBinaryString(op3.getValue());
		}
				
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
		
		registers[resIdx] = Integer.parseInt(mergedString, 2);
		
		return 1;
	}
	
	/**
	 * Stores the contents of op2 at the memory location indicated by op1
	 * @param op1
	 * @param op2
	 * @return
	 */
	private int store(Operand op1, Operand op2)
	{
		// TODO - error checking to prevent exceptions
		
		if (op1.getType() == 0)
		{
			memory[registers[op1.getValue()]] = registers[op2.getValue()];
			return 1;
		}
		else
		{
			memory[registers[op1.getValue()]] = op2.getValue();
			return 1;
		}		
	}
	
	/**
	 * Takes two operands as input; loads the value represented by op2 into op1
	 * @param op1
	 * @param op2
	 * @return
	 */
	private int load(Operand op1, Operand op2)
	{
		if (op1.getType() != 0)
		{
			return 0;
		}
		
		if (op2.getType() == 0)
		{
			registers[op1.getValue()] = memory[registers[op2.getValue()]];
			return 1;
		}
		else
		{
			registers[op1.getValue()] = memory[op2.getValue()];
			return 1;
		}
	}
	
	/**
	 * Takes an array of strings as input. If the string sequence makes a valid instruction,
	 * executes the instruction and returns 1; else returns 0
	 * @param instString The array of strings representing the instruction
	 * @return 0 if fail, 1 if successful
	 */
	public int executeInstruction(String[] instString)
	{
		// Check the command and execute appropriately
		String operation = instString[0];
		Operand op1, op2, op3;
		
		op1 = parseOperand(instString[1]);
		op2 = parseOperand(instString[2]);
		
		// if op1 or op2 are null, then the instruction is not valid
		if (op1 == null || op2 == null)
		{
			return 0;
		}
		
		// store and load have fewer arguments, so we handle them first
		if (operation.equals("load"))
		{
			// if op1 is not a register, then the load is bad
			if (op1.type != 0)
			{
				return 0;
			}
			
			return this.load(op1, op2);
		}
		else if (operation.equals("store"))
		{
			// if op2 is not a register, then we're storing from nowhere
			if (op2.getType() != 0)
			{
				return 0;
			}
			
			return this.store(op1, op2);
		}
		else
		{
			if (op1.getType() != 0)
			{
				return 0;
			}
			
			op3 = parseOperand(instString[3]);		
			
			// call the appropriate function based on the operation
			if (operation.equals("add"))
			{
				return this.add(op1, op2, op3);
			}
			else if (operation.equals("sub"))
			{
				return this.sub(op1, op2, op3);
			}
			else if (operation.equals("shiftl"))
			{
				return this.shiftl(op1, op2);
			}
			else if (operation.equals("shiftr"))
			{
				return this.shiftr(op1, op2);
			}
			else if (operation.equals("or"))
			{
				return this.or(op1, op2, op3);
			}
			else if (operation.equals("xor"))
			{
				return this.xor(op1, op2, op3);
			}
			else if (operation.equals("and"))
			{
				return this.and(op1, op2, op3);
			}
			else if (operation.equals("weave"))
			{
				return this.crazy(op1, op2, op3);
			}
			else
			{
				return 0;
			}
		}
	}

	/**
	 * Parses the operand - detects if it's a register and parses it that way.
	 * If unsuccessful, tries to parse it as an integer. If that's unsuccessful,
	 * returns null.
	 * @param op
	 * @return an operand object representing the parse value. Otherwise, returns a null
	 */
	private Operand parseOperand(String op)
	{
		Integer value = this.registerMap.get(op);
		
		if (value == null)
		{
			// the value should be an integer
			try 
			{
				value = Integer.parseInt(op);
				
				return (new Operand(value, 1));
			} catch (IllegalArgumentException e)
			{
				// the value was not an integer
				return null;
			}
		}
		
		return (new Operand(value, 0));
	}
	
	/** 
	 * Prints the contents of the registers
	 */
	public void printRegisters()
	{
		for (int i = 0; i < registerNames.length; i++)
		{
			System.out.print(registerNames[i] + ":" + 
								registers[registerMap.get(registerNames[i])] + "; ");
		}
		
		System.out.println();
	}
	
	public void testOr()
	{
		System.out.println("Pre-op:");
		printRegisters();
		registers[0] = 4;
		registers[1] = 1;
		System.out.println("Checking 4|1...");
		Operand op1 = new Operand(0, 0);
		Operand op2 = new Operand(1, 0);
		Operand op3 = new Operand(2, 0);
		this.or(op3, op1, op2);
		System.out.println("Post-op:");
		printRegisters(); // should be 5 in r3
		
		System.out.println("Checking 4|16...");
		registers[2] = 16;
		this.or(op2, op1, op3);
		System.out.println("Post-op:");
		printRegisters(); // should be 20 in r2
		
		System.out.println("7 `crazy` 0");
		registers[0] = 7;
		registers[1] = 0;
		this.crazy(op3, op1, op2);
		System.out.println("Post-op:");
		printRegisters(); // should be 42 in r3
	}
}
