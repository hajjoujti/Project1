package fr.eql.ai109.projet1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AlgoArbreDeTri {
	private Stagiaire root;
	private RandomAccessFile raf = null;
	final static int tailleChampMax = 30;
	final static int nombreChamp = 5;
	final static int tailleLong = 8;

	public AlgoArbreDeTri() {
		super();
		this.setRoot(null);
	}
	
	public AlgoArbreDeTri(Stagiaire root) {
		super();
		this.setRoot(root);
	}
	
	private Stagiaire ajoutRecursive(Stagiaire current, String nom, String prenom, String dep, String formation, int annee) {
		if (current == null) {
			Stagiaire newIntern = new Stagiaire(nom, prenom, dep, formation, annee);
			return newIntern;
		}
		comparaisonNom(current, nom, prenom, dep, formation, annee);
		return current;
	}

	private void comparaisonNom(Stagiaire current, String nom, String prenom, String dep, String formation, int annee) {
		if (nom.toUpperCase().compareTo(current.getNom().toUpperCase()) < 0) {
			current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee)); 
		} else if (nom.toUpperCase().compareTo(current.getNom().toUpperCase()) > 0) {
			current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));

		} else {
			// value already exists
			comparaisonPrenom(current, nom, prenom, dep, formation, annee);
		}
	}

	private void comparaisonPrenom(Stagiaire current, String nom, String prenom, String dep, String formation,
			int annee) {
		if(prenom.compareTo(current.getPrenom()) < 0) {
			current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
		} else if(prenom.compareTo(current.getPrenom()) > 0) {
			current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
		} else {
			comparaisonDep(current, nom, prenom, dep, formation, annee);
		}
	}

	private void comparaisonDep(Stagiaire current, String nom, String prenom, String dep, String formation, int annee) {
		if(prenom.compareTo(current.getDep()) < 0) {
			current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
		} else if(prenom.compareTo(current.getDep()) > 0) {
			current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
		} else {
			comparaisonFormation(current, nom, prenom, dep, formation, annee);
		}
	}

	private void comparaisonFormation(Stagiaire current, String nom, String prenom, String dep, String formation,
			int annee) {
		if(formation.compareTo(current.getFormation()) < 0) {
			current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
		} else if(formation.compareTo(current.getFormation()) > 0) {
			current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
		} else {
			comparaisonAnnee(current, nom, prenom, dep, formation, annee);
		}
	}

	private Stagiaire comparaisonAnnee(Stagiaire current, String nom, String prenom, String dep, String formation,
			int annee) {
		if(annee < current.getAnnee()) {
			current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
		} else if(annee > current.getAnnee()) {
			current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
		} else {
			return current;
		}
		return current;
	}
	
	public void ajout(String nom, String prenom, String dep, String formation, int annee) {
		root = ajoutRecursive(root, nom, prenom, dep, formation, annee);
	}
	
	public void ecrire(Stagiaire stagiaire) {
		try {
			String cheminFichier = System.getProperty("user.dir") + "\\stagiaires.bin";
			raf = new RandomAccessFile(cheminFichier, "rw");
			
			ecrireStagiaire(stagiaire, raf);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
	}
	
	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {

		try {
			if (stagiaire != null) {
				ecrireStagiaire(stagiaire.getStagiaireGauche(), raf);
	            
				ecrireChaqueChamp(stagiaire, raf);

				ecrireStagiaire(stagiaire.getStagiaireDroite(), raf);
				
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void ecrireChaqueChamp(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
		// ajouter les espaces pour compléter la taille du nom
		byte[] b = stagiaire.getNom().getBytes();
		raf.write(b);
		int nbEspaces = tailleChampMax - stagiaire.getNom().length();
		b = new byte[nbEspaces];
		for (int i = 0; i < b.length; i++) {
			b[i] = ' ';
		}
		raf.write(b);

		// ajouter les espaces pour compléter la taille du prenom
		b = stagiaire.getPrenom().getBytes();
		raf.write(b);
		nbEspaces = tailleChampMax - stagiaire.getPrenom().length();
		b = new byte[nbEspaces];
		for (int i = 0; i < b.length; i++) {
			b[i] = ' ';
		}
		raf.write(b);
		
		// ajouter les espaces pour compléter la taille du departement
		b = stagiaire.getDep().getBytes();
		raf.write(b);
		nbEspaces = tailleChampMax - stagiaire.getDep().length();
		b = new byte[nbEspaces];
		for (int i = 0; i < b.length; i++) {
			b[i] = ' ';
		}
		raf.write(b);
		
		// ajouter les espaces pour compléter la taille de la formation
		b = stagiaire.getFormation().getBytes();
		raf.write(b);
		nbEspaces = tailleChampMax - stagiaire.getFormation().length();
		b = new byte[nbEspaces];
		for (int i = 0; i < b.length; i++) {
			b[i] = ' ';
		}
		raf.write(b);
		
		// ajouter les espaces pour compléter la taille de l'annee
		b = Integer.toString(stagiaire.getAnnee()).getBytes();
		raf.write(b);
		nbEspaces = tailleChampMax - Integer.toString(stagiaire.getAnnee()).length();
		b = new byte[nbEspaces];
		for (int i = 0; i < b.length; i++) {
			b[i] = ' ';
		}
		raf.write(b);
		
		// ajouter le pointeur de stagiaire gauche
		stagiaire.setRefGauche(raf.getFilePointer() - ((nombreChamp * tailleChampMax) + tailleLong * 2));
		if(stagiaire.getRefGauche() < 0) {
			stagiaire.setRefGauche(0L);
		}
		raf.writeLong(stagiaire.getRefGauche());
		
		// ajouter le pointeur de stagiaire droite
		stagiaire.setRefDroite(raf.getFilePointer() + tailleLong);
		raf.writeLong(stagiaire.getRefDroite());
	}

	public Stagiaire getRoot() {
		return root;
	}

	public void setRoot(Stagiaire root) {
		this.root = root;
	}

}


