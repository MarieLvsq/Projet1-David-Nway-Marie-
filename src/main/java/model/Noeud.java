package model;

public class Noeud {

	private Stagiaire cle;
	private Noeud filsGauche;
	private Noeud filsDroit;

	// this constructor takes a Stagiaire object as its parameter
	// and initializes the cle field of the new Noeud object

	public Noeud(Stagiaire cle) {
		this.cle = cle;
		this.filsGauche = null;
		this.filsDroit = null;
	}
	// the second constructor allows you to create a Noeud object
	// with both a Stagiaire key and references to its left and right child nodes

	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		this.cle = cle;
		this.filsDroit = filsDroit;
		this.filsGauche = filsGauche;

	}

// ajouter Noeud 
	public void ajouterNoeud(Stagiaire cleAjout) {
		int comparaison = this.cle.getNom().compareTo(cleAjout.getNom());

		if (comparaison > 0) {
			if (this.filsGauche == null) { // cas de terminaison
				this.filsGauche = new Noeud(cleAjout);

			} else {
				this.filsGauche.ajouterNoeud(cleAjout);
			}
		} else {
			if (this.filsDroit == null) {
				this.filsDroit = new Noeud(cleAjout);
			} else {
				this.filsDroit.ajouterNoeud(cleAjout);
			}
		}
	}

	// to traverse the BST in an inorder traversal and print the node values to the
	// console
	public void affichageInfixeNoeud() {
		if (this.filsGauche != null) {
			this.filsGauche.affichageInfixeNoeud();
		}
		System.out.println(this.cle.getNom());
		if (this.filsDroit != null) {
			this.filsDroit.affichageInfixeNoeud();
		}
	}
	
	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	@Override
	public String toString() {
		return cle.toString();
	}
}