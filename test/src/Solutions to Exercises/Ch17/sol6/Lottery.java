// Chapter 17 Exercise 6
/*
 This is very easy. We just restore the previously redundant MouseHandler class and use an
 object of  this type to listen for events on each control button.
 */

// Applet to generate lottery entries
import javax.swing.JButton;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;

import java.util.Random;               // For random number generator
import java.util.Arrays;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.io.File;

public class Lottery extends JApplet {
  // Generate numberCount random selections from the values array
  static int[] getNumbers() {
    int[] numbers = new int[numberCount];  // Store for the numbers to be returned
    int candidate = 0;                     // Stores a candidate selection
    for(int i = 0; i < numberCount; i++) { // Loop to find the selections

      search:
      // Loop to find a new selection different from any found so far
      for(;;) {
        candidate = values[choice.nextInt(values.length)];
        for(int j = 0 ; j<i ; j++)         // Check against existing selections
          if(candidate==numbers[j])        // If it is the same
            continue search;               // get another random selection

        numbers[i] = candidate;            // Store the selection in numbers array
        break;                             // and go to find the next
      }
    }
    Arrays.sort(numbers);                  // Sort the selections
    return numbers;                        // Return the selections
  }

  // Initialize the applet
  public void init() {
    // Set up the lucky numbers buttons...
    // Set up the selection buttons
    Container content = getContentPane();

    // Set up the panel to hold the lucky number buttons
    JPanel buttonPane = new JPanel();  // Add the pane containing numbers

    // Let's have a fancy panel border
    buttonPane.setBorder(BorderFactory.createTitledBorder(
                         BorderFactory.createEtchedBorder(Color.cyan,
                                                          Color.blue),
                                                          "Every One a Winner!"));

    int[] choices = getNumbers();            // Get initial set of numbers
    SelectionHandler selectionHandler = new SelectionHandler();    // Create the listener
    for(int i = 0; i<numberCount; i++) {
      luckyNumbers[i] = new Selection(choices[i]);
      luckyNumbers[i].addMouseListener(selectionHandler);
      buttonPane.add(luckyNumbers[i]);
    }
    content.add(buttonPane,BorderLayout.CENTER);

    // Add the toolbar to the content pane
    toolBar.setBorder(BorderFactory.createCompoundBorder(       // Toolbar border
                      BorderFactory.createLineBorder(Color.darkGray),
                      BorderFactory.createEmptyBorder(2,2,4,2)));   
    toolBar.setFloatable(false);             // Inhibit toolbar floating
    getContentPane().add(toolBar, BorderLayout.NORTH);

    // Add the three buttons to the toolbar
    MouseHandler mouseHandler = new MouseHandler();
    newLuckyNumbers = toolBar.add(new ControlAction("numbers"));
    newLuckyNumbers.setBorder(BorderFactory.createRaisedBevelBorder());
    newLuckyNumbers.addMouseListener(mouseHandler);       

    color = toolBar.add(new ControlAction("color"));                     
    color.setBorder(BorderFactory.createRaisedBevelBorder());                    
    color.addMouseListener(mouseHandler);       

    order = toolBar.add(new ControlAction("order"));                   
    order.setBorder(BorderFactory.createRaisedBevelBorder()); 
    order.addMouseListener(mouseHandler);       
  }


  // Custom button showing lottery selection
  // Each button listens for its own events
  class Selection extends JButton {
  public Selection(int value) {
    super(Integer.toString(value));    // Call base constructor and set the label
    this.value = value;                // Save the value
    setBackground(startColor);
    setBorder(BorderFactory.createRaisedBevelBorder());    // Add button border
    setPreferredSize(new Dimension(80,20));
  }


  public void newSelection() {
    // Change this selection to a new selection
    int candidate = 0;
    for(;;) {                              // Loop to find a different selection
      candidate = values[choice.nextInt(values.length)];
      if(isCurrentSelection(candidate))    // If it is not different
        continue;                          // find another
      setValue(candidate);                 // We have one so set the button value
      return;
    }
  }

  // Get the value for this selection
  public int getValue() {
    return value;
  }

  // Set the value for the selection
  public void setValue(int value) {
    setText(Integer.toString(value));    // Set value as the button label
    this.value = value;                   // Save the value
  }

  // Check the value for the selection
  boolean hasValue(int possible) {
    return value==possible;               // Return true if equals current value 
  }

  // Check the current choices
  boolean isCurrentSelection(int possible) {
    for(int i = 0; i < numberCount; i++)         // For each button
      if(luckyNumbers[i].hasValue(possible))     // check against possible
        return true;                             // Return true for any =
    return false;                                // Otherwise return false 
  }

  private int value;                       // Value for the selection button
  }


  // Inner class to handle mouse events for the Selection buttons
  class SelectionHandler extends MouseAdapter {
  Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
  Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

  // Handle mouse entering the selection button
  public void mouseEntered(MouseEvent e) {
    e.getComponent().setCursor(handCursor);    // Switch to hand cursor
  }

  // Handle mouse exiting the selection button
  public void mouseExited(MouseEvent e) {
    e.getComponent().setCursor(defaultCursor); // Change to default cursor
  }
    public void mousePressed(MouseEvent e) {
      ((Lottery.Selection)(e.getComponent())).newSelection();
    }
  }

  // Class defining action objects for control buttons on the toolbar
  class ControlAction extends AbstractAction {    
    // Constructor
    ControlAction(String name) {
      super(name);
      String iconFileName = name + ".gif";
      if(new File(iconFileName).exists())
        putValue(SMALL_ICON, new ImageIcon(iconFileName));
    }

    // Event handler
    public void actionPerformed(ActionEvent e) {
 
      // We can identify the component originating the action by
      // comparing the object return by getSource() method for the event object 
      // with our three ControlAction objects
      Object component = e.getSource();
      if(component == newLuckyNumbers) {
          int[] numbers = getNumbers();            // Get maxCount random numbers
          for(int i = 0; i < numberCount; i++)
            luckyNumbers[i].setValue(numbers[i]);  // Set the button values
      } else if(component == color) {
          Color color = new Color(
                flipColor.getRGB()^luckyNumbers[0].getBackground().getRGB());
          for(int i = 0; i < numberCount; i++)
            luckyNumbers[i].setBackground(color);  // Set the button colors        
      } else if(component == order) {
          int[] numbers = new int[numberCount];
          for(int i = 0; i < numberCount; i++)
            numbers[i] = luckyNumbers[i].getValue();    // Get the button values
          Arrays.sort(numbers);                         // and sort them
          for(int i = 0; i < numberCount; i++)
            luckyNumbers[i].setValue(numbers[i]);
      }
    }
  }

  final static int numberCount = 6;                   // Number of lucky numbers
  final static int minValue = 1;                      // Minimum in range
  final static int maxValue = 49;                     // Maximum in range
  static int[] values = new int[maxValue-minValue+1]; // Array of possible values
  static {                                            // Initialize array
    for(int i = 0 ; i<values.length ; i++)
      values[i] = i + minValue;
  }

  // An array of custom buttons for the selected numbers
  private Selection[] luckyNumbers = new Selection[numberCount]; 

  final public static int PICK_LUCKY_NUMBERS = 1;                // Select button ID
  final public static int COLOR = 2;                             // Color button ID
  final public static int ORDER = 3;                             // Order button ID
  
  // swap colors
  Color flipColor = new Color(Color.yellow.getRGB()^Color.red.getRGB()); 
  
  Color startColor = new Color(Color.yellow.getRGB());           // start color

  private static Random choice = new Random();         // Random number generator

  private JToolBar toolBar = new JToolBar();           // Toolbar for control buttons
  private JButton newLuckyNumbers, color ,order; // Toolbar buttons
}
