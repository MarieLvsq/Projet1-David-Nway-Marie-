package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stagiaire implements Comparable<Stagiaire> {

	private String nom;
	private String departement;
	private String promo;
	private String prenom;
	private String annee;

	public Stagiaire(String nom, String prenom, String departement, String promo, String annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
	}

	public Stagiaire() {
		// TODO Auto-generated constructor stub
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	// public List<Stagiaire> readStagiairesFromResource(String resourceName) {
	public static List<Noeud> readNoeudsFromResource(String resourceName) {

		List<Noeud> noeuds = new ArrayList<>();
		List<Stagiaire> stagiaires = new ArrayList<>();
		try (InputStream inputStream = Stagiaire.class.getClassLoader().getResourceAsStream(resourceName)) {

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = reader.readLine();
			int lineNumber = 0;

			String nom = "";
			String prenom = "";
			String departement = "";
			String promo = "";
			String annee = "";

			while (line != null) {
				if (line.equals("*")) {
					line = reader.readLine();
					continue;
				}
				lineNumber++;
				switch (lineNumber) {
				case 1:
					nom = line;
					break;
				case 2:
					prenom = line;
					break;
				case 3:
					departement = line;
					break;
				case 4:
					promo = line;
					break;
				case 5:
					annee = line;
					stagiaires.add(new Stagiaire(nom, prenom, departement, promo, annee));
					lineNumber = 0; // reset the line number for the next set of values
					break;
				default:
					// handle invalid input
					break;
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			// handle the exception
		}
		// Sort the stagiaires list alphabetically by name and firstname
		//Collections.sort(stagiaires);

		for (Stagiaire stagiaire : stagiaires) {
			Noeud noeud = new Noeud(stagiaire);
			noeuds.add(noeud);
		}
		// ask Alix sustainable solution
	//	for (Noeud noeud : noeuds) {
		//	System.out.println(noeud);
			// pour afficher la liste
			// System.out.println(stagiaires);
	//	}
		return noeuds;
	}

	// pour afficher dans ordre alphabetique (o=other)
	//compare two Stagiaire objects based on their names and then their first names
	@Override
	public int compareTo(Stagiaire o) {
		int result = this.getNom().compareTo(o.getNom());
		if (result != 0) {
			return result;
		} else {
			return this.getPrenom().compareTo(o.getPrenom());
		}
	}
	
	   //TRAVAIL POUR LE FICHIER BINAIRE

	private final static int TAILLE_NOM = 21;
    private final static int TAILLE_DEPARTEMENT = 2;
    private final static int TAILLE_PROMO = 11;
    private final static int TAILLE_PRENOM = 20;
    private final static int TAILLE_ANNEE = 4;
    private final static int TAILLE_STAGIAIRE = (TAILLE_NOM +  TAILLE_PRENOM +TAILLE_DEPARTEMENT + TAILLE_PROMO + TAILLE_ANNEE)*2+4;
    
    private int taille;
    
    public static ArrayList<Stagiaire> lireFichier(String nomFichier) throws IOException {
        ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
        RandomAccessFile raf = new RandomAccessFile(nomFichier, "r");
      
        while (raf.getFilePointer() < raf.length()) {
            String nom = lireChaine(raf, TAILLE_NOM);
            String prenom = lireChaine(raf, TAILLE_PRENOM);
            String departement = lireChaine(raf, TAILLE_DEPARTEMENT);
            String promo = lireChaine(raf, TAILLE_PROMO);
            String annee = lireChaine(raf, TAILLE_ANNEE);
            Stagiaire s = new Stagiaire(nom, prenom,departement, promo,annee);
            stagiaires.add(s);
        }
        raf.close();
        return stagiaires;
        
    }
		    
        public static void ecrireFichier(String nomFichier, ArrayList<Stagiaire> stagiaires) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(nomFichier, "rw");
        for (Stagiaire s : stagiaires) {
            raf.writeChars(formaterChaine(s.getNom(), TAILLE_NOM));
            raf.writeChars(formaterChaine(s.getPrenom(), TAILLE_PRENOM));
            raf.writeChars(formaterChaine(s.getDepartement(), TAILLE_DEPARTEMENT));
            raf.writeChars(formaterChaine(s.getPromo(), TAILLE_PROMO));
            raf.writeChars(formaterChaine(s.getAnnee(), TAILLE_ANNEE));
        }
        raf.close();
    }
    
    
    
    // pour formatter
    private static String formaterChaine(String chaine, int taille) {
        StringBuilder sb = new StringBuilder(chaine);
        sb.setLength(taille);
        return sb.toString();
    }
// pOUR LIRE
    private static String lireChaine(RandomAccessFile raf, int taille) throws IOException {
        byte[] tab = new byte[taille * 2];
        raf.read(tab);
        char[] charArray = new char[taille];
        for (int i = 0, j = 0; i < tab.length; i += 2, j++) {
            charArray[j] = (char) ((tab[i] << 8) + (tab[i + 1] & 0xFF));
        }
        return new String(charArray);
    }

	
	
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getAnnee() {
		return annee;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s", nom, prenom, departement, promo, annee);
	}

		
}
