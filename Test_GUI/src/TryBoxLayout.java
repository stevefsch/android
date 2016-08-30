import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class TryBoxLayout {
	static JFrame aWindow = new JFrame("This is a Box Layout");

	public TryBoxLayout() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Toolkit theKit = aWindow.getToolkit();
		Dimension windowSize = theKit.getScreenSize();
		
		aWindow.setBounds(windowSize.width/4,windowSize.height/4,windowSize.width/2,windowSize.height/2);
		aWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		Box left = Box.createVerticalBox();
		left.add(Box.createVerticalStrut(50));
		ButtonGroup radioGroup = new ButtonGroup();
		JRadioButton rButton;
		radioGroup.add(rButton = new JRadioButton("Red"));
		left.add(rButton);
		left.add(Box.createVerticalStrut(50));
		radioGroup.add(rButton = new JRadioButton("Green"));
		left.add(rButton);
		left.add(Box.createVerticalStrut(50));
		radioGroup.add(rButton = new JRadioButton("Blue"));
		left.add(rButton);
		left.add(Box.createVerticalStrut(50));
		radioGroup.add(rButton = new JRadioButton("Yellow"));
		left.add(rButton);
		
		Box right = Box.createVerticalBox();
		right.add(Box.createVerticalStrut(30));
		right.add(new JCheckBox("Dashed"));
		right.add(Box.createVerticalStrut(30));
		right.add(new JCheckBox("Thick"));
		right.add(Box.createVerticalStrut(30));
		right.add(new JCheckBox("Dashed"));		
				
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(new TitledBorder(new EtchedBorder(),"Line Color"));
		leftPanel.add(left, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(new TitledBorder(new EtchedBorder(),"Line properties"));
		rightPanel.add(right, BorderLayout.CENTER);

		
		Box top = Box.createHorizontalBox();
		top.add(top.createHorizontalStrut(50));
		top.add(leftPanel);
		top.add(top.createHorizontalStrut(50));
		top.add(rightPanel);
		top.add(top.createHorizontalStrut(50));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK,1),BorderFactory.createBevelBorder(BevelBorder.RAISED)));
		Border edge = BorderFactory.createRaisedBevelBorder();
		JButton button;
		Dimension size = new Dimension(80,20);
		bottomPanel.add(button = new JButton("Defaults"));
		button.setBorder(edge);
		button.setPreferredSize(size);
		bottomPanel.add(button = new JButton("Ok"));
		button.setBorder(edge);
		button.setPreferredSize(size);
		bottomPanel.add(button = new JButton("cancel"));
		button.setBorder(edge);
		button.setPreferredSize(size);
				
		
		Container content = aWindow.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(top,BorderLayout.CENTER);
		content.add(bottomPanel, BorderLayout.SOUTH);
		
		aWindow.setVisible(true);

	}

}
