package tpAcsi;

import java.awt.EventQueue;
import java.sql.*;

import databaseManager.DBManager;
import ui.LoginFrame;
import models.*;

public class Program {
	
	public static void main(String[] args) throws Exception {
	
		var medicaments = DBManager.GetMedicaments();
		medicaments.forEach(medicament->{
			System.out.println("id: "+ medicament.idMedicament);
			System.out.println("nom: "+ medicament.nom);
			System.out.println("date fabrication    "+ medicament.dateFabrication);
			System.out.println("date expiration"+ medicament.dateExpiration);
			System.out.println("constituant"+ medicament.constituant);
			System.out.println("comment prendre          "+ medicament.commentPrendre);
			System.out.println("le prix est       "+ medicament.prix);
			
		});
System.out.println("none");
		
		
		
		var a  = true;
		if (a)return;
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

}
