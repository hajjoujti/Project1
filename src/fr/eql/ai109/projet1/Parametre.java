package fr.eql.ai109.projet1;

import java.io.File;

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

}
