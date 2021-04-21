package fr.eql.ai109.projet1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AlgoArbreDeTri {
	private Stagiaire root;
	private RandomAccessFile raf = null;
	final private int tailleNomMax = 30;
	final private int taillePrenomMax = 30;
	final private int tailleDepMax = 30;
	final private int tailleFormationMax = 30;
	final private int tailleAnneeMax = 30;
	

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

		if (nom.toUpperCase().compareTo(current.getNom().toUpperCase()) < 0) {
			current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee)); 
//			System.out.println(current);
		} else if (nom.toUpperCase().compareTo(current.getNom().toUpperCase()) > 0) {
			current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
//			System.out.println(current);

		} else {
			// value already exists
//			System.out.println(current);
			if(prenom.compareTo(current.getPrenom()) < 0) {
				current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
			} else if(prenom.compareTo(current.getPrenom()) > 0) {
				current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
			} else {
				if(prenom.compareTo(current.getDep()) < 0) {
					current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
				} else if(prenom.compareTo(current.getDep()) > 0) {
					current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
				} else {
					if(formation.compareTo(current.getFormation()) < 0) {
						current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
					} else if(formation.compareTo(current.getFormation()) > 0) {
						current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
					} else {
						if(annee < current.getAnnee()) {
							current.setStagiaireGauche(ajoutRecursive(current.getStagiaireGauche(), nom, prenom, dep, formation, annee));
						} else if(annee > current.getAnnee()) {
							current.setStagiaireDroite(ajoutRecursive(current.getStagiaireDroite(), nom, prenom, dep, formation, annee));
						} else {
							return current;
						}
					}
				}
			}
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
//				System.out.println(" " + intern.getFamilyName() + " " + intern.getName() + " " + intern.getDepartment() + " " 
//						+ intern.getTraining() + " " + intern.getYear());
	            
				// ajouter les espaces pour compléter la taille du nom
				byte[] b = stagiaire.getNom().getBytes();
				raf.write(b);
				int nbEspaces = tailleNomMax - stagiaire.getNom().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);

				// ajouter les espaces pour compléter la taille du prenom
				b = stagiaire.getPrenom().getBytes();
				raf.write(b);
				nbEspaces = taillePrenomMax - stagiaire.getPrenom().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);
				
				// ajouter les espaces pour compléter la taille du departement
				b = stagiaire.getDep().getBytes();
				raf.write(b);
				nbEspaces = tailleDepMax - stagiaire.getDep().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);
				
				// ajouter les espaces pour compléter la taille de la formation
				b = stagiaire.getFormation().getBytes();
				raf.write(b);
				nbEspaces = tailleFormationMax - stagiaire.getFormation().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);
				
				// ajouter les espaces pour compléter la taille de l'annee
				b = Integer.toString(stagiaire.getAnnee()).getBytes();
				raf.write(b);
				nbEspaces = tailleAnneeMax - Integer.toString(stagiaire.getAnnee()).length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);
				
				Long pointeurEnfantGauche = raf.getFilePointer() - ((5 * 30) + 16);
				if(pointeurEnfantGauche < 0) {
					pointeurEnfantGauche = 0L;
				}
				raf.writeLong(pointeurEnfantGauche);
				
				Long pointeurEnfantDroite = raf.getFilePointer() + 8;
				raf.writeLong(pointeurEnfantDroite);

				ecrireStagiaire(stagiaire.getStagiaireDroite(), raf);
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Stagiaire getRoot() {
		return root;
	}

	public void setRoot(Stagiaire root) {
		this.root = root;
	}

}


