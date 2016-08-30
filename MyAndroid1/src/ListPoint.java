/**
 * 
 */

/**
 * @author Steve
 *
 */
public class ListPoint {
	private Point point;
	public ListPoint next;
	
	/**
	 * 
	 */
	public ListPoint(Point point) {
		// TODO Auto-generated constructor stub
		this.point = point;
		next = null;
	}
	
	public ListPoint(double x, double y) {
		// TODO Auto-generated constructor stub
		this.point = new Point(x,y);
		next = null;
	}
	
	public void setNext(ListPoint next)
	{
		this.next = next;
	}
	
	public ListPoint getNext()
	{
		return this.next;
	}
	
	public String toString()
	{
		return "(" + point + ")";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

