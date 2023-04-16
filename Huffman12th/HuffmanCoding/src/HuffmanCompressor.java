
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HuffmanCompressor
{
	/*
	 * Compress method simply compresses file fileName into a binary tree and binary file
	 * @param fileName = file to compress
	 */
    public static void compress(String fileName) throws IOException
    {
   	 int[] arr = new int[256];
   	 try {
   	   	File myObj = new File(fileName);
   	   	Scanner myReader = new Scanner(myObj);
   	   	while (myReader.hasNextLine()) {
   	     	String line = myReader.nextLine();
   	     	for(int i = 0; i < line.length(); i++)
   	     	{
   	    		 int asc = (int)line.charAt(i);
   	    		 arr[asc] = arr[asc] + 1;
   	     	}
   	     	if(myReader.hasNextLine())
   	     	{
   	     		arr[10] = arr[10] + 1;
   	     	}
   	   	}
   	   	myReader.close();
   	 	} catch (FileNotFoundException e) {
   	   	System.out.println("An error occurred.");
   	   	e.printStackTrace();
   	 	}
   	 
   	 //Constructs tree
   	 HuffmanTree n = new HuffmanTree(arr);
   	 String code = fileName.substring(0, fileName.indexOf(".txt"));
   	 code = code + ".code";
   	 n.write(code);
   	 
   	 //Constructs binary
   	 String shortt = fileName.substring(0, fileName.indexOf(".txt"));
   	 shortt = shortt + ".short";
   	 HuffmanTree construct = new HuffmanTree(code);
  	 BitInputStream in = new BitInputStream(fileName);
  	 construct.decode(in, shortt);
    }
    
    /*
     * expand expands the codeFile of binary into readable words and writes those words onto fileName
     * @param codeFile = file of binary 1s and 0s
     * @param fileName = file to write words onto
     */
    public static void expand(String codeFile, String fileName) throws IOException
    {
    	String code = codeFile.substring(0, codeFile.indexOf(".short")) + ".code";
    	HuffmanTree n = new HuffmanTree(code);
    	n.decode(codeFile, fileName);
    }
    
    public static void main(String[] args) throws IOException
    {
    	//Enter txt fileName here 
     String fileName = "War and Peace";
     
   	 compress(fileName + ".txt");
   	 expand(fileName + ".short", fileName + ".new");
    }
}

