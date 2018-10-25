package edu.ncsu.csc316.compression_manager.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.ncsu.csc316.compression_manager.manager.CompressionManager;

/**
 * Creates the GUI for the program
 * @author John Ruisi
 *
 */
public class ManagerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates the UI
	 * @param args the file sent in by the user
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static void main(String args[]) throws FileNotFoundException {
		CompressionManager cm = null;
		String file = null;
		
		try {
			if (args.length == 1) {
				cm = new CompressionManager();
				file = args[0];
    		} else if (args.length == 0) {
    			throw new IllegalArgumentException("No file given");
    		} else {
    			throw new IllegalArgumentException("Please only enter one file");
    		}
			
		} catch (IllegalArgumentException e) {	
			JOptionPane.showMessageDialog(null, e.getMessage(), "File errors", JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
		}
		
		ManagerGUI mg = new ManagerGUI(cm, file);
		mg.setVisible(true);
	}

	/**
	 * Creates the GUI, only has a label that tells you if your file was compressed or decompressed
	 * @param cm the CompressionManager object
	 * @param file the file to be processed
	 */
	public ManagerGUI(CompressionManager cm, String file) {
		setSize(50, 100);
		setLocation(100, 100);
		setTitle("Compression Manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		String process = null;
		
		try {
			process = cm.process(file);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "File errors", JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
		}
		
		JLabel result = new JLabel(process);
		c.add(result, BorderLayout.CENTER);
	}
}
