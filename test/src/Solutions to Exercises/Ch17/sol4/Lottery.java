// Chapter 17 Exercise 4
/*
 The SelectionHandler class has been added as an inner class to Lottery.
 This just implements the mousePressed() method exactly as the MouseHandler class
 does in the solution to Exercise 3. An object of type SelectionHandler is used as
 the mouse listener for each selection button. This object handles the MOUSE_ENTERED 
 and MOUSE_EXITED events previously handled by a  MouseHandler object, as well as the
 MOUSE_PRESSED event.
 */

// Applet to generate lottery entries
import javax.swing.JButton;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.Cursor;

import java.util.Random;               // For random number generator
import java.util.Arrays;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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
    content.setLayout(new GridLayout(0,1));  // Set the layout for the applet

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
    content.add(buttonPane);

    // Set up the control buttons...
    // Add the pane containing control buttons
    JPanel controlPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));  

    // Add the three control buttons
    JButton button;                                      // A button variable
    Dimension buttonSize = new Dimension(100,20);        // Button size

    controlPane.add(button = new JButton("Lucky Numbers!"));
    button.setBorder(BorderFactory.createRaisedBevelBorder());
    button.addActionListener(new HandleControlButton(PICK_LUCKY_NUMBERS));
    button.setPreferredSize(buttonSize);

    controlPane.add(button = new JButton("Color"));
    button.setBorder(BorderFactory.createRaisedBevelBorder());
    button.addActionListener(new HandleControlButton(COLOR));
    button.setPreferredSize(buttonSize);

    controlPane.add(button = new JButton("Order"));
    button.setPreferredSize(buttonSize);
    button.addActionListener(new HandleControlButton(ORDER));
    button.setBorder(BorderFactory.createRaisedBevelBorder());

    content.add(controlPane);
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

    // We no longer need the Selection object as the action listener
    // since the mouse handler deals with changes.
//    addActionListener(this);           // Button listens for itself
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

  // Class defining a handler for a control button
  class HandleControlButton implements ActionListener {
    // Constructor...
    private int buttonID;   

    // Constructor
    public HandleControlButton(int buttonID) {
      this.buttonID = buttonID;                   // Store the button ID
    }

    // Handle button click
    public void actionPerformed(ActionEvent e) { 
     int[] numbers = null;
     switch(buttonID) {
        case PICK_LUCKY_NUMBERS:
          numbers = getNumbers();                  // Get maxCount random numbers
          for(int i = 0; i < numberCount; i++)
            luckyNumbers[i].setValue(numbers[i]);  // Set the button values
          break;
        case COLOR:
          Color color = new Color(
                flipColor.getRGB()^luckyNumbers[0].getBackground().getRGB());
          for(int i = 0; i < numberCount; i++)
            luckyNumbers[i].setBackground(color);  // Set the button colors
          break;
        case ORDER:                                // Sort the lucky number button values
          numbers = new int[numberCount];
          for(int i = 0; i < numberCount; i++)
            numbers[i] = luckyNumbers[i].getValue();    // Get the button values
          Arrays.sort(numbers);                         // and sort them
          for(int i = 0; i < numberCount; i++)
            luckyNumbers[i].setValue(numbers[i]);
          break;
      }
    }
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

  private static Random choice = new Random();        // Random number generator
}
