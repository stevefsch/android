public class CreateSpheres
{
  public static void main(String[] args)
  {
    System.out.println("Number of objects = " + Sphere.getCount());

    Sphere ball = new Sphere(4.0, 0.0, 0.0, 0.0);                // Create a sphere
    System.out.println("Number of objects = " + ball.getCount());

    Sphere globe = new Sphere(12., 1.0, 1.0, 1.0);               // Create a sphere
    System.out.println("Number of objects = " + Sphere.getCount());

    Sphere eightBall = new Sphere(10.0, 10.0, 0.0);
    Sphere oddBall = new Sphere();
    System.out.println("Number of objects = " + Sphere.getCount());

    // Output the volume of each sphere
    System.out.println("ball volume = " + ball.volume());
    System.out.println("globe volume = " + globe.volume());
    System.out.println("eightBall volume = " + eightBall.volume());
    System.out.println("oddBall volume = " + oddBall.volume());
  }
}
