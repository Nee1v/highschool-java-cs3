import java.util.Arrays;

public class MyStack implements StackADT
{
	//Square array stack
	Square[] stack;
	
	//Stores size
	int size;
	
	
	/*
	 * Default constructor
	 */
	public MyStack()
	{
		stack = new Square[1];
		size = stack.length-1;
	}
	
	
	/*
	 * Param constructor which passes a size
	 * @param initCap = size of array
	 */
	public MyStack(int initCap)
	{
		stack = new Square[initCap];
		size = stack.length-1;
	}
	
	
	/*
	 * Checks if stack is empty
	 */
	public boolean isEmpty()
	{
		for(int i = 0; i < stack.length; i++)
		{
			if(stack[i] != null)
				return false;
		}
		return true;
	}
	
	
	/*
	 * Peek returns most recent value
	 */
	public Square peek()
	{
		return stack[stack.length-1];
	}
	
	
	/*
	 * Pop returns most recent value and deletes it
	 */
	public Square pop()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			Square n = stack[stack.length-1];
			if(stack.length != 1)
			{
				stack = Arrays.copyOf(stack, stack.length-1);
				size = stack.length-1;
			}
			else
			{
				stack[size] = null;
			}
			return n;
		}
	}
	
	
	/*
	 * Push adds a new value onto stack
	 * @param item = item to be pushed
	 */
	public void push(Square item)
	{
		if(stack[stack.length-1] != null)
		{
			stack = Arrays.copyOf(stack, stack.length+1);
		}
		stack[stack.length-1] = item;
		size = stack.length-1;
	}
	
	
	/*
	 * Doubles capacity
	 */
	public void doubleCapacity()
	{
		stack = Arrays.copyOf(stack, stack.length * 2);
	}
	
	
	
	@Override
	public String toString()
	{
		String s = "[";
		for(int i = 0; i < stack.length-1; i++)
		{
			s = s + "" + stack[i] + ",";
		}
		return s + "" + stack[stack.length-1] + "]";
	}
	
	/*
	 * Returns size
	 */
	public int size()
	{
		return stack.length;
	}
	
	/*
	 * Clears stack
	 */
	public void clear()
	{
		for(int i = 0; i < stack.length; i++)
		{
			stack[i] = null;
		}
	}
	
}


class EmptyStackException extends Exception {

}

