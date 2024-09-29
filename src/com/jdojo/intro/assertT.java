package com.jdojo.intro;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class assertT {

    public boolean isMatriculeExists(String matricule) {
    	
        boolean exists = false;
        String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0071635";  
        String user = "user0071635";  
        String password = "Yf3IgyBsOPa34WR";  

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM Etudiant WHERE matricule = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, matricule);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    
    @Test
    public void testMat() {
        String matricule = "0071635";  // 

        boolean matriculeExists = isMatriculeExists(matricule);
        
    
        assertThat(matriculeExists).isTrue();  
    }

    
    @Test
    public void testMatriculeDoesNotExist() {
        String matricule = "1234567"; 

        boolean matriculeExists = isMatriculeExists(matricule);
        
       
        assertThat(matriculeExists).isFalse();  
    }
    
}

