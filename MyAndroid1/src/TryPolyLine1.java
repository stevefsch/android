
public class TryPolyLine1 {

	public TryPolyLine1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] coords = {{1.0, 1.0} , {1.0, 2.0}, {2.0, 3.0}, {-3.0, 5.0}, {-5.0, 1.0}, {0.0,0.0}}; 
		PolyLine1 polygon = new PolyLine1(coords);
		System.out.println(polygon);
		
		polygon.addPoint(10.0,10.0);
		System.out.println(polygon);
		
		Point[] points = new Point[coords.length];
		for (int i = 0; i < coords.length; i++)
		{
			points[i] = new Point(coords[i][0],coords[i][1]);
		}

	}

}
