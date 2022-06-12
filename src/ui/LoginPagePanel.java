package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import databaseManager.DBManager;
import models.Role;
import tpAcsi.Application;

public class LoginPagePanel extends JPanel implements ActionListener, MouseListener {

	// The title of the page
	JLabel pageTitle;

	// The button to click to confirm enter
	JButton submitButton;

	// The button to click to go to the sign in page
	JLabel changePageButton;

	// Panel container
	JPanel currentPagePanel;

	// Email label
	JLabel emailLabel;

	// Password label
	JLabel passwordLabel;

	// Email textfield
	JTextField emailTextField;

	// Password textfield
	JTextField passwordTextField;

	// The font we user in our elements
	Font textFont = new Font("sans-serif", Font.PLAIN, 20);

	// Constructor
	public LoginPagePanel() {
		// Create labels
		emailLabel = new JLabel();
		emailLabel.setText("Email");
		passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		pageTitle = new JLabel();
		pageTitle.setText("Login");

		// Create text fields
		passwordTextField = new JPasswordField(20);
		emailTextField = new JTextField();

		// Create submit button
		submitButton = new JButton("login");
		submitButton.setBackground(new Color(46, 227, 255));
		changePageButton = new JLabel("go to sign in");
		changePageButton.setForeground(new Color(46, 227, 255));

		// Set layout
		// x y w h
		pageTitle.setBounds(550, 100, 200, 100);
		emailLabel.setBounds(550, 320, 150, 25);
		passwordLabel.setBounds(550, 350, 150, 25);
		emailTextField.setBounds(670, 320, 200, 25);
		passwordTextField.setBounds(670, 350, 200, 25);
		submitButton.setBounds(750, 410, 120, 25);
		changePageButton.setBounds(750, 450, 150, 25);

		// Set front for elements
		pageTitle.setFont(new Font("sans-serif", Font.PLAIN, 50));
		emailLabel.setFont(textFont);
		passwordLabel.setFont(textFont);
		emailTextField.setFont(textFont);
		passwordTextField.setFont(textFont);
		submitButton.setFont(textFont);
		changePageButton.setFont(textFont);

		// Create panel to put form elements
		currentPagePanel = this;
		currentPagePanel.setLayout(null);
		currentPagePanel.add(pageTitle);
		currentPagePanel.add(emailLabel);
		currentPagePanel.add(emailTextField);
		currentPagePanel.add(passwordLabel);
		currentPagePanel.add(passwordTextField);
		currentPagePanel.add(submitButton);
		currentPagePanel.add(changePageButton);
		currentPagePanel.setBackground(new Color(250, 250, 250));

		// Perform action on button click
		submitButton.addActionListener(this);
		changePageButton.addMouseListener((MouseListener) currentPagePanel);

	}

	public void actionPerformed(ActionEvent e) {
		var email = emailTextField.getText();
		var password = passwordTextField.getText();

		// Get the user from the database
		var user = DBManager.GetUserByEmail(email);

		// if the user is null
		if (user == null) {
			// Display error to the user
			NotificationManager.DisplayError("informations eronnées, réessayez");

			// Don't do anything
			return;
		}

		// If the password is not correction
		if (!(user.motDePass.equals(password)))
			// Display error to the user
			NotificationManager.DisplayError("informations eronnées, réessayez");
		else {
			// Set the user in the app
			Application.AppUser = user;

			// If the user is an admin
			if (user.role == Role.admin)
				// Go to the main page for the admin
				Application.GotoPage(new AdminMainPagePanel());

			// Otherwise if it's a client
			else if (user.role == Role.client)
				// Go to the client main page
				Application.GotoPage(new ClientMainPagePanel());

		}
	}

	// When the goto sign in page button is clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		// Go to the sign in page
		Application.GotoPage(new SigninPagePanel());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
