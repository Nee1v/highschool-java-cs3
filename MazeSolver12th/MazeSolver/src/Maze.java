import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze
{
	
	//arr to store number types of squares in
	private int[][] arr;
	
	//row
	private int r;
	
	//col
	private int c;
	
	//stores start index in start[0] and start[1]
	private int[] start;
	
	//stores end index in end[0] and end[1]
	private int[] end;
	
	/*
	 * Empty constructor
	 */
	public Maze()
	{
   	 
	}
	
	/*
 	* The first line of the file contains two integers;
 	*  the first indicates the number of rows R, the second the number of columns C.
 	*/
    
	//ADD TRY CATCH TO CATCH ERRORS AND RETURN FALSE IF ERROR
	/*
 	* load maze creates new maze from a string file of ints and specified row/column
 	* returns true if maze is made
 	* @param fileName = file to be added to 2d array
 	* @param r = specified rows
 	* @param c = specified columns
 	*/
	public boolean loadMaze(String fileName)
	{
		String mayz = "";
   	 
    	//Splits string by spaces
    	String[] splitted = fileName.split(" ");
   	 
    	//r = rows and c = columns
    	int r = Integer.valueOf(splitted[0]);
    	int c = Integer.valueOf(splitted[1]);
    	
    	//Adds all the splitted numbers into a string
    	for(int i = 2; i < splitted.length; i++)
    	{
    		mayz = mayz + splitted[i];
    	}
    	
    	//Sets start and end array sizes to 2
    	start = new int[2];
    	end = new int[2];
   	 
    	//Count iterator equals zero and goes up everytime the 2d array passes a val
    	int count = 0;
   	 
    	//Sets 2d array for which we store int type
    	arr = new int[r][c]; //[r][c]
   	 
    	//Removes all spaces and separates all adjacent numbers
    	String s = "";
    	for(int i = 0; i < mayz.length(); i++)
    	{
        	if(mayz.charAt(i) != ' ')
            	s=s+mayz.charAt(i);
    	}
   	 
    	//System.out.println(s);
    	//Runs 2d array and adds all integer values to specified positions
    	for(int i = 0; i < r; i++)
    	{
       	 
        	for(int x = 0; x < c; x++)
        	{
            	arr[i][x] = Character.getNumericValue(s.charAt(count));
            	//System.out.print(s.charAt(count));
            	count++;
            	if(arr[i][x] == 2)
            	{
                	start[0] = i;
                	start[1] = x;
            	}
            	if(arr[i][x] == 3)
            	{
                	end[0] = i;
                	end[1] = x;
            	}
               	 
        	}
       	 
    	}
   	 
    	return true;
	}
    
	/*
 	* getNeighbors returns a list of Square type neighbors to passed square
 	* @param s = square to find neigbors of
 	*/
	public List<Square> getNeighbors(Square s)
	{
    	List<Square> list = new ArrayList<Square>();
   	 
    	int row = s.getRow();
    	int col = s.getCol();
   	 
    	if(row != 0)
    	{
        	Square top = new Square(row-1, col, arr[row-1][col]);
        	list.add(top);
    	}
    	if(col != c-1)
    	{
        	Square right = new Square(row, col+1, arr[row][col+1]);
        	list.add(right);
    	}
    	if(row != r-1)
    	{
        	Square bottom = new Square(row+1, col, arr[row+1][col]);
        	list.add(bottom);
    	}
    	if(col != 0)
    	{
        	Square left = new Square(row, col-1, arr[row][col-1]);
        	list.add(left);
    	}
   	 
    	return list;
	}
    
	//returns exit
	public Square getExit()
	{
    	Square exit = new Square(end[0], end[1], 3);
    	return exit;
	}
    
	//returns start
	public Square getStart()
	{
    	Square begin = new Square(start[0], start[1], 2);
    	return begin;
	}
    
	//resets all the squares
	public void reset()
	{
    	for(int i = 0; i < arr.length; i++)
    	{
        	for(int x = 0; x < arr[0].length; x++)
        	{
            	Square temp = new Square(i, x, 5);
            	temp.reset(temp);
        	}
    	}
	}
    
	//toString method
	@Override
	public String toString()
	{
    	String s = "";
    	for(int i = 0; i < arr.length; i++)
    	{
        	for(int x = 0; x < arr[0].length; x++)
        	{
            	if(arr[i][x] == 0)
                	s = s + " _";
            	if(arr[i][x] == 1)
                	s = s + " #";
            	if(arr[i][x] == 2)
                	s = s + " S";
            	if(arr[i][x] == 3)
                	s = s + " E";
        	}
        	s = s + "\n";
    	}
    	return s;
       	 
	}   	 
    
}


