package fr.eql.ai109.projet1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FichierInitial implements Parametre {
	
	static RandomAccessFile raf = null; 
	static FileReader fr = null; 
	static BufferedReader br = null; 
	static AlgoArbreDeTri arbre = new AlgoArbreDeTri();

	public static void main(String[] args) {	
		lectureFichier();	
	}

	private static void lectureFichier() {
		try {
			fr = new FileReader (cheminFichier);
			br = new BufferedReader (fr);
			long positionStagiaire = 0;
			arbre = new AlgoArbreDeTri();
			while(br.ready()) {
				Stagiaire stagiaire = creationStagiaire(positionStagiaire);
				positionStagiaire += tailleStagiaire;
				arbre.ajout(stagiaire);
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

	private static Stagiaire creationStagiaire(long positionStagiaire) throws IOException {
		String nom;
		String prenom;
		String dep;
		String formation;
		int annee;
		nom = br.readLine();
		prenom = br.readLine();
		dep = br.readLine();
		formation = br.readLine();
		annee = Integer.parseInt(br.readLine());
		Stagiaire stagiaire = null ;

		if (br.readLine().equals("*")){
			stagiaire = new Stagiaire(nom, prenom, dep, formation, annee);
			stagiaire.setPositionStagiaire(positionStagiaire);
		}
		return stagiaire;
	}
}
