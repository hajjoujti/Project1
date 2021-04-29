package fr.eql.ai109.projet1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class FichierInitial implements Parametre {
	static FileReader fr = null; 
	static AlgoArbreDeTri arbre = new AlgoArbreDeTri();

	public static void main(String[] args) {	
		lectureFichier(fichierInitial);	
	}

	public static void lectureFichier(File fichier) {
		try (RandomAccessFile raf = new RandomAccessFile(fichier, "r")) {
			long positionStagiaire = 0;
			arbre = new AlgoArbreDeTri();
			while(raf.getFilePointer() < fichier.length()) {
			
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
		String nomANSI = new String(nom.getBytes("CP1252"), StandardCharsets.UTF_8);

		prenom = raf.readLine();
		String prenomANSI = new String(prenom.getBytes("CP1252"), StandardCharsets.UTF_8);

		dep = raf.readLine();
		String depANSI = new String(dep.getBytes("CP1252"), StandardCharsets.UTF_8);

		formation = raf.readLine();
		String formationANSI = new String(formation.getBytes("CP1252"), StandardCharsets.UTF_8);

		annee = Integer.parseInt(raf.readLine());

		Stagiaire stagiaire = null ;

		if (raf.readLine().equals("*")){
			stagiaire = new Stagiaire(nomANSI, prenomANSI, depANSI, formationANSI, annee);
			stagiaire.setPositionStagiaire(positionStagiaire);
		}
		return stagiaire;
	}
}
