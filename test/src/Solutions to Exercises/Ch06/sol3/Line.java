// Chapter 6 Exercise 3

// This class now inherits implementation of the ShapeInterface
// interface so it must implement the show() method, otherwise
// it would be and abstract class.

public class Line extends Shape {
  // End point is defined relative to start point:
  private Point end;                    // Line end point.

// Constructor:
  public Line(Point start, Point end) {
    // Assume line is drawn from reference point:
    position = new Point(start);	                  // First point is position.
    this.end = new Point(end.x-start.x, end.y - start.y); // Get end point coords relative to start. 
  }

  // Overrides the method inherited from Object:
  public String toString()  {  
    // Create a string representation of the object:
    return "Line: " + position + " to " + position.add(end);
  }

  // Method to display a line:
  public void show() {
    System.out.println("\n"+toString());
  }
}