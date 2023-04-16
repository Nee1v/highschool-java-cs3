public class MyLinkedList<T>
{

    private ListNode head;
    private int size;
    private ListNode tail;
    
    public class ListNode
    {
        
        private T val;
        private ListNode next;
        
        public ListNode(T val)
        {
            this.val = val;
            next = null;
        }
        
        public void setNext(ListNode n)
        {
            next = n;
        }
        
        public ListNode getNext()
        {
            return next;
        }
        
        public T getVal()
        {
            return val;
        }
        
        public void setVal(T n)
        {
            val = n;
        }
        
        @Override
        public String toString()
        {
            return "" + this.val;    
        }
    }
    
    public MyLinkedList(T... val)
    {
    	for(T i : val)
    	{
    		add(i);
    	}
    	size = size();
    }
    
    public MyLinkedList()
    {
        head = null;
        size = 0;
        tail = null;
    }
    
    public MyLinkedList(T val)
    {
        ListNode n = new ListNode(val);
        head = n;
        size = 1;
        tail = n;
    }
    
    public T getHead()
    {
    	if(head != null)
            return head.getVal();
    	return null;
    }
    
    public void add(T n)
    {
        ListNode newNode = new ListNode(n);
        
        if(head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        //System.out.println(tail);
    }
    
    public boolean contains(T tar)
    {
        boolean yes = false;
        ListNode node = head;
        while(node.getNext() != null)
        {
            if(node.getVal() == tar)
            {
                yes = true;
            }
            node = node.getNext();
        }
        return yes;
    }
    
    public T get(int ind)
    {
        try
        {
            ListNode node = head;
            for(int i = 0; i < ind; i++)
            {
                node = node.getNext();
            }
            return node.getVal();
        }
        catch(IndexOutOfBoundsException e)
        {
            
        }
        return null;
    }
    
    public int indexOf(T n)
    {
        int index = -1;
        int count = 0;
        ListNode node = head;
        while(node.getNext() != null)
        {
            if(node.getVal() == n)
            {
                index = count;
            }
            node = node.getNext();
            count++;
        }
        if(node.getVal() == n)
        {
            index = count;
        }
        return index;
    }
    
    public int size()
    {
        return size;
    }
    
    
    
    /*public int sizeRecursively(ListNode current)
    {
        while(current.getNext() != null)
        {
            size =  1 + sizeRecursively(current.getNext());
        }
        return size;
    }*/
    
    public void set(T newVal, int index)
    {
        if(index < size)
        {
            ListNode node = head;
            for(int i = 0; i < index; i++)
            {
                node = node.getNext();
            }
            node.setVal(newVal);
        }
    }
    
    public boolean isEmpty()
    {
        if(size == 0)
        {
            return true;
        }
        return false;
    }

    public ListNode remove(int index)
    {
        ListNode r = null;
        if(head != null)
        {
        if(index == 0)
        {
            ListNode n = head;
            head = head.getNext();
            size--;
            return n;
        }
        if(index < size)
        {
        ListNode node = head;
        ListNode before = null;
        ListNode after = null;
        int count = 0;
        
        for(int i = 0; i < index; i++)
        {
            if(i == index - 1)
            {
                before = node;
            }
            node = node.getNext();
        }
        r = node;
        if(node.getNext() != null)
        {
            after = node.getNext();
        }
        else
        {
            before.setNext(null);
            count++;
        }
        
        if(count == 0)
        {
            before.setNext(after);
        }
        }
        }
        size--;
        return r;
    }
    
    /*
     * o --> o --> o --> o --> o --> o --> o
     * insert b into index 3
     * o --> o --> o --> b o --> o --> o --> o
     * need to connect before index to new listnode and listnode next = index after index var
     * o --> o --> o --> b --> o --> o --> o --> o
     */
    public void add(T newVal, int index)
    {
        if(index < size)
        {
        ListNode newNode = new ListNode(newVal);
        ListNode node = head;
        ListNode before = null;
        
        //Before node next = new node
        //New node next = after node
        
        for(int i = 0; i < index; i++)
        {
            if(i == index - 1)
            {
                before = node;
            }
            node = node.getNext();
        }
            before.setNext(newNode);
            newNode.setNext(node);
        }
    }
    
    @Override
    public String toString()
    {
        if(isEmpty())
        {
            return "[]";
        }
        
        String output = "[";
        ListNode node = head;
        
        while(node.getNext() != null)
        {
            output = output + String.valueOf(node.getVal()) + ", ";
            node = node.getNext();
        }
        output = output + String.valueOf(node.getVal()) + "]";
        return output;
    }
}
