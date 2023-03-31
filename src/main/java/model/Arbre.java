package model;

import java.util.ArrayList;
import java.util.List;

public class Arbre {

	private Noeud racine;

	// Default constructor that initializes the `racine` instance variable to null
	public Arbre() {
		this.racine = null;
	}

	// Constructor that takes a `Noeud` parameter and sets the `racine` instance
	// variable to the given value
	public Arbre(Noeud racine) {
		this.racine = racine;
	}

	public Noeud chercherStagiaire(String nom) {
		Noeud noeudCourant = this.racine;

		while (noeudCourant != null) {
			int comparaison = noeudCourant.getCle().getNom().compareTo(nom);
			if (comparaison == 0) {
				return noeudCourant;
			} else if (comparaison > 0) {
				noeudCourant = noeudCourant.getFilsGauche();
			} else {
				noeudCourant = noeudCourant.getFilsDroit();
			}
		}
		return null; // the node with the given name was not found in the tree
	}

	public void ajouterStagiaire(Stagiaire nouveauStagiaire, Noeud racine) {
		if (this.racine == null) {
			this.racine = new Noeud(nouveauStagiaire);
		} else {
			this.racine.ajouterNoeud(nouveauStagiaire);
		}
	}
	// to return a list of all the nodes in the tree, sorted in ascending order

	public List<Noeud> getNoeuds() {
		List<Noeud> noeuds = new ArrayList<>();// create an empty list of nodes
		Noeud racine = getRacine();
		System.out.println("racine -> " + racine);
		getNoeuds(noeuds, racine);

		// (Noeud noeud : noeuds) {
		racine.affichageInfixeNoeud();

		return noeuds;
	}

// helper method of getNoeuds= visit, then adds the current node to the noeuds
	public void getNoeuds(List<Noeud> noeuds, Noeud noeud) {
		if (noeud != null) {
			getNoeuds(noeuds, noeud.getFilsGauche());
			noeuds.add(noeud);
			getNoeuds(noeuds, noeud.getFilsDroit());
		}
	}

	public void afficherArbre() {
		// je veux ajouter ce methode pour afficher le table. il faut afficher la table
		afficherArbreRecursif(racine, 0);
	}

	private void afficherArbreRecursif(Noeud noeud, int niveau) {
		if (noeud == null) {
			return;
		}

		// afficher le sous-arbre gauche (plus profond)
		afficherArbreRecursif(noeud.getFilsGauche(), niveau + 1);

		// afficher le noeud courant
		for (int i = 0; i < niveau; i++) {
			System.out.print("    ");
		}
		System.out.println(noeud.getCle().toString());

		// afficher le sous-arbre gauche (moins profond)
		afficherArbreRecursif(noeud.getFilsDroit(), niveau + 1);
	}

	public void tableauArbre() {
		List<Noeud> noeuds = getNoeuds();
		System.out.println(noeuds.size());
		String[][] tableau = new String[noeuds.size() + 1][5];
		tableau[0][0] = "Nom";
		tableau[0][1] = "Prénom";
		tableau[0][2] = "Département";
		tableau[0][3] = "Promotion";
		tableau[0][4] = "Année";

		for (int i = 0; i < noeuds.size(); i++) {
			Stagiaire s = noeuds.get(i).getCle();
			tableau[i + 1][0] = s.getNom();
			tableau[i + 1][1] = s.getPrenom();
			tableau[i + 1][2] = s.getDepartement();
			tableau[i + 1][3] = s.getPromo();
			tableau[i + 1][4] = s.getAnnee();

		}
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[i].length; j++) {
				System.out.print(tableau[i][j] + "\t");
			}
			System.out.println();
		}

	}

	public void supprimerStagiaire(Stagiaire stagiaire) {
		// en usitisan t getNoeuds et exchanger?
		racine = supprimerNoeud(racine, stagiaire);

	}

	private Noeud supprimerNoeud(Noeud noeud, Stagiaire stagiaire) {
		if (noeud == null) {
			return null;
		}

		if (stagiaire.compareTo(noeud.getCle()) < 0) {
			noeud.setFilsGauche(supprimerNoeud(noeud.getFilsGauche(), stagiaire));
		} else if (stagiaire.compareTo(noeud.getCle()) > 0) {
			noeud.setFilsDroit(supprimerNoeud(noeud.getFilsDroit(), stagiaire));
		} else {
			// Le noeud courant doit être supprimé
			if (noeud.getFilsGauche() == null) {
				return noeud.getFilsDroit();
			} else if (noeud.getFilsDroit() == null) {
				return noeud.getFilsGauche();
			}

			// Le noeud courant a deux fils
			Noeud successeur = trouverSuccesseur(noeud.getFilsDroit());
			noeud.setCle(successeur.getCle());
			noeud.setFilsDroit(supprimerNoeud(noeud.getFilsDroit(), successeur.getCle()));
		}

		return noeud;
	}

	private Noeud trouverSuccesseur(Noeud noeud) {
		Noeud successeur = noeud;
		while (successeur.getFilsGauche() != null) {
			successeur = successeur.getFilsGauche();
		}
		return successeur;

	}

	public void modifierStagiaire(int ancienneValeur, int nouvelleValeur) {
		// en usitisant getNoeuds+
	}

	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

}
