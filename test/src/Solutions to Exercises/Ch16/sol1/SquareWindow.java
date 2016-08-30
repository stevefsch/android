// Chapter 16 Exercise 1
// Creating a square window

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

public class SquareWindow extends JFrame {
  public SquareWindow(String title) {
    super(title);

    Toolkit theKit = this.getToolkit();
    Dimension wndSize = theKit.getScreenSize();

    // Calculate maximum side to fit comfortably within the screen
    int maxSize = 9*Math.min(wndSize.width, wndSize.height)/10;     

    setBounds((wndSize.width - maxSize)/2, (wndSize.height-maxSize)/2,  // Position
                     maxSize, maxSize);                                 // Size

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

   public static void main(String[] args) {
      SquareWindow myWindow = new SquareWindow("Chapter 16 Exercise 1 - Look, I'm square...");
   }
}