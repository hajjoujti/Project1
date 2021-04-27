package fr.eql.ai109.projet1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StagiaireDAO implements Parametre {


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

		long positionStagiaire = 0; 
		//RandomAccessFile raf2 = null; 
		while (positionStagiaire<fichierCible.length()) {
			Stagiaire s = Parametre.lireStagiaire(positionStagiaire);
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





