/**
 * 
 */

/**
 * @author Steve
 *
 */
public class Point {
	
	
	double x;
	double y;	

	/**
	 * 
	 */
	public Point() {
		// TODO Auto-generated constructor stub
		x = 0;
		y = 0;
	}
	
	Point(double xValue, double yValue)
	{
		x = xValue;
		y = yValue;
	}
	
	Point(final Point oldPoint)
	{
		x = oldPoint.x;
		y = oldPoint.y;
	}
	
	void move(double xDelta, double yDelta)
	{
		x += xDelta;
		y += yDelta;
	}
	
	double distance(final Point aPoint)
	{
		return
				(x-aPoint.x)*(x-aPoint.x) + (y - aPoint.y)*(y-aPoint.y);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String toString()
	{
		return x + " " + y;
	}

}
