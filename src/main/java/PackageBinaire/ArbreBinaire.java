package binaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArbreBinaire {
	private NoeudBinaire racine;
	private RandomAccessFile raf;

	public ArbreBinaire(NoeudBinaire racine, RandomAccessFile raf) {
		super();
		this.racine = new NoeudBinaire(null);
		try {
			this.raf = new RandomAccessFile("Stagiaires.DON", "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArbreBinaire() {
		// TODO Auto-generated constructor stub
	}

	public void ajouterNoeud(StagiaireBinaire noeudStagiaire) {
		// base case : s'il n'y a pas de racine
		try {
			if (raf.length() == 0) {
				racine = new NoeudBinaire(noeudStagiaire);
				raf.writeChars(racine.getNomLong());
				raf.writeChars(racine.getPrenomLong());
				raf.writeChars(racine.getDepartementLong());
				raf.writeChars(racine.getPromoLong());
				raf.writeChars(racine.getAnneeLong());

//			raf.writeInt(-1); //au début de l'arbre pas de FilsGauche
//
//			raf.writeInt(-1); //au début de l'arbre pas de FilsDroit

			}

			else {
				// lire le premier noeud du fichier binaire et le stocker dans la racine
				raf.seek(0);
				String nom = "";
				String prenom = "";
				String departement = "";
				String promo = "";
				String annee = "";
				for (int j = 0; j < NoeudBinaire.TAILLE_MAX_LIGNE; j++) {
					nom += raf.readChar();
				}
				for (int j = 0; j < NoeudBinaire.TAILLE_MAX_LIGNE; j++) {
					prenom += raf.readChar();
				}
				for (int j = 0; j < NoeudBinaire.TAILLE_MAX_LIGNE; j++) {
					departement += raf.readChar();
				}
				for (int j = 0; j < NoeudBinaire.TAILLE_MAX_LIGNE; j++) {
					promo += raf.readChar();
				}
				for (int j = 0; j < NoeudBinaire.TAILLE_MAX_LIGNE; j++) {
					annee += raf.readChar();
				}
				System.out.println("Nom : " + nom + ", Prénom : " + prenom + ", Département : " + departement
						+ ", Promo : " + promo + ", Année : " + annee);
				racine = new NoeudBinaire (new StagiaireBinaire(nom, prenom, departement, promo, annee));
				racine.ajouterNoeud(new NoeudBinaire(noeudStagiaire), raf);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//public static void affichageInfixeNoeud(Noeud noeud) {
//	if (noeud.getFilsGauche() != -1) {
//		noeud.getFilsGauche().affichageInfixeNoeud();
//	}
//	System.out.println(noeud.getCle());
//	if (noeud.getFilsDroit() != -1) {
//		noeud.getFilsDroit().affichageInfixeNoeud();
//	}
//}

	public NoeudBinaire getRacine() {
		return racine;
	}

	public void setRacine(NoeudBinaire racine) {
		this.racine = racine;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}
}
