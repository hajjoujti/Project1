package fr.eql.ai109.projet1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class MethodesCommuns implements Parametre {
	public Stagiaire lireStagiaire(long positionStagiaire) {
		try(RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "r")) {

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
			long positionParent;

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
			// lecture position parent
			positionParent = raf.readLong();
			// construction du stagiaire actuel
			stagiaireActuel = new Stagiaire(nom, prenom, dep, formation, annee);
			// attribution de la valeur de refGauche dans le stagiaire actuel
			stagiaireActuel.setRefGauche(refGauche);
			// attribution de la valeur de refDroite dans le stagiaire actuel
			stagiaireActuel.setRefDroite(refDroite);
			// attribution de la position la position du stagiaire actuel
			stagiaireActuel.setPositionStagiaire(positionStagiaire);
			// attribition de la ppsition parent
			stagiaireActuel.setPositionParent(positionParent);
			return stagiaireActuel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return null;

	}

	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
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
		raf.writeLong(stagiaire.getRefGauche());

		// ecririe le pointeur de stagiaire droite
		raf.writeLong(stagiaire.getRefDroite());

		// ecririe le pointeur du debut du stagiaire actuel
		raf.writeLong(stagiaire.getPositionStagiaire());

		//ecrire le pointeur du debut du parent du stagiaire
		raf.writeLong(stagiaire.getPositionParent());
	}

}
