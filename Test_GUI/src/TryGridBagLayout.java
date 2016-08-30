import javax.swing.*;

import java.awt.*;

import javax.swing.border.*;

public class TryGridBagLayout {
	static JFrame aWindow = new JFrame("This is a Box Layout");
	

	public TryGridBagLayout() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Toolkit theKit = aWindow.getToolkit();
		Dimension windowSize = theKit.getScreenSize();
		
		aWindow.setBounds(windowSize.width/4,windowSize.height/4,windowSize.width/2,windowSize.height/2);
		aWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		aWindow.getContentPane().setLayout(gridbag);
		constraints.weightx = constraints.weighty = 10.0;
		constraints.fill = constraints.BOTH;
		addButton("Press",constraints,gridbag);
		
		constraints.weightx = 5;

		constraints.insets = new Insets(10,30,10,20);
		constraints.gridwidth = constraints.RELATIVE;
		constraints.gridheight = 5;
		addButton("Go",constraints,gridbag);

		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		addButton("Back",constraints,gridbag);

		
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 1;
		constraints.gridwidth = 0;
		addButton("Push",constraints,gridbag);
		
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 1;
		constraints.gridwidth = 1;
		addButton("Come",constraints,gridbag);

		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 0;
		addButton("Welcome",constraints,gridbag);
		
		aWindow.setVisible(true);
		
	}
	
	static void addButton(String label, GridBagConstraints constraints, GridBagLayout layout)
	{
		Border edge = BorderFactory.createRaisedBevelBorder();
		JButton button = new JButton(label);
		button.setBorder(edge);
		layout.setConstraints(button, constraints);
		aWindow.getContentPane().add(button);
	}

}
