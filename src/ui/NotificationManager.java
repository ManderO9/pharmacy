package ui;

import javax.swing.JOptionPane;

public class NotificationManager {

	// Show a message to the user
	public static void DisplayError(String errorMessage) {

		// Show the error message
		JOptionPane.showMessageDialog(null, errorMessage);
	}
}
