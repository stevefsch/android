import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class Sketcher2 implements WindowListener{
	static SketchFrame2 window;
	private static Sketcher2 theApp;
	private SketchView2 view;
	private SketchModel sketch;	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		theApp = new Sketcher2();
		theApp.init();
	}
	
	public Sketcher2() {
		// TODO Auto-generated constructor stub		
	}
	
	public void init()
	{
		window = new SketchFrame2("Sketcher2",this);
		
		Toolkit theKit = window.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		window.setBounds(wndSize.width/4,wndSize.height/4,wndSize.width/2,wndSize.height/2);
		//window.addWindowListener(this);
		window.addWindowListener(new WindowHandler());
		
		sketch = new SketchModel();
		view = new SketchView2(this);
		sketch.addObserver((Observer)view);
		window.getContentPane().add(view,BorderLayout.CENTER);		
		window.setVisible(true);
	}
	
	class WindowHandler extends WindowAdapter
	{		
		public void windowClosing(WindowEvent e)
		{
			window.dispose();
			System.exit(0);
		}
	}
			
	public void windowOpened(WindowEvent e){};
	public void windowClosed(WindowEvent e){};
	public void windowIconified(WindowEvent e){};
	public void windowDeiconified(WindowEvent e){};
	public void windowActivated(WindowEvent e){};	
	public void windowDeactivated(WindowEvent e){};
	
	public SketchFrame2 getWindow()
	{
		return window;
	}
	
	public SketchModel getModel()
	{
		return sketch;
	}
	
	public SketchView2 getView()
	{
		return view;
	}
}
