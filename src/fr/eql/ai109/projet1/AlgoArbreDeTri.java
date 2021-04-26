package fr.eql.ai109.projet1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AlgoArbreDeTri implements Parametre {
	private Stagiaire root;
	private MethodesCommuns mc = new MethodesCommuns();

	private long pointeurEnfantGauche = 0;
	private long pointeurEnfantDroite = 0;
	private long pointeurParent = 0;

	public AlgoArbreDeTri() {
		super();
		fichierCible.delete();
		this.setRoot(null);
	}
	
	public AlgoArbreDeTri(Stagiaire root) {
		super();
		this.root = root;
	}
	
	public Stagiaire getRoot() {
		return root;
	}

	public void setRoot(Stagiaire root) {
		this.root = root;
	}
	
	public void ajout(Stagiaire nouveauStagiaire) {
	  root = ajoutRecursive(root, nouveauStagiaire);
	}

	private Stagiaire ajoutRecursive(Stagiaire parent, Stagiaire nouveauStagiaire) {
		// verification du stagiaire parent
		if (parent == null) {
			try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "rw")) {
			parent = nouveauStagiaire;
			// ecriture nouveau stagiaire
			ecrireStagiaireImporteDansFichier(parent);
			// verification de la reference droite du parent et mettre a jour pour nouveau stagiaire
			if (pointeurEnfantDroite != -1) {
				raf.seek(pointeurParent + (tailleChampMax * 5) + tailleLong);
				raf.writeLong(pointeurEnfantDroite);
			}
			// verification de la reference gauche du parent et mettre a jour pour nouveau stagiaire
			if (pointeurEnfantGauche != -1) {
				raf.seek(pointeurParent + (tailleChampMax * 5));
				raf.writeLong(pointeurEnfantGauche);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return parent;
		}

		// comparaison nom
		if (nouveauStagiaire.getNom().toUpperCase().compareTo(parent.getNom().toUpperCase()) < 0) {
			nouveauStagiairePlusPetitParent(parent, nouveauStagiaire);
			
		} else if (nouveauStagiaire.getNom().toUpperCase().compareTo(parent.getNom().toUpperCase()) > 0) {
			nouveauStagiairePlusGrandParent(parent, nouveauStagiaire);
		} else {
			// comparaison prenom
			comparaisonPrenom(parent, nouveauStagiaire);
			return parent;
		}

		return parent;
	}

	private void comparaisonPrenom(Stagiaire parent, Stagiaire nouveauStagiaire) {
		if (nouveauStagiaire.getPrenom().toUpperCase().compareTo(parent.getPrenom().toUpperCase()) <= 0) {
			nouveauStagiairePlusPetitParent(parent, nouveauStagiaire);
			
		} else if (nouveauStagiaire.getPrenom().toUpperCase().compareTo(parent.getPrenom().toUpperCase()) > 0) {
			nouveauStagiairePlusGrandParent(parent, nouveauStagiaire);
		}
	}

	private void nouveauStagiairePlusPetitParent(Stagiaire parent, Stagiaire nouveauStagiaire) {
		// enregistrer position parent
		pointeurParent = parent.getPositionStagiaire();
		// enregisterer position de nouveau stagiaire dans reference gauche parent
		parent.setRefGauche(nouveauStagiaire.getPositionStagiaire());
		// enregistrer position parent du nouveau stagiaire
		nouveauStagiaire.setPositionParent(parent.getPositionStagiaire());
		// enregistrer position enfant gauche dans pointeur enfant gauche
		pointeurEnfantGauche = parent.getRefGauche();
		// reinitialiser le pointeur enfant droite
		pointeurEnfantDroite = -1;
		// recursivite
		parent.setStagiaireGauche(ajoutRecursive(parent.getStagiaireGauche(), nouveauStagiaire));
	}

	private void nouveauStagiairePlusGrandParent(Stagiaire parent, Stagiaire nouveauStagiaire) {
		// enregistrer position parent
		pointeurParent = parent.getPositionStagiaire();	
		// enregisterer position de nouveau stagiaire dans reference droite parent
		parent.setRefDroite(nouveauStagiaire.getPositionStagiaire());
		// enregistrer position parent du nouveau stagiaire
		nouveauStagiaire.setPositionParent(parent.getPositionStagiaire());
		// enregistrer position enfant droite dans pointeur enfant droite
		pointeurEnfantDroite = parent.getRefDroite();
		// reinitialiser le pointeur enfant gauche
		pointeurEnfantGauche = -1;
		// recursivite
		parent.setStagiaireDroite(ajoutRecursive(parent.getStagiaireDroite(), nouveauStagiaire));
	}

	private void ecrireStagiaireImporteDansFichier(Stagiaire stagiaire) {
		try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "rw")) {
			raf.seek(fichierCible.length());
			mc.ecrireStagiaire(stagiaire, raf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


