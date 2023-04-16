
public class MazeSolverStack extends MazeSolver
{
	MyStack stack;
	
	MazeSolverStack(Maze maze) 
	{
		super(maze);
		makeEmpty();
	}

	/*
	 * Overridden make empty
	 */
	@Override
	public void makeEmpty()
	{
		stack = new MyStack();
	}

	/*
	 * Overridden is empty
	 */
	@Override
	public boolean isEmpty()
	{
		if(stack.isEmpty())
			return true;
		return false;
	}

	/*
	 * Overridden add
	 */
	@Override
	public void add(Square a) 
	{
		stack.push(a);
	}

	/*
	 * Overridden next
	 */
	@Override
	public Square next() 
	{
		return stack.peek();
	}
	
}
