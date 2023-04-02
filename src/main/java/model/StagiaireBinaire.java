package model;
import java.io.*;
import java.util.ArrayList;
import model.Stagiaire;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.List;

public class StagiaireBinaire {
	
	private final static int TAILLE_NOM = 21;
    private final static int TAILLE_DEPARTEMENT = 2;
    private final static int TAILLE_PROMO = 11;
    private final static int TAILLE_PRENOM = 20;
    private final static int TAILLE_ANNEE = 4;
    private final static int TAILLE_STAGIAIRE = (TAILLE_NOM +  TAILLE_PRENOM +TAILLE_DEPARTEMENT + TAILLE_PROMO + TAILLE_ANNEE)*2+4;
    
    private String nom;
    private String prenom;
    private String departement;
    private String promo;
    private String annee;
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
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	}

