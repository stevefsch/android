// Chapter 19 Exercise 5 
// Add a highlight on/off button to the toolbar
/*
  SketchView class:
    - added a boolean field highlighting with a default value of true.
    - added a toggleHighlighting() method to invert the state of highlighting
      and return its value
    - modified the mouseMoved() method in the inner MouseHandler class to do nothing
      when highlighting is true
      
  SketchFram class:
    - added an inner HighlightAction class to define an Action object for the
      toolbar button to toggle highlighting
    - created the toolbar button in the constructor and defined its initial state 
      as selected and defined an icon to be used in its selected state.
      
  I created the icon for the button to indicate the state that the button would
  change to when it is clicked. If you would prefer the icon to reflect the current
  state just switch the icon in the HighlightAction constructor with that in the
  argument to the setSelectedIcon() method call in the SketchFrame constructor.

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
