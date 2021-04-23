package fr.eql.ai109.projet1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GestionnaireFichier implements Parametre{
		Stagiaire root;
		RandomAccessFile raf = null;

		long pointeur = 0;
		long pointeurEnfantGauche = 0;
		long pointeurEnfantDroite = 0;
		long pointeurParent = 0;

		public GestionnaireFichier() {
			super();
			try {
				lecturePremierStagiaireFichierCible();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated constructor stub
		}

		public GestionnaireFichier(Stagiaire root) {
			super();
			this.root = root;
		}

		private void lecturePremierStagiaireFichierCible() throws FileNotFoundException, IOException {
			raf = new RandomAccessFile(cheminFichierCible, "r");
			byte[] b;
			String nom;
			String prenom;
			String dep;
			String formation;
			String anneeString;
			int annee;
			long refGauche;
			long refDroite;
			long positionStagiaire;

			Stagiaire stagiaireActuel;

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
			annee = Integer.parseInt(anneeString);
			refGauche = raf.readLong();
			refDroite = raf.readLong();
			positionStagiaire = raf.readLong();
			stagiaireActuel = new Stagiaire(nom, prenom, dep, formation, annee);
			stagiaireActuel.setRefGauche(refGauche);
			stagiaireActuel.setRefDroite(refDroite);
			stagiaireActuel.setPositionStagiaire(positionStagiaire);
			this.setRoot(stagiaireActuel);
			raf.close();
		}	
		
		public Stagiaire getRoot() {
			return root;
		}

		public void setRoot(Stagiaire root) {
			this.root = root;
		}
		
		public void add(Stagiaire nouveauStagiaire) {
		  root = addRecursive(root, nouveauStagiaire);
		}

		private Stagiaire addRecursive(Stagiaire parent, Stagiaire nouveauStagiaire) {
			// verification du stagiaire parent
			if (parent == null) {
				try {
				parent = nouveauStagiaire;
				// ecriture nouveau stagiaire
				ecritureNouveauStagiaireDansFichier(parent);
				// verification de la reference droite du parent et mettre a jour pour nouveau stagiaire
				if (pointeurEnfantDroite != 0) {
					raf.seek(pointeurParent + (tailleChampMax * 5) + tailleLong);
					raf.writeLong(pointeurEnfantDroite);
				}
				// verification de la reference gauche du parent et mettre a jour pour nouveau stagiaire
				if (pointeurEnfantGauche != 0) {
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
				nouvequStagiairePlusGrandParent(parent, nouveauStagiaire);
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
				nouvequStagiairePlusGrandParent(parent, nouveauStagiaire);
			}
		}

		private void nouveauStagiairePlusPetitParent(Stagiaire parent, Stagiaire nouveauStagiaire) {
			// enregistrer position parent
			pointeurParent = parent.getPositionStagiaire();
			// verification valeur reference gauche du parent
			if (parent.getRefGauche() != -1) {
				parent.setStagiaireGauche(lectureStagiaire(parent.getRefGauche()));
			}
			// enregisterer position de nouveau stagiaire dans reference gauche parent
			parent.setRefGauche(nouveauStagiaire.getPositionStagiaire());
			// enregistrer position enfant gauche dans pointeur enfant gauche
			pointeurEnfantGauche = parent.getRefGauche();
			// reinitialiser le pointeur enfant droite
			pointeurEnfantDroite = 0;
			// recursivite
			parent.setStagiaireGauche(addRecursive(parent.getStagiaireGauche(), nouveauStagiaire));
		}

		private void nouvequStagiairePlusGrandParent(Stagiaire parent, Stagiaire nouveauStagiaire) {
			// enregistrer position parent
			pointeurParent = parent.getPositionStagiaire();	
			// verification valeur reference gauche du parent
			if (parent.getRefDroite() != -1) {
				parent.setStagiaireDroite(lectureStagiaire(parent.getRefDroite()));
			}
			// enregisterer position de nouveau stagiaire dans reference droite parent
			parent.setRefDroite(nouveauStagiaire.getPositionStagiaire());
			// enregistrer position enfant droite dans pointeur enfant droite
			pointeurEnfantDroite = parent.getRefDroite();
			// reinitialiser le pointeur enfant gauche
			pointeurEnfantGauche = 0;
			// recursivite
			parent.setStagiaireDroite(addRecursive(parent.getStagiaireDroite(), nouveauStagiaire));
		}
		
		public Stagiaire lectureStagiaire(long positionStagiaire) {
			try {
				raf = new RandomAccessFile(cheminFichierCible, "r");
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
				annee = Integer.parseInt(anneeString);
				refGauche = raf.readLong();
				refDroite = raf.readLong();
				positionStagiaire = raf.readLong();
				stagiaireActuel = new Stagiaire(nom, prenom, dep, formation, annee);
				stagiaireActuel.setRefGauche(refGauche);
				stagiaireActuel.setRefDroite(refDroite);
				stagiaireActuel.setPositionStagiaire(positionStagiaire);
				return stagiaireActuel;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
	
		private void ecritureNouveauStagiaireDansFichier(Stagiaire stagiaire) {
			try {
				raf = new RandomAccessFile(cheminFichierCible, "rw");

				raf.seek(fichierCible.length());
				// ecririe le nom et les espaces pour compléter la taille
				byte[] b = stagiaire.getNom().getBytes();
				raf.write(b);
				int nbEspaces = tailleChampMax - stagiaire.getNom().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);

				// ecririe le prenom et les espaces pour compléter la taille
				b = stagiaire.getPrenom().getBytes();
				raf.write(b);
				nbEspaces = tailleChampMax - stagiaire.getPrenom().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);

				// ecririe le departement et les espaces pour compléter la taille
				b = stagiaire.getDep().getBytes();
				raf.write(b);
				nbEspaces = tailleChampMax - stagiaire.getDep().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);

				// ecririe la formation et les espaces pour compléter la taille
				b = stagiaire.getFormation().getBytes();
				raf.write(b);
				nbEspaces = tailleChampMax - stagiaire.getFormation().length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);

				// ecririe l'annee et les espaces pour compléter la taille
				b = Integer.toString(stagiaire.getAnnee()).getBytes();
				raf.write(b);
				nbEspaces = tailleChampMax - Integer.toString(stagiaire.getAnnee()).length();
				b = new byte[nbEspaces];
				for (int i = 0; i < b.length; i++) {
					b[i] = ' ';
				}
				raf.write(b);

				// ecririe le pointeur de stagiaire gauche
				raf.writeLong(-1);
				System.out.println("refGauche " + stagiaire.getNom() + " " + stagiaire.getRefGauche());

				// ecririe le pointeur de stagiaire droite
				raf.writeLong(-1);
				System.out.println("refDroite " + stagiaire.getRefDroite());
				
				// ecririe le pointeur du debut du stagiaire actuel
				raf.writeLong(stagiaire.getPositionStagiaire());
				System.out.println("refStagiaire " + stagiaire.getPositionStagiaire());
				
				pointeur = raf.getFilePointer();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}
