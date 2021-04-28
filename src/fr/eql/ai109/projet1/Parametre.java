package fr.eql.ai109.projet1;

import java.io.File;

public interface Parametre {
	final String cheminFichier = System.getProperty("user.dir") + "\\stagiaires.txt";
	final String cheminFichierCible = System.getProperty("user.dir") + "\\stagiaires.bin";
	final String cheminFichierTrieOrdreCroissant = System.getProperty("user.dir") + "\\stagiairesEnOrdre.txt";
	final String cheminFichierDoc = System.getProperty("user.dir") + "\\README.txt";
	final String chemingLogoEQL = System.getProperty("user.dir") + "\\icons\\eql-logo.png";
	final String cheminExportPdf = System.getProperty("user.dir") + "\\";
	final String cheminIconAjout = "file:\\" + System.getProperty("user.dir") + "\\icons\\add.png";
	final String cheminIconSupp = "file:\\" + System.getProperty("user.dir") + "\\icons\\delete.png";
	final String cheminIconModif = "file:\\" + System.getProperty("user.dir") + "\\icons\\edit.png";
	final File fichierInitial = new File(cheminFichier);
	final File fichierCible = new File(cheminFichierCible);
	final File fichierTrieOrdreCroissant = new File(cheminFichierTrieOrdreCroissant);
	final File fichierDoc = new File(cheminFichierDoc);
	final int nombreChamp = 5;
	final int tailleLong = 8;
	final int nombreLong = 4;
	final int tailleChampMax = 30;
	final int tailleStagiaire = (tailleChampMax * nombreChamp) + (tailleLong * nombreLong);
	final long nombreStagiaire = fichierCible.length() / tailleStagiaire;
	final String messageErreurFormatNumbero = "L'année choisi n'est pas un nombre. Veuillez saisir un nombre.";
	final String messageErreurAnneQuatreChiffre = "L'année saisi doit être un nombre à quatre chiffres. Veuillez resaisir.";
	final String messageFormationTailleGrande = "La taille de la formation ne peut pas depasser " + tailleChampMax + " caractères.";
	final String messageDepTailleGrande = "La taille du departement ne peut pas depasser " + tailleChampMax + " caractères.";
	final String messagePrenomTailleGrande = "La taille du prénom ne peut pas depasser " + tailleChampMax + " caractères.";
	final String messageNomTailleGrande = "La taille du nom ne peut pas depasser " + tailleChampMax + " caractères.";
	final String messageDocumentationErreur = "Imposseible d'ouvrir la documentation.";
}
