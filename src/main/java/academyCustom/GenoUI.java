package academyCustom;

import java.awt.Container;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * JXDatePicker for calendar display
 * JFileChooser for folder set up
 */

public class GenoUI {
	
	private static void createAndShowGUI() {
		
		InforUI ui = new InforUI();		
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = ui.getContentPane();
		
		c.setPreferredSize(new Dimension(450,550));
		
		ui.pack();
		ui.setResizable(false);
		ui.setVisible(true);
		
	}
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	
}
