import java.awt.*;

import javax.swing.*;

import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;

public abstract class Elements {
	private static Color color;

	public Elements(Color color) {
		// TODO Auto-generated constructor stub
		this.color = color;
	}
	
	Color getColor()
	{		
		return color;
	}
	
	public abstract Shape getShape(){};
	
	public abstract java.awt.Rectangle getBounds() {};
	
	public abstract void modify(Point start, Point last){};
	
	public abstract void draw(Graphics2D g2D){};

	public void setHighlighted(boolean isHighlight){};
	public boolean getHightlighted(){};
	
	public static class Line extends Elements
	{
		private Line2D.Double line;
		private boolean isHighlight = false;

		public Line(Point start, Point end, Color color)
		{
			super(color);
			line = new Line2D.Double(start,end);
		}
		
		public Shape getShape()
		{
			return line;
		}
		
		public java.awt.Rectangle getBounds()
		{
			return line.getBounds();
		}
		
		public void modify(Point start, Point last)
		{
			line.x2 = last.x;
			line.y2 = last.y;
		}
		
		public void draw(Graphics2D g2D)
		{
			g2D.setPaint(color);
			g2D.draw(line);
		}

		public void setHighlighted(boolean isHighlight)
		{
			this.isHighlight = isHighlight;
		}
		
		public boolean getHightlighted()
		{
			return isHighlight;
		}
	}
	
	public static class Rectangle extends Elements
	{
		private Rectangle2D.Double rectangle;
		private boolean isHighlight = false;

		public Rectangle(Point start, Point end, Color color)
		{
			super(color);
			rectangle = new Rectangle2D.Double(
					Math.min(start.x, end.x),Math.min(start.y,end.y),
					Math.abs(start.x-end.x),Math.abs(start.y-end.y)
					);
		}
		
		public Shape getShape()
		{
			return rectangle;
		}
		
		public java.awt.Rectangle getBounds()
		{
			return rectangle.getBounds();
		}
		
		public void modify(Point start, Point last)
		{
			rectangle.x = Math.min(start.x,last.x);
			rectangle.y = Math.min(start.y,last.y);
			rectangle.width = Math.abs(start.x-last.x);
			rectangle.height = Math.abs(start.y-last.y);
		}

		public void draw(Graphics2D g2D)
		{
			g2D.setPaint(color);
			g2D.draw(rectangle);			
		}		

		public void setHighlighted(boolean isHighlight)
		{
			this.isHighlight = isHighlight;
		}

		public boolean getHightlighted()
		{
			return isHighlight;
		}
	}
	
	public static class Circle extends Elements
	{
		private Ellipse2D.Double circle;
		private boolean isHighlight = false;
		
		public Circle(Point center, Point circum, Color color)
		{
			super(color);
			double radius = center.distance(circum);
			
			circle = new Ellipse2D.Double(center.x - radius, center.y - radius,2*radius,2*radius);
		}
		
		public Shape getShape()
		{
			return circle;
		}
		
		public java.awt.Rectangle getBounds()
		{
			return circle.getBounds();
		}
		
		public void modify(Point center, Point circum)
		{
			double radius = center.distance(circum);
			circle.x = center.x - (int)radius;
			circle.y = center.y - (int)radius;
			circle.width = circle.height = 2*radius;
		}
		
		public void draw(Graphics2D g2D)

		{
			g2D.setPaint(color);
			g2D.draw(circle);
		}

		public void setHighlighted(boolean isHighlight)
		{
			this.isHighlight = isHighlight;
		}

		public boolean getHightlighted()
		{
			return isHighlight;
		}
	}
	
	public static class Curve extends Elements
	{
		private GeneralPath curve;
		private boolean isHighlight = false;
		
		public Curve(Point start, Point next, Color color)
		{
			super(color);
			curve = new GeneralPath();
			curve.moveTo(start.x, start.y);
			curve.lineTo(next.x, next.y);
		}
		
		public void modify(Point start, Point next)
		{
			curve.lineTo(next.x, next.y);
		}
		
		public Shape getShape()
		{
			return curve;
		}
		
		public java.awt.Rectangle getBounds()
		{
			return curve.getBounds();
		}
		
		public void draw(Graphics2D g2D)
		{
			g2D.setPaint(color);
			g2D.draw(curve);
		}		

		public void setHighlighted(boolean isHighlight)
		{
			this.isHighlight = isHighlight;
		}

		public boolean getHightlighted()
		{
			return isHighlight;
		}
	}
	
	public static class Text extends Elements
	{
		private Font font;
		private String text;
		private Point position;
		private Color color;
		private java.awt.Rectangle bounds = null;

		public Text(Font font, String text, Point position, Color color, java.awt.Rectangle bounds)
		{
			super(color);
			this.font = font;
			this.position = position;
			this.text = text;
			this.bounds = bounds;
			this.bounds.setLocation(position.x, position.y - (int)bounds.getHeight());
		}
		
		public java.awt.Rectangle getBounds()
		{
			return bounds;
		}
		
		public void draw(Graphics2D g2D)
		{
			g2D.setPaint(color);
			Font oldFont = g2D.getFont();
			g2D.setFont(font);
			g2D.drawString(text, position.x, position.y);
			g2D.setFont(oldFont);
		}
		
		public void modify(Point start, Point last)
		{
			
		}
	}		
}
