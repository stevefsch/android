// Frame for the Sketcher application
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.print.*;
import java.awt.geom.*;


public class SketchFrame2 extends JFrame implements Constants, ActionListener
{
	private static final Color DEFAULT_ELEMENT_COLOR = Color.red;
	private static final int DEFAULT_ELEMENT_TYPE = 0, LINE =1, RECTANGLE = 2, CIRCLE = 3, CURVE = 4, TEXT = 5;
	Font DEFAULT_FONT = new Font("Times New Roman",Font.PLAIN,12);

	private JMenuBar menuBar = new JMenuBar();	
	private Color elementColor = DEFAULT_ELEMENT_COLOR;
	private int elementType = DEFAULT_ELEMENT_TYPE;
	private Font font = DEFAULT_FONT;
	
	private FileAction newAction, openAction, closeAction, saveAction, saveAsAction, printAction;
	private TypeAction lineAction, rectangleAction, circleAction, curveAction, textAction;
	private ColorAction redAction, yellowAction, greenAction, blueAction;
	
	private JMenuItem fontItem;
	private JMenuItem aboutItem,aboutItem2;
	private JMenuItem customColorItem;
	
	private JToolBar toolBar = new JToolBar();
	private JPanel panel = new JPanel();
	private JLabel labelColor = new JLabel();
	private JLabel labelType = new JLabel();	
	
	public Font getCurrentFont(){return font;}
	
	private FontDialog fontDlg;
	
	private JPopupMenu popup;
	
	private Sketcher2 theApp;
	
	public SketchFrame2(String title, Sketcher2 theApp)
	{
		this.theApp = theApp;
		setTitle(title);	
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu elementMenu = new JMenu("Elements");
		JMenu optionsMenu = new JMenu("Options");
		JMenu helpMenu = new JMenu("Help");
		
		fileMenu.setMnemonic('F');
		elementMenu.setMnemonic('E');
		optionsMenu.setMnemonic('O');
		helpMenu.setMnemonic('H');
		
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
		elementMenu.add(textAction = new TypeAction("Text",TEXT));

		//construct the types menu items using actions		
		elementMenu.addSeparator();		
		JMenu colorMenu = new JMenu("Color");
		elementMenu.add(colorMenu);
		
		//add color menu items using actions
		colorMenu.add(redAction = new ColorAction("Red",Color.red));
		colorMenu.add(yellowAction = new ColorAction("Yellow",Color.yellow));
		colorMenu.add(greenAction = new ColorAction("Green",Color.green));
		colorMenu.add(blueAction = new ColorAction("Blue",Color.blue));
		
		//add options font menu
		fontItem = new JMenuItem("Chooze Font...");
		fontItem.addActionListener(this);
		optionsMenu.add(fontItem);	
		
		customColorItem = new JMenuItem("Custom Color...");
		customColorItem.addActionListener(this);
		optionsMenu.add(customColorItem);

		//add help menu
		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);
		helpMenu.add(aboutItem);
		aboutItem2 = new JMenuItem("About 2");
		aboutItem2.addActionListener(this);
		helpMenu.add(aboutItem2);
				
		menuBar.add(fileMenu);
		menuBar.add(elementMenu);
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);		
		
		//add toolbar
		getContentPane().add(toolBar, BorderLayout.NORTH);
		toolBar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.darkGray), 
				BorderFactory.createEmptyBorder(2,2,4,2)));
		toolBar.setFloatable(false);
		
		//add toolbar button
		ImageIcon icon = new ImageIcon("folder.gif");
		toolBar.add(newAction);
		addToolBarButton(openAction,icon);
		addToolBarButton(closeAction,null);		
		addToolBarButton(saveAction,null);
		addToolBarButton(printAction,null);
		toolBar.addSeparator();
		addToolBarButton(lineAction,null);
		addToolBarButton(rectangleAction,null);
		addToolBarButton(circleAction,null);
		addToolBarButton(curveAction,null);
		addToolBarButton(textAction,null);
		
		lineAction.setEnabled(true);
		
		toolBar.addSeparator();
		addToolBarButton(redAction,null);
		addToolBarButton(yellowAction,null);
		addToolBarButton(greenAction,null);
		addToolBarButton(blueAction,null);

		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		getContentPane().add(panel,BorderLayout.SOUTH);
		panel.add(labelColor);
		panel.add(labelType);
		labelColor.setBorder(BorderFactory.createEtchedBorder());
		labelType.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new FlowLayout());
		
		fontDlg = new FontDialog(this);
		
		popup = new JPopupMenu("Choose type and color...");
		popup.add(lineAction = new TypeAction("Line",LINE));
		popup.add(rectangleAction = new TypeAction("Rectangle",RECTANGLE));
		popup.add(circleAction = new TypeAction("Circle",CIRCLE));
		popup.add(curveAction = new TypeAction("Curve",CURVE));
		popup.add(textAction = new TypeAction("Text",TEXT));
		popup.addSeparator();
		popup.add(redAction = new ColorAction("Red",Color.red));
		popup.add(yellowAction = new ColorAction("Yellow",Color.yellow));
		popup.add(greenAction = new ColorAction("Green",Color.green));
		popup.add(blueAction = new ColorAction("Blue",Color.blue));
	}	
	
	public JPopupMenu getPopup()
	{
		return popup;
	}
	
	private JButton addToolBarButton(Action action, ImageIcon icon)
	{
		JButton button = toolBar.add(action);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		
		if (icon != null)
			button.setText(null);
		
		button.setIcon(icon);
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
			labelType.setText(""+typeID);
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
			labelColor.setText(color.toString());

		}
	}
	
	//add action objects as members 
	public Color getElementColor()
	{
		return elementColor;
	}
	
	public int getElementType()
	{
		return elementType;
	}
	
	class AboutDialog extends JDialog implements ActionListener
	{
		public AboutDialog(Frame parent, String title, String message)
		{
			super(parent, title, true);
			
			if (parent != null)
			{
				Dimension parentSize = parent.getSize();
				Point p = parent.getLocation();
				setLocation(p.x + parentSize.width/4,p.y+parentSize.height/4);
			}
			
			JPanel messagePane = new JPanel();
			messagePane.add(new JLabel(message));
			getContentPane().add(messagePane);
			
			JPanel buttonPane = new JPanel();
			JButton button = new JButton("OK");
			buttonPane.add(button);
			button.addActionListener(this);
			getContentPane().add(buttonPane,BorderLayout.SOUTH);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pack();
			setVisible(true);
		}	
		
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
			dispose();
		}		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == aboutItem)
		{
			AboutDialog aboutDlg = new AboutDialog(this,"About Sketcher","Sketcher Copyright Steve Kong 2013");
		}

		else if (source == aboutItem2)
		{
			JOptionPane.showMessageDialog((Component)e.getSource(), "Sketcher Copyright steve kong 2013","About Sketcher",JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showInputDialog((Component)e.getSource(), "Sketcher Copyright steve kong 2013","About Sketcher",JOptionPane.INFORMATION_MESSAGE);
		}
		else if (source ==fontItem)
		{
			Rectangle bounds = getBounds();
			fontDlg.setLocation(bounds.x + bounds.width / 3, bounds.y + bounds.height / 3);
			fontDlg.setVisible(true);
		}
		else if (source == customColorItem)
		{
			Color color = JColorChooser.showDialog(this, "Select custom color...", elementColor);
			
			if (color != null)
			{
				elementColor = color;
			}
					
		}
	}
	
	public void setCurrentFont(Font font)
	{
		this.font = font;
	}
}
