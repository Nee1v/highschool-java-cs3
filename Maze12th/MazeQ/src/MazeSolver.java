import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MazeSolver
{
	//Period
	private char period = '.';
	
	//Boolean variable to represent incompletion of maze
	private boolean stop = false;
	
	//Maze object
	private Maze maze;
	
	//Stack object
	private MyStack ms;
	
	//Stores the list of squares returned by getNeighbors
	private List<Square> a;
	
	//Boolean representation of maze solved status
	private boolean solved = false;
	
	Square exit;
	
	/*
	 * Constructor
	 */
	public MazeSolver (Maze maze)
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
		{
			exit = ms.peek();
			solved = true;
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	public void step()
	{
		if(ms.isEmpty() != true /*&& ms.peek() != null*/)
		{
			if(ms.peek().equals(maze.getExit()))
			{
				solved = isSolved();
			}
			//Gets neighbor and set popped val to worked on
			if(ms.peek().getType() != 2)
				ms.peek().setStatus(period);
			Square s = ms.peek();
			a = maze.getNeighbors(ms.pop());
			//Adds neighbors that aren't walls or already explored
			for(int i = 0; i < a.size(); i++)
			{
				if(a.get(i).getStatus() == '_' || a.get(i).getType() == 3)
				{
					a.get(i).setPrev(s);
					maze.getSquare(a.get(i).getRow(), a.get(i).getCol()).setStatus(period);
					a.get(i).setStatus('o');
					ms.push(a.get(i));
				}
			}
		}
		else
		{
			stop = true;
			solved = true;
			//System.out.println("Not Solvable");
		}
	}
	
	
	
	
	
	
	/*
	 * Returns the path
	 */
	public String getPath()
	{
		if(stop == true)
			return "Not Solvable";
		else if(solved)
		{
			while(exit.getPrev() != null)
			{
				System.out.println("[" + exit.getRow() + "," + exit.getCol() + "]");
				exit = exit.getPrev();
				if(exit.getPrev() == null)
					System.out.println("[" + exit.getRow() + "," + exit.getCol() + "]");
			}
			//System.out.println("[" + exit.getRow() + "," + exit.getCol() + "]");
			return "Solved";
		}
		else
			return "Not Solved";
	}
	
	/*
	 * Solves the actual maze by repeatedly calling step
	 */
	public void solve()
	{
		//If is empty is true then the maze was unsolvable
		//If solved equal true it was solved
		while(solved != true)
		{
			step();
			//System.out.println(ms.toString() + "\n\n");
		}
		
		
	}
	
}
