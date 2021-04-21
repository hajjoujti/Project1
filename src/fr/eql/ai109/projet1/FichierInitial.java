package fr.eql.ai109.projet1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FichierInitial {
	
	static String cheminFichier = System.getProperty("user.dir") + "\\stagiaires.txt";
	static RandomAccessFile raf = null; 
	static FileReader fr = null; 
	static BufferedReader br = null; 
	
	
	public static void main(String[] args) {
		
		
	try {
		fr = new FileReader (cheminFichier);
		br = new BufferedReader (fr); 
		String nom = ""; 
		String prenom = ""; 
		int dep = 0; 
		String formation = "";
		int annee = 0; 
		
		while(br.ready()) {
			nom = br.readLine();
			prenom = br.readLine();
			dep = Integer.parseInt(br.readLine());
			formation = br.readLine();
			annee = Integer.parseInt(br.readLine());
		if (br.readLine().equals("*")){
			Stagiaire stagiaire = new Stagiaire(nom, prenom, dep, formation, annee); 
		}
		}		
		
		
	} catch (Exception e) {
	
		e.printStackTrace();
	} finally {
		try {
			fr.close();
			br.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
	
	}

	
}
}
