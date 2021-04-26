package fr.eql.ai109.projet1;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FichierInitial implements Parametre {
	static FileReader fr = null; 
	static AlgoArbreDeTri arbre = new AlgoArbreDeTri();

	public static void main(String[] args) {	
		lectureFichier();	
	}

	private static void lectureFichier() {
		try (RandomAccessFile raf = new RandomAccessFile(cheminFichier, "r")) {
			long positionStagiaire = 0;
			arbre = new AlgoArbreDeTri();
			while(raf.getFilePointer() < fichierInitial.length()) {
			
				Stagiaire stagiaire = creationStagiaire(positionStagiaire, raf);
				positionStagiaire += tailleStagiaire;
				arbre.ajout(stagiaire);
			}		
		} catch (Exception e) {

			e.printStackTrace();
		} 
	}

	private static Stagiaire creationStagiaire(long positionStagiaire, RandomAccessFile raf) throws IOException {
		String nom;
		String prenom;
		String dep;
		String formation;
		int annee;
		nom = raf.readLine();

		prenom = raf.readLine();

		dep = raf.readLine();

		formation = raf.readLine();

		annee = Integer.parseInt(raf.readLine());

		Stagiaire stagiaire = null ;

		if (raf.readLine().equals("*")){
			stagiaire = new Stagiaire(nom, prenom, dep, formation, annee);
			stagiaire.setPositionStagiaire(positionStagiaire);
		}
		return stagiaire;
	}
}
