// Chapter 19 Exercise 2
// Add an element context menu item that will display information about an element.
/*
  This is easy, once you make each element responsible for supplying info
  about itself. This can be done by implementing a method for each element type
  that you can call polymorphically.

 Element class:
  - added an abstract method, getInfo() to return an info string
  - added implementations of the getInfo() method in each of the inner classes
 SketchView class:
  - added the infoItem field that stores a reference to the new menu item
  - added infoItem to the element popup menu
  - extended the actionPerformed() method to respond to Action events for the
    infoItem menu item.

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
