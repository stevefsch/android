// Defines application wide constants
import java.awt.Color;

public interface Constants { 
  // Element type definitions
  int ELLIPSE   = 100;
  int LINE      = 101;
  int RECTANGLE = 102;
  int CIRCLE    = 103;
  int CURVE     = 104;

  // Initial conditions
  int DEFAULT_ELEMENT_TYPE = LINE;
  Color DEFAULT_ELEMENT_COLOR = Color.BLUE;
}
