
public class LinkedList {
	
	private ListPoint start = null;
	private ListPoint end = null;
	private ListPoint current = null;

	public LinkedList() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedList(final ListPoint item)
	{
		if (item != null)
			current = end = start = item;
	}
	
	public LinkedList(final ListPoint[] items)
	{
		if (items != null)
		{
			for (int i = 0; i < items.length; i++)
			{
				addItem(items[i]);
			}
			current = start;
		}
	}
	
	public void addItem(ListPoint item)
	{
		ListPoint newEnd = item;
		if (start == null)
			start = end = newEnd;
		else
		{
			end.next = newEnd;
			end = newEnd;
		}
	}
	
	public ListPoint getFirst()
	{
		current = start;
		return start == null ? null : start;  //start.point;
	}
	
	public ListPoint getNext()
	{
		if (current != null)
		{
			current = current.next;
		}
		return current == null ? null : current;  //current.point
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
