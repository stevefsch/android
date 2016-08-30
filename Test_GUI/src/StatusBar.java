
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class StatusBar extends JPanel implements Constants{
	private static final int DEFAULT_ELEMENT_TYPE = 0, LINE =1, RECTANGLE = 2, CIRCLE = 3, CURVE = 4;

	private JLabel colorPane = new JLabel();
	private JLabel typePane = new JLabel();
	
	private static JPanel statusBar = new JPanel();

	public StatusBar(Color color, int type) {
		// TODO Auto-generated constructor stub
		statusBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		statusBar.setLayout(new FlowLayout());
		statusBar.add(colorPane,BorderLayout.EAST);
		statusBar.add(typePane,BorderLayout.EAST);
		setTypePane(type);
		setColorPane(color);
	}
		
	public void setTypePane(int elementType)
	{
		String text;
		switch(elementType)
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
		default:
			text = "ERROR";
			break;
		}
		
		typePane.setText(text);
	}
	
	public void setColorPane(Color color)
	{
		String text;
		if (color.equals(Color.red))
			text = "RED";
		else if (color.equals(Color.yellow))
			text = "YELLOW";
		else if (color.equals(Color.green))
			text = "GREEN";
		else if(color.equals(Color.blue))
			text = "BLUE";
		else
			text = "CUSTOM COLOR";
		
		colorPane.setForeground(color);
		colorPane.setText(text);		
	}
	
}
