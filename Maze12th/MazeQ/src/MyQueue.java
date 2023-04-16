
public class MyQueue<T> implements QueueADT
{
	MyLinkedList<T> queue;
	
	public MyQueue(T... val)
	{
		queue = new MyLinkedList(val);
	}
	
	public MyQueue()
	{
		queue = new MyLinkedList();
	}
	
	public boolean isEmpty()
	{
		if(queue.isEmpty())
			return true;
		return false;
	}
	
	public void offer(Object i)
	{
		queue.add((T)i);
	}
	
	public T poll()
	{
		return queue.remove(0).getVal();
	}
	
	public int size()
	{
		return queue.size();
	}
	
	public void clear()
	{
		int size = size();
		for(int i = 0; i < size; i++)
		{
			queue.remove(0);
		}
	}
	
	public T peek()
	{
		return queue.getHead();
	}
}
