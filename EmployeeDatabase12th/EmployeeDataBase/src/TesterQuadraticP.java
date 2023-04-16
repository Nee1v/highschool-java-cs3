import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TesterQuadraticP 
{
	public static void main(String[] args) 
	{
		for(int i = 0; i < 3; i++)
		{
			double lf = 0;
			if(i == 0)
			{
				lf = .1;
			}
			else if(i == 1)
			{
				lf = .5;
			}
			else if(i == 2)
			{
				lf = .9;
			}
		long startTime = System.nanoTime();
		EmployeeDatabaseQuadratic ez = null;
		try {
		      File myObj = new File("Large Data Set.txt");
		      Scanner myReader = new Scanner(myObj);
		      ez = new EmployeeDatabaseQuadratic(lf);
		      
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
		long endTime = System.nanoTime();
		long dur = (endTime - startTime);
		//System.out.println(ez);
		//System.out.println("Time to build data[] " + dur/1000000);
		
		startTime = System.nanoTime();
		try {
		      File myObj = new File("Successful Search Records.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  String[] split = myReader.nextLine().split(" ");
		    	  int id = Integer.valueOf(split[0]);
		    	  String name = split[1] + " " + split[2];
		          ez.find(name, id);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		endTime = System.nanoTime();
		dur = (endTime - startTime);
		//System.out.println("Time to search data[] " + dur/1000000);
		
		System.out.println("\rLinear probing with load factor of " + ez.getLf() + " - ");
		System.out.println("Entries : " + ez.getEntries() + "\rSize : " + ez.getSize());
		System.out.println("Average time per entry add : " + ez.getAvgTime() + "ns");
		System.out.println("Average time per find : " + ez.getAvgFind() + "ns");
		System.out.println("\r\r");
		}
	}
}
