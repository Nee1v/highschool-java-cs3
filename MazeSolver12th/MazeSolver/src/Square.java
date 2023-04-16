public class Square
{
	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int START = 2;
	final static int EXIT = 3;
    
	final static char WORKING = 'o';
	final static char EXPLORED = '.';
	final static char ON_EXIT_PATH = 'x';
	final static char UNKNOWN = '_';
    
	private int row, col;
	private int type;
	private char status;
    
	/*
 	* Square constructor
 	* @param row = row that square is located in
 	* @param col = column that square is located in
 	* @param type = type that square is
 	*/
	public Square(int row, int col, int type)
	{
    	this.row = row;
    	this.col = col;
    	this.type = type;
    	if(type == 0)
    		status = UNKNOWN;
	}
    
	/*
 	* returns true if square is equal to obj
 	* @param obj = obj to compare this square to
 	*/
	public boolean equals(Object obj)
	{
    	Square temp = (Square)obj;
    	if(row == temp.row && col == temp.col)
        	return true;
    	return false;
	}
    
	//returns row
	public int getRow()
	{
    	return row;
	}
    
	//returns col
	public int getCol()
	{
    	return col;
	}
    
	//returns type
	public int getType()
	{
    	return type;
	}
    
	//returns status
	public char getStatus()
	{
    	return status;
	}
    
	/*
 	* sets status
 	* @param stat = status to be changed to
 	*/
	public void setStatus(char stat)
	{
    	status = stat;
	}
	
	/*
 	* sets row
 	* @param row = row to be changed to
 	*/
	public void setRow(int row)
	{
    	this.row = row;
	}
	
	/*
 	* sets col
 	* @param col = col to be changed to
 	*/
	public void setCol(int col)
	{
    	this.col = col;
	}
	
	/*
 	* sets type
 	* @param type = type to be changed to
 	*/
	public void setType(int type)
	{
    	this.type = type;
	}
    
	/*
 	* helper reset method for reset() in maze class
 	* @param s = square to be reset
 	*/
	public void reset(Square s)
	{
    	if(s.getStatus() == 'o' || s.getStatus() == '.' || s.getStatus() == 'x')
    	{
        	s.setStatus('_');
    	}
	}
    
	//toString method
	@Override
	public String toString()
	{
    	return "Type : " + type + "\nStatus : " + status + "\nRow : " + row + "\nColumn : " + col;
	}
}



