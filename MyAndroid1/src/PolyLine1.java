
public class PolyLine1 {
	private LinkedList polyline;

	public PolyLine1(double[][] coords) {
		// TODO Auto-generated constructor stub	
		ListPoint[] listPoints = new ListPoint[coords.length];
		for (int i = 0; i < coords.length; i++)
		{
			listPoints[i] = new ListPoint(coords[i][0],coords[i][1]);
		}
		
		polyline = new LinkedList(listPoints);
	}
	
	public PolyLine1(Point[] points)
	{
		ListPoint[] listPoints = new ListPoint[points.length];
		for (int i = 0; i < points.length; i++)
		{
			listPoints[i] = new ListPoint(points[i]);
		}
		polyline = new LinkedList(listPoints);
	}
	
	public void addPoint(Point point)
	{
		ListPoint listPoint = new ListPoint(point);
		polyline.addItem(listPoint);
	}
	
	public final void addPoint(double x, double y)
	{
		ListPoint listPoint = new ListPoint(x,y);
		polyline.addItem(listPoint);
	}
	
	public String toString()
	{
		StringBuffer str = new StringBuffer("poly line:");
		ListPoint listPoint = polyline.getFirst();
		
		while (listPoint != null)
		{
			str.append("(" + listPoint + ")");
			listPoint = (ListPoint)polyline.getNext();
		}
		
		return str.toString();		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
