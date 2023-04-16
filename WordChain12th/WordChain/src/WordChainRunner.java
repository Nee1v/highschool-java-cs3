
import java.util.Scanner;

public class WordChainRunner
{
    public static void main(String[] args)
    {
   	 Scanner scan = new Scanner(System.in);
   	 System.out.println("Takes approximately 12 seconds for BREWING to WHISKEY\n\nB--**Needs to be all caps**--\n");
   	 System.out.println("Enter first word : ");
   	 String s = scan.nextLine();
   	 System.out.println("Enter second word : ");
   	 String t = scan.nextLine();
   	 System.out.println("");
   	 WordChain w = new WordChain(s,t);
   	 w.solve2();
   	 
    }

}
