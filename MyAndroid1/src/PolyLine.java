public class PolyLine
{
	private ListPoint start;
	private ListPoint end;
		
	public PolyLine(Point[] points)
	{
		if (points != null)
		{
			start = new ListPoint(points[0]);
			end = start;
		}
		
		for (int i = 1; i < points.length; i++ )
		{
			addPoint(points[i]);
		}
	}
	
	public PolyLine(double[][] coords)
	{
		if (coords != null)
		{
			start = new ListPoint(new Point(coords[0][0],coords[0][1]));
			end = start;
					
		}
		
		for (int i = 1; i < coords.length; i ++)
		{
			addPoint(coords[i][0],coords[i][1]);
		}
	}

	public void addPoint(final Point point)
	{
		ListPoint newEnd = new ListPoint(point);
		
		if (null == start)
			start = newEnd;
		else
			end.setNext(newEnd);
		end = newEnd;
	}
	
	public void addPoint(double x, double y)
	{
		addPoint(new Point(x,y));
	}
	
	public String toString()
	{
		StringBuffer str = new StringBuffer("Polyline:");
		ListPoint nextPoint = start;
		
		while(nextPoint != null)
		{
			str.append(" " + nextPoint);
			nextPoint = nextPoint.getNext();
		}
		
		return str.toString();
	}

}
