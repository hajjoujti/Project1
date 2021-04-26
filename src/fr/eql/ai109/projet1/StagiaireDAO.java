package fr.eql.ai109.projet1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StagiaireDAO {
		

	final static int tailleChampMax = 30;
	static List<Stagiaire> stagiairesList = new ArrayList<Stagiaire>();	

	public List<Stagiaire> getAll(){
		try {
			ConstruireListe();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return stagiairesList;
	}

	public void ConstruireListe() throws Exception{

		RandomAccessFile raf1 = null; 
		RandomAccessFile raf2 = null; 
		try {
			raf1 = new RandomAccessFile ("C:\\Users\\formation\\Desktop\\Int JAVA POO\\Project1\\stagiaires.bin", "r");
			raf1.seek(0); 
			System.out.println(raf1.getFilePointer());
			raf2 = new RandomAccessFile("C:\\Users\\formation\\Desktop\\Int JAVA POO\\Project1\\stagiaires.bin", "r");
			
			for (int i=150; i<=300; i+=150) {	       //Indiquer de lire tout le fichier 			
				raf1.seek(i);
				raf2.seek(i-150);
				System.out.println(raf2.getFilePointer());
				byte[] b = new byte [tailleChampMax];
				//Lecture NOMS
				raf2.read(b);
				String nom = new String(b);
				nom = nom.trim();
				//Lecture Prenoms
				raf2.read(b);
				String prenom = new String(b);
				prenom = prenom.trim();
				//Lecture Departement
				raf2.read(b);
				String dep = new String(b);
				dep = dep.trim();
				//Lecture Formation
				raf2.read(b);
				String formation = new String(b);
				formation = formation.trim();
				//Lecture Annee
				raf2.read(b);
				String annees = new String(b);
				annees = annees.trim();
				int annee = Integer.parseInt(annees);
				Stagiaire s = new Stagiaire(nom, prenom, dep, formation, annee); 
				//s.setNom(nom);
				stagiairesList.add(s);
			}	
			System.out.println("ou se trouve le pointeur apres la lecture : " + raf1.getFilePointer());
			System.out.println(stagiairesList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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





