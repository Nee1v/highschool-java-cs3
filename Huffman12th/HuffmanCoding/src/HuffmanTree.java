import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree<T>
{
	//Root node
    Node root;
    
    /*
     * Priority queue q which sorts the node objects by frequency so that
     * when you poll it returns the node with lowest frequency.
    */
    PriorityQueue<Node> q;
    
    /*
     * Stores the Integer ascii value of a letter and the String value of its bit path to a map
     * Example : if you say m.get(key) it will return the but path to key from the tree.
     */
    Map<Integer, String> m;
    
    /*
     * Node class with normal binary tree attributes
     * In addition to the normal methods and variables it has int ascii, int weight, and string bit variables 
     * for easier sorting and access to values.
     */
    class Node implements Comparable<T>
    {
   	 Node left;
   	 Node right;
   	 int ascii;
   	 int weight;
   	 String bit;
   	 
   	 public Node()
   	 {
   		 left = null;
   		 right = null;
   		 ascii = 0;
   		 weight = -1;
   		 bit = "";
   	 }
   	 
   	 public Node(int string, int weight, String path)
   	 {
   		 this.ascii = string;
   		 this.weight = weight;
   		 bit = path;
   	 }
   	 
   	 public Node(Node left, Node right, int weight)
   	 {
   		 this.left = left;
   		 this.right = right;
   		 this.weight = weight;
   		 ascii = 0;
   		 bit = "";
   	 }

   	 @Override
   	 public int compareTo(T arg0) {
   		 Node n = (Node)arg0;
   		 if(this.weight > n.weight)
   			 return 1;
   		 if(this.weight < n.weight)
   			 return -1;
   		 return 0;
   	 }
   	 
   	 public void bitAdd(char n)
   	 {
   		 bit = n + bit;
   	 }
   	 
   	 public String toString()
   	 {
   		 return String.valueOf(ascii) + " " + String.valueOf(weight);
   	 }
    }

    /*
     * This constructor constructs a tree from an int[] array of frequencies with indexes being the 
     * ascii value and the value at the index being frequency. Once its done it calls buildTree() helper.
     * @param count = an array of frequencies
     */
    HuffmanTree(int[] count)
    {
   	 m = null;
   	 q = new PriorityQueue<Node>();
   	 root = null;
   	 for(int i = 0; i < count.length; i++)
   	 {
   		 if(count[i] > 0)
   		 {
   			 ///////////////
   			 Node node = new Node(i, count[i], "");
   			 q.add(node);
   		 }
   	 }
   	 buildTree();
    }
    
    /*
     * This constructor takes a string codeFile and reconstructs the tree from that. This code file
     * comes in a designated format in which the ascii value and path are shown sequentially on separate lines
     * @param codeFile = code file that contains tree to be recreated
     */
    HuffmanTree(String codeFile)
    {
   	 root = new Node(null, null, 0);
   	 m = new HashMap<Integer, String>();
   	 try {
   	   	File myObj = new File(codeFile);
   	   	Scanner myReader = new Scanner(myObj);
   	   	while (myReader.hasNextLine()) {
   	     	String ascii = myReader.nextLine();
   	     	String path = myReader.nextLine();
   	     	m.put(Integer.valueOf(ascii), path);
   	     	Node node = new Node(Integer.valueOf(ascii), 0, path);
   	     	addNode(root, node, path);
   	   	}
   	   	myReader.close();
   	 	} catch (FileNotFoundException e) {
   	   	System.out.println("An error occurred.");
   	   	e.printStackTrace();
   	 	}
    }
    
    /*
     * addNode adds a node to the tree with the given path
     * @param root = root node to help traverse
     * @param add = node to be added
     * @param path = string of numbers 1s & 0s to guide you through tree with 0s being go left and 1s being go right 
     */
    public void addNode(Node root, Node add, String path)
    {
   	 int num;
   	 for(num = 0; num < path.length()-1; num++)
   	 {
   		 if(path.charAt(num) == '0')
   		 {
   			 if(root.left == null)
   			 {
   				 Node blank = new Node();
   				 root.left = blank;
   			 }
   			 root = root.left;
   		 }
   		 if(path.charAt(num) == '1')
   		 {
   			 if(root.right == null)
   			 {
   				 Node blank = new Node();
   				 root.right = blank;
   			 }
   			 root = root.right;
   		 }
   	 }
   	 if(path.charAt(num) == '0')
   	 {
   		 root.left = add;
   	 }
   	 if(path.charAt(num) == '1')
   	 {
   		 root.right = add;
   	 }
    }
    
    /*
     * build tree simply builds a tree from the priority queue by continuously 
     * polling the two smallest nodes and combining them over and over again until
     * there is only one left which then becomes the root of the tree
     */
    public void buildTree()
    {
   	 while(q.size() > 1)
   	 {
   		 Node right = q.poll();
   		 Node left = q.poll();
   		 Node node = new Node(left, right, left.weight + right.weight);
   		 setBits(node.right, '1');
   		 setBits(node.left, '0');
   		 q.add(node);
   	 }
   	 root = q.poll();
    }
    
    /*
     * set bits is a helper method that will set bits to a given node with char n
     * adds 1s to all the right and 0s to all the left nodes
     * @param n = number to add to bit
     */
    public void setBits(Node node, char n)
    {
   	 node.bitAdd(n);
   	 if(node.left != null)
   	 {
   		 setBits(node.left, n);
   	 }
   	 if(node.right != null)
   	 {
   		 setBits(node.right, n);
   	 }
    }
    
    /*
     * returns root;
     */
    public Node getRoot()
    {
   	 return root;
    }
    
    /*
     * write method calls a separate write method with a created file output stream
     * because I use recursion to run through the method i didnt want to constantly create objects for the fileoutput stream
     * @param fileName = file name of file that the program writes the ascii values and bit values too
     */
    public void write(String fileName)
    {
   	 try {
   		 FileOutputStream os = new FileOutputStream(fileName);
   		 write(root, os);
   	 } catch (IOException e) {
   		 System.out.println("File not found");
   	 }
    }
    
    /*
     * this write method runs through tree with a simple in order traversal and prints the node ascii and node bit to designated file in order
     * @param node = current node
     * @param os = file output stream object that writes ascii values and bit values to file
     */
    public void write(Node node, FileOutputStream os) throws IOException
    {
   	 if (node == null)
        	return;
 
   	 if(node.ascii != 0)
   	 {
   		 String pair = node.ascii + "\r" + node.bit + "\r";
   		 os.write(pair.getBytes());
   	 }
 
    	write(node.left, os);
 
    	write(node.right, os);
    }
    
    /*
     * Need to change a txt of words to a txt of my own defined set of 0s & 1s
     * Word txt -> Java set binary code of ascii vals -> Find in my tree -> Add my own bit value of said char to new file
     * 
     * This decode method creates the .short files by finding the values of the ascii number decoded by the bit input stream
     * we find the bit value to write onto the file by using the map we created in the constructor 
     * after we've found the supplied bit we write it to the outFile and at the end add the EOF character
     * @param in = BitInputStream object
     * @param outFile = file to write bit path values onto
     */
    public void decode(BitInputStream in, String outFile) throws IOException
    {
   	 FileOutputStream newBit = null;
   	 try {
   		 newBit = new FileOutputStream(outFile);
   	 } catch (IOException e) {
   		 System.out.println("File not found");
   	 }
   	 boolean go = true;
   	 Node r = root;
   	 String val = "";
   	 int count = 0;
   	 while(go != false)
   	 {
   		 String ascii = String.valueOf(in.readBit());
   		 if(ascii.equals("-1"))
   		 {
   			 go = false;
   		 }
   		 else
   		 {
   			 val = ascii + val;
   			 //if(val.charAt(0) == '0')
   				 //val = "";
   			 count++;
   			 if(count == 8)
   			 {
   				 if(val.charAt(0) == '0')
   				 {
   					 val = val.substring(val.indexOf('1'), val.length());
   				 }
   				 Integer a = Integer.parseInt(val, 2);
   				 if(m.containsKey(a) == true)
   				 	newBit.write(m.get(a).getBytes());
   				 //System.out.println((char) a + " " + m.get(a));
   				 val = "";
   				 count = 0;
   			 }
   		 }
   	 }
   	 char eof = (char) 31;
   	 newBit.write(eof);
    }
    
    /*
     * This decode method expands the binary values and turns it into readable text
     * @param fileName = file name of binary values
     * @param outFile = file to print words onto
     */
    public void decode(String fileName, String outFile) throws IOException
    {
     FileOutputStream newBit = null;
   	 Node node = root;
   	 try {
   		newBit = new FileOutputStream(outFile);
   	   	File myObj = new File(fileName);
   	   	Scanner myReader = new Scanner(myObj);
   	   	while (myReader.hasNextLine()) {
   	     	String str = myReader.nextLine();
   	     	for(int i = 0; i < str.length(); i++)
   	     	{
   	     		 if(node == null)
   	     		 {
   	     			break;
   	     		 }
   	     		 else if(node.left == null && node.right == null)
   	    		 {
   	    			char s = (char) node.ascii;
   	    	   	 	newBit.write(s);
   	    			 node = root;
   	    		 }
   	    		 if(str.charAt(i) == '0')
   	    		 {
   	    			 node = node.left;
   	    		 }
   	    		 else if(str.charAt(i) == '1')
   	    		 {
   	    			 node = node.right;
   	    		 }
   	     	}
   	   	}
   	   	myReader.close();
   	 	} catch (FileNotFoundException e) {
   	   	System.out.println("An error occurred.");
   	   	e.printStackTrace();
   	 	}
    }
}

