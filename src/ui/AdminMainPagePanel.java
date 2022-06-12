package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import databaseManager.DBManager;
import models.Medicament;
import tpAcsi.Application;

public class AdminMainPagePanel extends JPanel implements ActionListener, MouseListener {

	// The title of the page
	JLabel pageTitle;

	// The text to show to the user
	JLabel ajouterMedicamentTitle;

	// The data of the medicament to add
	JLabel medicamentName;
	JLabel medicamentDateFabrication;
	JLabel medicamentDateExpiration;
	JLabel medicamentConstituant;
	JLabel medicamentCommentPrendre;
	JLabel medicamentPrix;
	JTextField medicamentNameField;
	JTextField medicamentDateFabricationField;
	JTextField medicamentDateExpirationField;
	JTextField medicamentConstituantField;
	JTextField medicamentCommentPrendreField;
	JTextField medicamentPrixField;

	// Changes page to the medicaments list page
	JLabel gotoMedicamentsListPage;

	// When clicked adds a medicament to the database
	JButton addMedicament;

	// The font we user in our elements
	Font textFont = new Font("sans-serif", Font.PLAIN, 20);

	// Constructor
	public AdminMainPagePanel() {
		// Create labels
		pageTitle = new JLabel();
		medicamentName = new JLabel();
		medicamentDateFabrication = new JLabel();
		medicamentDateExpiration = new JLabel();
		medicamentConstituant = new JLabel();
		medicamentCommentPrendre = new JLabel();
		medicamentPrix = new JLabel();

		// Set text of labels
		pageTitle.setText("Admin main page");
		medicamentName.setText("nom");
		medicamentDateFabrication.setText("date fabrication");
		medicamentDateExpiration.setText("date expiration");
		medicamentConstituant.setText("constituant");
		medicamentCommentPrendre.setText("comment prendre");
		medicamentPrix.setText("prix");

		// Set welcome text
		ajouterMedicamentTitle = new JLabel();
		ajouterMedicamentTitle.setText("ajouter medicament: ");
		ajouterMedicamentTitle.setBounds(250, 200, 400, 25);
		ajouterMedicamentTitle.setFont(textFont);

		// Create text fields
		medicamentNameField = new JTextField();
		medicamentDateFabricationField = new JTextField();
		medicamentDateExpirationField = new JTextField();
		medicamentConstituantField = new JTextField();
		medicamentCommentPrendreField = new JTextField();
		medicamentPrixField = new JTextField();

		// Create buttons
		addMedicament = new JButton("ajouter");
		gotoMedicamentsListPage = new JLabel("voir les medicaments");
		
		// Set layout
		// x y w h
		pageTitle.setBounds(450, 100, 500, 100);
		medicamentNameField.setBounds(450, 250, 200, 25);
		medicamentDateFabricationField.setBounds(450, 300, 200, 25);
		medicamentDateExpirationField.setBounds(450, 350, 200, 25);
		medicamentConstituantField.setBounds(450, 400, 200, 25);
		medicamentCommentPrendreField.setBounds(450, 450, 200, 25);
		medicamentPrixField.setBounds(450, 500, 200, 25);
		addMedicament.setBounds(550, 550, 100, 25);
		gotoMedicamentsListPage.setBounds(750,550,300,25);
		
		medicamentName.setBounds(250, 250, 200, 25);
		medicamentDateFabrication.setBounds(250, 300, 200, 25);
		medicamentDateExpiration.setBounds(250, 350, 200, 25);
		medicamentConstituant.setBounds(250, 400, 200, 25);
		medicamentCommentPrendre.setBounds(250, 450, 200, 25);
		medicamentPrix.setBounds(250, 500, 200, 25);

		// Set font for elements
		pageTitle.setFont(new Font("sans-serif", Font.PLAIN, 50));
		medicamentNameField.setFont(textFont);
		medicamentDateFabricationField.setFont(textFont);
		medicamentDateExpirationField.setFont(textFont);
		medicamentConstituantField.setFont(textFont);
		medicamentCommentPrendreField.setFont(textFont);
		medicamentPrixField.setFont(textFont);
		gotoMedicamentsListPage.setFont(textFont);
		addMedicament.setFont(textFont);
		gotoMedicamentsListPage.setForeground(new Color(46, 227, 255));
		
		medicamentName.setFont(textFont);
		medicamentDateFabrication.setFont(textFont);
		medicamentDateExpiration.setFont(textFont);
		medicamentConstituant.setFont(textFont);
		medicamentCommentPrendre.setFont(textFont);
		medicamentPrix.setFont(textFont);

		// Create panel to put form elements
		this.setLayout(null);
		this.add(pageTitle);
		this.add(medicamentNameField);
		this.add(medicamentDateFabricationField);
		this.add(medicamentDateExpirationField);
		this.add(medicamentConstituantField);
		this.add(medicamentCommentPrendreField);
		this.add(medicamentPrixField);
		this.add(medicamentName);
		this.add(medicamentDateFabrication);
		this.add(medicamentDateExpiration);
		this.add(medicamentConstituant);
		this.add(medicamentCommentPrendre);
		this.add(medicamentPrix);
		this.add(ajouterMedicamentTitle);
		this.add(addMedicament);
		this.add(gotoMedicamentsListPage);

		this.setBackground(new Color(250, 250, 250));

		// Perform action on button click
		addMedicament.addActionListener(this);
		gotoMedicamentsListPage.addMouseListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		
		// Get the data
		var medicament = new Medicament();
		try {
			// Get the data from the text fields
			medicament.commentPrendre = medicamentCommentPrendreField.getText();
			medicament.nom = medicamentNameField.getText();
			var fabrication = medicamentDateFabricationField.getText();
			var expiration = medicamentDateExpirationField.getText();
			medicament.constituant = medicamentConstituantField.getText();
			medicament.prix = Float.parseFloat(medicamentPrixField.getText());

			// Parse the dates
			var sdf = new SimpleDateFormat("yyyy-MM-dd");
			medicament.dateFabrication = sdf.parse(fabrication);
			medicament.dateExpiration = sdf.parse(expiration);
			medicament.idMedicament = 0;

			// Try add medicament to database
			var result = DBManager.AddMedicament(medicament);

			// If we have no error
			if (result == "") {
				// Display added successfully
				NotificationManager.DisplayError("successfully added medicament");
				return;
			}
			
			// Display error message
			NotificationManager.DisplayError(result);
			
			
		} 
		// If we had an error during parsing the dates
		catch (ParseException ex) {
			// Display error
			NotificationManager.DisplayError("please enter valid dates");
		}
		// If we had an error during parsing the price
		catch (NumberFormatException ex) {
			// Display error
			NotificationManager.DisplayError("Enter a valid price");
		} 
		// If we had a null reference error
		catch (NullPointerException ex) {
			// Display error
			NotificationManager.DisplayError("please provide all fields");
		} 
		// By default if any error happened
		catch (Exception ex) {
			// Display error
			NotificationManager.DisplayError("enter valid data");
		}
	}

	// When the goto medicaments list page button is clicked
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Go to the medicaments list page
		Application.GotoPage(new MedicamentsPagePanel());
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
