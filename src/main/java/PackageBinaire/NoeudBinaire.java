package binaire;

import java.io.IOException;
import java.io.RandomAccessFile;


public class NoeudBinaire{
	public final static int TAILLE_MAX_LIGNE = 30;
	public final static int TAILLE_MAX_NOEUD_OCTET = 150 + 2 * 4;
	private StagiaireBinaire cle;
	private NoeudBinaire cleAjout;
	private int filsGauche;
	private int filsDroit;

	// this constructor takes a Stagiaire object as its parameter
	// and initializes the cle field of the new Noeud object

	public NoeudBinaire(StagiaireBinaire cle) {
		this.cle = cle;
		this.filsGauche = -1;
		this.filsDroit = -1;
	}
	// the second constructor allows you to create a Noeud object
	// with both a Stagiaire key and references to its left and right child nodes

	public NoeudBinaire(StagiaireBinaire cle, int filsGauche, int filsDroit) {
		this.cle = cle;
		this.filsDroit = filsDroit;
		this.filsGauche = filsGauche;
	}

//	RandomAccessFile raf = new RandomAccessFile("src/fichiers/Stagiaires.bin", "rw");

// ajouter Noeud 
	public void ajouterNoeud( NoeudBinaire noeudStagiaire, RandomAccessFile raf) {
		int comparaison = this.cle.getNom().compareTo(noeudStagiaire.cle.getNom());
		try {
			if (comparaison > 0) {
				if (this.filsGauche == -1) { // cas de terminaison
					this.filsGauche = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);
					raf.seek(raf.getFilePointer() - 8);//on remonte d'un fils gauche
					raf.writeInt(this.filsGauche); // on donne la valeur au fils gauche
					raf.seek(raf.getFilePointer() - TAILLE_MAX_NOEUD_OCTET + StagiaireBinaire.getTailleNom() * 2); // Aller à la fin de ton noeud
					ecrireNoeud(cleAjout, raf);

				} else {
					raf.seek(this.filsGauche * TAILLE_MAX_NOEUD_OCTET);
					NoeudBinaire filsGauche = lireNoeud(raf);
					filsGauche.ajouterNoeud(noeudStagiaire, raf);
				}
			} else if (comparaison < 0) {
				if (this.filsDroit == -1) {
					this.filsDroit = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET); 
					raf.seek(raf.getFilePointer() - 4); // ici on remonte de 4 octets seulement
					raf.writeInt(this.filsDroit); // on donne la valeur au fils droit
					raf.seek(raf.getFilePointer() - TAILLE_MAX_NOEUD_OCTET + StagiaireBinaire.getTailleNom() * 2); // Aller à la fin de ton noeud
					ecrireNoeud(noeudStagiaire, raf);

				} else {
					raf.seek(this.filsDroit * TAILLE_MAX_NOEUD_OCTET);
					NoeudBinaire filsDroit = lireNoeud(raf);
					filsDroit.ajouterNoeud(noeudStagiaire, raf);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	public NoeudBinaire lireNoeud(RandomAccessFile raf) {
//		int filsGauche = -1;
//		int filsDroit = -1;
		String nom = "";
		String prenom = "";
		String departement = "";
		String promo = "";
		String annee = "";
		try {
			for (int j = 0; j < StagiaireBinaire.getTailleNom(); j++) {
				nom += raf.readChar();
			}
			for (int j = 0; j < StagiaireBinaire.getTaillePrenom(); j++) {
				prenom += raf.readChar();
			}
			for (int j = 0; j < StagiaireBinaire.getTailleDepartement(); j++) {
				departement += raf.readChar();
			}
			for (int j = 0; j < StagiaireBinaire.getTaillePromo(); j++) {
				promo += raf.readChar();
			}
			for (int j = 0; j < StagiaireBinaire.getTailleAnnee(); j++) {
				annee += raf.readChar();
			}

			int filsGauche = raf.readInt();
			int filsDroit = raf.readInt();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Nom : " + nom + ", Prénom : " + prenom + ", Département : " + departement + ", Promo : "
				+ promo + ", Année : " + annee);

		return new NoeudBinaire(new StagiaireBinaire(nom, prenom, departement, promo, annee));
	}

	public NoeudBinaire ecrireNoeud(NoeudBinaire cleAjout, RandomAccessFile raf) {
		// TODO Auto-generated method stub
		try {
			raf.writeChars(cleAjout.getNomLong());
			raf.writeChars(cleAjout.getPrenomLong());
			raf.writeChars(cleAjout.getDepartementLong());
			raf.writeChars(cleAjout.getPromoLong());
			raf.writeChars(cleAjout.getAnneeLong());
			// entier fils gauche
			raf.writeInt(-1);
			// entier fils droit
			raf.writeInt(-1);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cleAjout;
	}

	// to traverse the BST in an inorder traversal and print the node values to the
	// console
//	public void affichageInfixeNoeud() {
//		if (this.filsGauche != -1) {
//			this.filsGauche.affichageInfixeNoeud();
//		}
//		System.out.println(this.cle);
//		if (this.filsDroit != -1) {
//			this.filsDroit.affichageInfixeNoeud();
//		}
//	}
	public String getNomLong() {
		String nomLong = this.cle.getNom();
		for (int i = this.cle.getNom().length(); i < StagiaireBinaire.getTailleNom(); i++) {
			nomLong += "*";
		}
		return nomLong;
	}

	public String getPrenomLong() {
		String prenomLong = this.cle.getPrenom();
		for (int i = this.cle.getPrenom().length(); i < StagiaireBinaire.getTaillePrenom(); i++) {
			prenomLong += "*";
		}
		return prenomLong;
	}

	public String getDepartementLong() {
		String departementLong = this.cle.getDepartement();
		for (int i = this.cle.getDepartement().length(); i < StagiaireBinaire.getTailleDepartement(); i++) {
			departementLong += "*";
		}
		return departementLong;
	}

	public String getPromoLong() {
		String promoLong = this.cle.getPromo();
		for (int i = this.cle.getPromo().length(); i < StagiaireBinaire.getTaillePromo(); i++) {
			promoLong += "*";
		}
		return promoLong;
	}

	public String getAnneeLong() {
		String anneeLong = this.cle.getAnnee();
		for (int i = this.cle.getAnnee().length(); i < StagiaireBinaire.getTailleAnnee(); i++) {
			anneeLong += "*";
		}
		return anneeLong;
	}
	public StagiaireBinaire getCle() {
		return cle;
	}

	public void setCle(StagiaireBinaire cle) {
		this.cle = cle;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	@Override
	public String toString() {
		return cle.toString();
	}

}


