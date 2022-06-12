package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import databaseManager.DBManager;
import models.Medicament;
import tpAcsi.Application;

public class MedicamentsPagePanel extends JPanel implements ActionListener, MouseListener {

	// The font we user in our elements
	Font textFont = new Font("sans-serif", Font.PLAIN, 20);

	// Constructor
	public MedicamentsPagePanel() {

		// Get control host
		var controlHost = Application.AppFrame.getContentPane();

		// Set layout
		controlHost.setLayout(new BorderLayout());

		// Get the medicaments
		GetListOfMedicaments();

		// Create the scroll pane
		var jsp = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		controlHost.add(jsp);

		// Set background color
		this.setBackground(new Color(250, 250, 250));

	}

	public void GetListOfMedicaments() {

		// Get all the medicines
		var medicaments = DBManager.GetMedicaments();

		// The grid layout
		var gl = new GridLayout(medicaments.size(), 9);

		// Set the panel to the layout
		this.setLayout(gl);

		// For each medicament
		medicaments.forEach(medicament -> {
			// Create the labels and text fields
			var nom = new JLabel("<html><span color=\"purple\">nom: </span></html>");
			var nomField = new JTextField(medicament.nom);
			var dateFabrication = new JLabel("<html><span color=\"purple\">dateFabrication: </span><br/><br/></html>");
			var dateFabricationField = new JTextField(medicament.dateFabrication.toString());
			var dateExpiration = new JLabel("<html><span color=\"purple\">dateExpiration: </span></html>");
			var dateExpirationField = new JTextField(medicament.dateExpiration.toString());
			var prix = new JLabel("<html><span color=\"purple\">prix: </span></html>");
			var prixField = new JTextField(String.valueOf(medicament.prix));
			var modify = new JLabel("<html><span color=\"red\">modifier</span></html>");
			var supprimer = new JLabel("<html><span color=\"red\">supprimer</span></html>");

			// Set their name
			nom.setName("nom" + String.valueOf(medicament.idMedicament));
			nomField.setName("nomField" + String.valueOf(medicament.idMedicament));
			dateFabrication.setName("dateFabrication" + String.valueOf(medicament.idMedicament));
			dateFabricationField.setName("dateFabricationField" + String.valueOf(medicament.idMedicament));
			dateExpiration.setName("dateExpiration" + String.valueOf(medicament.idMedicament));
			dateExpirationField.setName("dateExpirationField" + String.valueOf(medicament.idMedicament));
			prix.setName("prix" + String.valueOf(medicament.idMedicament));
			prixField.setName("prixField" + String.valueOf(medicament.idMedicament));
			modify.setName("modify" + String.valueOf(medicament.idMedicament));
			supprimer.setName("supprimer" + String.valueOf(medicament.idMedicament));

			// Add them to the current panel
			this.add(nom);
			this.add(nomField);
			this.add(dateFabrication);
			this.add(dateFabricationField);
			this.add(dateExpiration);
			this.add(dateExpirationField);
			this.add(prix);
			this.add(prixField);
			this.add(modify);
			this.add(supprimer);

			// Add the event listeners
			modify.addMouseListener(this);
			supprimer.addMouseListener(this);
		});
		

	}

	public void actionPerformed(ActionEvent e) {

	}

	// When the delete or modify buttons are clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		var name = ((JLabel) e.getSource()).getName();

		// If we want to delete an element
		if (name.startsWith("supprimer")) {
			// Get the id of the element to delete
			var medicamentId = name.substring("supprimer".length());

			// Delete item from database
			DBManager.DeleteMedicamentById(Integer.parseInt(medicamentId));

			// Remove all the children
			this.removeAll();

			// Get the new list
			this.GetListOfMedicaments();

			// Update UI
			Application.AppFrame.revalidate();
		} else
		// If we want to modify an element
		if (name.startsWith("modify")) {
			// Get the id of the element to delete
			var medicamentId = name.substring("modify".length());

			// Get the data to modify
			var nom = ((JTextField) GetElementByName("nomField" + String.valueOf(medicamentId))).getText();
			var dateFabrication = ((JTextField) GetElementByName("dateFabricationField" + String.valueOf(medicamentId)))
					.getText();
			var dateExpiration = ((JTextField) GetElementByName("dateExpirationField" + String.valueOf(medicamentId)))
					.getText();
			var prix = ((JTextField) GetElementByName("prixField" + String.valueOf(medicamentId))).getText();

			// Parse the dates
			var sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fabrication = null;
			Date expiration =null;
			
			try {
			fabrication = sdf.parse(dateFabrication);
			expiration = sdf.parse(dateExpiration);
			}
			catch (Exception ee) {
				NotificationManager.DisplayError("invalid date entered");
				return;
			}
			
			// Modify item in database
			DBManager.ModifyMedicament(new Medicament(Integer.parseInt(medicamentId), nom, fabrication, expiration, "",
					"", Float.parseFloat(prix)));

			// Show to the user that we finished the modification
			NotificationManager.DisplayError("modifié avec succes");
		}
	}

	// Return an element that has the passed in name
	public Object GetElementByName(String name) {
		// Get all the components
		var components = this.getComponents();

		// For each component
		for (int i = 0; i < components.length; i++) {
			// Get component
			var component = components[i];

			// Get component name
			var componentName = component.getName();

			// If this components name is equal to the profided name
			if (componentName.equals(name))
				// Return the element
				return component;
		}
		// Return null if there was an error
		return null;
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
