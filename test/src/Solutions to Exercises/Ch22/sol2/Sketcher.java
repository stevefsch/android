// Chapter 22 Exercise 2
// Importing a sketch using SAX
/*
  The tricky part about this exercise is that with SAX we lose all the structure
  of a sketch in XML. We get each XML element separately, even though some elements
  are nested inside another. As a consequence we have to save data until we have enough
  to construct a sketcher element.
  
  The following changes were made to SketchFrame:
  - deleted the inner class for DOM error handling
  - modified openXMLSketch() to use a SAX parser instead of a DOM parser.
  - added the SAXElementHandler inner class that handles SAX parser events
  
  
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
    sketch.addObserver(window);              // Register window as observer
    
    window.getContentPane().add(view, BorderLayout.CENTER);
    window.setVisible(true);
  }

  public void insertModel(SketchModel newSketch) {
    sketch = newSketch;                          // Store the new sketch
    sketch.addObserver((Observer)view);          // Add the view as observer
    sketch.addObserver((Observer)window);        // Add the app window as
                                                 // observer
    view.repaint();                              // Repaint the view
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
    public void windowClosing(WindowEvent e) {
      window.checkForSave();
    }
  }

  private SketchModel sketch;                     // The data model for the sketch
  private SketchView view;                        // The view of the sketch
  private static SketchFrame window;              // The application window
  private static Sketcher theApp;                 // The application object
}
