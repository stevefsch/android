/**
 * 
 */

/**
 * @author Steve
 *
 */
public class Line {
	
	Point start;
	Point end;

	/**
	 * 
	 */
	public Line() {
		// TODO Auto-generated constructor stub
		start = new Point(0.0,0.0); 
		end = new Point();
	}
	
	Line(final Point start, final Point end)
	{
		this.start = new Point(start);
		this.end = new Point(end);
	}
	
	Line(double xStart, double yStart, double xEnd, double yEnd)
	{
		start = new Point(xStart,yStart);
		end = new Point(xEnd,yEnd);
	}
	
	Point intersects(final Line line1)
	{
		Point localPoint = new Point(0.0,0.0);
		double num = 
				(this.end.y - this.start.y)*(this.start.x - line1.start.x) - 
				(this.end.x - this.start.x)*(this.start.y - line1.start.y);
		
		double denom = 
				(this.end.y - this.start.y)*(line1.end.x - line1.start.x) - 
				(this.end.x - this.start.x)*(line1.end.y - line1.start.y);
		
		localPoint.x = line1.start.x + (line1.end.x - line1.start.x)*num/denom;
		localPoint.y = line1.start.y + (line1.end.y - line1.start.y)*num/denom;
		
		return localPoint;
				
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point start = new Point(0.0,1.0);
		Point end = new Point(5.0,6.0);
		Point intersectPoint = new Point(0.0,0.0);
		System.out.println("Points created are " + start.x + "," + start.y + " and " + end.x + "," + end.y);
		
		Line line1 = new Line(start, end);
		Line line2 = new Line(0.0,3.0,3.0,0.0);
		
		//System.out.println("lines created are " + line1 + " and " + line2);
		
		end.move(1.0,-5.0);
		
		intersectPoint = line1.intersects(line2);
		
		System.out.println("intersection is " + intersectPoint.x + "," + intersectPoint.y );

	}

}
