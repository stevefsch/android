import java.util.Observable;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.Serializable;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMException;

class SketchModel extends Observable implements Serializable {
  // Get the rectangle enclosing an entire sketch
  Rectangle getModelExtent() {
    Iterator elements = getIterator();
    Rectangle rect = new Rectangle();                // An empty rectangle
    Element element;                                 // Stores an element
    while(elements.hasNext()) {                      // Go through the list
      element = (Element)elements.next();            // Get the next element
      rect.add(element.getBounds());                 // Expand union
    }
    if(rect.width == 0)                              // Make sure width
      rect.width = 1;                                // is non-zero
    if(rect.height == 0)                             // and the height
      rect.height = 1;
    return rect;
  }

  public boolean remove(Element element) {
    boolean removed = elementList.remove(element);
    if(removed) {
      setChanged();
      notifyObservers(element.getBounds());
    }

    return removed;
  }
  
  public void add(Element element) {
    elementList.add(element);
    setChanged();
    notifyObservers(element.getBounds());
  }

  public Iterator getIterator() {
    return elementList.listIterator();  
  }

  // Creates a DOM Document object encapsulating the current sketch  
  public Document createDocument() {
    Document doc = null;
    try {
      DOMImplementation domImpl = DocumentBuilderFactory.newInstance()
                                                        .newDocumentBuilder()
                                                        .getDOMImplementation();
      doc = domImpl.createDocument(null, "sketch",
                   domImpl.createDocumentType("sketch", null, "sketcher.dtd"));

    } catch(ParserConfigurationException e) {
      JOptionPane.showInternalMessageDialog(null,
                  "Parser configuration error while creating document",
                  "DOM Parser Error",
                  JOptionPane.ERROR_MESSAGE); 
 

      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
      return null;

    } catch(DOMException e) {
      JOptionPane.showInternalMessageDialog(null,
                  "DOM exception thrown while creating document",
                  "DOM Error",
                  JOptionPane.ERROR_MESSAGE); 
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
      return null;
    }

    // Each element in the sketch can create its own node in the document
    Iterator iter = getIterator();                 // Iterator for sketch elements
    while(iter.hasNext())                          // For each element...
      ((Element)iter.next()).addElementNode(doc);  // ...add its node.
    return doc;
  }

  protected LinkedList elementList = new LinkedList();
}
