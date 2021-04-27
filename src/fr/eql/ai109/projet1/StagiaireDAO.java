package fr.eql.ai109.projet1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;

public class StagiaireDAO implements Parametre {
	private List<Stagiaire> stagiairesList = new ArrayList<Stagiaire>();	
	
	public void setStagiairesList(List<Stagiaire> stagiairesList) {
		this.stagiairesList = stagiairesList;
	}

	public List<Stagiaire> getStagiairesList() {
		return this.stagiairesList;
	}

	MethodesCommuns mc = new MethodesCommuns();
	GestionnaireFichier gf;

	public List<Stagiaire> getAll(){
		stagiairesList.clear();
		Stagiaire premierStagiaire = mc.lireStagiaire(0);
		recursiveLectureTraverseFichierOrdreCroissant(premierStagiaire);
		return this.stagiairesList;
	}
	
	private void recursiveLectureTraverseFichierOrdreCroissant(Stagiaire stagiaire) {
		if (stagiaire != null) {
			if (stagiaire.getRefGauche() != -1) {
				// definire le stagiaire gauche
				stagiaire.setStagiaireGauche(mc.lireStagiaire(stagiaire.getRefGauche()));
			}
			if (stagiaire.getRefDroite() != -1) {
				// definire le stagiaire droite
				stagiaire.setStagiaireDroite(mc.lireStagiaire(stagiaire.getRefDroite()));
			}
			// recursivite gauche
			recursiveLectureTraverseFichierOrdreCroissant(stagiaire.getStagiaireGauche());
			// ajout du stagiaire dans la liste
			stagiairesList.add(stagiaire);
			// recursivite droite
			recursiveLectureTraverseFichierOrdreCroissant(stagiaire.getStagiaireDroite());
		}
	}

	public void ConstruireListe() throws Exception{

		long positionStagiaire = 0; 
		//RandomAccessFile raf2 = null; 
		while (positionStagiaire<fichierCible.length()) {
			Stagiaire s = mc.lireStagiaire(positionStagiaire);
			positionStagiaire += tailleStagiaire; 
			stagiairesList.add(s);
		
		}
	}
	
	
	public void remove(int idx) {
		stagiairesList.remove(idx);	
	}
	
	public void replaceSeriesInListAndFile(Stagiaire newValue, Stagiaire series) {
		stagiairesList.set(stagiairesList.indexOf(newValue), series);
	}

	public List<Stagiaire> rechercheAvancee(String nom, String prenom, String dep, String formation, String annee) {
		gf = new GestionnaireFichier();
		stagiairesList = gf.rechercheAvancee(nom, prenom, dep, formation, annee);
		return stagiairesList;
	}

	public void ajoutNouveauStagiaire(Stagiaire nouveauStagiaire) {
		gf = new GestionnaireFichier();
		nouveauStagiaire.setPositionStagiaire(fichierCible.length());
		gf.ajout(nouveauStagiaire);
		this.stagiairesList.add(nouveauStagiaire);
	}

	public void supprimerStagiaire(Stagiaire StagiaireASupprimer) {
		gf = new GestionnaireFichier();
		System.out.println(StagiaireASupprimer);
		gf.supprimeStagiaire(StagiaireASupprimer);
		this.stagiairesList.remove(getStagiairesList().indexOf(StagiaireASupprimer));
	}

	public void modifierStagiaire(Stagiaire newValue, Stagiaire stagiaireRemplacant) {
		supprimerStagiaire(newValue);
		ajoutNouveauStagiaire(stagiaireRemplacant);
	}

}





//----------------------------------------------------------------------------------------------------------------------------------
//	public List<Stagiaire> getAll(){
//		 List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
//		 
//		 Stagiaire chimiste = new Stagiaire("Lu", "Loic", "94", "AI109", 2021);
//		 Stagiaire physicien = new Stagiaire("kick", "Drew", "75", "AI108", 2020);
//		 
//		 
//		 
//
//		 
//		 Collections.addAll(stagiaires, chimiste, physicien);
//		
//		return stagiaires;
//	}
//}





