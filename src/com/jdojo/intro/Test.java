package com.jdojo.intro;

import static org.junit.jupiter.api.Assertions.*;

	public class Test {

		@org.junit.jupiter.api.Test
		void MatNotNull() {
			String Matricule = "0071635";
			assertNotNull(Matricule, "Le matricule doit pas être nul!");
		}
		
		@org.junit.jupiter.api.Test
		void tnp()
		{
			String nom = "Koffi";
			String prenom = "David";
			assertEquals("Koffi David", nom + " " + prenom);
		}
		
		@org.junit.jupiter.api.Test
		void testmoy()
		{
			Double moy = 16.0;
			assertTrue(moy >= 10, "Reussite!");
		}
		
		@org.junit.jupiter.api.Test
		void testmoyfail()
		{
			Double moy = 9.0;
			assertFalse(moy >= 10, "Echec! ");
		}
		
		// Grade DE Classification : 

		@org.junit.jupiter.api.Test
		void testGrade() {
			Double moy = 12.5;
			String Grade = (moy >= 10) ? "Admis" : "Refusé";
			assertEquals("Admis", Grade, "L'Etudiant est admis! ");
			
			moy = 9.0; 
			
			Grade = (moy >= 10) ? "Admis" : "Refusé";
			assertEquals("Refusé", Grade, "L'Etudiant n'est pas admis!");
			
		}
		
	}


