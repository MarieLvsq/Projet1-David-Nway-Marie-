package model;

public class Noeud {

	private Stagiaire nom;
	private Noeud filsGauche;
	private Noeud filsDroit;

	public Noeud(Stagiaire stagiaire, Noeud filsGauche, Noeud filsDroit) {
		this.nom = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	
	public void ajouterStagiaire(Stagiaire stagiaire) {
		 
		  int comparaison = this.stagiaire.compareTo(stagiaire);
		 
		  if (comparaison > 0) { 
		   if (this.filsGauche == null) { 
		    this.filsGauche = new Noeud(stagiaire);
		   } else { 
		    this.filsGauche.ajouterStagiaire(stagiaire);
		   }
		  } else { 
		   if(this.filsDroit == null) {
		    this.filsDroit = new Noeud(stagiaire);
		   }else {
		    this.filsDroit.ajouterStagiaire(stagiaire);
		   }
		  }
		 }

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
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