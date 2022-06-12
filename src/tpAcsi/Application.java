package tpAcsi;

import javax.swing.*;

import models.User;
import ui.MedicamentsPagePanel;

public class Application {

	// The frame containing the application
	public static JFrame AppFrame;

	// The current page of the application
	public static JPanel CurrentPage;

	// The current user of the application
	public static User AppUser;

// Go to a specific page
	public static void GotoPage(JPanel page) {
		// If we have an old page
		if (CurrentPage != null)
			// Remove it
			AppFrame.remove(CurrentPage);

		// Ignore the medicaments page cuz it has got a stupid quirk
		if (page instanceof MedicamentsPagePanel) {
			// Update UI
			Application.AppFrame.revalidate();
			return;
		}

		// Set the current page
		CurrentPage = page;

		// Add the page to the frame
		AppFrame.add(CurrentPage);

		// Update ui after change
		AppFrame.revalidate();
	}

}
