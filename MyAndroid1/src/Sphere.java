/**
 * 
 */

/**
 * @author Steve
 *
 */
public class Sphere {
	
	static final double PI = Math.PI;
	
	double radius = 5.0;
	double xCenter = 10.0;
	double yCenter = 10.0;
	double zCenter = 10.0;

	private static int count = 0;
	
	/**
	 * 
	 */
	public Sphere() {
		// TODO Auto-generated constructor stub
		radius = 1.0;
		xCenter = 0.0;
		yCenter = 0.0;
		zCenter = 0.0;
		++count;
	}
	
	Sphere(double theRadius, double x, double y, double z)
	{
		this.radius = theRadius; //radius = theRadius
		xCenter = x;
		yCenter = y;
		zCenter = z;
		
		++count;
	}
	
	Sphere(double theRadius)
	{
		radius = theRadius;
		xCenter = 1;
		yCenter = 1;
		zCenter = 1;
		++count;
	}
	
	Sphere(final Sphere oldShpere)
	{
		radius = oldShpere.radius;
		xCenter = oldShpere.xCenter;
		yCenter = oldShpere.yCenter;
		zCenter = oldShpere.zCenter;
		++count;
	}
	
	static int getCount()
	{
		return count;
	}
	
	double volume()
	{
		return 4.0/3.0*PI*radius*radius*this.radius;
	}
	
	void changeRadius(double radius)
	{
		this.radius = radius;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sphere ball = new Sphere(10.0,1.0,1.0,1.0);
		Sphere myBall = ball;
		
		System.out.println("The volume of the ball is " + ball.volume());
		myBall.changeRadius(20.0);
		System.out.println("The volume of the ball is " + ball.volume());
		System.out.println("The volume of the myBall is " + myBall.volume());
		
		System.out.println("The count of ball is " + ball.getCount());
		ball.count++;
		System.out.println("The count of myBall is " + myBall.getCount());
		ball.count--;
		System.out.println("The count of myBall is " + myBall.getCount());
		
		Sphere yourBall = new Sphere(30.0);
		System.out.println("The volume of the yourBall is " + yourBall.volume());
		yourBall.changeRadius(20.0);
		System.out.println("The volume of the yourBall is " + yourBall.volume());	
		System.out.println("The count of ball is " + ball.getCount());
		System.out.println("The count of ball is " + yourBall.getCount());
		
		Sphere newBall = new Sphere(myBall);
		System.out.println("The volume of the newBall is " + newBall.volume());
		System.out.println("The count of ball is " + ball.getCount());
		System.out.println("The count of ball is " + newBall.getCount());
	}

}
