import java.util.Arrays;

public class MyStack implements StackADT
{
	//Square array stack
	Square[] stack;
	
	//Stores sizr
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
	 * Param constructor whihc passes a size
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
		if(isEmpty())
		{
			return null;
		//throw new EmptyStackException();
		}
		else
			return stack[size];
	}
	
	
	/*
	 * Pop returns most recent value and delets it
	 */
	public Square pop()
	{
		if(isEmpty())
		{
			return null;
		//throw new EmptyStackException();
		}
		else
		{
			Square n = stack[size];
			stack[size] = null;
			size--;
			return n;
		}
	}
	
	
	/*
	 * Push adds a new value onto stack
	 */
	public void push(Square item)
	{
		//System.out.println(size);
		if(stack[size] == null)
		{
			stack[size] = item;
			if(size + 1 != stack.length)
				size++;
		}
		else
		{
			doubleCapacity();
			size++;
			stack[size] = item;
		}
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
		for(int i = 0; i < size; i++)
		{
			s = s + "" + stack[i] + ",";
		}
		return s + "" + stack[size] + "]";
	}
	
	//Returns size
	public int size()
	{
		return size;
	}
	
	//Clears stack
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

