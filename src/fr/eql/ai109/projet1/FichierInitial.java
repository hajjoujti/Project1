package fr.eql.ai109.projet1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FichierInitial {

	static String cheminFichier = System.getProperty("user.dir") + "\\stagiaires.txt";
	static String cheminFichierCible = System.getProperty("user.dir") + "\\stagiaires.bin";
	static RandomAccessFile raf = null; 
	static FileReader fr = null; 
	static BufferedReader br = null; 
	static List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	static AlgoArbreDeTri arbre = new AlgoArbreDeTri();
	static File f = new File(cheminFichierCible);

	public static void main(String[] args) {	
		lectureFichier();
		for (Stagiaire stagiaire : stagiaires) {
			arbre.ajout(stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getDep(), stagiaire.getFormation(), stagiaire.getAnnee());
//			System.out.println(stagiaire.getDep());
		}
		arbre.ecrire(arbre.getRoot());
		
		//passage a null de la list pour ne laisser rien en memoire
		stagiaires = null;
		
//		try {
//			raf = new RandomAccessFile(cheminFichierCible, "rw");
//			raf.seek(158);
//			long tailleFichier = f.length();
////			byte[] b = new byte[158];
//			Long enfantDroite = raf.readLong();
////			String stagiaire = new String(b);
//			System.out.println(enfantDroite);
//			
//			raf.seek(enfantDroite);
//			byte[] b = new byte[150];
//			raf.read(b);
//			String stagiaire = new String(b);
//			System.out.println(stagiaire);
//			
//			raf.seek(158);
//			raf.writeLong(tailleFichier);
//			
//			raf.seek(tailleFichier);
//			String nom = "AYOUB                         ";
//			b = nom.getBytes();
//			raf.write(b);
//			String prenom = "Nicolas                       ";
//			b = prenom.getBytes();
//			raf.write(b);
//			String dep = "75                            ";
//			b = dep.getBytes();
//			raf.write(b);
//			String formation = "AI 109                              ";
//			b = formation.getBytes();
//			raf.write(b);
//			int annee = 2021;
//			String anneeString = Integer.toString(annee) + "                          ";
//			b = anneeString.getBytes();
//			raf.write(b);
//			long stagiaireGauche = 0L;
//			raf.writeLong(stagiaireGauche);
//			long stagiaireDroite = 166L;
//			raf.writeLong(stagiaireDroite);
//			
//			raf.seek(stagiaireDroite+150L);
//			raf.writeLong(tailleFichier);
//			
//			raf.seek(158);
//			long stagiaireNouveau = raf.readLong();
//			System.out.println(stagiaireNouveau);
//			raf.seek(stagiaireNouveau);
//			b = new byte[150];
//			raf.read(b);
//			stagiaire = new String(b);
//			System.out.println(stagiaire);
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	private static void lectureFichier() {
		try {
			fr = new FileReader (cheminFichier);
			br = new BufferedReader (fr); 
			while(br.ready()) {
				creationStagiaire();
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

	private static void creationStagiaire() throws IOException {
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
		if (br.readLine().equals("*")){
			Stagiaire stagiaire = new Stagiaire(nom, prenom, dep, formation, annee);
			stagiaires.add(stagiaire);
		}
	}
}
