import java.awt.*;
import java.awt.event.*;

public class Sketcher implements WindowListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		theApp = new Sketcher();
		theApp.init();
	}
	
	public Sketcher() {
		// TODO Auto-generated constructor stub		
	}
	
	public void init()
	{
		window = new SketchFrame("Sketcher");
		Toolkit theKit = window.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		window.setBounds(wndSize.width/4,wndSize.height/4,wndSize.width/2,wndSize.height/2);
		//window.addWindowListener(this);
		window.addWindowListener(new WindowHandler());
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

	static SketchFrame window;
	private static Sketcher theApp;	
}
