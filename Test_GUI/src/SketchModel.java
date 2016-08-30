import java.util.*;
import java.awt.*;

public class SketchModel extends Observable{

	protected LinkedList elementList = new LinkedList();
	private boolean removed = false;

	public SketchModel() {
		// TODO Auto-generated constructor stub
	}
		
	public boolean remove(Elements element)
	{ 
		if (removed)
		{
			setChanged();
			notifyObservers(element.getBounds());
			removed = false;
		}
		return removed;
	}
	
	public void add(Elements element)
	{
		elementList.add(element);
		setChanged();
		notifyObservers(element.getBounds());
	}
	
	public Iterator getIterator()
	{
		return elementList.listIterator();
	}	
}
