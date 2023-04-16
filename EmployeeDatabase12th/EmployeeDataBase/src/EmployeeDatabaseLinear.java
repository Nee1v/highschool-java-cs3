import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeDatabaseLinear 
{
	//Instance variables
	private Entry[] data;
	private int size;
	double loadfactor;
	int entries;
	long nanosec;
	long nanosec2;
	int gets;
	
	/*
	 * Class for creating entries
	 */
	private class Entry
	{
		int id;
		Employee employee;
		
		public Entry(int id, Employee emp)
		{
			this.id = id;
			employee = emp;
		}
		
		@Override
		public String toString()
		{
			return "ID : " + id + " Employee : " + employee + "\r";
		}
		
		public int hash()
		{
			return 0;
		}
	}
	
	/*
	 * Class for creating employees
	 */
	private class Employee
	{
		String name;
		public Employee(String n)
		{
			name = n;
		}
		
		public String getName()
		{
			return name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
	
	/*
	 * Constructor
	 * @param lf = loadfactor
	 */
	public EmployeeDatabaseLinear(double lf)
	{
		//size = max-min;
		data = new Entry[10];
		loadfactor = lf;
		entries = 0;
		this.size = size;
		nanosec = 0;
		nanosec2 = 0;
		gets = 0;
	}
	
	/*
	 * Adds an employee to data[] using linear probing
	 * @param id = employee id
	 * @param name = employee name
	 * Entry and employee are private so we have to instantiate object within database class
	 */
	public void add(int id, String name)
	{
		long st = System.nanoTime();
		Employee emp = new Employee(name);
		Entry ent = new Entry(id, emp);
		int num = 0;
		boolean go = true;
		
		while(go != false)
		{
			int ind = (ent.id + num) % data.length;
			if(data[ind] == null)
			{
				data[ind] = ent;
				entries++;
				go = false;
			}
			else
			{
				num++;
			}
		}
		double pp = (double)entries / data.length;
		if(loadfactor == .9)
		{
			if(pp >= .9 && pp < .95)
				sizeup();
		}
		else if(pp == loadfactor)
		{
			sizeup();
		}
		long et = System.nanoTime();
		nanosec = nanosec + (et - st);
	}
	
	/*
	 * Size increase helper method
	 */
	public void sizeup()
	{
		data = Arrays.copyOf(data, (int)(data.length / loadfactor));
	}
	
	/*
	 * Find method
	 * @param name = name of employee
	 * @param id = id of employee
	 * return true if found employee with id and name
	 */
	public boolean find(String name, int id)
	{
		long st = System.nanoTime();
		Employee emp = new Employee(name);
		Entry ent = new Entry(id, emp);
		int num = 0;
		boolean go = true;
		
		while(go != false)
		{
			int ind = (ent.id + num) % size;
			if(data[ind] == ent)
			{
				gets++;
				long et = System.nanoTime();
				nanosec2 = nanosec2 + (et-st);
				return true;
			}
			else if(num > size)
			{
				go = false;
			}
			else
			{
				num++;
			}
		}
		long et = System.nanoTime();
		nanosec2 = nanosec2 + (et-st);
		gets++;
		return false;
	}
	
	/*
	 * Getters
	 */
	public int getEntries()
	{
		return entries;
	}
	
	public int getSize()
	{
		return data.length;
	}
	
	public double getLf() 
	{
		return loadfactor;
	}
	
	public double getAvgTime()
	{
		double num =  (nanosec / entries);
		//num = num / 1000000;
		return num;
	}
	
	public double getAvgFind()
	{
		double num =  (nanosec2 / gets);
		//num = num / 1000000;
		return num;
	}
	
	/*
	 * To string
	 */
	public String toString()
	{
		int num = 0;
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] != null)
			{
				System.out.println(data[i] + "              " + i);
			}
			else
			{
				num++;
			}
		}
		//System.out.println(num);
		return "";
	}
}
