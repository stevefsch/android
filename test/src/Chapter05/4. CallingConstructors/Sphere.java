class Sphere
{
  static final double PI = 3.14; // Class variable that has a fixed value
  static int count = 0;          // Class variable to count objects

  // Instance variables
  double radius;                 // Radius of a sphere

  double xCenter;                // 3D coordinates
  double yCenter;                // of the center
  double zCenter;                // of a sphere

  // Class constructors

  // Construct a unit sphere at the origin
  Sphere()
  {
    radius = 1.0;
    // Other data members will be zero by default
  ++count;                    // Update object count
  }

  // Construct a unit sphere at a point
  Sphere(double x, double y, double z)
  {
    this();                    // Call the constructor with no arguments
    xCenter = x;
    yCenter = y;
    zCenter = z;
  }

  Sphere(double theRadius, double x, double y, double z)
  {
    this(x, y, z);              // Call the 3 argument constructor
    radius = theRadius;         // Set the radius
  }

  // Static method to report the number of objects created
  static int getCount()
  {
    return count;               // Return current object count
  }

  // Instance method to calculate volume
  double volume()
  {
    return 4.0/3.0*PI*radius*radius*radius;
  }
}
