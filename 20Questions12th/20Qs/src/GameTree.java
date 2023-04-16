import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameTree
{
	//ANSWERS == YES
	//QUESTIONS == NO
	/**
 	* Will you need to create an inner class?
 	*/
    	//TODO?
	private class Node
	{
    	String data;
    	Node yes;
    	Node no;
    	Choice x;
   	 
    	public Node()
    	{
        	data = null;
        	yes = null;
        	no = null;
    	}
   	 
    	public Node(String phrase)
    	{
        	data = phrase;
        	yes = null;
        	no = null;
        	this.x = null;
    	}
   	 
    	public String toString()
    	{
        	return data;
    	}
   	 
    	public void setVal(String str)
    	{
        	data = str;
    	}
	}

	/**
 	* Will you need any instance variables?
 	*/
    	//TODO?
	Node root;
	String ans = "Answer";
	String q = "Question";
	Node currentParent;
	Node current;
	Scanner scan;
	String c;
	File files;
	FileOutputStream fos;
	String lineSeparator = System.getProperty("line.separator");
	/**
 	* Constructor needed to create the game.
 	*
 	* @param fileName this is the name of the file we need to import the game
 	*             	questions and answers from.
	 * @throws FileNotFoundException 
 	*/
	public GameTree(String fileName) throws FileNotFoundException
	{
		files = new File(fileName);
		fos = new FileOutputStream(files, true);
    	try
    	{
        	scan = new Scanner(new File(fileName));
        	root = new Node(scan.nextLine());
        	current = root;
        	c = scan.nextLine();
        	add(root);
    	}
    	catch (FileNotFoundException s)
    	{
        	System.out.println("File does Not Exist Please Try Again: ");
    	}
	}
    
	public boolean add(Node node)//, String str)
	{
		if(type(node.data) == ans)
		{
			return false;
		}
		if(node.yes == null)
		{
			node.yes = new Node(c);
			if(scan.hasNextLine())
			{
				c = scan.nextLine();
				add(node.yes);
			}
		}
		if(node.no == null)
		{
			node.no = new Node(c);
			if(scan.hasNextLine())
			{
				c = scan.nextLine();
				add(node.no);
			}
		}
		return true;
	}
    
	/*
 	* True if answer
 	* False if question
 	*/
	public String type(String s)
	{
    	if(s == null)
        	return "";
    	if(s.contains("?"))
        	return "Question";
    	return "Answer";
	}
    
	public boolean containsAllAnswers(Node node)
	{
    	if(node != null)
    	{
    	if(type(node.data) == q && node.yes == null && node.no == null)
    	{
        	return false;
    	}
    	containsAllAnswers(node.yes);
    	containsAllAnswers(node.no);
    	}
    	return true;
	}

	/*
 	* Add a new question and answer to the currentNode. If the current node has the
 	* answer chicken, theGame.add("Does it swim?", "goose"); should change that
 	* node like this:
 	*/
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
 	* @param newQ The question to add where the old answer was.
 	* @param newA The new Yes answer for the new question.
 	*/
	public void add(String newQ, String newA)
	{
    	// TODO
   	 //Old answer should be where current is
    	Node neQ = new Node(newQ);
    	Node neA = new Node(newA);
    	neQ.yes = neA;
    	adds(neQ);
	}
	private void adds(Node Question)
	{
		if(currentParent.yes == current)
		{
			Node temp = currentParent.yes;
    		currentParent.yes = Question;
    		Question.no = temp;
		}
		if(currentParent.no == current) 
		{
			Node temp = currentParent.no;
	    	currentParent.no = Question;
	    	Question.no = temp;
		}
	}

	/**
 	* True if getCurrent() returns an answer rather than a question.
 	*
 	* @return False if the current node is an internal node rather than an answer
 	*     	at a leaf.
 	*/
	public boolean foundAnswer()
	{
    	// TODO
    	if(type(getCurrent()) == ans)
    	{
        	return true;
    	}

    	return false; // replace
	}

	/**
 	* Return the data for the current node, which could be a question or an answer.
 	* Current will change based on the users progress through the game.
 	*
 	* @return The current question or answer.
 	*/
	public String getCurrent()
	{
    	// TODO

    	return current.data; // replace
	}

	/**
 	* Ask the game to update the current node by going left for Choice.yes or right
 	* for Choice.no Example code: theGame.playerSelected(Choice.Yes);
 	*
 	* @param yesOrNo
 	*/
	public void playerSelected(Choice yesOrNo)
	{
    	// TODO
    	if(yesOrNo == Choice.Yes)
    	{
    		currentParent = current;
        	current = current.yes;
    	}
    	if(yesOrNo == Choice.No)
    	{
    		currentParent = current;
        	current = current.no;
    	}
	}

	/**
 	* Begin a game at the root of the tree. getCurrent should return the question
 	* at the root of this GameTree.
 	*/
	public void reStart()
	{
    	// TODO
    	current = root;
	}

	@Override
	public String toString()
	{
    	return current.data;
	}

	/**
 	* Overwrite the old file for this gameTree with the current state that may have
 	* new questions added since the game started.
	 * @throws IOException 
 	*
 	*/
	public void saveGame() throws IOException
	{
    	// TODO
		String blank = "";
		if(files.exists())
		{
			FileOutputStream foos = new FileOutputStream(files, false);
			foos.write(blank.getBytes());
			
			rewrite(root);
			
			fos.close();
		}
		
	}
	
	public void rewrite(Node node) throws IOException
	{
		if (node == null)
            return;
 
        /* first print data of node */
		String str = node.data;
        fos.write(str.getBytes());
        fos.write(lineSeparator.getBytes());
        
        
        /* then recur on left subtree */
        rewrite(node.yes);
 
        /* now recur on right subtree */
        rewrite(node.no);
	}
    
	public static void main(String[] args) throws FileNotFoundException
	{
    	GameTree g = new GameTree("animals.txt");
	}
}






