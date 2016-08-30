import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class TryMenu {
	static JFrame  aWindow = new JFrame("This is the window title");
	static private JMenuBar jmb;

	public TryMenu() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Toolkit theKit = aWindow.getToolkit();
		Dimension windowSize = theKit.getScreenSize();
		
		aWindow.setBounds(windowSize.width/4,windowSize.height/4,windowSize.width/2,windowSize.height/2);
		aWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		/*
		JMenu jm = new JMenu("main menu");
		JMenuItem jmi1 = new JMenuItem("menu1");
		JMenuItem jmi2 = new JMenuItem("menu2");
		jm.add(jmi1);
		jm.add(jmi2);
		JMenuBar jmb = new JMenuBar();
		jmb.add(jm);
		
		jm.addSeparator();
		JMenu colorMenu = new JMenu();
		jmi1.add(colorMenu );
		
		colorMenu .add(lineItem = new JRadioButtonMenuItem("Line",true));
		colorMenu .add(circleItem = new JRadioButtonMenuItem("Circle",true));
		lineItem.setMnemonic('F');
		
		*/
	    JMenu fileMenu = new JMenu("File");           // Create File menu
	    JMenu elementMenu = new JMenu("Elements");    // Create Elements menu
	    JMenu optionsMenu = new JMenu("Options");     // Create options menu
	    JMenu helpMenu = new JMenu("Help");           // Create Help menu

	    fileMenu.setMnemonic('F');                    // Create shortcut
	    elementMenu.setMnemonic('E');                 // Create shortcut
	    optionsMenu.setMnemonic('O');                 // Create shortcut
	    helpMenu.setMnemonic('H');                    // Create shortcut 

	    jmb = new JMenuBar();
	    jmb.add(fileMenu);
	    jmb.add(elementMenu);
	    jmb.add(optionsMenu);
	    jmb.add(helpMenu);
	    
	    JMenuItem newItem = fileMenu.add("New");
	    
	    fileMenu.addSeparator();
	    
	    JMenuItem openItem = fileMenu.add("Open");
	    
	    openItem.setAccelerator(KeyStroke.getKeyStroke('L',Event.CTRL_MASK));
	    
	    JMenuItem saveItem = fileMenu.add("Save");
	    
	    JMenu savePullDownMenu = new JMenu("Color");
	    saveItem.add(savePullDownMenu);
	    
	    JCheckBoxMenuItem redItem, blueItem;
	    
	    savePullDownMenu.add(redItem = new JCheckBoxMenuItem("Red",false));
	    savePullDownMenu.add(blueItem = new JCheckBoxMenuItem("Blue",true));

	    fileMenu.addSeparator();
	    JMenuItem helpItem = fileMenu.add("Help"); 
	    
	    JPopupMenu popHelp = new JPopupMenu();
	    helpItem.add(popHelp);
	    
	    
		
		panel.add(jmb,BorderLayout.NORTH);
		aWindow.getContentPane().add(panel);
		//aWindow.pack();
		aWindow.setVisible(true);		
	}

}
