

/*
  File:	Main.java
  Author: kevingary	
  Date:	February 19, 2017
  
  Description: This is the main class
*/

package banking.gui;

import javax.swing.JFrame;

/**
 * main method for running the program.
 * @author kevinagary
 *
 */
final class Main {
	/**
	 * Private constructor to address STYLE issue.
	 */
	private Main() {
	}
	
	
	/**
	  Method: main
	  Inputs: args command-line arguments
	  Returns: void

	  Description: main method
	*/

	public static void main(final String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java FormMain <property file>");
			System.exit(1);
		}

		String propertyFile = args[0];
		JFrame frame = new MainFrame(propertyFile);
		frame.setVisible(true);

	}
}
