import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class StatusBar extends JPanel implements Constants{
	private Font paneFont = new Font("Serif", Font.PLAIN,10);
	private StatusPane colorPane = new StatusPane("BLUE");
	private StatusPane typePane = new StatusPane("LINE");
	
	class StatusPane extends JLabel
	{
		public StatusPane(String text)
		{
			setBackground(Color.LIGHT_GRAY);
			setForeground(Color.BLACK);
			setFont(paneFont);
			setHorizontalAlignment(CENTER);
			setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			setPreferredSize(new Dimension(100,20));
			setText(text);
		}
	}
		
	public StatusBar() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout(FlowLayout.LEFT,10,3));
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setColorPane(DEFAULT_ELEMENT_COLOR);
		setTypePane(DEFAULT_ELEMENT_TYPE);
		add(colorPane);
		add(typePane);
	}
	
	public void setColorPane(Color color)
	{
		String text = null;
		if (color.equals(Color.red))
			text = "RED";
		else if (color.equals(Color.YELLOW))
			text = "YELLOW";
		else if (color.equals(Color.GREEN))
			text = "GREEN";
		else if (color.equals(Color.BLUE))
			text = "BLUE";
		else 
			text = "CUSTOM COLOR";
		
		colorPane.setForeground(color);
		colorPane.setText(text);
	}
	
	public void setTypePane(int elementType)
	{
		String text = null;
		switch (elementType)		
		{
		case LINE:
			text = "LINE";
			break;
		case RECTANGLE:
			text = "RECTANGLE";
			break;
		case CIRCLE:
			text = "CIRCLE";
			break;
		case CURVE:
			text = "CURVE";
			break;
		case TEXT:
			text = "TEXT";
			break;
		default:
			assert false;			
		}
		typePane.setText(text);
	}
}
