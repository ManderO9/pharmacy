package models;

import java.util.Date;

public class Medicament {

	public int idMedicament;
	public String nom;
	public Date dateFabrication;
	public Date dateExpiration;
	public String constituant;
	public String commentPrendre;
	public float prix;
	
	public Medicament() {
		
	}
	
	public Medicament(int idMedicament, String nom, Date dateFabrication, Date dateExpiration, String constituant,
			String commentPrendre, float prix) {
		super();
		this.idMedicament = idMedicament;
		this.nom = nom;
		this.dateFabrication = dateFabrication;
		this.dateExpiration = dateExpiration;
		this.constituant = constituant;
		this.commentPrendre = commentPrendre;
		this.prix = prix;
	}
	
	
	

}
