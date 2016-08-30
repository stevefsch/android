import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Sketcher1 implements WindowListener{
	static SketchFrame1 window;
	private static Sketcher1 theApp;
	private SketchView view;
	private SketchModel sketch;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		theApp = new Sketcher1();
		theApp.init();
	}
	
	public Sketcher1() {
		// TODO Auto-generated constructor stub		
	}
	
	public void init()
	{
		window = new SketchFrame1("Sketcher1",this);
		
		Toolkit theKit = window.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		window.setBounds(wndSize.width/4,wndSize.height/4,wndSize.width/2,wndSize.height/2);
		//window.addWindowListener(this);
		window.addWindowListener(new WindowHandler());
		
		sketch = new SketchModel();
		view = new SketchView(this);
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
	
	public SketchFrame1 getWindow()
	{
		return window;
	}
	
	public SketchModel getModel()
	{
		return sketch;
	}
	
	public SketchView getView()
	{
		return view;
	}
}
