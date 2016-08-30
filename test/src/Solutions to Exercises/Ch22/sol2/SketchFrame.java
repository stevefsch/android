// Frame for the Sketcher application
import javax.swing.*;

import java.awt.Color;
import java.awt.Event;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.ParserConfigurationException;

import java.util.Observer;
import java.util.Observable;
import java.util.Vector;

import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PageFormat;

public class SketchFrame extends JFrame
                         implements Constants, ActionListener, Observer {
  // Method called by SketchModel object when it changes
  public void update(Observable o, Object obj) {
    sketchChanged = true;
  }

  // Constructor
  public SketchFrame(String title, Sketcher theApp) {
    //setTitle(title);                             // Set the window title
    this.theApp = theApp;
    setJMenuBar(menuBar);                       // Add the menu bar to the window
    setDefaultCloseOperation(EXIT_ON_CLOSE);      // Default is exit the application

    JMenu fileMenu = new JMenu("File");          // Create File menu
    JMenu elementMenu = new JMenu("Elements");   // Create Elements menu
    JMenu optionsMenu = new JMenu("Options");              // Create options menu
    JMenu helpMenu = new JMenu("Help");           // Create Help menu

    fileMenu.setMnemonic('F');                   // Create shortcut
    elementMenu.setMnemonic('E');                // Create shortcut
    optionsMenu.setMnemonic('O');                          // Create shortcut
    helpMenu.setMnemonic('H');                    // Create shortcut 

    // Create the action items for the file menu
    newAction = new FileAction("New", KeyStroke.getKeyStroke('N',Event.CTRL_MASK ), "Create new sketch");
    openAction = new FileAction("Open", KeyStroke.getKeyStroke('O',Event.CTRL_MASK), "Open existing sketch");
    saveAction = new FileAction("Save", KeyStroke.getKeyStroke('S',Event.CTRL_MASK), "Save sketch");
    saveAsAction = new FileAction("Save As...", "Save as new file");
    printAction = new FileAction("Print", KeyStroke.getKeyStroke('P',Event.CTRL_MASK), "Print sketch");

    addMenuItem(fileMenu, newAction);
    addMenuItem(fileMenu, openAction);
    fileMenu.addSeparator();                                      // Add separator
    addMenuItem(fileMenu, saveAction);
    addMenuItem(fileMenu, saveAsAction);
    fileMenu.addSeparator();                                      // Add separator

    addMenuItem(fileMenu, 
            exportAction = new XMLExportAction("Export XML",
                                               "Export sketch as an XML file"));
    addMenuItem(fileMenu, 
            importAction = new XMLImportAction("Import XML",
                                               "Import sketch from an XML file"));
    fileMenu.addSeparator();                                    // Add separator

    addMenuItem(fileMenu, printAction);

    // Construct the Element pull down menu
    addMenuItem(elementMenu, lineAction = new TypeAction("Line", LINE, "Draw lines")); 
    addMenuItem(elementMenu, rectangleAction = new TypeAction("Rectangle",RECTANGLE, "Draw rectangles"));
    addMenuItem(elementMenu, circleAction = new TypeAction("Circle", CIRCLE, "Draw circles"));
    addMenuItem(elementMenu, curveAction = new TypeAction("Curve", CURVE, "Draw curves"));
    addMenuItem(elementMenu, textAction = new TypeAction("Text", TEXT, "Draw text"));

    elementMenu.addSeparator();                      // Add separator

    JMenu colorMenu = new JMenu("Color");         // Color sub-menu
    elementMenu.add(colorMenu);                   // Add the sub-menu

    addMenuItem(colorMenu, redAction = new ColorAction("Red", Color.RED, "Draw in red"));
    addMenuItem(colorMenu, yellowAction = new ColorAction("Yellow", Color.YELLOW, "Draw in yellow"));
    addMenuItem(colorMenu, greenAction = new ColorAction("Green", Color.GREEN, "Draw in green"));
    addMenuItem(colorMenu, blueAction = new ColorAction("Blue", Color.BLUE, "Draw in blue"));
    
    menuBar.add(fileMenu);                       // Add the file menu
    menuBar.add(elementMenu);                    // Add the element menu 
    menuBar.add(optionsMenu);                     // Add the options menu
    
    toolBar.setFloatable(false);    // Inhibit toolbar floating
    getContentPane().add(toolBar, BorderLayout.NORTH);

    // Add file buttons
    toolBar.addSeparator();                                 // Space at the start
    addToolBarButton(newAction);
    addToolBarButton(openAction);
    addToolBarButton(saveAction);
    addToolBarButton(printAction);
    
    fileMenu.addSeparator();                                // Add separator
    addMenuItem(fileMenu, closeAction = new FileAction("Exit",
                KeyStroke.getKeyStroke('X',Event.CTRL_MASK ),
                "Exit Sketcher"));

    // Add element type buttons
    toolBar.addSeparator();
    addToolBarButton(lineAction);
    addToolBarButton(rectangleAction);
    addToolBarButton(circleAction);
    addToolBarButton(curveAction);
    addToolBarButton(textAction);

    // Add element color buttons
    toolBar.addSeparator();
    addToolBarButton(redAction);
    addToolBarButton(yellowAction);
    addToolBarButton(greenAction);
    addToolBarButton(blueAction);
    toolBar.addSeparator();                            // Space at the end

    toolBar.setBorder(BorderFactory.createCompoundBorder(       // Toolbar border
                      BorderFactory.createLineBorder(Color.darkGray),
                      BorderFactory.createEmptyBorder(2,2,4,2)));   

    toolBar.setFloatable(false);                       // Inhibit toolbar floating
    getContentPane().add(toolBar, BorderLayout.NORTH); // Add the toolbar
    
    // Disable actions
    //saveAction.setEnabled(false);
    //closeAction.setEnabled(false);
    //printAction.setEnabled(false);

    getContentPane().add(statusBar, BorderLayout.SOUTH);        // Add the statusbar

    // Add the About item to the Help menu
    aboutItem = new JMenuItem("About");           // Create the item
    aboutItem.addActionListener(this);            // Listener is the frame
    helpMenu.add(aboutItem);                      // Add item to menu
    menuBar.add(helpMenu);                        // Add the Help menu

    // Add the font choice item to the options menu
    fontItem = new JMenuItem("Choose font...");
    fontItem.addActionListener(this);
    optionsMenu.add(fontItem);

    fontDlg = new FontDialog(this);
    
    // Create pop-up menu
    popup.add(lineAction);
    popup.add(rectangleAction);
    popup.add(circleAction);
    popup.add(curveAction);
    popup.add(textAction);

    popup.addSeparator();
    popup.add(redAction);
    popup.add(yellowAction);
    popup.add(greenAction);
    popup.add(blueAction);

    customColorItem = popup.add("Custom Color...");    // Add the item
    customColorItem.addActionListener(this);           // and add its listener
    
    frameTitle = title + ": ";
    setTitle(frameTitle + filename);

    if(!DEFAULT_DIRECTORY.exists())
      if(!DEFAULT_DIRECTORY.mkdirs())
        JOptionPane.showMessageDialog(this,
                                      "Error creating default directory",
                                      "Directory Creation Error",
                                      JOptionPane.ERROR_MESSAGE);

    files = new JFileChooser(DEFAULT_DIRECTORY);
  }

  // Method for opening file
  public void openSketch(File inFile) {
    try {
      ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                                       new FileInputStream(inFile)));
      theApp.insertModel((SketchModel)in.readObject());
      in.close();
      modelFile = inFile;
      filename = modelFile.getName();            // Update the file name
      setTitle(frameTitle+modelFile.getPath());  // Change the window title
      sketchChanged = false;                     // Status is unchanged
    } catch(Exception e) {
      System.out.println(e);
      JOptionPane.showMessageDialog(SketchFrame.this,
                                    "Error reading a sketch file.",
                                    "File Input Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  // Prompt for save operation when necessary
  public void checkForSave() {
    if(sketchChanged)
      if(JOptionPane.YES_OPTION ==
            JOptionPane.showConfirmDialog(SketchFrame.this,
                             "Current file has changed. Save current file?",
                             "Confirm Save Current File",
                             JOptionPane.YES_NO_OPTION,
                             JOptionPane.WARNING_MESSAGE))
        saveOperation();
  }

  // Write a sketch to outFile
  private void saveSketch(File outFile) {
    try {
      ObjectOutputStream out =  new ObjectOutputStream(
                                new BufferedOutputStream(
                                new FileOutputStream(outFile)));
      out.writeObject(theApp.getModel());        // Write the sketch to the
                                                 // stream
      out.close();                               // Flush & close it
    } catch(IOException e) {
      System.err.println(e);
      JOptionPane.showMessageDialog(SketchFrame.this,
                                    "Error writing a sketch file.",
                                    "File Output Error",
                                    JOptionPane.ERROR_MESSAGE);
      return;                                    // Serious error - return
    }
    if(outFile != modelFile) {             // If we are saving to a new file
                                           // we must update the window
      modelFile = outFile;                       // Save file reference
      filename = modelFile.getName();            // Update the file name
      setTitle(frameTitle + modelFile.getPath());  // Change the window
                                                   // title
    }
    sketchChanged = false;                       // Set as unchanged
  }

  // Save the sketch if it is necessary
  private void saveOperation() {
    if(!sketchChanged)
      return;

    File file = modelFile;
    if(file == null) {
      file = showDialog("Save Sketch",
                        "Save",
                        "Save the sketch",
                        's',
                        new File(files.getCurrentDirectory(), filename));
      if(file == null || (file.exists() &&                      // Check for existence
        JOptionPane.NO_OPTION ==          // Overwrite warning
            JOptionPane.showConfirmDialog(SketchFrame.this,
                                          file.getName()+" exists. Overwrite?",
                                          "Confirm Save As",
                                          JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE)))
                                          return;                            // No selected file
    }
    saveSketch(file);    
  }

  // Display a custom file save dialog
  private File showDialog(String dialogTitle,
                          String approveButtonText,
                          String approveButtonTooltip,
                          char approveButtonMnemonic,
                          File file) {                // Current file - if any

    files.setDialogTitle(dialogTitle);
    files.setApproveButtonText(approveButtonText);
    files.setApproveButtonToolTipText(approveButtonTooltip);
    files.setApproveButtonMnemonic(approveButtonMnemonic);
    files.setFileSelectionMode(files.FILES_ONLY);
    files.rescanCurrentDirectory();
    files.setSelectedFile(file);

    ExtensionFilter sketchFilter = new ExtensionFilter(".ske",
                                                    "Sketch files (*.ske)");
    files.addChoosableFileFilter(sketchFilter);            // Add the filter
    files.setFileFilter(sketchFilter);                     // and select it

    int result = files.showDialog(SketchFrame.this, null);  // Show the dialog
    return (result == files.APPROVE_OPTION) ? files.getSelectedFile() : null;
  }

  // Retrieve the pop-up menu
  public JPopupMenu getPopup() {  
    return popup;  
  }

  private JButton addToolBarButton(Action action) {
    JButton button = toolBar.add(action);              // Add toolbar button
    button.setBorder(BorderFactory.createRaisedBevelBorder());// Add button border
    return button;
  }

  // Method to save the current sketch as an XML document
  private void saveXMLSketch(File outFile) {
    FileOutputStream outputFile = null;      // Stores an output stream reference 
    try {
      outputFile = new FileOutputStream(outFile);    // Output stream for the file 
      FileChannel outChannel = outputFile.getChannel(); // Channel for file stream 
      writeXMLFile(theApp.getModel().createDocument(), outChannel);

    } catch(FileNotFoundException e) {
      e.printStackTrace(System.err);
      JOptionPane.showMessageDialog(SketchFrame.this,
                       "Sketch file " + outFile.getAbsolutePath() + " not found.",
                       "File Output Error",
                       JOptionPane.ERROR_MESSAGE);
      return;                           // Serious error - return
    }
  }

  // Method to write the XML file for the current sketch (helper for saveXMLSketch())
  private void writeXMLFile(org.w3c.dom.Document doc, FileChannel channel) {
    StringBuffer xmlDoc = new StringBuffer(
                                 "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); 
    xmlDoc.append(NEWLINE).append(getDoctypeString(doc.getDoctype()));
    xmlDoc.append(getDocumentNode(doc.getDocumentElement(), ""));
 
    try {
      channel.write(ByteBuffer.wrap(xmlDoc.toString().getBytes("UTF-8")));

    } catch(UnsupportedEncodingException e) {
      System.out.println(e.getMessage());

    } catch(IOException e) {
      JOptionPane.showMessageDialog(SketchFrame.this,
                                          "Error writing XML to channel.",
                                              "File Output Error",
                                              JOptionPane.ERROR_MESSAGE);
      e.printStackTrace(System.err);
      return;
    }
  }

   private String getDoctypeString(org.w3c.dom.DocumentType doctype) {
      // Create the opening string for the DOCTYPE declaration with its name
      String str = doctype.getName();
      StringBuffer doctypeStr = new StringBuffer("<!DOCTYPE ").append(str);        

      // Check for a system ID
      if((str = doctype.getSystemId()) != null)
        doctypeStr.append(" SYSTEM ").append(QUOTE).append(str).append(QUOTE);

      // Check for a public ID
      if((str = doctype.getPublicId()) != null)
        doctypeStr.append(" PUBLIC ").append(QUOTE).append(str).append(QUOTE);

      // Check for an internal subset
      if((str = doctype.getInternalSubset()) != null)
        doctypeStr.append('[').append(str).append(']');           

      return doctypeStr.append(TAG_END).toString();  // Append '>' & return string
  }

  private String getDocumentNode(Node node, String indent) {
    StringBuffer nodeStr = new StringBuffer().append(NEWLINE).append(indent); 
    String nodeName = node.getNodeName();       // Get name of this node

    switch(node.getNodeType()) {
      case Node.ELEMENT_NODE:
      nodeStr.append(TAG_START);
      nodeStr.append(nodeName);
      if(node.hasAttributes()) {             // If the element has attributes...
        org.w3c.dom.NamedNodeMap attrs = node.getAttributes();   // ...get them 
        for(int i = 0 ; i<attrs.getLength() ; i++) {
          org.w3c.dom.Attr attribute = (org.w3c.dom.Attr)attrs.item(i);      
          // Append " name="value" to the element string
          nodeStr.append(' ').append(attribute.getName()).append('=')
                        .append(QUOTE).append(attribute.getValue()).append(QUOTE);
        }
      }
      if(!node.hasChildNodes()) {        // Check for no children for this element
        nodeStr.append(EMPTY_TAG_END);   // There are none-close as empty element
        return nodeStr.toString();       // and return the completed element

      } else {                             // It has children
        nodeStr.append(TAG_END);         // so close start-tag
        NodeList list = node.getChildNodes();       // Get the list of child nodes
        assert list.getLength()>0;                  // There must be at least one
 
       // Append child nodes and their children...
        for(int i = 0 ; i<list.getLength() ; i++)     
          nodeStr.append(getDocumentNode(list.item(i), indent+" "));     
      }
      nodeStr.append(NEWLINE).append(indent).append(END_TAG_START)
                                               .append(nodeName).append(TAG_END);
      break;    

      case Node.TEXT_NODE:
      nodeStr.append(replaceQuotes(((org.w3c.dom.Text)node).getData()));
      break;

      default:
      assert false;
    }
    return nodeStr.toString();
  }
 
  public String replaceQuotes(String str) {
    StringBuffer buf = new StringBuffer();
    for(int i = 0 ; i<str.length() ; i++)
      if(str.charAt(i)==QUOTE)
        buf.append(QUOTE_ENTITY);
      else
        buf.append(str.charAt(i));

    return buf.toString(); 
  }       

  // Uses a SAX parser to open an XML sketch file
  private void openXMLSketch(File xmlFile) {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser parser = null;
    spf.setNamespaceAware(true);
    spf.setValidating(true);
    try {
     parser = spf.newSAXParser();
    } catch(SAXException e) {
      e.printStackTrace(System.err);
      System.exit(1);    
    } catch(ParserConfigurationException e) {
      e.printStackTrace(System.err);
      System.exit(1);    
    }
    SAXElementHandler handler = new SAXElementHandler(); 
    try {
       parser.parse(xmlFile, handler);
    }  catch(IOException e) {
      e.printStackTrace(System.err);
    }
    catch(SAXException e) {
      e.printStackTrace(System.err);
    }
    checkForSave();
    filename = xmlFile.getName();            // Update the file name
    setTitle(frameTitle+xmlFile.getPath());  // Change the window title
    sketchChanged = false;                     // Status is unchanged
  }

  // Method to create a SketchModel object from an XML Document object
  private SketchModel createSketchModel(org.w3c.dom.Document doc) {
   SketchModel model = new SketchModel();                 // The new model object

   // Get the first child of the root node
   org.w3c.dom.Node node = doc.getDocumentElement().getFirstChild();

   // Starting with the first child, check out each child in turn
   while (node != null) {
    assert node instanceof org.w3c.dom.Element;          // Should all be Elements
 
    String name = ((org.w3c.dom.Element)node).getTagName();  // Get the name

    if(name.equals("line"))                               // Check for a line
      model.add(new Element.Line((org.w3c.dom.Element)node)); 

    else if(name.equals("rectangle"))                     // Check for a rectangle
      model.add(new Element.Rectangle((org.w3c.dom.Element)node));  

    else if(name.equals("circle"))                        // Check for a circle
 

      model.add(new Element.Circle((org.w3c.dom.Element)node));     

    else if(name.equals("curve"))                         // Check for a curve
      model.add(new Element.Curve((org.w3c.dom.Element)node));      

    else if(name.equals("text"))                          // Check for a text
      model.add(new Element.Text((org.w3c.dom.Element)node));   

    node = node.getNextSibling();                          // Next child node
   }
      return model;    
  }

  // Defines a handler for SAX events when parsing an XML sketch
  /*
    At the start of a document we create an empty model. Data for Sketcher elements
    is recorded in the startElement() callback method and the element is ultimately
    created in the endElement() callback and added to the model. The model is inserted
    into the Sketcher application in the endDocument() callback method.
  */
  class SAXElementHandler extends DefaultHandler {
    public void startDocument() {
      xmlSketch = new SketchModel();                // Create the new model
    }    
    public void endDocument()  {
      theApp.insertModel(xmlSketch);                // Insert the new model
    }
  
    // This method will recognize an XML element and save any attributes it has
    // in a form to be used when the sketch element is created in the endElement() method
    public void startElement(String uri, String localName, String qname, 
                                                               Attributes attr) {
      // Determine what kind of element we have by comparing localName
      // with known element names.
      if(localName.equals("line")) {
        // Should be one attribute - the angle
        angle = Double.parseDouble(attr.getValue("angle"));
        elementType = LINE;

      } else if(localName.equals("position")) {
        position = getPoint(attr, localName);

      } else if(localName.equals("endpoint")) {
        endPoint = getPoint(attr,localName);

      } else if(localName.equals("color")) {
        // Should be three attributes - the RGB values
        color = new Color(Integer.parseInt(attr.getValue("R")),
                          Integer.parseInt(attr.getValue("G")),
                          Integer.parseInt(attr.getValue("B")));

      } else if(localName.equals("rectangle")) {
        // Should be one attribute - the angle
        angle = Double.parseDouble(attr.getValue("angle"));
        elementType = RECTANGLE;

      } else if(localName.equals("bottomright")) {
        bottomRight = getPoint(attr,localName);

      } else if(localName.equals("circle")) {
        // Should be two attributes - radius and angle of type double
        angle = Double.parseDouble(attr.getValue("angle"));
        radius = (int)(Double.parseDouble(attr.getValue("radius")));
        elementType = CIRCLE;

      } else if(localName.equals("curve")) {
        // Should be one attribute - the angle
        angle = Double.parseDouble(attr.getValue("angle"));
        elementType = CURVE;

      } else if(localName.equals("point")) {
        points.add(getPoint(attr, localName));

      } else if(localName.equals("text")) {
        // Should be one attribute - the angle
        angle = Double.parseDouble(attr.getValue("angle"));
        elementType = TEXT;

      } else if(localName.equals("bounds")) {
        // Should be two attributes - the width and height
        bounds = new java.awt.Rectangle(0, 0, Integer.parseInt(attr.getValue("width")),
                                              Integer.parseInt(attr.getValue("height")));

      } else if(localName.equals("font")) {
        // Should be three attributes - the font name, the font style, and the point size
        String styleStr = attr.getValue("fontstyle");
        int style = 0;

        // Figure out the style from the style attribute value
        if(styleStr.equals("bold"))
          style = Font.BOLD;
        else if(styleStr.equals("plain"))
          style = Font.PLAIN;
        else if(styleStr.equals("italic"))
          style = Font.ITALIC;
        else if(styleStr.equals("bold-italic"))
          style = Font.BOLD +Font.ITALIC;
        else
          assert false:"Error! Invalid font style.";
        
        font = new Font(attr.getValue("fontname"),       // Create the font object
                        style,
                        Integer.parseInt(attr.getValue("pointsize")));
      }
    }

    // Helper method - creates a Point object from attributes
    private Point getPoint(Attributes attr, String name) {
      return new Point((int)(Double.parseDouble(attr.getValue("x"))),
                       (int)(Double.parseDouble(attr.getValue("y"))));
    }
      
    // Creates Sketcher elements
    public void endElement(String uri, String localName, String qname) {
      // Determine what kind of element we are ending
      if(localName.equals("line")) {
        assert elementType == LINE          // Validate we have the data
               && position !=null
               && endPoint != null
               && color != null : "Error creating line";
        // Create the Line element
        Element.Line line = new Element.Line(position, endPoint, color);
        line.rotate(angle);               // Rotate it to the required angle
        xmlSketch.add(line);              // Add it to the sketch model

        // Reset data values for next time around
        resetLocalStores();
        endPoint = null;

      } else if(localName.equals("rectangle")) {
        assert elementType == RECTANGLE     // Validate we have the data
               && position !=null
               && bottomRight != null
               && color != null : "Error creating rectangle";
        Element.Rectangle rect = new Element.Rectangle(position, bottomRight, color); 
        rect.rotate(angle);               // Rotate it to the required angle
        xmlSketch.add(rect);              // Add it to the sketch model

        // Reset data values for next time around
        resetLocalStores();
        bottomRight = null;

      } else if(localName.equals("circle")) {
        assert elementType == CIRCLE      // Validate we have the data 
               && position !=null
               && radius > 0
               && color != null : "Error creating circle";
        // Convert position to circle center
        position.x += radius;
        position.y += radius;
        Element.Circle circle = new Element.Circle(position, 
                                                   new Point(position.x, position.y+radius),
                                                   color);
        circle.rotate(angle);            // Rotate it to the required angle
        xmlSketch.add(circle);           // Add it to the sketch model

        // Reset data values for next time around
        resetLocalStores();
        radius = 0;         
        
      } else if(localName.equals("curve")) {
        assert elementType == CURVE       // Validate we have the data
               && position !=null
               && points.size() > 0
               && color != null : "Error creating curve";
        // Create initial 2-point curve
        Element.Curve curve = new Element.Curve(position, (Point)(points.elementAt(0)), color);

        // Add the other points
        for(int i = 1 ; i<points.size() ; i++) 
          curve.modify(position, (Point)(points.elementAt(i)));
    
        curve.rotate(angle);             // Rotate it to the required angle
        xmlSketch.add(curve);            // Add it to the sketch model

        // Reset data values for next time around
        resetLocalStores();
        points = new Vector();
        
      } else if(localName.equals("text")) {
        assert elementType == TEXT 
               && position!=null
               && font != null
               && textStr.length() != 0
               && color != null : "Error creating curve";
        Element.Text text = new Element.Text(font, textStr.toString(),
                                              position, color, bounds);        
        text.rotate(angle);              // Rotate it to the required angle
        xmlSketch.add(text);             // Add it to the sketch model

        // Reset data values for next time around
        resetLocalStores();
        font = null;
        textStr = new StringBuffer();
        bounds = null;
      }
    }

    // Helper to reset basic data stores
    private void resetLocalStores() {
      position = null;
      color = null;
      angle = 0.0;
    }
  
    // This accumulates the text for a Text element.
    // Note that this method may be called several times for a single
    // piece of text so we assemble the text in a string buffer.
    public void characters(char[] ch, int start, int length) {
      textStr.append(ch, start, length);
    }
  
    public void error(SAXParseException spe){
      JOptionPane.showMessageDialog(SketchFrame.this,
                                    "Error at line "+spe.getLineNumber()
                                  + "\n"+spe.getMessage(),
                                    "SAX Parser Error",
                                     JOptionPane.ERROR_MESSAGE);
    }
    public void warning(SAXParseException spe){
      JOptionPane.showMessageDialog(SketchFrame.this,
                                    "Warning at line "+spe.getLineNumber()
                                  + "\n"+spe.getMessage(),
                                    "SAX Parser Warning",
                                     JOptionPane.ERROR_MESSAGE);
    }

    // Because we get data for a Sketcher element piecemeal, we need to save
    // it in these fields until we have what we need to create the element
    private SketchModel xmlSketch = null;      // The new sketch model
    private Point position = null;             // Stores an element position
    private Point endPoint = null;             // Stores line end point
    private Point bottomRight = null;          // Stores rectangle bottom right
    private Color color = null;                // Stores an element color
    private int radius = 0;                    // Stores radius of a circle
    private double angle = 0.0;                // Stores the rotation angle of an element
    Vector points = new Vector();              // Holds points for curve
    java.awt.Rectangle bounds = null;          // Bounds for a text element
    Font font = null;                          // Font for a text element 
    StringBuffer textStr = new StringBuffer(); // The text for a text element                    
  }

  // We will add inner classes defining action objects here...

  // Inner class defining the XML export action
  class XMLExportAction extends AbstractAction {
    public XMLExportAction(String name, String tooltip) {
      super(name);
      if(tooltip != null)                             // If there is tooltip text
        putValue(SHORT_DESCRIPTION, tooltip);         // ...squirrel it away
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(DEFAULT_DIRECTORY);
        chooser.setDialogTitle("Export Sketch as XML");
        chooser.setApproveButtonText("Export");
        ExtensionFilter xmlFiles = new ExtensionFilter(".xml",
                                                      "XML Sketch files (*.xml)");
        chooser.addChoosableFileFilter(xmlFiles);             // Add the filter
        chooser.setFileFilter(xmlFiles);                      // and select it
        int result = chooser.showDialog(SketchFrame.this, null); // Show dialog
        File file = null;
        if(chooser.showDialog(SketchFrame.this, null) == chooser.APPROVE_OPTION){
          file = chooser.getSelectedFile();
          if(file.exists()) {                           // Check file exists
            if(JOptionPane.NO_OPTION ==                 // Overwrite warning
 

               JOptionPane.showConfirmDialog(SketchFrame.this,
                                  file.getName()+" exists. Overwrite?",
                                  "Confirm Save As",
                                  JOptionPane.YES_NO_OPTION,
                                  JOptionPane.WARNING_MESSAGE))
               return;                                   // No overwrite
          }
          saveXMLSketch(file);
      }
    }
  }

  // Inner class defining the XML import action
  class XMLImportAction extends AbstractAction {
    public XMLImportAction(String name, String tooltip) {
      super(name);
      if(tooltip != null)                             // If there is tooltip text
        putValue(SHORT_DESCRIPTION, tooltip);         // ...squirrel it away
    }

    public void actionPerformed(ActionEvent e) {
      JFileChooser chooser = new JFileChooser(DEFAULT_DIRECTORY);
      chooser.setDialogTitle("Import Sketch from XML");
      chooser.setApproveButtonText("Import");
      ExtensionFilter xmlFiles = new ExtensionFilter(".xml",
                                                    "XML Sketch files (*.xml)");
      chooser.addChoosableFileFilter(xmlFiles);             // Add the filter
      chooser.setFileFilter(xmlFiles);                      // and select it
      int result = chooser.showDialog(SketchFrame.this, null);  // Show dialog
      if(chooser.showDialog(SketchFrame.this, null) == chooser.APPROVE_OPTION)
        openXMLSketch(chooser.getSelectedFile());     
    }
  }

  // Inner class defining file actions
  class FileAction extends AbstractAction {    
    // Constructor
    FileAction(String name) {
      super(name);
      String iconFileName = "Images/" + name + ".gif";
      if(new File(iconFileName).exists())
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
    }

   // Constructor
    FileAction(String name, KeyStroke keystroke) {
      this(name);
      if(keystroke != null)
        putValue(ACCELERATOR_KEY, keystroke);
    }

    FileAction(String name, String tooltip) {
      this(name);                                // Call the other constructor
      if(tooltip != null)                        // If there is tooltip text
        putValue(SHORT_DESCRIPTION, tooltip);    // ...squirrel it away
    }

    FileAction(String name, KeyStroke keystroke, String tooltip) {
      this(name, keystroke);                         // Call the other constructor
      if(tooltip != null)                             // If there is tooltip text
        putValue(SHORT_DESCRIPTION, tooltip);         // ...squirrel it away
    }
    
    // Event handler
    public void actionPerformed(ActionEvent e) {
      String name = (String)getValue(NAME);
      if(name.equals(saveAction.getValue(NAME))) {
        saveOperation();
      } else if(name.equals(saveAsAction.getValue(NAME))) {
       File file = showDialog("Save Sketch As",
                              "Save",
                              "Save the sketch",
                              's',
                              modelFile == null ? new File(
                                                files.getCurrentDirectory(),
                                                filename):modelFile);
       if(file != null) {
          if(file.exists() && !file.equals(modelFile))
           if(JOptionPane.NO_OPTION ==                  // Overwrite warning
              JOptionPane.showConfirmDialog(SketchFrame.this,
                                       file.getName()+" exists. Overwrite?",
                                       "Confirm Save As",
                                       JOptionPane.YES_NO_OPTION,
                                       JOptionPane.WARNING_MESSAGE))
            return;                                     // No file selected
          saveSketch(file);
        }
        return;
      } else if(name.equals(openAction.getValue(NAME))) {
        checkForSave();

        // Now open a sketch file
        File file = showDialog(
                           "Open Sketch File",        // Dialog window title
                           "Open",                    // button lable
                           "Read a sketch from file", // Button tooltip text
                           'o',                       // Shortcut character
                           null);                     // No file selected
        if(file != null)                           // If a file was selected
          openSketch(file);                        // then read it
      } else if(name.equals(newAction.getValue(NAME))) {
        checkForSave();
        theApp.insertModel(new SketchModel());    // Insert new empty sketch
        modelFile = null;                         // No file for it
        filename = DEFAULT_FILENAME;              // Default name
        setTitle(frameTitle + files.getCurrentDirectory() + 
                 "\\" + filename);
        sketchChanged = false;                    // Not changed yet
      } else if(name.equals(closeAction.getValue(NAME))) {
        checkForSave();
        System.exit(0);
      } if(name.equals(printAction.getValue(NAME))) {
        // Get a printing object
        PrinterJob printJob = PrinterJob.getPrinterJob(); 

        PrintService printer = printJob.getPrintService();
        if(printer == null) {
          JOptionPane.showMessageDialog(SketchFrame.this,
                                        "No default printer available.",
                                        "Printer Error",
                                        JOptionPane.ERROR_MESSAGE);
          return;
        }
        // The view is the page source
        printJob.setPageable(theApp.getView());   // The view is the page source
        
        if(printJob.printDialog()) {     // Display print dialog
                                                  // If true is returned...
          try {
            printJob.print();                            // then print
          } catch(PrinterException pe) {
            System.out.println(pe);
            JOptionPane.showMessageDialog(SketchFrame.this,
                                          "Error printing a sketch.",
                                          "Printer Error",
                                          JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    }
  }

  class TypeAction extends AbstractAction {    
    TypeAction(String name, int typeID) {
      super(name);
      this.typeID = typeID;
      String iconFileName = "Images/" + name + ".gif";
      if(new File(iconFileName).exists())
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
    }
    
    TypeAction(String name, int typeID, String tooltip) {
      this(name, typeID);
      if(tooltip != null)                               // If there is a tooltip
        putValue(SHORT_DESCRIPTION, tooltip);           // ...squirrel it away
    }

    public void actionPerformed(ActionEvent e) {
      elementType = typeID;  
      statusBar.setTypePane(typeID);
    }

    private int typeID;
  }

  // Handles color menu items
  class ColorAction  extends AbstractAction {
    public ColorAction(String name, Color color) {
      super(name);
      this.color = color;
      String iconFileName = "Images/" + name + ".gif";
      if(new File(iconFileName).exists())
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
    }
    
    public ColorAction(String name, Color color, String tooltip) {
      this(name, color);
       if(tooltip != null)                               // If there is a tooltip
         putValue(SHORT_DESCRIPTION, tooltip);           // ...squirrel it away
    }
    
    public void actionPerformed(ActionEvent e) {
      elementColor = color;
      statusBar.setColorPane(color);
    }

    private Color color;
  }

  private JMenuItem addMenuItem(JMenu menu, Action action) {
    JMenuItem item = menu.add(action);                  // Add the menu item

    KeyStroke keystroke = (KeyStroke)action.getValue(action.ACCELERATOR_KEY);
    if(keystroke != null)
      item.setAccelerator(keystroke);
      
    // remove this comments to disable icons
    // item.setIcon(null);                                 // Remove the icon
    return item;                                        // Return the menu item
  }

    public Color getElementColor() { 
      return elementColor; 
    }

  public int getElementType() { 
    return elementType; 
  }

  // Handle About menu action events
  public void actionPerformed(ActionEvent e)  {
    if(e.getSource() == aboutItem) {
      // Create about dialog with the menu item as parent
      JOptionPane.showMessageDialog(this,                          // Parent
                            "Sketcher Copyright Ivor Horton 2001", // Message
                            "About Sketcher",                      // Title
                            JOptionPane.INFORMATION_MESSAGE);      // Message type

    } else if(e.getSource() == fontItem) {      // Set the dialog window position
      Rectangle bounds = getBounds();
      fontDlg.setLocation(bounds.x + bounds.width/3, bounds.y + bounds.height/3);
      fontDlg.setVisible(true);            // Show the dialog

    } else if(e.getSource() == customColorItem) {
      Color color = JColorChooser.showDialog(this, "Select Custom Color",
                                                           elementColor);
      if(color != null) {
        elementColor = color;
        statusBar.setColorPane(color);
      }
    }
  }

    // Class defining a general purpose message box
  class AboutDialog extends JDialog implements ActionListener   {
    public AboutDialog(Frame parent, String title, String message)  {
      super(parent, title, true);
      // If there was a parent, set dialog position inside
      if(parent != null) {
        Dimension parentSize = parent.getSize();     // Parent size
        Point p = parent.getLocation();              // Parent position
        setLocation(p.x+parentSize.width/4,p.y+parentSize.height/4); 
      }

      // Create the message pane
      JPanel messagePane = new JPanel();
      messagePane.add(new JLabel(message));        
      getContentPane().add(messagePane);

      // Create the button pane
      JPanel buttonPane = new JPanel();
      JButton button = new JButton("OK");        // Create OK button
      buttonPane.add(button);                    // add to content pane
      button.addActionListener(this);
      getContentPane().add(buttonPane, BorderLayout.SOUTH);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      pack();                                    // Size window for components
      setVisible(true);
    }

    // OK button action
    public void actionPerformed(ActionEvent e)  {
      setVisible(false);                         // Set dialog invisible
      dispose();                                 // Release the dialog resources
    }
  }

  public Font getCurrentFont() {  
    return font;  
  }

  public void setCurrentFont(Font font) {  
    this.font = font;  
  }

  public String getSketchName() {
    return filename;
  }

  private JPopupMenu popup = new JPopupMenu("General");       // Window pop-up

  // Element color actions
  private ColorAction redAction, yellowAction, greenAction, blueAction;

  // Element type actions
  private TypeAction lineAction, rectangleAction, circleAction, curveAction, textAction;

  // File actions
  private FileAction newAction, openAction, closeAction, saveAction, saveAsAction, printAction;
  private XMLExportAction exportAction;    // Stores action for XML export menu item
  private XMLImportAction importAction;    // Stores action for XML import menu item

  private JMenuBar menuBar = new JMenuBar();     // Window menu bar

  private Color elementColor = DEFAULT_ELEMENT_COLOR;   // Current element color
  private int elementType = DEFAULT_ELEMENT_TYPE;       // Current element type

  private JToolBar toolBar = new JToolBar();      // Window toolbar
  private Sketcher theApp;

  private StatusBar statusBar = new StatusBar();    // Window status bar

  // Sundry menu items
  private JMenuItem aboutItem, fontItem;
  private Font font = DEFAULT_FONT;                      // Current font

  private FontDialog fontDlg;                      // The font dialog
  private String frameTitle;                  // Frame title
  private String filename = DEFAULT_FILENAME; // Current model file name
  private File modelFile;                     // File for the current sketch
  private boolean sketchChanged = false;         // Model changed flag
  private JFileChooser files;                   // File chooser dialog

  private JMenuItem customColorItem;

  private HashPrintRequestAttributeSet requestAttr = new  HashPrintRequestAttributeSet();
}