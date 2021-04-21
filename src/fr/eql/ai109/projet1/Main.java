package fr.eql.ai109.projet1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

	public static void main(String[] args) {
		String cheminFichier = System.getProperty("user.dir") + "\\stagiaires.txt";
		try {
			RandomAccessFile raf = new RandomAccessFile(cheminFichier, "r");
			String line = raf.readLine();
			System.out.println(line);
			raf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
