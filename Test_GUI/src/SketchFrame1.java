// Frame for the Sketcher application
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.print.*;
import java.awt.geom.*;

public class SketchFrame1 extends JFrame implements Constants
{
	private static final Color DEFAULT_ELEMENT_COLOR = Color.red;
	private static final int DEFAULT_ELEMENT_TYPE = 0, LINE =1, RECTANGLE = 2, CIRCLE = 3, CURVE = 4;

	private JMenuBar menuBar = new JMenuBar();	
	private Color elementColor = DEFAULT_ELEMENT_COLOR;
	private int elementType = DEFAULT_ELEMENT_TYPE;
	
	private FileAction newAction, openAction, closeAction, saveAction, saveAsAction, printAction;
	private TypeAction lineAction, rectangleAction, circleAction, curveAction;
	private ColorAction redAction, yellowAction, greenAction, blueAction;
	
	private JToolBar toolBar = new JToolBar();
	
	private Sketcher1 theApp;
	
	public SketchFrame1(String title, Sketcher1 theApp)
	{
		this.theApp = theApp;
		setTitle(title);	
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu elementMenu = new JMenu("Elements");
		
		fileMenu.setMnemonic('F');
		elementMenu.setMnemonic('E');
		
		//construct the file pull down menu using actions
		JMenuItem item;
		item = fileMenu.add(newAction = new FileAction("New","create a new file"));
		item.setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		item = fileMenu.add(openAction = new FileAction("Open","open a file"));
		item.setAccelerator(KeyStroke.getKeyStroke('O',Event.CTRL_MASK));
		item = fileMenu.add(closeAction = new FileAction("Close","close current file"));
		fileMenu.addSeparator();
		item = fileMenu.add(saveAction = new FileAction("Save","save current file"));
		item.setAccelerator(KeyStroke.getKeyStroke('S',Event.CTRL_MASK));
		item = fileMenu.add(saveAsAction = new FileAction("Save As...","save current file as..."));
		fileMenu.addSeparator();
		item = fileMenu.add(printAction = new FileAction("Print","print current file"));
		item.setAccelerator(KeyStroke.getKeyStroke('P',Event.CTRL_MASK));
		
		//construct the element pull down menu
		elementMenu.add(lineAction = new TypeAction("Line",LINE));
		elementMenu.add(rectangleAction = new TypeAction("Rectangle",RECTANGLE));
		elementMenu.add(circleAction = new TypeAction("Circle",CIRCLE));
		elementMenu.add(curveAction = new TypeAction("Curve",CURVE));

		//construct the types menu items using actions		
		elementMenu.addSeparator();		
		JMenu colorMenu = new JMenu("Color");
		elementMenu.add(colorMenu);
		
		//add color menu items using actions
		colorMenu.add(redAction = new ColorAction("Red",Color.red));
		colorMenu.add(yellowAction = new ColorAction("Yellow",Color.yellow));
		colorMenu.add(greenAction = new ColorAction("Green",Color.green));
		colorMenu.add(blueAction = new ColorAction("Blue",Color.blue));
				
		menuBar.add(fileMenu);
		menuBar.add(elementMenu);		
		
		//add toolbar
		getContentPane().add(toolBar, BorderLayout.NORTH);
		toolBar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.darkGray), 
				BorderFactory.createEmptyBorder(2,2,4,2)));
		toolBar.setFloatable(false);
		
		//add toolbar button
		toolBar.add(newAction);
		addToolBarButton(openAction);
		addToolBarButton(closeAction);		
		addToolBarButton(saveAction);
		addToolBarButton(printAction);
		toolBar.addSeparator();
		addToolBarButton(lineAction);
		addToolBarButton(rectangleAction);
		addToolBarButton(circleAction);
		addToolBarButton(curveAction);
		
		lineAction.setEnabled(true);
		
		toolBar.addSeparator();
		addToolBarButton(redAction);
		addToolBarButton(yellowAction);
		addToolBarButton(greenAction);
		addToolBarButton(blueAction);
		
	}	
	
	private JButton addToolBarButton(Action action)
	{
		JButton button = toolBar.add(action);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		//button.setText(null);
		return button;
	}
	
	
	//add inner classes defining actions objects
	class FileAction extends AbstractAction
	{
		FileAction(String name, String tooltip)
		{
			super(name);
			if (tooltip != null)
				putValue(SHORT_DESCRIPTION,tooltip);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			//add action code eventually
			
		}
	}
	
	class TypeAction extends AbstractAction
	{
		private int typeID;
		TypeAction(String name, int typeID)
		{
			super(name);
			this.typeID = typeID;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			elementType = typeID;
		}
	}
	
	class ColorAction extends AbstractAction
	{
		private Color color;
		public ColorAction(String name, Color color)
		{
			super(name);
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			elementColor = color;
			getContentPane().setBackground(color);
		}
	}
	
	//add action objects as members 
	
}
