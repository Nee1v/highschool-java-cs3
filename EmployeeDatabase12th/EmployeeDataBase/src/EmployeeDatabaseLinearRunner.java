import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDatabaseLinearRunner 
{

	public static void main(String[] args) 
	{
		EmployeeDatabaseLinear ez = null;
		try {
		      File myObj = new File("Large Data Set.txt");
		      Scanner myReader = new Scanner(myObj);
		      Scanner other = new Scanner(myObj);
		      int n = 0;
		      while(other.hasNextLine())
		      {
		    	  n++;
		    	  other.nextLine();
		      }
		      ez = new EmployeeDatabaseLinear(.9);
		      
		      while (myReader.hasNextLine()) {
		    	  String[] split = myReader.nextLine().split(" ");
		    	  int id = Integer.valueOf(split[0]);
		    	  String name = split[1] + " " + split[2];
		          ez.add(id, name);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		//System.out.println(ez);
		System.out.println("Entries : " + ez.getEntries() + "\rSize : " + ez.getSize());
	}

}