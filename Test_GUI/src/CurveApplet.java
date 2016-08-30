import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import javax.swing.event.*;

import java.awt.event.*;

public class CurveApplet extends JApplet {
	Point2D.Double startQ = new Point2D.Double(50,150);
	Point2D.Double endQ = new Point2D.Double(150,150);
	Point2D.Double control = new Point2D.Double(80,100);
	
	Point2D.Double startC = new Point2D.Double(50,300);
	Point2D.Double endC = new Point2D.Double(150,300);
	Point2D.Double controlStart = new Point2D.Double(80,250);
	Point2D.Double controlEnd = new Point2D.Double(160,250);
	
	QuadCurve2D.Double quadCurve;
	CubicCurve2D.Double cubicCurve;
	CurvePane pane = new CurvePane();
	
	GeneralPath p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);

	

	public void init()
	{
		Container content = getContentPane();
		content.add(pane);	
		MouseHandler handler = new MouseHandler();
		pane.addMouseListener(handler);
		pane.addMouseMotionListener(handler);

		p.moveTo(10.0f, 10.f);
		
	}
	

	public CurveApplet() {
		// TODO Auto-generated constructor stub
	}
	
	class CurvePane extends JComponent
	{
		public CurvePane()
		{
			quadCurve = new QuadCurve2D.Double(startQ.x,startQ.y,control.x,control.y,endQ.x,endQ.y);
			cubicCurve = new CubicCurve2D.Double(startC.x,startC.y,controlStart.x,controlStart.y,controlEnd.x,controlEnd.y,endC.x,endC.y);			
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		g2D.setPaint(Color.green);
		g2D.draw(quadCurve);
		g2D.draw(cubicCurve);
		g2D.draw(p);		
	}
	
	class Marker
	{
		public Marker(Point2D.Double control)
		{			
		}
	}
	
	class MouseHandler extends MouseInputAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			System.out.println("Mouse Pressed");
			p.lineTo(e.getX(), e.getY());	
			repaint();
		}

		public void mouseReleased(MouseEvent e)
		{
			System.out.println("Mouse Release");
		}

		public void mouseDragged(MouseEvent e)
		{
			System.out.println("Mouse Dragged");	
			p.closePath();
		}
		
	}
}