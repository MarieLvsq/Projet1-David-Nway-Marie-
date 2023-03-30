package model;

public class Arbre {

	private Noeud racine;
	// Default constructor that initializes the `racine` instance variable to null
	public Arbre() {
		this.racine = null;
	}
	// Constructor that takes a `Noeud` parameter and sets the `racine` instance variable to the given value
	public Arbre(Noeud racine) {
		this.racine = racine;
	}

	
	
	public Noeud chercherStagiaire(String nom, Noeud noeud) {
		if (noeud == null) {
			return null;
		}
		if (noeud.getStagiaire().getNom().equals(nom)) {
			return noeud;
		}
		if (nom.compareTo(noeud.getStagiaire().getNom()) < 0) {
			return chercherStagiaire(nom, noeud.getFilsGauche());
		} else {
			return chercherStagiaire(nom, noeud.getFilsDroit());
		}
	
	}
	
	// method public adjouter
	public void ajouterStagiaire(Stagiaire stagiaire, Noeud noeud) {
		//if (stagiaire.getNom().compareTo(noeud.getStagiaire().getNom()) < 0) {
			if (noeud != null && stagiaire.getNom().compareTo(noeud.getStagiaire().getNom()) < 0) {
			if (noeud.getFilsGauche() == null) {
				noeud.setFilsGauche(new Noeud(stagiaire, noeud, noeud));
			} else {
				ajouterStagiaire(stagiaire, noeud.getFilsGauche());
			}
		} else {
			if (noeud.getFilsDroit() == null) {
				noeud.setFilsDroit(new Noeud(stagiaire, noeud, noeud));
			} else {
				ajouterStagiaire(stagiaire, noeud.getFilsDroit());
			}
		}
	}
	public Noeud getRacine() {
		return racine;
	}
	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

}
