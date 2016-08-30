// Frame for the Sketcher application
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.print.*;
import java.awt.geom.*;

public class SketchFrame extends JFrame implements Constants
{
	private static final Color DEFAULT_ELEMENT_COLOR = Color.red;
	private static final int DEFAULT_ELEMENT_TYPE = 0, LINE =1, RECTANGLE = 2, CIRCLE = 3, CURVE = 4;
	private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem;
	private JRadioButtonMenuItem lineItem, rectangleItem, circleItem, curveItem, textItem;
	private JCheckBoxMenuItem redItem, yellowItem, blueItem, greenItem;
	private JMenuBar menuBar = new JMenuBar();
	
	private Color elementColor = DEFAULT_ELEMENT_COLOR;
	private int elementType = DEFAULT_ELEMENT_TYPE;
	
	public SketchFrame(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu elementMenu = new JMenu("Elements");
		
		fileMenu.setMnemonic('F');
		elementMenu.setMnemonic('E');
				
		newItem = fileMenu.add("New");
		openItem = fileMenu.add("Open");
		closeItem = fileMenu.add("Close");
		
		fileMenu.addSeparator();
		saveItem = fileMenu.add("Save");
		saveAsItem = fileMenu.add("Save As...");
		
		fileMenu.addSeparator();
		printItem = fileMenu.add("Print");
		
		newItem.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke('O',Event.CTRL_MASK));
		closeItem.setAccelerator(KeyStroke.getKeyStroke('C',Event.CTRL_MASK));
		printItem.setAccelerator(KeyStroke.getKeyStroke('P',Event.CTRL_MASK));				
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S',Event.CTRL_MASK));
		
		elementMenu.add(lineItem = new JRadioButtonMenuItem("Line",elementType == LINE));
		elementMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangle",elementType == RECTANGLE));
		elementMenu.add(circleItem = new JRadioButtonMenuItem("Circle",elementType == CIRCLE));
		elementMenu.add(curveItem = new JRadioButtonMenuItem("Curve",elementType == CURVE));
		lineItem.addActionListener(new TypedListener(LINE));
		rectangleItem.addActionListener(new TypedListener(RECTANGLE));
		circleItem.addActionListener(new TypedListener(CIRCLE));
		curveItem.addActionListener(new TypedListener(CURVE));

		ButtonGroup types = new ButtonGroup();
		types.add(lineItem);
		types.add(rectangleItem);
		types.add(circleItem);
		types.add(curveItem);
		
		lineItem.setAccelerator(KeyStroke.getKeyStroke('L',Event.CTRL_MASK));
		rectangleItem.setAccelerator(KeyStroke.getKeyStroke('E',Event.CTRL_MASK));
		circleItem.setAccelerator(KeyStroke.getKeyStroke('I',Event.CTRL_MASK));
		curveItem.setAccelerator(KeyStroke.getKeyStroke('V',Event.CTRL_MASK));
		
		
		elementMenu.addSeparator();
		JMenu colorMenu = new JMenu("Color");
		elementMenu.add(colorMenu);
		colorMenu.add(redItem= new JCheckBoxMenuItem("Red",elementColor.equals(Color.red)));
		colorMenu.add(yellowItem= new JCheckBoxMenuItem("Yellow",elementColor.equals(Color.yellow)));
		colorMenu.add(greenItem= new JCheckBoxMenuItem("Green",elementColor.equals(Color.green)));
		colorMenu.add(blueItem= new JCheckBoxMenuItem("Blue",elementColor.equals(Color.blue)));
		redItem.addActionListener(new ColorListener(Color.red));
		yellowItem.addActionListener(new ColorListener(Color.yellow));
		greenItem.addActionListener(new ColorListener(Color.green));
		blueItem.addActionListener(new ColorListener(Color.blue));
		
		redItem.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));
		yellowItem.setAccelerator(KeyStroke.getKeyStroke('Y',Event.CTRL_MASK));
		greenItem.setAccelerator(KeyStroke.getKeyStroke('G',Event.CTRL_MASK));
		blueItem.setAccelerator(KeyStroke.getKeyStroke('B',Event.CTRL_MASK));
		
		menuBar.add(fileMenu);
		menuBar.add(elementMenu);
		
		//enableEvents(AWTEvent.WINDOW_EVENT_MASK);		
	}	
	
	class TypedListener implements ActionListener
	{
		private int type;
		
		TypedListener(int type)
		{
			this.type = type;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			elementType = type;
		}
	}
	
	class ColorListener implements ActionListener
	{
		private Color color;
		public ColorListener(Color color)
		{
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			elementColor = color;
		}
	}
}
