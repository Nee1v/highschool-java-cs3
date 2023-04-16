//import MyLinkedList.ListNode;

public class GemList 
{
	private Node head;
	private Node tail;
	private int size;
	
	public class Node
	{
		private Gem val;
        private Node next;
        
        public Node(Gem val)
        {
            this.val = val;
            next = null;
        }
        
        public void setNext(Node n)
        {
            next = n;
        }
        
        public Node getNext()
        {
            return next;
        }
        
        public Gem getVal()
        {
            return val;
        }
        
        public void setVal(Gem n)
        {
            val = n;
        }
        
        @Override
        public String toString()
        {
            return "" + this.val;    
        }
        
        
	}

	public GemList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size()
	{
		return size;
	}
	
	public void draw(double y)
	{
		int num = 0;
		if(head != null)
		{
			Node node = head;
			
			while(node.getNext() != null)
			{
				Gem g = node.getVal();
				double x = (0.5 + num) * (1.0 / 16);
				g.draw(x, y);
				node = node.getNext();
				num++;
			}
			Gem g = node.getVal();
			double x = (0.5 + num) * (1.0 / 16);
			g.draw(x, y);
		}
	}
	
	public String toString()
	{
		if(head != null)
		{
			Node node = head;
			
			while(node.getNext() != null)
			{
				System.out.println(node.getVal());
				node = node.getNext();
			}
			System.out.println(node.getVal());
		}
		return "";
	}
	
	public int score()
	{
		int score = 0;
		int s = 0;
		int mult = 1;
		int count;
		Node node = head;
		GemType g;
		
		if(node != null)
		{
			if(node.getNext() != null)
			{
				while(node.getNext() != null)
				{
					if(node.getVal().getType() == node.getNext().getVal().getType())
					{
						mult++;
						s = s + node.getVal().getPoints();
						node = node.getNext();
					}
					else
					{
						if(s != 0)
						{
							s = s + node.getVal().getPoints();
							score = score + (s * mult);
							node = node.getNext();
							s = 0;
							mult = 1;
						}
						else
						{
							score = score +  node.getVal().getPoints();
							node = node.getNext();
						}
					}
				}
				if(s != 0)
				{
					s = s + node.getVal().getPoints();
					score = score + (s * mult);
					node = node.getNext();
					s = 0;
					mult = 1;
				}
				else
				{
					score = score +  node.getVal().getPoints();
					node = node.getNext();
				}
			}
			else if(node != null && node.getNext() == null)
			{
				return node.getVal().getPoints();
			}
		}
		
		return score;
	}
	
	
	
	public void insertBefore(Gem gem, int index)
	{
		Node node = head;
		Node newNode = new Node(gem);
		if(head != null)
		{
			if(index == 0)
			{
				newNode.setNext(node);
				head = newNode;
			}
		else if(index < size)
		{
			for(int i = 0; i < index-1; i++)
			{
				node = node.getNext();
			}
			if(node.getNext() != null)
			{
				Node next = node.getNext();
				newNode.setNext(next);
				node.setNext(newNode);
			}
			else
			{
				node.setNext(newNode);
			}
		}	
		else
		{
			while(node.getNext() != null)
			{
				node = node.getNext();
			}
			node.setNext(newNode);
		}
		}
		else
		{
			head = newNode;
		}
		size++;
	}
	
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score() + "\n\n\n");
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score()+ "\n\n\n");
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score()+ "\n\n\n");
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score()+ "\n\n\n");
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score()+ "\n\n\n");
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score()+ "\n\n\n");
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score()+ "\n\n\n");
		list.draw(0.3);		
	}	
}
