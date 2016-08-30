// Chapter 19 Exercise 4
// Change the element classes to make use of the combined translate & rotate method
/*
 This exercise is really to get you thinking about what these transforms - and the
 combined rotate and translate in particular - do.
 The operation:
 
   g2D.rotate(angle, x, y);
   
   is equivalent to:
   
   g2D.translate(x, y);   // Move to new point
   g2D.rotate(angle);     // Rotate about that point
   g2D.translate(-x, -y); // Move back to the original point

 In other words, the operation rotates the coordinate system about the point x,y
 but leaves the origin where it is.
 
 This is not particularly useful in our Element classes. The use of this operation in our
 element classes is to apply the rotation about the reference point, position, for an
 element, but we still must apply the translation to position before drawing the element.
 The net effect therefore is to change to sequence of the ratate and translate operations.
 Since this involves two more translations than our previous code, the old code is
 better! Still, you should have a clearer idea of what this operation does.
 
 Element class:
  - removed the original rotate operation in the draw() method.
  - added a trnaslate/rotate operation BEFORE the existing translate operation
    
   

*/
// Sketching application
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.awt.BorderLayout;
import java.util.Observer;

public class Sketcher {

  public static void main(String[] args) {
    theApp = new Sketcher();              // Create the application object
    theApp.init();                        // ...and initialize it
  }

  public void init() {
    window = new SketchFrame("Sketcher", this);    // Create the app window
    Toolkit theKit = window.getToolkit();        // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();  // Get screen size

    // Set the position to screen center & size to half screen size
    window.setBounds(wndSize.width/4, wndSize.height/4,        // Position
                     wndSize.width/2, wndSize.height/2);       // Size

    window.addWindowListener(new WindowHandler());  // Add window listener
    sketch = new SketchModel();               // Create the model
    view = new SketchView(this);              // Create the view
    sketch.addObserver((Observer)view);       // Register the view with the model
    window.getContentPane().add(view, BorderLayout.CENTER);
    window.setVisible(true);
  }
  
  // Return a reference to the application window
  public SketchFrame getWindow() { 
     return window; 
  }

  // Return a reference to the model
  public SketchModel getModel() { 
     return sketch; 
  }

  // Return a reference to the view
  public SketchView getView() { 
     return view; 
  }

  // Handler class for window events
  class WindowHandler extends WindowAdapter {
    // Handler for window closing event
    public void windowClosing(WindowEvent e)
    {
      // Code to be added here later...
    }
  }

  private SketchModel sketch;                     // The data model for the sketch
  private SketchView view;                        // The view of the sketch
  private static SketchFrame window;              // The application window
  private static Sketcher theApp;                 // The application object
}
