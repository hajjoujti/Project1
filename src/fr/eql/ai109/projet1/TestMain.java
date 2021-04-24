package fr.eql.ai109.projet1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class TestMain implements Parametre {
	private static GestionnaireFichier gf;

	public static void main(String[] args) {
		
//		Stagiaire s1 = new Stagiaire("Maalouf", "Nicolas", "asdas", "sdgfsd", 2010);
//		s1.setPositionStagiaire(0);
//		Stagiaire s2 = new Stagiaire("Ayoub", "Nicolas", "asdas", "sdgfsd", 2010);
//		s2.setPositionStagiaire(174);
//		Stagiaire s3 = new Stagiaire("Vines", "Nicolas", "asdas", "sdgfsd", 2010);
//		s3.setPositionStagiaire(348);
//		Stagiaire s4 = new Stagiaire("Bou", "Nicolas", "asdas", "sdgfsd", 2010);
//		s4.setPositionStagiaire(522);
//		Stagiaire s5 = new Stagiaire("Nemr", "Nicolas", "asdas", "sdgfsd", 2010);
//		s5.setPositionStagiaire(696);
//		Stagiaire s6 = new Stagiaire("Jabal", "Nicolas", "asdas", "sdgfsd", 2010);
//		s6.setPositionStagiaire(870);
//		Stagiaire s7 = new Stagiaire("Abou", "Nicolas", "asdas", "sdgfsd", 2010);
//		s7.setPositionStagiaire(1044);
//		Stagiaire s8 = new Stagiaire("Ayoub", "Nicolas", "asdas", "sdgfsd", 2010);
//		s8.setPositionStagiaire(1218);
//		Stagiaire s9 = new Stagiaire("Ayoub", "Nicolas", "asdas", "sdgfsd", 2010);
//		s9.setPositionStagiaire(1392);
//		Stagiaire s10 = new Stagiaire("Aasdfsd", "Nicolas", "asdas", "sdgfsd", 2010);
//		s10.setPositionStagiaire(1566);
//		Stagiaire s11 = new Stagiaire("vvsdfsd", "Nicolas", "asdas", "sdgfsd", 2010);
//		s11.setPositionStagiaire(1740);
//		Stagiaire s12 = new Stagiaire("zjsdfds", "Nicolas", "asdas", "sdgfsd", 2010);
//		s12.setPositionStagiaire(1914);
//		gf = new GestionnaireFichier();
//		gf.add(s1);
//		gf.add(s2);
//		gf.add(s3);
//		gf.add(s4);
//		gf.add(s5);
//		gf.add(s6);
//		gf.add(s7);
//		gf.add(s8);
//		gf.add(s9);
//		gf.add(s10);
//		gf.add(s11);
//		gf.add(s12);
		
//		Stagiaire s13 = new Stagiaire("Ayoub", "Nicolas", "75", "AI 209", 2021);
//		s13.setPositionStagiaire(fichierCible.length());
//		gf = new GestionnaireFichier();
//		gf.add(s13);
		
//		Stagiaire s14 = new Stagiaire("zrrsnab", "Nicolas", "asdas", "sdgfsd", 2010);
//		s14.setPositionStagiaire(fichierCible.length());
//		gf = new GestionnaireFichier();
//		gf.add(s14);
		
//		gf = new GestionnaireFichier();
//		gf.traverseFichierOrdreCroissant();
		
//		try {
//			RandomAccessFile raf = new RandomAccessFile(cheminFichierCible, "r");
//			System.out.println(fichierCible.length());
//			System.out.println(fichierCible.length() - 166);
//			raf.seek(0);// lecture nom
//
//			byte[] b;
//			String nom;
//			String prenom;
//			String dep;
//			String formation;
//			String anneeString;
//			//			System.out.println(anneeString);
//			int annee;
//
//			long refGauche;
//			long refDroite;
//			long refStagiaire;
//
//			Stagiaire stagiaireActuel;
//			System.out.println(raf.getFilePointer());
//			for (int i = 0; i < nombreStagiaire; i++) {
//				b = new byte[tailleChampMax];
//				raf.read(b);
//				nom = new String(b);
//				nom = nom.trim();
//				// lecture prenom
//				b = new byte[tailleChampMax];
//				raf.read(b);
//				prenom = new String(b);
//				prenom = prenom.trim();
//				// lecture departement
//				b = new byte[tailleChampMax];
//				raf.read(b);
//				dep = new String(b);
//				dep = dep.trim();
//				// lecture formation
//				b = new byte[tailleChampMax];
//				raf.read(b);
//				formation = new String(b);
//				formation = formation.trim();
//				// lecture annee
//				b = new byte[tailleChampMax];
//				raf.read(b);
//				anneeString = new String(b);
//				anneeString = anneeString.trim();
//				annee = Integer.parseInt(anneeString);
//				refGauche = raf.readLong();
//				refDroite = raf.readLong();
//				refStagiaire = raf.readLong();
//				stagiaireActuel = new Stagiaire(nom, prenom, dep, formation, annee);
//				stagiaireActuel.setRefGauche(refGauche);
//				stagiaireActuel.setRefDroite(refDroite);
//				stagiaireActuel.setPositionStagiaire(refStagiaire);
//				System.out.println(stagiaireActuel);
//				System.out.println(raf.getFilePointer());
//			}
//
//
//			raf.close();
//
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
