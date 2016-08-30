// Chapter 18 Exercise 1
// Add the ability to draw an ellipse
/*
 In SketchFrame class:
  - added ellipseAction member
  - constructor creates ellipseAction Action object and Ellipse menu item 
  - constuctor adds a toolbar button for creating an ellipse 
  
  In Element class:
  - added Ellipse sub- and inner class to Element 
  
  In SketchView.MouseHandler class:
  - added ELLIPSE case to the switch statement in the createElement() method
  
  In Constants interface:
  - added ELLIPSE contant definition
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
      // Code to be added here later...
  }

  private SketchModel sketch;                     // The data model for the sketch
  private SketchView view;                        // The view of the sketch
  private static SketchFrame window;              // The application window
  private static Sketcher theApp;                 // The application object
}
