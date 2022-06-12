package databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import models.*;

public class DBManager {

	// The driver to connect to the sql database
	private static String Driver = "com.mysql.cj.jdbc.Driver";

	// The url to the database
	private static String Url = "jdbc:mysql://localhost:3306/tpAcsi";

	// The name of the commands table in sql
	private static String CommandesTableName = "commands";

	// The name of the users table in sql
	private static String UsersTableName = "users";

	// The name of the medicaments table in sql
	private static String MedicamentsTableName = "medicaments";

	// The password of the database
	private static String DBPassword = "12345";

	// The username to connect to the database
	private static String Username = "root";

	//
	//
	//
	//

	// Gets all the users from the database
	public static ArrayList<User> GetUsers() {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create the query
			var query = "select * from " + UsersTableName;

			// Create the statement
			var statement = connection.createStatement();

			// Execute the query and get the result
			var result = statement.executeQuery(query);

			// Create the list of users
			var users = new ArrayList<User>();

			// For each row in the result
			while (result.next()) {
				// Create the user
				var user = new User();

				// Set email
				user.email = result.getString("email");

				// Set id
				user.idUser = result.getInt("idUser");

				// Set name
				user.nom = result.getString("nom");

				// Set role
				switch (result.getString("role")) {
				case "admin":
					user.role = Role.admin;
					break;
				case "client":
					user.role = Role.client;
					break;
				default:
					user.role = Role.client;
					throw new Exception("the user role that has been provided does not exist");
				}

				// Set password
				user.motDePass = result.getString("motDePass");

				// Add this user to the list of users
				users.add(user);
			}

			// Return the list of users
			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was a problem, return an empty list
		return new ArrayList<User>();
	}

	// Gets the user with the specific email
	public static User GetUserByEmail(String email) {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create the query
			var query = "select * from " + UsersTableName + " where email='" + email + "'";

			// Create the statement
			var statement = connection.createStatement();

			// Execute the query and get the result
			var result = statement.executeQuery(query);

			// Create the user
			var user = new User();

			while (result.next()) {

				// Set email
				user.email = result.getString("email");

				// Set id
				user.idUser = result.getInt("idUser");

				// Set name
				user.nom = result.getString("nom");

				// Set role
				switch (result.getString("role")) {
				case "admin":
					user.role = Role.admin;
					break;
				case "client":
					user.role = Role.client;
					break;
				default:
					user.role = Role.client;
					throw new Exception("the user role that has been provided does not exist");
				}

				// Set password
				user.motDePass = result.getString("motDePass");

				// Return the user
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was a problem, return null
		return null;
	}

	// Gets all the commandes from the database
	public static ArrayList<Commande> GetCommandes() {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create the query
			var query = "select * from " + CommandesTableName;

			// Create the statement
			var statement = connection.createStatement();

			// Execute the query and get the result
			var result = statement.executeQuery(query);

			// Create the list of commandes
			var commandes = new ArrayList<Commande>();

			// For each row in the result
			while (result.next()) {
				// Create the commande
				var commande = new Commande();

				// Set Date of creation
				commande.dateCreation = result.getDate("dateCreation");

				// Set id
				commande.idCommande = result.getInt("idCommande");

				// Get the id of the user that created it
				commande.idUser = result.getInt("idUser");

				// Set the state
				switch (result.getString("etat")) {
				case "unsaved":
					commande.etat = EtatCommande.unsaved;
					break;
				case "saved":
					commande.etat = EtatCommande.saved;
					break;
				case "confirmed":
					commande.etat = EtatCommande.confirmed;
					break;
				default:
					throw new Exception("l'etat de la commande n'est pas connus");
				}

				// Add this commande to the list of commandes
				commandes.add(commande);
			}

			// Return the list of commandes
			return commandes;

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was a problem, return an empty list
		return new ArrayList<Commande>();
	}

	// Gets all the commandes of the specific user from the database
	public static ArrayList<Commande> GetCommandes(int idUser) {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create the query
			var query = "select * from " + CommandesTableName + " where idUser=" + idUser;

			// Create the statement
			var statement = connection.createStatement();

			// Execute the query and get the result
			var result = statement.executeQuery(query);

			// Create the list of commandes
			var commandes = new ArrayList<Commande>();

			// For each row in the result
			while (result.next()) {
				// Create the commande
				var commande = new Commande();

				// Set Date of creation
				commande.dateCreation = result.getDate("dateCreation");

				// Set id
				commande.idCommande = result.getInt("idCommande");

				// Get the id of the user that created it
				commande.idUser = result.getInt("idUser");

				// Set the state
				switch (result.getString("etat")) {
				case "unsaved":
					commande.etat = EtatCommande.unsaved;
					break;
				case "saved":
					commande.etat = EtatCommande.saved;
					break;
				case "confirmed":
					commande.etat = EtatCommande.confirmed;
					break;
				default:
					throw new Exception("l'etat de la commande n'est pas connus");
				}

				// Add this commande to the list of commandes
				commandes.add(commande);
			}

			// Return the list of commandes
			return commandes;

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was a problem, return an empty list
		return new ArrayList<Commande>();
	}

	// Adds a new user to the database, returns an error if not successful, returns
	// an empty string if successful
	public static String AddUser(User user) {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// If we have no user
			if (user == null)
				// Return error message
				return "user must not be null";

			// If the user has no email
			if (user.email == null)
				// Return error message
				return "must give a valid email";

			// If the user has an empty email
			if (user.email.isBlank())
				// Return error message
				return "must give a valid email";

			// If the user has no password
			if (user.motDePass == null)
				// Return error message
				return "must give a valid password";

			// If the user has an empty password
			if (user.motDePass.isBlank())
				// Return error message
				return "must give a valid password";

			// If the user has no name
			if (user.nom == null)
				// Return error message
				return "must give a valid name";

			// If the user has an empty name
			if (user.nom.isBlank())
				// Return error message
				return "must give a valid name";

			// If we got here we are sure that we have a name, email and password

			// Create the query to execute
			var query = "";

			// Check if we will add this user with a given id
			if (user.idUser == 0)
				// We add a user without using a specific id
				query = "insert into users (role, nom, motDePass, email) values('" + user.role + "', '" + user.nom
						+ "','" + user.motDePass + "','" + user.email + "')";
			else {
				// We add a user using a specific id
				query = "insert into users (idUser, role, nom, motDePass, email) values(" + user.idUser + ",'"
						+ user.role + "', '" + user.nom + "','" + user.motDePass + "','" + user.email + "')";

			}
			// Create the statement
			var statement = connection.createStatement();

			// Execute the query
			statement.executeUpdate(query);

			// If we got here there was no problem, return an empty string
			return "";

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was an unknown error
		return "unknown error occured";
	}

	// Gets all the medicaments from the database
	public static ArrayList<Medicament> GetMedicaments() {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create the query
			var query = "select * from " + MedicamentsTableName;

			// Create the statement
			var statement = connection.createStatement();

			// Execute the query and get the result
			var result = statement.executeQuery(query);

			// Create the list of medicaments
			var medicaments = new ArrayList<Medicament>();

			// For each row in the result
			while (result.next()) {
				// Create the medicament
				var medicament = new Medicament();

				// Set date of creation
				medicament.dateFabrication = result.getDate("dateFabrication");

				// Set expiration date
				medicament.dateExpiration = result.getDate("dateExpiration");

				// Set id
				medicament.idMedicament = result.getInt("idMedicament");

				// Set name
				medicament.nom = result.getString("nom");

				// Set components
				medicament.constituant = result.getString("constituant");

				// Set how to consume
				medicament.commentPrendre = result.getString("commentPrendre");

				// Set price
				medicament.prix = result.getFloat("prix");

				// Add this medicament to the list of medicaments
				medicaments.add(medicament);
			}

			// Return the list of commandes
			return medicaments;

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was a problem, return an empty list
		return new ArrayList<Medicament>();
	}

	// Adds a new medicament to the database, returns an error if not successful,
	// returns
	// an empty string if successful
	public static String AddMedicament(Medicament medicament) {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// If we have no medicament
			if (medicament == null)
				// Return error message
				return "medicament must not be null";

			// If the medicament has no name
			if (medicament.nom == null)
				// Return error message
				return "must give a valid name";

			// If the medicament has an empty name
			if (medicament.nom.isBlank())
				// Return error message
				return "must give a valid name";

			// If the medicament has no commentPrendre
			if (medicament.commentPrendre == null)
				// Return error message
				return "must give a valid comment prendre";

			// If the medicament has an empty commentPrendre
			if (medicament.commentPrendre.isBlank())
				// Return error message
				return "must give a valid comment prendre";

			// If the medicament has no expiration date
			if (medicament.dateExpiration == null)
				// Return error message
				return "must give a valid expiration date";

			// If the medicament has no fabrication date
			if (medicament.dateFabrication == null)
				// Return error message
				return "must give a valid fabrication date";

			// If expiration date is before fabrication date
			if(medicament.dateExpiration.before(medicament.dateFabrication))
				// Return error message
				return "expiration date must be greater than fabrication date";
			
			// If the medicament has no price
			if (medicament.prix == 0)
				// Return error message
				return "must give a valid price";

			// If we got here we are sure that we have all required data

			// Create the query to execute
			var query = "";

			// Check if we will add this user with a given id
			if (medicament.idMedicament == 0)
				// We add a medicament without using a specific id
				query = "insert into medicaments ( nom, dateFabrication, dateExpiration, constituant, commentPrendre, prix)"
						+ " values('" + medicament.nom + "','" +  new java.sql.Date(medicament.dateFabrication.getTime()) + "', '"
						+ new java.sql.Date(medicament.dateExpiration.getTime()) + "','" + medicament.constituant + "' , '"
						+ medicament.commentPrendre + "'," + medicament.prix + ");";
			else {
				// We add a medicament using a specific id
				query = "insert into medicaments (idMedicament, nom, dateFabrication, dateExpiration, constituant, commentPrendre, prix)"
						+ " values(" + medicament.idMedicament + ",'" + medicament.nom + "','"
						+ medicament.dateFabrication + "', '" + medicament.dateExpiration + "','"
						+ medicament.constituant + "' , '" + medicament.commentPrendre + "'," + medicament.prix + ");";

			}
			// Create the statement
			var statement = connection.createStatement();

			// Execute the query
			statement.executeUpdate(query);

			// If we got here there was no problem, return an empty string
			return "";

		} catch (Exception e) {
			e.printStackTrace();
		}

		// If we got here there was an unknown error
		return "unknown error occured";
	}

	// Deletes a medicament with the given id from the database
	public static void DeleteMedicamentById(int id) {
		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create query
			var query = "delete from medicaments where idMedicament=" + id;

			// Create the statement
			var statement = connection.createStatement();

			// Execute the query
			statement.executeUpdate(query);

			// If we got here there was no problem, return an empty string

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Changes the information of the medicament that's passed in in the databae, only changes info
	// that is not null or blank
	public static void ModifyMedicament(Medicament medicament) {

		try {
			// Create connection
			Class.forName(Driver);
			Connection connection = DriverManager.getConnection(Url, Username, DBPassword);

			// Create query
			var query = "update medicaments set";

			// If we have a name
			if (medicament.nom != null && !medicament.nom.isBlank())
				// Change it
				query += " nom = '" + medicament.nom + "',";

			// If we have a constituant
			if (medicament.constituant != null && !medicament.constituant.isBlank())
				// Change it
				query += " constituant = '" + medicament.constituant + "',";

			// If we have how to take
			if (medicament.commentPrendre != null && !medicament.commentPrendre.isBlank())
				// Change it
				query += " commentPrendre = '" + medicament.commentPrendre + "',";

			// If we have a price
			if (medicament.prix != 0)
				// Change it
				query += " prix = '" + medicament.prix + "',";

			// If we have a date of creation
			if (medicament.dateFabrication != null)
				// Change it
				query += " dateFabrication = '" +  new java.sql.Date(medicament.dateFabrication.getTime()) + "',";

			// If we have a date of expiration
			if (medicament.dateExpiration != null)
				// Change it
				query += " dateExpiration = '" + new java.sql.Date(medicament.dateExpiration.getTime())+ "',";

			// Remove last ","
			query = query.substring(0,query.length()-1);
			
			// Finish the query
			query += " where idMedicament =" + medicament.idMedicament;
			
			// Create the statement
			var statement = connection.createStatement();

			// Execute the query
			statement.executeUpdate(query);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
