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
	public List<Noeud> getNoeuds() {
	    List<Noeud> noeuds = new ArrayList<>();
	    Noeud racine = getRacine();
	    getNoeuds(noeuds, racine);
	  
	    for (Noeud noeud : noeuds) {
	        noeud.affichageInfixeNoeud();
	    }
	    return noeuds;
	}

	private void getNoeuds(List<Noeud> noeuds, Noeud noeud) {
	    if (noeud != null) {
	        getNoeuds(noeuds, noeud.getFilsGauche());
	        noeuds.add(noeud);
	        getNoeuds(noeuds, noeud.getFilsDroit());
	    }
	}


	public void afficherArbre() {
		// je veux ajouter ce methode pour  afficher en commande
	}
		
	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

}
