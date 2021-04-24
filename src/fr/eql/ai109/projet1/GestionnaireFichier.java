package fr.eql.ai109.projet1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

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

		private Stagiaire lecturePremierStagiaireFichierCible() throws FileNotFoundException, IOException {
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
			return this.root;
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
				ecrireNouveauStagiaireDansFichier(parent);
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
			// verification valeur reference gauche du parent
			if (parent.getRefGauche() != -1) {
				parent.setStagiaireGauche(lireStagiaire(parent.getRefGauche()));
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

		private void nouveauStagiairePlusGrandParent(Stagiaire parent, Stagiaire nouveauStagiaire) {
			// enregistrer position parent
			pointeurParent = parent.getPositionStagiaire();	
			// verification valeur reference gauche du parent
			if (parent.getRefDroite() != -1) {
				parent.setStagiaireDroite(lireStagiaire(parent.getRefDroite()));
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
		
		public Stagiaire lireStagiaire(long positionStagiaire) {
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
	
		private void ecrireNouveauStagiaireDansFichier(Stagiaire stagiaire) {
			try {
				raf = new RandomAccessFile(cheminFichierCible, "rw");

				raf.seek(fichierCible.length());
				
				ecrireStagiaire(stagiaire);
				
				pointeur = raf.getFilePointer();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void ecrireStagiaire(Stagiaire stagiaire) throws IOException {
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

			// ecririe le pointeur de stagiaire droite
			raf.writeLong(-1);
			
			// ecririe le pointeur du debut du stagiaire actuel
			raf.writeLong(stagiaire.getPositionStagiaire());
		}
		
		public void traverseFichierOrdreCroissant() {
			Stagiaire stagiaire;
			fichierTrieOrdreCroissant.delete();
			try {
				fichierTrieOrdreCroissant.createNewFile();
				FileWriter out = new FileWriter(fichierTrieOrdreCroissant, true);
				BufferedWriter bw = new BufferedWriter(out);
				stagiaire = lecturePremierStagiaireFichierCible();
				recursiveLectureTraverseFichierOrdreCroissant(stagiaire, bw);
				bw.close();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}

		private void recursiveLectureTraverseFichierOrdreCroissant(Stagiaire stagiaire, BufferedWriter bw) throws IOException {
			if (stagiaire != null) {
		    	if (stagiaire.getRefGauche() != -1) {
		    		stagiaire.setStagiaireGauche(lireStagiaire(stagiaire.getRefGauche()));
				}
		    	if (stagiaire.getRefDroite() != -1) {
		    		stagiaire.setStagiaireDroite(lireStagiaire(stagiaire.getRefDroite()));
				}
		    	recursiveLectureTraverseFichierOrdreCroissant(stagiaire.getStagiaireGauche(), bw);
		    	String stagiaireOrdreCroissant = stagiaire.getNom() + "\t" + stagiaire.getPrenom() + "\t" + stagiaire.getDep() 
		        + "\t" + stagiaire.getFormation() + "\t" + stagiaire.getAnnee();
		    	bw.write(stagiaireOrdreCroissant);
		    	bw.newLine();
		        recursiveLectureTraverseFichierOrdreCroissant(stagiaire.getStagiaireDroite(), bw);
		    }
		}
		
		public ArrayList<Stagiaire> traverseFichierRechercheNom(String nom, String elementRecherche) {
			Stagiaire stagiaire;
			ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
			try {
				stagiaire = lecturePremierStagiaireFichierCible();
				recursiveLectureTraverseFichierRechercheChamps(stagiaire, nom, stagiaires, elementRecherche);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return stagiaires;
		}
		
		private void recursiveLectureTraverseFichierRechercheChamps(Stagiaire stagiaire, String champ, List<Stagiaire> stagiaires,
				String elementRecherche) throws IOException {
			if (stagiaire != null) {
		    	if (stagiaire.getRefGauche() != -1) {
		    		stagiaire.setStagiaireGauche(lireStagiaire(stagiaire.getRefGauche()));
				}
		    	if (stagiaire.getRefDroite() != -1) {
		    		stagiaire.setStagiaireDroite(lireStagiaire(stagiaire.getRefDroite()));
				}
		    	recursiveLectureTraverseFichierRechercheChamps(stagiaire.getStagiaireGauche(), champ, stagiaires, elementRecherche);
		    	int lengthChamp = champ.length();
		    	switch (elementRecherche) {
		    	case "nom":
		    		if(stagiaire.getNom().length()>= lengthChamp) {
		    			if(((String) stagiaire.getNom().toUpperCase().subSequence(0, lengthChamp)).contains(champ.toUpperCase())) {
		    				stagiaire.setStagiaireDroite(null);
		    				stagiaire.setStagiaireGauche(null);
		    				stagiaires.add(stagiaire);
		    			}
		    		}
		    	break;
		    	case "prenom":
		    		if(stagiaire.getPrenom().length()>= lengthChamp) {
		    			if(((String) stagiaire.getPrenom().toUpperCase().subSequence(0, lengthChamp)).contains(champ.toUpperCase())) {
		    				stagiaire.setStagiaireDroite(null);
		    				stagiaire.setStagiaireGauche(null);
		    				stagiaires.add(stagiaire);
		    			}
		    		}
		    	break;
		    	case "departement":
		    		if(stagiaire.getDep().length()>= lengthChamp) {
		    		if(((String) stagiaire.getDep().toUpperCase().subSequence(0, lengthChamp)).contains(champ.toUpperCase())) {
		    			stagiaire.setStagiaireDroite(null);
	    				stagiaire.setStagiaireGauche(null);
		    			stagiaires.add(stagiaire);
		    		}
		    		}
		    	break;
		    	case "formation":
		    		if(stagiaire.getFormation().length()>= lengthChamp) {
		    			if(((String) stagiaire.getFormation().toUpperCase().subSequence(0, lengthChamp)).contains(champ.toUpperCase())) {
		    				stagiaire.setStagiaireDroite(null);
		    				stagiaire.setStagiaireGauche(null);
		    				stagiaires.add(stagiaire);
		    			}
		    		}
		    	break;
		    	case "annee":
		    		if((stagiaire.getAnnee() == Integer.parseInt(champ))) {
		    			stagiaire.setStagiaireDroite(null);
	    				stagiaire.setStagiaireGauche(null);
		    			stagiaires.add(stagiaire);
		    		}
		    	break;
		    	}
		    		
		        recursiveLectureTraverseFichierRechercheChamps(stagiaire.getStagiaireDroite(), champ, stagiaires, elementRecherche);
		    }
		}

		public void modificationStagiaire(Stagiaire stagiaireModifie) {
			try {
				raf = new RandomAccessFile(cheminFichierCible, "rw");
				raf.seek(stagiaireModifie.getPositionStagiaire());
				ecrireStagiaire(stagiaireModifie);
				raf.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
