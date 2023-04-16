import java.awt.Font;
import java.util.ArrayList;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{	
	ArrayList<Integer> n = new ArrayList<Integer>();
	GemType g;
	int points;
	
	public Gem()
	{
		n.add(0);
		n.add(5);
		n.add(10);
		n.add(15);
		n.add(20);
		n.add(25);
		n.add(30);
		n.add(35);
		n.add(40);
		n.add(45);
		n.add(50);
		
		points = (int)(Math.random() * 11);  
		System.out.println(points);
		int color = (int)(Math.random() * 3) + 1;  
		points = n.get(points);
		
		if(color == 1)
			g = GemType.GREEN;
		if(color == 2)
			g = GemType.BLUE;
		if(color == 3)
			g = GemType.ORANGE;
	}
	
	Gem(GemType type, int points)
	{
		g = type;
		this.points = points;
	}
	
	@Override
	public String toString()
	{
		return "Color : " + g + "\nPoints : " + points;
	}
	
	public GemType getType()
	{
		return g;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void draw(double x, double y)
	{
		StdDraw.setPenColor(StdDraw.BLUE);
		if(this.getType() == GemType.GREEN)
			StdDraw.picture(x, y, "gem_green.png");
		if(this.getType() == GemType.BLUE)
			StdDraw.picture(x, y, "gem_blue.png");
		if(this.getType() == GemType.ORANGE)
			StdDraw.picture(x, y, "gem_orange.png");
		StdDraw.text(x, y, String.valueOf(getPoints()));
		
	}
	
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
