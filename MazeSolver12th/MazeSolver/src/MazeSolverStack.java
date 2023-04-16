
public class MazeSolverStack extends MazeSolver
{
	MyStack stack;
	
	public MazeSolverStack(Maze maze) 
	{
		super(maze);
		stack = new MyStack();
	}

	@Override
	public void makeEmpty()
	{
		stack = new MyStack();
	}

	@Override
	public boolean isEmpty()
	{
		if(stack.isEmpty())
			return true;
		return false;
	}

	@Override
	public void add(Square a) 
	{
		stack.push(a);
	}

	@Override
	public Square next() 
	{
		return stack.peek();
	}
	
}
