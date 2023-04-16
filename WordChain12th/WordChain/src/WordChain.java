import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class WordChain
{
    //final stack
	Stack<String> fin;
    
	//array list of dictionary
	ArrayList<String> dict;
    
	//stacks queue
	Queue<Stack> stacks;
    
	//start string
	private String start;
    
	//end string
	private String end;
    
	/*
 	* Constructor
 	* @param s = string start
 	* @param e = string end
 	*/
	public WordChain(String s, String e)
	{
    	fin = new Stack<String>();
    	stacks = new LinkedList<Stack>();
    	dict = new ArrayList<String>();
    	start = s;
    	end = e;
	}
    
	/*
 	* Calls findcommons continuously until either the queue is empty or the end has been found
 	*/
	public boolean solve2()
	{
   	 boolean go = false;
   	 Stack<String> dummy = new Stack<String>();
    	dummy.add(start);
    	stacks.add(dummy);
    	dict = dictionary();
   	 
    	if(start.equals(end))
   	 {
   		 System.out.println("Found a ladder! >>> " + stacks.peek());
   		 return true;
   	 }
   	 
   	 while(go == false)
   	 {
   		 findCommons(stacks.poll());
   		 
   		 if(fin.isEmpty() != true)
   	  	{
   	      	System.out.println("Found a ladder! >>> " + fin);
   	      	return true;
   	  	}
   		 else if(stacks.isEmpty())
   		 {
   			 System.out.println("No ladder between " + start + " and " + end);
   			 go = true;
   		 }
   	 }
   	 return false;
	}
    
	/*
 	* Finds all the common words(1 letter off) and creates a new stack for
 	* each and returns a queue of said stacks
 	* @param s = stack to find commons of the peek
 	*/
	public void findCommons(Stack<String> s)
	{
    	//Creates array list of letters in top of stack
    	ArrayList<Character> lets = chars(s.peek());
    	for(int i = 0; i < dict.size(); i++)
    	{
        	//Counter for amount of common letters
        	int common = 0;
        	if(dict.get(i).length() == s.peek().length())
        	{
            	for(int x = 0; x < lets.size(); x++)
            	{
                	if(dict.get(i).charAt(x) == lets.get(x))
                	{
                    	common++;
                	}
            	}
           	 
            	if(common == start.length()-1)
            	{
               	 
                	//System.out.println(dict.get(i));
                	Stack<String> news = (Stack<String>) s.clone();
                	news.add(dict.get(i));
                	dict.remove(i);
                	i--;
                	stacks.add(news);
               	 
                	if(news.peek().equals(end))
                	{
                    	fin = (Stack<String>) news.clone();
                	}
            	}
         	}
      	}
	}
    
	/*
 	* Return array list of characters
 	* @param s = string to return array list of chars of
 	*/
	public ArrayList<Character> chars(String s)
	{
    	ArrayList<Character> lets = new ArrayList<Character>();
    	//System.out.println(s);
    	for(int i = 0; i < s.length(); i++)
    	{
   		 Character ch = s.charAt(i);
   		 lets.add(ch);
    	}
    	//System.out.println(lets);
    	return lets;
	}
    
	/*
 	* Returns array list of dictionary
 	*/
	public ArrayList<String> dictionary()
	{
    	ArrayList<String> dic = new ArrayList<String>();
    	try {
          	File b = new File("dictionary.txt");
          	Scanner myReader = new Scanner(b);
          	while (myReader.hasNextLine()) {
            	dic.add(myReader.nextLine());
          	}
          	myReader.close();
        	} catch (FileNotFoundException e) {
          	System.out.println("An error occurred.");
          	e.printStackTrace();
        	}
    	return dic;
	}
}





