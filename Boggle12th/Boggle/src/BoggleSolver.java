import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.Scanner;

	public class BoggleSolver
	{
		ArrayList<String> dict;
		ArrayList<String> found;
		String abc;
		int[] index;
		int X;
		int Y;
		// Initializes the data structure using the given array of strings as the dictionary.
		// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
		public BoggleSolver(String dictionaryName)
		{
			System.out.println(dictionaryName);
			abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			index = new int[abc.length()];
			dict = new ArrayList<String>();
			found = new ArrayList<String>();
			int count = 0;
			int count2 = 0;
			//TODO
			try {
			      File myObj = new File(dictionaryName);
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			    	String line = myReader.nextLine();
			    	if(abc != "" && line.charAt(0) == (abc.charAt(0)))
			    	{
			    		index[count2] = count + 1;
			    		abc = abc.substring(1, abc.length());
			    		count2++;
			    	}
			        dict.add(line);
			        count++;
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			index[0]--;
			abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}

		// Returns the set of all valid words in the given Boggle board, as an Iterable object
		public ArrayList<String> getAllValidWords(BoggleBoard board)
		{
			//TODO
			X = board.cols();
			Y = board.rows();
			boolean[][] passed = new boolean[X][Y];
			
			for(int i = 0; i < X; i++)
				for(int j = 0; j < Y; j++)
				{
					getWords(i, j, "", board, passed);
					passed = new boolean[X][Y];
				}
			//Iterable<String> words = (Iterable<String>) found.iterator();
			return found;
		}
		
		public void getWords(int x, int y, String current, BoggleBoard board, boolean[][] pass)
		{
			if(current.length() == 0 || prefix(current))
			{
	
				pass[x][y] = true;
				if(board.getLetter(x,y) == 'Q')
				{
					current = current + "QU";
				}
				else
					current = current + board.getLetter(x, y);
				
				if(dict.contains(current))
				{
					if(!found.contains(current))
					{
						found.add(current);
					}
				}

				for (int row = x - 1; row <= x + 1 && row < X; row++)
				{
					for (int col = y - 1; col <= y + 1 && col < Y; col++)
					{
						if (row >= 0 && col >= 0 && !pass[row][col])
							getWords(row, col, current, board, pass);
					}
				}

				current = "" + current.charAt(current.length() - 1);
				pass[x][y] = false;
			}
		}
		
		public boolean prefix(String pre)
		{
			int ind = abc.indexOf(pre.charAt(0));
			for(int i = index[ind]; i < index[ind + 1]; i++)
			{
				if(dict.get(i).contains(pre))
				{
					return true;
				}
			}
			return false;
		}

		// Returns the score of the given word if it is in the dictionary, zero otherwise.
		// (You can assume the word contains only the uppercase letters A - Z.)
		public int scoreOf(String word)
		{
			//TODO
			if(word.length() <= 2)
				return 0;
			else if(word.length() <= 4)
				return 1;
			else if(word.length() <= 5)
				return 2;
			else if(word.length() <= 6)
				return 3;
			else if(word.length() <= 7)
				return 5;
			else
				return 11;
			
		}

		public static void main(String[] args) {
			System.out.println("WORKING");

			final String PATH   = "./data/";
			BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
			BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

			int totalPoints = 0;

			for (String s : solver.getAllValidWords(board)) {
				System.out.println(s + ", points = " + solver.scoreOf(s));
				totalPoints += solver.scoreOf(s);
			}

			System.out.println("Score = " + totalPoints); //should print 84

			//new BoggleGame(4, 4);
		}

	}