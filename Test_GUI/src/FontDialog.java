import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.event.*;

public class FontDialog extends JDialog implements Constants, ActionListener, ChangeListener, ListSelectionListener{
	private SketchFrame2 window;
	private Font font;
	private int fontStyle;
	private int fontSize;
	
	private JButton ok;
	private JButton cancel;
	
	private JList fontList;
	
	private JLabel fontDisplay;
	private JComboBox chooseSize;

	public FontDialog(SketchFrame2 window) {
		// Call the base constructor to create a modal dialog
		super(window, "Font selection",true);
		
		this.window = window;
		font = window.getCurrentFont();
		fontStyle = font.getStyle();
		fontSize = font.getSize();
		
		//Create the dialog button panel 
		JPanel buttonPane = new JPanel();
		
		//Create and add the buttons to the buttonPane
		buttonPane.add(ok = createButton("OK"));
		buttonPane.add(cancel = createButton("Cancel"));
		getContentPane().add(buttonPane,BorderLayout.SOUTH);
		
		//Code to create the data input panel
		JPanel dataPane = new JPanel();
		dataPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(5,5,5,5)));
		
		GridBagLayout gbLayout = new GridBagLayout();
		dataPane.setLayout(gbLayout);
		
		GridBagConstraints constraints = new GridBagConstraints();		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;

		//Code to create the font choice and add it to the input panel
		JLabel label = new JLabel("Choose a font");
		gbLayout.setConstraints(label, constraints);
		
		dataPane.add(label);
		
		//Code to set up font list choice component
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = e.getAvailableFontFamilyNames();
		fontList = new JList(fontNames);
		fontList.setValueIsAdjusting(true);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setSelectedValue(font.getFamily(), true);
		fontList.addListSelectionListener(this);
		JScrollPane chooseFont = new JScrollPane(fontList);
		chooseFont.setMinimumSize(new Dimension(300,100));
		chooseFont.setWheelScrollingEnabled(true);
		
		//panel to display font samples
		JPanel display = new JPanel();
		fontDisplay = new JLabel("Sample sieze: X x Y y Z z");
		fontDisplay.setPreferredSize(new Dimension(300,100));
		display.add(fontDisplay);
		
		//create a split pane with font choice at the top and font display at the bottom
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				true,chooseFont,display);
		
		gbLayout.setConstraints(splitPane,constraints);
		dataPane.add(splitPane);
		
		splitPane.setOneTouchExpandable(true);
		
		//set up the size choice using a combobox
		JPanel sizePane = new JPanel();
		label = new JLabel("choose point size");
		sizePane.add(label);
		String[] sizeList= {"8","10","12","14","16","18","20","22","24"};
		chooseSize = new JComboBox(sizeList);
		chooseSize.setSelectedItem(Integer.toString(fontSize));
		chooseSize.addActionListener(this);
		sizePane.add(chooseSize);
		gbLayout.setConstraints(sizePane, constraints);
		dataPane.add(sizePane);
		
		//setup the style options using radio buttons
		JRadioButton bold = new JRadioButton("Bold",(fontStyle&Font.BOLD) > 0);
		JRadioButton italic = new JRadioButton("Italic",(fontStyle&Font.ITALIC) > 0);
		bold.addItemListener(new StyleListener(Font.BOLD));
		italic.addItemListener(new StyleListener(font.ITALIC));
		JPanel stylePane = new JPanel();
		stylePane.add(bold);
		stylePane.add(italic);
		gbLayout.setConstraints(stylePane, constraints);
		dataPane.add(stylePane);
		
		getContentPane().add(dataPane,BorderLayout.CENTER);
		pack();
		setVisible(false);
	}

	public void stateChanged(ChangeEvent e) {
	    fontSize = ((Number)(((JComboBox)e.getSource()).getValue())).intValue();  
	    font = font.deriveFont((float)fontSize);
	    fontDisplay.setFont(font);
	    fontDisplay.repaint();    
	}

	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			font = new Font((String)fontList.getSelectedValue(),fontStyle,fontSize);
			fontDisplay.setFont(font);
			fontDisplay.repaint();
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == ok)
		{
			window.setCurrentFont(font);
			setVisible(false);
		}
		else if (source == cancel)
		{
			setVisible(false);
		}
		else if (source == chooseSize)
		{
			fontSize = Integer.parseInt((String)chooseSize.getSelectedItem());
			font = font.deriveFont((float)fontSize);
			fontDisplay.setFont(font);
			fontDisplay.repaint();
		}
	}
	
	class StyleListener implements ItemListener
	{
		public StyleListener(int style)
		{
			this.style = style;
		}
		
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				fontStyle |= style;
			}
			else
			{
				fontStyle &= style;
			}
			
			font = font.deriveFont(fontStyle);
			fontDisplay.setFont(font);
			fontDisplay.repaint();
		}
		
		private int style;
	}
	
	JButton createButton(String label)
	{
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(80,20));
		button.addActionListener(this);
		return button;
	}

}
