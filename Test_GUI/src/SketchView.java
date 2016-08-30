import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D.Float;

public class SketchView extends JComponent implements Observer{
	private Sketcher1 theApp;

	public SketchView(Sketcher1 theApp) {
		// TODO Auto-generated constructor stub
		this.theApp = theApp;
	}
	
	public void update(Observable o, Object rectangle)
	{
		
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
				
		g2D.setPaint(Color.red);
		
		Point2D.Float p1 = new Point2D.Float(50.0f,10.0f);
		float width1 = 50;
		float height1 = 80;
		
		Rectangle2D.Float rect = new Rectangle2D.Float(p1.x,p1.y,width1,height1);
		g2D.draw(rect);
		g2D.setPaint(Color.pink);
		g2D.fill(rect);				
		
		Point2D.Float p2 = new Point2D.Float(1500f,1000f);
		float width2 = width1*30;
		float height2 = height1*40;
		
		g2D.draw(new Rectangle2D.Float((float)p2.getX(),(float)p2.getY(),width2,height2));
		g2D.setPaint(Color.blue);
		
		Line2D.Float line = new Line2D.Float(p1,p2);
		g2D.draw(line);
		
		p1.setLocation(p1.x+width1,p1.y);
		p2.setLocation(p2.x+width2,p2.y);
		g2D.draw(new Line2D.Float(p1,p2));
		
		p1.setLocation(p1.x,p1.y+height1);
		p2.setLocation(p2.x,p2.y+height2);
		g2D.draw(new Line2D.Float(p1,p2));

		p1.setLocation(p1.x-width1,p1.y);
		p2.setLocation(p2.x-width2,p2.y);
		g2D.draw(new Line2D.Float(p1,p2));
		
		p1.setLocation(p1.x,p1.y-height1);
		p2.setLocation(p2.x,p2.y=height2);
		g2D.draw(new Line2D.Float(p1,p2));
		
		g2D.drawString("line and rectangle", 60, 250);
		

		Point2D.Double position = new Point2D.Double(10,10);

		double width = 200;
		double height = 100;
		double cornerWidth = 15.0;
		double cornerHeight = 10.0;
		
		RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(position.x+70,position.y+60,width,height,cornerWidth,cornerHeight);		
		g2D.draw(roundRect);
		
		Ellipse2D.Double ellipse = new Ellipse2D.Double(position.x+300,position.y,width,height);
		g2D.draw(ellipse);
		
		Arc2D.Double arc = new Arc2D.Double(position.x+300,position.y,width,height*2,0,360,Arc2D.OPEN);
		g2D.draw(arc);
	}
}
