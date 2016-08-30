// Chapter 6 Exercise 3

public class Point {
  protected double x;
  protected double y;

// Constructors:

  public Point() {
    x = 0.0;
    y = 0.0;
  }

  // Construct a Point from its coordinates:
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // Construct a Point from another Point;
  public Point(Point point) {
    x = point.x;
    y = point.y;
  }

  // Method to return a point defined relative to this
  // point in coordinates:
  public Point add(Point z) {
    return new Point(x+z.x,y+z.y);
  }

  // Overrides the method inherited from Object:
  public String toString() {
    return "Point: " + x + "," + y;
  }
}