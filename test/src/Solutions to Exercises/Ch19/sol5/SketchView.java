import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import java.util.Observer;                  
import java.util.Observable;                  
import java.util.Iterator;

import java.awt.Graphics;
import java.awt.Graphics2D;                          
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;

import java.awt.event.ActionEvent;    
import java.awt.event.ActionListener;    
import java.awt.event.MouseEvent;    

import java.awt.geom.Line2D;    

import java.awt.font.TextLayout;  

import javax.swing.event.MouseInputAdapter;    

class SketchView extends JComponent implements Observer, Constants, ActionListener {
  public SketchView(Sketcher theApp) {
    this.theApp = theApp;
    MouseHandler handler = new MouseHandler();        // create the mouse listener
    addMouseListener(handler);                        // Listen for button events
    addMouseMotionListener(handler);                  // Listen for motion events

    // Add the pop-up menu items to the Element popup
    moveItem = elementPopup.add("Move");
    deleteItem = elementPopup.add("Delete");
    rotateItem = elementPopup.add("Rotate");
    sendToBackItem = elementPopup.add("Send-to-back");
    infoItem = elementPopup.add("Info");
    editItem = new JMenuItem("Edit");

    // Add the menu item listeners
    moveItem.addActionListener(this);
    deleteItem.addActionListener(this);
    rotateItem.addActionListener(this);
    sendToBackItem.addActionListener(this);
    infoItem.addActionListener(this);
    editItem.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e ) {
    Object source = e.getSource();
    if(source == moveItem) {
      mode = MOVE;
      selectedElement = highlightElement;

    } else if(source == deleteItem) {
      if(highlightElement != null) {                    // If there's an element
        theApp.getModel().remove(highlightElement);     // then remove it
        highlightElement = null;                        // Remove the reference
      }

    } else if(source == rotateItem) {
      mode = ROTATE;
      selectedElement = highlightElement;

    } else if(source == sendToBackItem) {
      if(highlightElement != null) {
        theApp.getModel().remove(highlightElement);
        theApp.getModel().add(highlightElement);
        highlightElement.setHighlighted(false);
        highlightElement = null;
        repaint();
      }
    } else if(source == infoItem) {
        JOptionPane.showMessageDialog(null, highlightElement.getInfo());
    } else if(source == editItem) {
        String newText = JOptionPane.showInputDialog("Edit the current element text:",
                                        ((Element.Text)highlightElement).getText());

        if(newText != null && newText.length()!=0)  {          // Do we have new text?        
          // If so, update the element
          // Note that if we change the text we need to obtain the
          // bounds for the new text, otherwise it might not display properly.
          ((Element.Text)highlightElement).setText(newText,       // The new text plus
          new java.awt.font.TextLayout(newText,
                      ((Element.Text)highlightElement).getFont(), // its bounding rectangle
          ((Graphics2D)getGraphics()).getFontRenderContext()).getBounds().getBounds());
        }
    } 
  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D)g;                 // Get a 2D device context
    Iterator elements = theApp.getModel().getIterator();
    while(elements.hasNext())                       // Go through the list
      ((Element)elements.next()).draw(g2D);         // Get the next element to draw 
                                                    // itself
  }

  public void update(Observable o, Object rectangle) {
    if(rectangle == null)
      repaint();
    else
      repaint((Rectangle)rectangle);
  }

  class MouseHandler extends MouseInputAdapter {
    public void mousePressed(MouseEvent e) {
      start = e.getPoint();                   // Save the cursor position in start
      if((button1Down = (e.getButton()==MouseEvent.BUTTON1))) { 
        g2D = (Graphics2D)getGraphics();                 // Get graphics context
        g2D.setXORMode(getBackground());                 // Set XOR mode
        g2D.setPaint(theApp.getWindow().getElementColor());     // Set color
      }
    }

    public void mouseDragged(MouseEvent e) {
      last = e.getPoint();                               // Save cursor position

      if(button1Down && (theApp.getWindow().getElementType() != TEXT) 
                     && (mode == NORMAL)) {
        if(tempElement == null) {                       // Is there an element?
          tempElement = createElement(start, last);     // No, so create one
        } else {
          tempElement.draw(g2D);                          // Yes - draw to erase it
          tempElement.modify(start, last);              // Now modify it
        }
        tempElement.draw(g2D);                          // and draw it      }

      } else if(button1Down && mode == MOVE && selectedElement != null) {
        selectedElement.draw(g2D);                    // Draw to erase the element
        selectedElement.move(last.x-start.x, last.y-start.y);  // Move it
        selectedElement.draw(g2D);                     // Draw in its new position
        start = last;                                  // Make start current point

      } else if(button1Down && mode == ROTATE && selectedElement != null) {
        selectedElement.draw(g2D);                   // Draw to erase the element
        selectedElement.rotate(getAngle(selectedElement.getPosition(),
                                                                    start, last));
        selectedElement.draw(g2D);                  // Draw in its new position
        start = last;                               // Make start current point
      }
    }

    public void mouseReleased(MouseEvent e) {
      if(e.isPopupTrigger()) {
        start = e.getPoint();

        if(highlightElement==null)
          theApp.getWindow().getPopup().show((Component)e.getSource(), 
                                             start.x, start.y);
        else {
          if(highlightElement instanceof Element.Text) {
            elementPopup.add(editItem);
            elementPopup.show((Component)e.getSource(), start.x, start.y);
          } else {
            elementPopup.remove(editItem);
            elementPopup.show((Component)e.getSource(), start.x, start.y);
          }
        }

      } else if((e.getButton()==MouseEvent.BUTTON1) && 
              (theApp.getWindow().getElementType() != TEXT) && mode == NORMAL) {
        button1Down = false;                    // Reset the button 1 flag
        if(tempElement != null) {
	     theApp.getModel().add(tempElement);  // Add element to the model
          tempElement = null;                   // No temporary now stored
        }
      } else if((e.getButton()==MouseEvent.BUTTON1) &&
                (mode == MOVE || mode == ROTATE)) {
        button1Down = false;                    // Reset the button 1 flag
        if(selectedElement != null)
          repaint();
        mode = NORMAL;
      }

      if(g2D != null) {
        g2D.dispose();                       // Release graphic context resource
        g2D = null;                          // Set it to null
      }
      start = last = null;                   // Remove the points
      selectedElement = tempElement = null;  // Reset elements
    }

    private Element createElement(Point start, Point end) {
      switch(theApp.getWindow().getElementType()) {
        case LINE:
           return new Element.Line(start, end,
                                  theApp.getWindow().getElementColor());   
        case RECTANGLE:
           return new Element.Rectangle(start, end,
                                        theApp.getWindow().getElementColor());
        
        case CIRCLE:
           return new Element.Circle(start, end, 
                                     theApp.getWindow().getElementColor());

        case CURVE:
         return new Element.Curve(start, end,
                                  theApp.getWindow().getElementColor());
        default:
          assert false;                     // We should never get to here
     }
      return null;
    }

    // Helper method for calculating getAngle()
    double getAngle(Point position, Point start, Point last) {
      // Get perpendicular distance from last to the line from position to start
      double perp = Line2D.ptLineDist(position.x, position.y,
                                      last.x, last.y, start.x, start.y);
      // Get the distance from position to start
      double hypotenuse = position.distance(start);
      if(hypotenuse == 0.0)                        // Make sure its
        hypotenuse = 1.0;                          // non-zero

      // Angle is the arc sine of perp/hypotenuse. Clockwise is positive angle
      return -Line2D.relativeCCW(position.x, position.y,
                                 start.x, start.y,
                                 last.x, last.y)*Math.asin(perp/hypotenuse);
    }

    public void mouseClicked(MouseEvent e) {
      if((e.getButton()== MouseEvent.BUTTON1) && 
         (theApp.getWindow().getElementType() == TEXT)) {

        start = e.getPoint();              // Save cursor position - start of text
        String text = JOptionPane.showInputDialog(
                     (Component)e.getSource(),            // Used to get the frame
                     "Enter Text:",                       // The message
                     "Dialog for Text Element",           // Dialog title
                     JOptionPane.PLAIN_MESSAGE);          // No icon

        if(text != null && text.length()!=0)  {           // If we have text        
                                                         // create the element
          g2D = (Graphics2D)getGraphics();
          Font font = theApp.getWindow().getCurrentFont();
          tempElement = new Element.Text(
                       font,                                  // The font
                       text,                                  // The text
                       start,                                 // Position of the text
                       theApp.getWindow().getElementColor(),  // The text color
               new java.awt.font.TextLayout(text, font,       // The bounding rectangle
          g2D.getFontRenderContext()).getBounds().getBounds());

          if(tempElement != null) {                          // If we created one
            theApp.getModel().add(tempElement);              // add it to the model
            tempElement = null;                              // and reset the field
          }
          g2D.dispose();                                     // Release context resources
          g2D = null;
          start = null;
        }
      }
    }

    // Handle mouse moves
    public void mouseMoved(MouseEvent e) {
      if(!highlighting)      // If highlight flag is false 
        return;              // then do nothing

      Point currentCursor = e.getPoint();  // Get current cursor position
      Iterator elements = theApp.getModel().getIterator();
      Element element = null;                             // Stores an element

      while(elements.hasNext())  {                        // Go through the list
        element = (Element)elements.next();               // Get the next element
        if(element.getBounds().contains(currentCursor)) { // Under the cursor?
          if(element==highlightElement)            // If its already highlighted
            return;                                // we are done
          g2D = (Graphics2D)getGraphics();         // Get graphics context
          if(highlightElement!=null)   {           // If an element is highlighted
            highlightElement.setHighlighted(false);// un-highlight it and
            highlightElement.draw(g2D);            // draw it normal color
          }
          element.setHighlighted(true);           // Set highlight for new element
          highlightElement = element;             // Store new highlighted element
          element.draw(g2D);                      // Draw it highlighted 
          g2D.dispose();                      // Release graphic context resources
          g2D = null;

          return;
        }
      }

      // Here there is no element under the cursor so...
      if(highlightElement!=null)   {            // If an element is highlighted
        g2D = (Graphics2D)getGraphics();        // Get graphics context
        highlightElement.setHighlighted(false); // ...turn off highlighting
        highlightElement.draw(g2D);             // Redraw the element
        highlightElement = null;                // No element highlighted
        g2D.dispose();                        // Release graphic context resources
        g2D = null;
      }
    }

    private Point start;                     // Stores cursor position on press
    private Point last;                      // Stores cursor position on drag
    private Element tempElement;             // Stores a temporary element

    private boolean button1Down = false;          // Flag for button 1 state
    private Graphics2D g2D = null;                   // Temporary graphics context
  }

  // Method to toggle highlighting - called by the app window.
  public boolean toggleHighlighting() {
    return (highlighting = !highlighting);
  }

  private Element selectedElement;
  private int mode = NORMAL;

  private JPopupMenu elementPopup = new JPopupMenu("Element");
  private JMenuItem moveItem, deleteItem,rotateItem, sendToBackItem;
  private JMenuItem infoItem, editItem;
  private Sketcher theApp;                  // The application object
  private Element highlightElement;         // Highlighted element
  private boolean highlighting = true ;     // Highlighting flag
}
