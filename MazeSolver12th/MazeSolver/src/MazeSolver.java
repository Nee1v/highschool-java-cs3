import java.util.ArrayList;
import java.util.List;

public abstract class MazeSolver
{
	//Maze object
	private Maze maze;
	
	//Stack object
	private MyStack ms;
	
	//Stores the list of squares returned by getNeighbors
	private List<Square> a;
	
	//Boolean representation of maze solved status
	private boolean solved = false;
	
	/*
	 * Constructor
	 */
	MazeSolver (Maze maze)
	{
		this.maze = maze;
		ms = new MyStack();
		ms.push(maze.getStart());
	}
	
	/*
	 * Makes the stack storing squares empty
	 */
	public abstract void makeEmpty();
	
	/*
	 * Checks if the stack is empty
	 */
	public abstract boolean isEmpty();
	
	/*
	 * Adds a new square to the stack
	 * @param a = new Square to be added
	 */
	public abstract void add(Square a);
	
	/*
	 * Returns what I believe to be the next item from the stack
	 */
	public abstract Square next();
	
	/*
	 * Returns true if solved
	 */
	public boolean isSolved()
	{
		if(ms.peek() == maze.getExit())
			return true;
		return false;
	}
	
	/*
	 * Plays one step of the maze
	 */
	public void step()
	{
		//Sets value being looked at to working on
		if(ms.peek().getType() != 2 || ms.peek().getType() != 3)
		{
			ms.peek().setStatus('o');
		}
		
		//Checks if most recent value added is exit
		solved = isSolved();
		
		//Sets staus of current square to explored before popping it
		if(ms.peek().getType() != 2 || ms.peek().getType() != 3)
		{
			ms.peek().setStatus('.');
		}
		
		//Gets neighbors
		a = maze.getNeighbors(ms.pop());
		
		//Adds neighbors that arent walls or already explored
		for(int i = 0; i < a.size(); i++)
		{
			if(a.get(i).getStatus() != '.' || a.get(i).getType() != 1 || a.get(i).getType() != 2)
				ms.push(a.get(i));
		}
	}
	
	/*
	 * Returns the path
	 */
	public String getPath()
	{
		return "not solved";
	}
	
	/*
	 * Solves the actual maze by repeatedly calling step
	 */
	public void solve()
	{
		
		//If is empty is true then the maze was unsolvable
		//If solved equal true it was solved
		while(ms.isEmpty() != true || solved != true)
		{
			step();
		}
		
		System.out.println(solved);
	}
	
}
