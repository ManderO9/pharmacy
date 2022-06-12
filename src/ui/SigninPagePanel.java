package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import models.Role;
import models.User;
import tpAcsi.Application;
import user.UserManager;

public class SigninPagePanel extends JPanel implements ActionListener, MouseListener {

	// The title of the page
	JLabel pageTitle;

	// The button to click to confirm enter
	JButton submitButton;

	// The button to click to go to the login page
	JLabel changePageButton;

	// Panel container
	JPanel currentPagePanel;

	// Email label
	JLabel emailLabel;

	// Password label
	JLabel passwordLabel;

	// Name label
	JLabel nameLabel;

	// Name textfield
	JTextField nameTextField;

	// Email textfield
	JTextField emailTextField;

	// Password textfield
	JTextField passwordTextField;

	Font textFont = new Font("sans-serif", Font.PLAIN, 20);

	// Constructor
	public SigninPagePanel() {
		// Create labels
		emailLabel = new JLabel();
		emailLabel.setText("Email");
		nameLabel = new JLabel();
		nameLabel.setText("Name");
		passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		pageTitle = new JLabel();
		pageTitle.setText("Sign in");

		// Create text fields
		passwordTextField = new JPasswordField(20);
		nameTextField = new JTextField();
		emailTextField = new JTextField();

		// Create submit button
		submitButton = new JButton("sign in");
		submitButton.setBackground(new Color(46, 227, 255));
		changePageButton = new JLabel("go to login");
		changePageButton.setForeground(new Color(46, 227, 255));

		// Set layout
		// x y w h
		pageTitle.setBounds(550, 100, 200, 100);
		emailLabel.setBounds(550, 320, 150, 25);
		nameLabel.setBounds(550, 350, 150, 25);
		passwordLabel.setBounds(550, 380, 150, 25);
		emailTextField.setBounds(670, 320, 200, 25);
		nameTextField.setBounds(670, 350, 200, 25);
		passwordTextField.setBounds(670, 380, 200, 25);
		submitButton.setBounds(750, 410, 120, 25);

		changePageButton.setBounds(750, 450, 150, 25);

		// Set front for elements
		pageTitle.setFont(new Font("sans-serif", Font.PLAIN, 50));
		emailLabel.setFont(textFont);
		nameLabel.setFont(textFont);
		passwordLabel.setFont(textFont);
		emailTextField.setFont(textFont);
		nameTextField.setFont(textFont);
		passwordTextField.setFont(textFont);
		submitButton.setFont(textFont);
		changePageButton.setFont(textFont);

		// Create panel to put form elements
		currentPagePanel = this;
		currentPagePanel.setLayout(null);
		currentPagePanel.add(pageTitle);
		currentPagePanel.add(emailLabel);
		currentPagePanel.add(emailTextField);
		currentPagePanel.add(nameLabel);
		currentPagePanel.add(nameTextField);
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
		var name = nameTextField.getText();

		var message = UserManager.Signin(new User(email, 0, Role.client, name, password));

		// If there was an error
		if (message != "")
			// Display it to the user
			NotificationManager.DisplayError(message);
		
		// Else Signed in
		else {
			// Go to the main page
			
		}
		
	}

	// When the goto sign in page button is clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		// Go to the sign in page
		Application.GotoPage(new LoginPagePanel());

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
