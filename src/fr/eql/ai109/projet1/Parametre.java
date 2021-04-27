package fr.eql.ai109.projet1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public interface Parametre {
	final String cheminFichier = System.getProperty("user.dir") + "\\stagiaires.txt";
	final String cheminFichierCible = System.getProperty("user.dir") + "\\stagiaires.bin";
	final String cheminFichierTrieOrdreCroissant = System.getProperty("user.dir") + "\\stagiairesEnOrdre.txt";
	final File fichierCible = new File(cheminFichierCible);
	final File fichierTrieOrdreCroissant = new File(cheminFichierTrieOrdreCroissant);
	final int nombreChamp = 5;
	final int tailleLong = 8;
	final int nombreLong = 3;
	final int tailleChampMax = 30;
	final int tailleStagiaire = (tailleChampMax * nombreChamp) + (tailleLong * 3);
	final long nombreStagiaire = fichierCible.length() / tailleStagiaire;

	
	
	public static Stagiaire lireStagiaire(long positionStagiaire) {
		try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "r")){
			
			raf.seek(positionStagiaire);
			byte[] b;
			String nom;
			String prenom;
			String dep;
			String formation;
			String anneeString;
			int annee;
			long refGauche;
			long refDroite;

			Stagiaire stagiaireActuel;
			// lecture nom
			b = new byte[tailleChampMax];
			raf.read(b);
			nom = new String(b);
			nom = nom.trim();
			// lecture prenom
			b = new byte[tailleChampMax];
			raf.read(b);
			prenom = new String(b);
			prenom = prenom.trim();
			// lecture departement
			b = new byte[tailleChampMax];
			raf.read(b);
			dep = new String(b);
			dep = dep.trim();
			// lecture formation
			b = new byte[tailleChampMax];
			raf.read(b);
			formation = new String(b);
			formation = formation.trim();
			// lecture annee
			b = new byte[tailleChampMax];
			raf.read(b);
			anneeString = new String(b);
			anneeString = anneeString.trim();
			// parser string annee en integer
			annee = Integer.parseInt(anneeString);
			// lecture reference gauche
			refGauche = raf.readLong();
			// lecture reference droite
			refDroite = raf.readLong();
			// lecture position du stagigiaire actuel
			positionStagiaire = raf.readLong();
			// construction du stagiaire actuel
			stagiaireActuel = new Stagiaire(nom, prenom, dep, formation, annee);
			// attribution de la valeur de refGauche dans le stagiaire actuel
			stagiaireActuel.setRefGauche(refGauche);
			// attribution de la valeur de refDroite dans le stagiaire actuel
			stagiaireActuel.setRefDroite(refDroite);
			// attribution de la position la position du stagiaire actuel
			stagiaireActuel.setPositionStagiaire(positionStagiaire);
			return stagiaireActuel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
