package com.jdojo.intro;

 // Connection BD : 

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class connectionBD {
	static Connection connexion;

	public static void main(String[] args) {
		String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0071635"; // Link -> Mysql WORKBENCH
		String utilisateur = "user0071635"; //user
		String motDePasse = "Yf3IgyBsOPa34WR"; // Password
		
		
		Connection connexion = null; // Initialize to  null;

		try {
			
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			if (connexion != null) {
				
                System.out.println("Connexion à la base de données db0071635 réussie !");
          
                Statement statement = connexion.createStatement();
                
                String result = "SELECT * FROM Etudiant WHERE matricule = 0071635"; // Select 
                
        		ResultSet resultset = statement.executeQuery(result);
        		
        		// Parcourir et afficher les résultats
        
        		if (resultset.next()) {
        			
        			String matricule = resultset.getString("matricule");
        			String nom = resultset.getString("nom");
        			String prenom = resultset.getString("prenom");
        			String dateNaiss = resultset.getString("date_naissance");
        			String ecole = resultset.getString("ecole");
        			String moyenne = resultset.getString("moyenne");
        			String Decision = resultset.getString("Decision");
        			String Grade = resultset.getString("Grade");
        			
        			// Display details : 
        			
        			System.out.println(matricule + " " + nom + " " + prenom + " " + dateNaiss + " " + ecole + " " + moyenne + " " + Decision + " " + Grade);
        			
        			
        			
        		}
          
            } else {
                System.out.println("Échec de la connexion.");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
