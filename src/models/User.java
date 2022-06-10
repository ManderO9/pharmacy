package models;

public class User {
	public String email;
	public int idUser;
	public Role role;
	public String nom;
	public String motDePass;
	
	public User() {
		
	}

	public User(String email, int idUser, Role role, String nom, String motDePass) {
		super();
		this.email = email;
		this.idUser = idUser;
		this.role = role;
		this.nom = nom;
		this.motDePass = motDePass;
	}

}
