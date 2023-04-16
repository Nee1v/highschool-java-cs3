import java.util.List;

public class MazeSolverQueue extends MazeSolver
{
		public MyQueue<Square> mq;
		
		//Period
		private char period = '.';
		
		//Boolean variable to represent incompletion of maze
		private boolean stop = false;
		
		//Maze object
		private Maze maze;
		
		//Stores the list of squares returned by getNeighbors
		private List<Square> a;
		
		//Boolean representation of maze solved status
		private boolean solved = false;
		
		Square exit = null;
		
		/*
		 * Constructor
		 */
		MazeSolverQueue (Maze maze)
		{
			super(maze);
			this.maze = maze;
			mq = new MyQueue();
			mq.offer(maze.getStart());
		}
		
		/*
		 * Makes the stack storing squares empty
		 */
		@Override
		public void makeEmpty()
		{
			mq.clear();
		}
		
		/*
		 * Checks if the stack is empty
		 */
		@Override
		public boolean isEmpty()
		{
			return mq.isEmpty();
		}
		
		/*
		 * Adds a new square to the stack
		 * @param a = new Square to be added
		 */
		@Override
		public void add(Square a)
		{
			mq.offer(a);
		}
		
		/*
		 * Returns what I believe to be the next item from the stack
		 */
		@Override
		public Square next()
		{
			return mq.peek();
		}
		
		
		/*
		 * Returns true if solved
		 */
		@Override
		public boolean isSolved()
		{
			if(exit != null)
			{
			if(exit.getType() == 3)
			{
				solved = true;
				return true;
			}
			}
			return false;
		}
		
		@Override
		public void step()
		{
			if(mq.isEmpty() != true /*&& ms.peek() != null*/)
			{
				if(exit != null)
				{
				if(exit.getType() == 3)
				{
					solved = isSolved();
				}
				}
				//Gets neighbor and set popped val to worked on
				if(mq.peek().getType() != 2)
					mq.peek().setStatus(period);
				Square s = mq.peek();
				a = maze.getNeighbors(mq.poll());
				//Adds neighbors that aren't walls or already explored
				for(int i = 0; i < a.size(); i++)
				{
					if(a.get(i).getStatus() == '_' || a.get(i).getType() == 3)
					{
						a.get(i).setPrev(s);
						
						if(a.get(i).getType() == 3)
						{
							exit = a.get(i);
							solved = isSolved();
						}
						
						maze.getSquare(a.get(i).getRow(), a.get(i).getCol()).setStatus(period);
						a.get(i).setStatus('o');
						mq.offer(a.get(i));
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
		@Override
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
				return "Solved";
			}
			else
				return "Not Solved";
		}
		
		/*
		 * Solves the actual maze by repeatedly calling step
		 */
		@Override
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
