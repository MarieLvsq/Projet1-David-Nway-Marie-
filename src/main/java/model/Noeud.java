package model;

public class Noeud {
	
	
	private String cle;
	private Noeud filsGauche;
	private Noeud filsDroit;
	
	
	// constructeurs
	public Noeud (String cle) {
		this.cle = cle;
		this.filsGauche = null;
		this.filsDroit = null;
	}
	
	public Noeud(String cle, Noeud filsGauche, Noeud filsDroit) {
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	
	public void ajouterNoeud(String cleAjout) {
		//arbre binaire de recherche ABR
		// à gauche ce qui est plus petit que la racine
		// à droite, ce qui est plus grand que la racine
		int comparaison = this.cle.compareTo(cleAjout);
		// comparaison < 0 if this < cleAjout
		// comparaison > 0 if this >cleAjout
		// comparaison = 0 if this = cleAjout
		if (comparaison > 0) { // ajout à gauche car this > cleAjout
			if (this.filsGauche == null) { // cas de terminaison
				this.filsGauche = new Noeud(cleAjout);
			} else { // cas de base
				this.filsGauche.ajouterNoeud(cleAjout);
			}
		} else { // cleAjout soit plus grande soit égale -> on va à droite
			if(this.filsDroit == null) {
				this.filsDroit = new Noeud(cleAjout);
			}else {
				this.filsDroit.ajouterNoeud(cleAjout);
			}
		}
	}
	
	public void affichageInfixeNoeud() {
		if(this.filsGauche != null) {
			this.filsGauche.affichageInfixeNoeud();
		}
		System.out.println(this.cle);
		if(this.filsDroit !=null) {
			this.filsDroit.affichageInfixeNoeud();
		}
	}
	
	//toString
	@Override
	public String toString() {
		// parcours infixe = Gauhe Noeud Droit
		if (this.filsGauche == null && this.filsDroit == null) { // récursivité => cas de terminaison
			return " -> X";
		} else {  // récursivité => cas de base
			return filsGauche.toString() + " / " + this.cle + " / " + filsDroit.toString();
		}
		
	}
	
	//getters et setters
	public String getCle() {
		return cle;
	}
	public void setCle(String cle) {
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
	

}
