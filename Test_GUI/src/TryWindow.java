import java.awt.*;

import javax.swing.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;


public class TryWindow {
	static JFrame  aWindow = new JFrame("This is the window title");

	public TryWindow() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		aWindow.setBounds(100,50,400,150);
		aWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		aWindow.setVisible(true);
		System.out.println(aWindow.getSize());
		System.out.println(aWindow.getLocation());
		
		Toolkit theKit = aWindow.getToolkit();
		Dimension windowSize = theKit.getScreenSize();
		aWindow.setBounds(windowSize.width/4, windowSize.height/4, windowSize.width/2, windowSize.height/2);
		aWindow.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		aWindow.getContentPane().setBackground(Color.pink);
		
	
		Color bgColor = new Color(0,0,255);
		aWindow.getContentPane().setBackground(bgColor);
		aWindow.getContentPane().setForeground(bgColor);
		float h;
		float s;
		float b;
		Color v = new Color(0,0,0);
		String nm = new String();
		SystemColor.getColor(nm, v);
		System.out.println(nm+v);
		System.out.println(SystemColor.window.getRGB());
		Color c = new Color(0,0,125);
		
		aWindow.setCursor(Cursor.HAND_CURSOR);
		
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] font = e.getAllFonts();
		String[] fontNames = e.getAvailableFontFamilyNames();

		for (int i = 0;i < fontNames.length; i ++)
		{
			System.out.println(fontNames[i]);
		}

		for (int i = 0;i < font.length; i ++)
		{
			System.out.println(font[i]);
		}
		
		System.out.println("Screen resolution is " + theKit.getScreenResolution() + " dot per inch");
		
		System.out.println("screen size " + windowSize.width + " " + windowSize.height);
		
		Component aComponent = null;
		int numComponents =aWindow.getComponentCount();
		
		for (int i = 0; i < numComponents; i++)
		{
			System.out.println(aWindow.getComponent(i));
		}

		JButton button;
		Font[] fonts = {new Font("Arial",Font.ITALIC,10), new Font("Playbill",Font.PLAIN,14)};
		
		Container content = aWindow.getContentPane();
		//content.setLayout(new FlowLayout(FlowLayout.RIGHT));

		BorderLayout border = new BorderLayout();
		content.setLayout(border);
		content.setLayout(new BorderLayout(50,50));
		
		EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);
		content.add(button = new JButton("EAST"),BorderLayout.EAST);
		button.setBorder(edge);
		content.add(button = new JButton("WEST"),BorderLayout.WEST);
		button.setBorder(edge);
		content.add(button = new JButton("NORTH"),BorderLayout.NORTH);
		button.setBorder(edge);
		content.add(button = new JButton("SOUTH"),BorderLayout.SOUTH);
		button.setBorder(edge);
		content.add(button = new JButton("CENTER"),BorderLayout.CENTER);
		button.setBorder(edge);
/*
		FlowLayout flow = new FlowLayout();
		content.setLayout(flow);
	
		for (int i = 0; i < 6; i++)
		{
			content.add(button = new JButton("Press " + i));
			button.setFont(fonts[i%2]);
		}		
		
*/
/*		
		GridLayout grid = new GridLayout();
		grid.setColumns(3);
		grid.setRows(3);
		grid.setHgap(30);
		grid.setVgap(50);
		content.setLayout(grid);
*/		
		aWindow.setVisible(true);
		
	}
}
