package fr.eql.ai109.projet1;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.function.Consumer;

public class GestionnaireFichier implements Parametre{
	private Stagiaire root;
	private MethodesCommuns mc = new MethodesCommuns();

	private long pointeur = 0;
	private long pointeurEnfantGauche = 0;
	private long pointeurEnfantDroite = 0;
	private long pointeurParent = 0;

	public GestionnaireFichier() {
		super();
		lirePremierStagiaireFichierCible();
	}

	public GestionnaireFichier(Stagiaire root) {
		super();
		this.root = root;
	}

	private Stagiaire lirePremierStagiaireFichierCible() {
		this.setRoot(mc.lireStagiaire(0));
		return this.root;
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
				ecrireNouveauStagiaireDansFichier(parent);
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
		// enregistrer position parent du nouveau stagiaire
		nouveauStagiaire.setPositionParent(parent.getPositionStagiaire());
		// verification valeur reference gauche du parent
		if (parent.getRefGauche() != -1) {
			parent.setStagiaireGauche(mc.lireStagiaire(parent.getRefGauche()));
		}
		// enregisterer position de nouveau stagiaire dans reference gauche parent
		parent.setRefGauche(nouveauStagiaire.getPositionStagiaire());
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
		// enregistrer position parent du nouveau stagiaire
		nouveauStagiaire.setPositionParent(parent.getPositionStagiaire());
		// verification valeur reference gauche du parent
		if (parent.getRefDroite() != -1) {
			parent.setStagiaireDroite(mc.lireStagiaire(parent.getRefDroite()));
		}
		// enregisterer position de nouveau stagiaire dans reference droite parent
		parent.setRefDroite(nouveauStagiaire.getPositionStagiaire());
		// enregistrer position enfant droite dans pointeur enfant droite
		pointeurEnfantDroite = parent.getRefDroite();
		// reinitialiser le pointeur enfant gauche
		pointeurEnfantGauche = -1;
		// recursivite
		parent.setStagiaireDroite(ajoutRecursive(parent.getStagiaireDroite(), nouveauStagiaire));
	}

	private void ecrireNouveauStagiaireDansFichier(Stagiaire stagiaire) {
		try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "rw")) {
			// placer le pointeur sur a la fin du fichier
			raf.seek(fichierCible.length());
			// ecrire le nouveau stagiaire
			mc.ecrireStagiaire(stagiaire, raf);
			// mise a jour de la valeur du pointeur
			setPointeur(raf.getFilePointer());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void traverseFichierOrdreCroissant() {
		// creation d'un stagiaire
		Stagiaire stagiaire;
		// suppression du fichier s'il existe
		fichierTrieOrdreCroissant.delete();
		try {
			// creation du fichier
			fichierTrieOrdreCroissant.createNewFile();
			FileWriter out = new FileWriter(fichierTrieOrdreCroissant, true);
			BufferedWriter bw = new BufferedWriter(out);
			// lecture du premier stagiaire
			stagiaire = lirePremierStagiaireFichierCible();
			// lecture et ecriture du fichier en ordre croissant
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
				// definire le stagiaire gauche
				stagiaire.setStagiaireGauche(mc.lireStagiaire(stagiaire.getRefGauche()));
			}
			if (stagiaire.getRefDroite() != -1) {
				// definire le stagiaire droite
				stagiaire.setStagiaireDroite(mc.lireStagiaire(stagiaire.getRefDroite()));
			}
			// recursivite gauche
			recursiveLectureTraverseFichierOrdreCroissant(stagiaire.getStagiaireGauche(), bw);
			String stagiaireOrdreCroissant = stagiaire.getNom() + "\t" + stagiaire.getPrenom() + "\t" + stagiaire.getDep() 
			+ "\t" + stagiaire.getFormation() + "\t" + stagiaire.getAnnee();
			// ecriture du stagiaire dans le fichier
			bw.write(stagiaireOrdreCroissant);
			bw.newLine();
			// recursivite droite
			recursiveLectureTraverseFichierOrdreCroissant(stagiaire.getStagiaireDroite(), bw);
		}
	}

	public ArrayList<Stagiaire> rechercheAvancee(String nom, String prenom, String dep, String formation, String annee) {
		Stagiaire stagiaire;
		ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
		try {
			// enregistrement du premier stagiaire lu dans le stagiaire
			stagiaire = lirePremierStagiaireFichierCible();
			// recherche recursive
			rechercheAvanceerecursive(stagiaire, nom, stagiaires, prenom, dep, formation, annee);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stagiaires;
	}

	private void rechercheAvanceerecursive(Stagiaire stagiaire, String nom, ArrayList<Stagiaire> stagiaires,
			String prenom, String dep, String formation, String annee) throws IOException {
		if (stagiaire != null) {
			if (stagiaire.getRefGauche() != -1) {
				stagiaire.setStagiaireGauche(mc.lireStagiaire(stagiaire.getRefGauche()));
			}
			if (stagiaire.getRefDroite() != -1) {
				stagiaire.setStagiaireDroite(mc.lireStagiaire(stagiaire.getRefDroite()));
			}
			// appel recursivite gauche
			rechercheAvanceerecursive(stagiaire.getStagiaireGauche(), nom, stagiaires, prenom, dep, formation, annee);
			// creation des variables contenant les tailles des champs recherches
			int tailleNom = nom.length(), taillePrenom = prenom.length(), tailleDep = dep.length(), 
					tailleFormation = formation.length(), tailleAnnee = annee.length();
			// verification si les tailles des champs sont plus grand a tailles chaque info utilisateur
			if(stagiaire.getNom().length() >= tailleNom && stagiaire.getPrenom().length() >= taillePrenom && 
					stagiaire.getDep().length() >= tailleDep && stagiaire.getFormation().length() >= tailleFormation) {
				// verification si les champs recherches se trouve dans le stagiaire
				if(nomStagiaireContientNomRecherche(stagiaire, nom, tailleNom) &&
						prenomStagiaireContinentPrenomRecherche(stagiaire, prenom, taillePrenom) &&
						depStagiaireContientDepRecherche(stagiaire, dep, tailleDep) &&
						formationStagiaireContienFormationRecherche(stagiaire, formation, tailleFormation) &&
						anneeStagiaireContientAnneRecherche(stagiaire, annee, tailleAnnee)) {
					// ajout du stagiaire conforme a la recherche
					ajoutStagiaireDansListRecherche(stagiaire, stagiaires);
				}
			}
			// appel recursivite gauche
			rechercheAvanceerecursive(stagiaire.getStagiaireDroite(), nom, stagiaires, prenom, dep, formation, annee);
		}
	}

	private boolean anneeStagiaireContientAnneRecherche(Stagiaire stagiaire, String annee, int tailleAnnee) {
		return ((String) Integer.toString(stagiaire.getAnnee()).subSequence(0, tailleAnnee)).contains(annee);
	}

	private boolean formationStagiaireContienFormationRecherche(Stagiaire stagiaire, String formation,
			int taillFormation) {
		return ((String) stagiaire.getFormation().toUpperCase().subSequence(0, taillFormation)).contains(formation.toUpperCase());
	}

	private boolean depStagiaireContientDepRecherche(Stagiaire stagiaire, String dep, int tailleDep) {
		return ((String) stagiaire.getDep().toUpperCase().subSequence(0, tailleDep)).contains(dep.toUpperCase());
	}

	private boolean prenomStagiaireContinentPrenomRecherche(Stagiaire stagiaire, String prenom, int taillePrenom) {
		return ((String) stagiaire.getPrenom().toUpperCase().subSequence(0, taillePrenom)).contains(prenom.toUpperCase());
	}

	private boolean nomStagiaireContientNomRecherche(Stagiaire stagiaire, String nom, int tailleNom) {
		return ((String) stagiaire.getNom().toUpperCase().subSequence(0, tailleNom)).contains(nom.toUpperCase());
	}

	private void ajoutStagiaireDansListRecherche(Stagiaire stagiaire, ArrayList<Stagiaire> stagiaires) {
		// creation stagiaire et enregistrement de ses instances
		Stagiaire stagiaireAlternatif = new Stagiaire(stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getDep(),
				stagiaire.getFormation(), stagiaire.getAnnee());
		stagiaireAlternatif.setRefGauche(stagiaire.getRefGauche());
		stagiaireAlternatif.setRefDroite(stagiaire.getRefDroite());
		stagiaireAlternatif.setPositionStagiaire(stagiaire.getPositionStagiaire());
		stagiaireAlternatif.setPositionParent(stagiaire.getPositionParent());
		// ajout du stagiaire dans la liste
		stagiaires.add(stagiaireAlternatif);
	}

	public void supprimeStagiaire(Stagiaire stagiaireASupprimer) {
		// si le stagiaire a supprime n'a pas d'enfants
		stagiaireASupprimerPasEnfants(stagiaireASupprimer);
		// si le stagiaire a supprime a un seul enfant
		stagiaireASupprimerAUnEnfant(stagiaireASupprimer);
		// si le stagiaire a supprime a deux enfants
		stagiaireASupprimerADeuxEnfant(stagiaireASupprimer);
	}

	private void stagiaireASupprimerADeuxEnfant(Stagiaire stagiaireASupprimer) {
		if(aDeuxEnfants(stagiaireASupprimer)) {
			Stagiaire stagiaireRemplacant = null, parentDuStagiaireRemplacant = null, enfantGaucheDuStagiaireRemplacant = null;
			// choisir le stagiaire gauche du stagiaire a supprime
			stagiaireRemplacant = mc.lireStagiaire(stagiaireASupprimer.getRefGauche());
			// trouver l'enfant extreme droite du gauche
			stagiaireRemplacant = trouverPlusGrandEnfantDansGauche(stagiaireRemplacant);
			// verification si le remplacant a un enfant gauche
			if(stagiaireRemplacant.getRefGauche() != -1) {
				enfantGaucheDuStagiaireRemplacant = mc.lireStagiaire(stagiaireRemplacant.getRefGauche());
			}
			// definir le parent du stagiaire remplacant
			parentDuStagiaireRemplacant = mc.lireStagiaire(stagiaireRemplacant.getPositionParent());
			// mettre les references du stagiaire a supprime dans le stagiaire rempalacant
			stagiaireRemplacant.setRefGauche(stagiaireASupprimer.getRefGauche());
			stagiaireRemplacant.setRefDroite(stagiaireASupprimer.getRefDroite());
			stagiaireRemplacant.setPositionStagiaire(stagiaireASupprimer.getPositionStagiaire());
			stagiaireRemplacant.setPositionParent(stagiaireASupprimer.getPositionParent());
			try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "rw")) {
				// ecraser le stagiaire a supprime par le stagiaire remplacant
				raf.seek(stagiaireASupprimer.getPositionStagiaire());
				mc.ecrireStagiaire(stagiaireRemplacant, raf);
				raf.seek(parentDuStagiaireRemplacant.getPositionStagiaire() + (tailleChampMax * nombreChamp) + tailleLong);
				if (enfantGaucheDuStagiaireRemplacant != null) {
					// chamgement de la reference parent du l'enfant du stagiaie remplacant
					raf.writeLong(enfantGaucheDuStagiaireRemplacant.getPositionStagiaire());
					// changemetn de la reference enfant droite du parent du stagiaire remplacant
					raf.seek(enfantGaucheDuStagiaireRemplacant.getPositionStagiaire() + tailleStagiaire - tailleLong);
					raf.writeLong(parentDuStagiaireRemplacant.getPositionStagiaire());
				} else {
					// si le stagiare ramplacant n'a pas d'enfant gauche on remplace la reference droite de son parent par -1
					raf.writeLong(-1);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private Stagiaire trouverPlusGrandEnfantDansGauche(Stagiaire enfantRemplacant) {
		Stagiaire enfantPlusGrandDeGauche = enfantRemplacant;
		while(enfantPlusGrandDeGauche.getRefDroite() != -1) {
			enfantPlusGrandDeGauche = mc.lireStagiaire(enfantPlusGrandDeGauche.getRefDroite());
		}
		return enfantPlusGrandDeGauche;
	}

	private boolean aDeuxEnfants(Stagiaire stagiaireASupprimer) {
		return stagiaireASupprimer.getRefGauche() != -1 && stagiaireASupprimer.getRefDroite() != -1;
	}

	private void stagiaireASupprimerAUnEnfant(Stagiaire stagiaireASupprimer) {
		if(aUnEnfant(stagiaireASupprimer)) {
			// creation et attribution du stagiaire parent
			Stagiaire parent = mc.lireStagiaire(stagiaireASupprimer.getPositionParent());;
			Stagiaire enfant = null;
			// trouver la branche du stagiaire enfant (gauche ou droite)
			if(stagiaireASupprimer.getRefGauche() != -1) {
				enfant = mc.lireStagiaire(stagiaireASupprimer.getRefGauche());
			} else if(stagiaireASupprimer.getRefDroite() != -1) {
				enfant = mc.lireStagiaire(stagiaireASupprimer.getRefDroite());
			}
			try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "rw")) {
				// trouver la branche du stagiaire a supprimer par rapport a son parent (gauche ou droite)
				if(stagiaireASupprimer.getPositionStagiaire() == parent.getRefGauche()) {
					// ecriture du position stagiaire enfant dans la reference gauche du parent
					raf.seek(parent.getPositionStagiaire() + (tailleChampMax * nombreChamp));
					raf.writeLong(enfant.getPositionStagiaire());
					// ecriture position parent dans la reference parent de l'enfant
					raf.seek(enfant.getPositionStagiaire() + tailleStagiaire - tailleLong);
					raf.writeLong(parent.getPositionStagiaire());
				} else if(stagiaireASupprimer.getPositionStagiaire() == parent.getRefDroite()) {
					// ecriture du position stagiaire enfant dans la reference droite du parent
					raf.seek(stagiaireASupprimer.getPositionParent() + (tailleChampMax * nombreChamp) + tailleLong);
					raf.writeLong(enfant.getPositionStagiaire());
					// ecriture position parent dans la reference parent de l'enfant
					raf.seek(enfant.getPositionStagiaire() + tailleStagiaire - tailleLong);
					raf.writeLong(parent.getPositionStagiaire());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean aUnEnfant(Stagiaire stagiaireASupprimer) {
		return (stagiaireASupprimer.getRefGauche() == -1 && stagiaireASupprimer.getRefDroite() != -1) || 
				(stagiaireASupprimer.getRefDroite() == -1 && stagiaireASupprimer.getRefGauche() != -1);
	}

	private void stagiaireASupprimerPasEnfants(Stagiaire stagiaireASupprimer) {
		if(pasDEnfants(stagiaireASupprimer)) {
			// creation et attribution du stagiaire parent
			Stagiaire parent = mc.lireStagiaire(stagiaireASupprimer.getPositionParent());
			try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "rw")) {
				// trouver la branche du stagiaire a supprimer par rapport a son parent (gauche ou droite)
				if(stagiaireASupprimer.getPositionStagiaire() == parent.getRefGauche()) {
					// mettre la reference gauche du parent a -1
					raf.seek(stagiaireASupprimer.getPositionParent() + (tailleChampMax * nombreChamp));
					raf.writeLong(-1);
				} else if(stagiaireASupprimer.getPositionStagiaire() == parent.getRefDroite()) {
					// mettre la reference droite du parent a -1
					raf.seek(stagiaireASupprimer.getPositionParent() + (tailleChampMax * nombreChamp) + tailleLong);
					raf.writeLong(-1);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private boolean pasDEnfants(Stagiaire stagiaireASupprimer) {
		return stagiaireASupprimer.getRefGauche() == -1 && stagiaireASupprimer.getRefDroite() == -1;
	}

	public Stagiaire getRoot() {
		return root;
	}

	public void setRoot(Stagiaire root) {
		this.root = root;
	}

	public long getPointeur() {
		return pointeur;
	}

	public void setPointeur(long pointeur) {
		this.pointeur = pointeur;
	}
}
