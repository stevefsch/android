import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D.Float;
import java.awt.event.*;

import javax.swing.event.*;

public class SketchView2 extends JComponent implements Observer, ActionListener{
	private static final int DEFAULT_ELEMENT_TYPE = 0, LINE =1, RECTANGLE = 2, CIRCLE = 3, CURVE = 4, TEXT = 5,
			MOVE = 6, DELETE = 7, ROTATE = 8, SENDTOBACK = 9;
	private Sketcher2 theApp;
	private Elements highlightElement = null;
	
	private JPopupMenu elementPopup = new JPopupMenu("Element");
	private JMenuItem moveItem, deleteItem, rotateItem, sendToBackItem;
	
	public SketchView2(Sketcher2 theApp) {
		// TODO Auto-generated constructor stub
		this.theApp = theApp;
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
		
		elementPopup.add(moveItem = new JMenuItem("Move", MOVE));
		elementPopup.add(deleteItem = new JMenuItem("Delete", DELETE));
		elementPopup.add(rotateItem = new JMenuItem("rotate", ROTATE));
		elementPopup.add(sendToBackItem = new JMenuItem("sendtoBack", SENDTOBACK));
		moveItem.addActionListener(this);
		deleteItem.addActionListener(this);
		rotateItem.addActionListener(this);
		sendToBackItem.addActionListener(this);
	}
	
	public void update(Observable o, Object rectangle)
	{
		if (rectangle != null)
			repaint();
		else
			repaint((Rectangle)rectangle);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		Iterator elements = theApp.getModel().getIterator();
		
		Elements element;
		while (elements.hasNext())
		{
			element = (Elements)elements.next();

			//g2D.setPaint(element.getColor());
			//g2D.draw(element.getShape());
			
			//you may use the above two line replace the following line 
			if (element.getHightlighted() == true)
			{
				g2D.fill(element.getShape());
				g2D.setPaint(Color.MAGENTA);  
				
			}
			else
			{
				g2D.setPaint(theApp.getWindow().getElementColor());
			}

			element.draw(g2D);
		}
	}
	
	class MouseHandler extends MouseInputAdapter
	{
		private Point start;
		private Point last;
		private Elements tempElement;
		private Graphics2D g2D;

		public void mousePressed(MouseEvent e)
		{
			start = e.getPoint();
			int modifier = e.getModifiers();
			
			if ((modifier & e.BUTTON1_MASK) != 0)
			{
				g2D = (Graphics2D)getGraphics();
				g2D.setXORMode(getBackgroud());
				g2D.setPaint(theApp.getWindow().getElementColor());
			}
		}

		public void mouseReleased(MouseEvent e)
		{
			int modifier = e.getModifiers();
			
			if (e.isPopupTrigger())
			{
				start = e.getPoint();
				
				if (highlightElement == null)
				{
					theApp.getWindow().getPopup().show((Component)e.getSource(),start.x,start.y);
				}
				else
				{
					elementPopup.show((Component)e.getSource(),start.x,start.y);
				}
				start = null;
			}
			
			else if ((modifier & e.BUTTON1_MASK) != 0 && (theApp.getWindow().getElementType() != TEXT))
			{
				if (tempElement != null)
				{
					theApp.getModel().add(tempElement);
					tempElement = null;
				}
				if (g2D != null)
				{
					g2D.dispose();
					g2D = null;
				}
				start = last = null;
			}
		}

		public void mouseDragged(MouseEvent e)
		{
			last = e.getPoint();
			int modifier = e.getModifiers();
			
			if ((modifier & e.BUTTON1_MASK) != 0)
			{
				if (tempElement == null)
				{
					tempElement = createElement(start,last);					
				}
				else
				{
					g2D.draw(tempElement.getShape());
					tempElement.modify(start,last);
				}
				if (tempElement != null)
				{
					g2D.draw(tempElement.getShape());
				}
				else
				{
					//
				}
			}			
		}
		
		public void mouseClicked(MouseEvent e)
		{
			int modifier = e.getModifiers();
								
			if ((modifier & e.BUTTON1_MASK) != 0 &&
					(theApp.getWindow().getElementType() == TEXT))
			{
				start = e.getPoint();
				String text = JOptionPane.showInputDialog((Component)e.getSource(),
						"Enter Text",
						"Dialog for text Element",
						JOptionPane.PLAIN_MESSAGE);
				
				if (text != null)
				{
					g2D = (Graphics2D)getGraphics();
					Font font = theApp.getWindow().getCurrentFont();
					
					tempElement = new Elements.Text(
							font, 
							text, 
							start, 
							theApp.getWindow().getElementColor(),
							font.getStringBounds(text, g2D.getFontRenderContext()).getBounds());
					
					if (tempElement != null)
					{
						theApp.getModel().add(tempElement);
					}
					tempElement = null;
					g2D.dispose();
					g2D = null;
					start = null;
				}
			}
		}
		
		public void mouseMoved(MouseEvent e)
		{
			Point currentCursor = e.getPoint();
			Iterator elements = theApp.getModel().getIterator();
			Elements element;
			
			while(elements.hasNext())
			{
				element = (Elements)elements.next();
				
				if (element.getBounds().contains(currentCursor))
				{
					if (element == highlightElement)
						return;
					
						highlightElement = element;
						element.setHighlighted(true);
						paint((Graphics2D)getGraphics());
						return;
				}
				
				element.setHighlighted(false);				
			}
		}
		
		public Color getBackgroud()
		{
			return theApp.getWindow().getContentPane().getBackground();
		}
		
		private Elements createElement(Point start, Point end)
		{
			switch(theApp.getWindow().getElementType())
			{
			case LINE:
				return new Elements.Line(start,end,theApp.getWindow().getElementColor());
			case RECTANGLE:
				return new Elements.Rectangle(start,end,theApp.getWindow().getElementColor());
			case CIRCLE:
				return new Elements.Circle(start,end,theApp.getWindow().getElementColor());
			case CURVE:
				return new Elements.Curve(start,end,theApp.getWindow().getElementColor());
			case TEXT:
			}
			return null;
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == moveItem)
		{
			if (highlightElement != null)
			{
				highlightElement = null;
			}
		}
		else if (source == deleteItem)
		{
			theApp.getModel().remove(highlightElement);
			highlightElement = null;
			
		}
		else if (source == rotateItem)
		{
			highlightElement = null;
			
		}
		else if (source == sendToBackItem)
		{
			highlightElement = null;
			
		}
		
	}
	
}
