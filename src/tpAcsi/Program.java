package tpAcsi;

import java.sql.*;

public class Program {
	
	public static void main(String[] args) throws Exception {

		
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tpAcsi";
	
	Class.forName(driver);
	Connection connection = DriverManager.getConnection(url,"root","12345");

	String query = "select * from commands";
	var statement = connection.createStatement();
	
	var result = statement.executeQuery(query);
	
	while(result.next()) {
		var id = result.getInt("id");
		var nom = result.getString("nom");
		System.out.println("id: " + id);
		System.out.println("nom: " + nom);
	}
	
	

	
	
	}

}
