import java.util.Arrays;

public class EmployeeDatabaseQuadratic 
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
	 *Entry private class 
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
			return "ID : " + id + "\rEmployee : " + employee;
		}
	}
	
	/*
	 * Employee private class
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
	public EmployeeDatabaseQuadratic(double lf)
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
	 * Adds employee to data[]
	 * @param if = id
	 * @param name = name
	 */
	public void add(int id, String name)
	{
		long st = System.nanoTime();
		Employee emp = new Employee(name);
		Entry ent = new Entry(id, emp);
		
		int num = 0;
		
		boolean go = true;
		//System.out.println(id);
		while(go != false)
		{
			if(num > 10000)
				go = false;
			
			int ind = (ent.id + (num * num)) % data.length;
			if(data[(int)ind] == null)
			{
				data[(int)ind] = ent;
				go = false;
			}
			else
			{
				num++;
			}
			//System.out.println(num);
		}
		entries++;
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
	 * Size increase helper
	 */
	public void sizeup()
	{
		data = Arrays.copyOf(data, (int)(data.length / loadfactor));
	}
	
	/*
	 * Find method
	 * @param name = name of employee tom be found
	 * @param id = id of employee to be found
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
			if(num > 10000)
				go = false;
			
			int ind = (ent.id + (num * num)) % size;
			if(data[(int)ind] == ent)
			{
				gets++;
				long et = System.nanoTime();
				nanosec2 = nanosec2 + (et - st);
				return true;
			}
			else
			{
				num++;
			}
		}
		gets++;
		long et = System.nanoTime();
		nanosec2 = nanosec2 + (et - st);
		return false;
	}
	
	/*
	 * getters
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
	 * toString
	 */
	public String toString()
	{
		int num = 0;
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] != null)
			{
				System.out.println(data[i] + " \rIndex : " + i);
			}
			else
			{
				num++;
			}
		}
		System.out.println("\r" + num);
		return "";
	}
}