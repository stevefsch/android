import javax.swing.*;

import java.awt.*;

import javax.swing.border.*;
import java.awt.event.*;

public class TryApplet extends JApplet implements ActionListener{
	CardLayout card = new CardLayout(50,50);
	
	public void init()
	{		
		Container content = getContentPane();
		//content.setLayout(new FlowLayout(FlowLayout.LEFT));
		content.setLayout(card);
		
		JButton button;
		Font[] fonts = {new Font("Arial",Font.ITALIC,10), new Font("Playbill",Font.PLAIN,14)};
		
		//BevelBorder edge = new BevelBorder(BevelBorder.RAISED);
		
		for (int i = 0; i < 6; i++)
		{
			content.add(button = new JButton("Press " + i),"Card" + i);
			button.setFont(fonts[i%2]);
			button.addActionListener(this);			
		}		
		
		card.show(content, "Card4");
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		card.next(getContentPane());
	}
}
