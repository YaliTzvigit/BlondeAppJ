package com.jdojo.intro;

import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;



public class ExamResult extends Application {

    private TextField matriculefld;
    private Label resultLabel;
    private Button BtDetails;
    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        // INITIALISATION DES VARIABLES
    	
        Label matriculeLabel = new Label("Entrez le matricule :");
        matriculefld = new TextField();
        Button BtValider = new Button("VALIDER");
         // CSS : 
        BtValider.setStyle("-fx-font-weight: bold;");
        BtValider.setPrefSize(300, 10);
        resultLabel = new Label("");
        BtDetails = new Button("Afficher les détails");
        BtDetails.setDisable(true); 
        Button btfermer = new Button("FERMER");
        
        btfermer.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: red; " 
                + "-fx-font-size: 14px;");
        
        btfermer.setPrefSize(300, 10);
        
         // BT FERMER 
        
        btfermer.setOnAction(event -> {
        	primaryStage.close();
        });

        BtValider.setOnAction(e -> VerifierMat());
        BtDetails.setOnAction(e -> Details());

        VBox layout = new VBox(10, matriculeLabel, matriculefld, BtValider, resultLabel, BtDetails, btfermer);
        layout.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("CONSULTATION DES RESULTATS");
        primaryStage.setScene(scene);
        primaryStage.show();

        // CONNEXION A LA BASE DE DONNEES :
        
        connexionalabd();
    }

    private void connexionalabd() {
        try {
            
        	
            connection = DriverManager.getConnection("jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0071635", "user0071635", "Yf3IgyBsOPa34WR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void VerifierMat() {
        String matricule = matriculefld.getText();
        String query = "SELECT Decision FROM Etudiant WHERE matricule = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, matricule);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String Decision = resultSet.getString("Decision");
                resultLabel.setText(Decision.equals("Succès") ? "Succès" : "Échec");
                BtDetails.setDisable(false);  // bt active details
            } else {
                resultLabel.setText("Aucun résultat trouvé");
                
                BtDetails.setDisable(true);  // Désactiver si y'a pas de resultats
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void Details() {
        String matricule = matriculefld.getText();
        String query = "SELECT matricule, nom, prenom, date_naissance, ecole, moyenne, Grade FROM Etudiant WHERE matricule = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, matricule);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	
                String details = "Matricule: " + resultSet.getString("matricule") +
                        "\nNom: " + resultSet.getString("nom") +
                        "\nPrénom: " + resultSet.getString("prenom") +
                        "\nDate de naissance: " + resultSet.getString("date_naissance") +
                        "\nÉcole: " + resultSet.getString("ecole") +
                        "\nMoyenne: " + resultSet.getDouble("moyenne") +
                        "\nGrade : " + resultSet.getString("Grade");

                
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Détails de l'étudiant");
                alert.setHeaderText(null);
                alert.setContentText(details);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




	
	