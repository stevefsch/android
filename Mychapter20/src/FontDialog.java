import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;

public class FontDialog extends JDialog implements Constants, ActionListener, ChangeListener, ListSelectionListener{	

	public FontDialog(SketchFrame window) {
		// TODO Auto-generated constructor stub
		super(window, "Font Selection", true);
		
		font = window.getCurrentFont();
		fontStyle = font.getStyle();
		fontSize = font.getSize();
		
		JPanel buttonPane = new JPanel();
		
		buttonPane.add(ok = createButton("OK"));
		buttonPane.add(cancel = createButton("Cancel"));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JPanel dataPane = new JPanel();
		dataPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagLayout gbLayout = new GridBagLayout();
		dataPane.setLayout(gbLayout);
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel label = new JLabel("Choose a Font");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gbLayout.setConstraints(label, constraints);
		dataPane.add(label);
		
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = e.getAvailableFontFamilyNames();
		
		fontList = new JList(fontNames);
		fontList.setValueIsAdjusting(true);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setSelectedValue(font.getFamily(),true);
		fontList.addListSelectionListener(this);
		JScrollPane chooseFont = new JScrollPane(fontList);
		chooseFont.setMinimumSize(new Dimension(300,100));
		chooseFont.setWheelScrollingEnabled(true);
		
		JPanel display = new JPanel();
		fontDisplay = new JLabel("Sample Size: x X y Y z Z");
		fontDisplay.setPreferredSize(new Dimension(300,100));
		display.add(fontDisplay);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				true,chooseFont,display);
		gbLayout.setConstraints(splitPane, constraints);
		dataPane.add(splitPane);
		
		JPanel sizePane = new JPanel();
		label = new JLabel("Choose point size");
		sizePane.add(label);
		
		chooseSize = new JSpinner(new SpinnerNumberModel(
				fontSize, pointSizeMin, pointSizeMax, pointSizeStep));
		chooseSize.addChangeListener(this);
		sizePane.add(chooseSize);
		
		gbLayout.setConstraints(sizePane, constraints);
		dataPane.add(sizePane);
		
		JRadioButton bold = new JRadioButton("Bold", (fontStyle & Font.BOLD) > 0 );
		JRadioButton italic = new JRadioButton("Italic", (fontStyle & Font.ITALIC) > 0 );
		bold.addItemListener(new StyleListener(Font.BOLD));
		italic.addItemListener(new StyleListener(Font.ITALIC));
		JPanel stylePane = new JPanel();
		stylePane.add(bold);
		stylePane.add(italic);
		gbLayout.setConstraints(stylePane, constraints);
		dataPane.add(stylePane);
		
		getContentPane().add(dataPane, BorderLayout.CENTER);
		pack();
		setVisible(false);		
	}

	public void stateChanged(ChangeEvent e)
	{
		fontSize = ((Number)(((JSpinner)e.getSource()).getValue())).intValue();
		font = font.deriveFont((float)fontSize);
		fontDisplay.setFont(font);
		fontDisplay.repaint();
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		if (!e.getValueIsAdjusting())
		{
			font = new Font((String)fontList.getSelectedValue(), fontStyle, fontSize);
			fontDisplay.setFont(font);
			fontDisplay.repaint();
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == ok)
		{
			((SketchFrame)getOwner()).setCurrentFont(font);
			setVisible(false);
		}
		else if (source == cancel)
		{
			setVisible(false);
		}
	}
	
	JButton createButton(String label)
	{
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(80,20));
		button.addActionListener(this);
		return button;
	}
	
	private JSpinner chooseSize;
	private JLabel fontDisplay;
	
	private JList fontList;
	
	private JButton ok, cancel;
	
	private Font font;
	private int fontStyle, fontSize;
	
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
				fontStyle &= ~style;
			}
			
			font = font.deriveFont(fontStyle);
			fontDisplay.setFont(font);
			fontDisplay.repaint();
		}
		
		private int style;
	}
	
}
