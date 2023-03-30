package model;

public class ArbreBinaire {
	
private Noeud racine;
	
	public Noeud getRacine() {
	return racine;
}

public void setRacine(Noeud racine) {
	this.racine = racine;
}

	public ArbreBinaire() {
		this.racine =null;
	}
	
	public ArbreBinaire(Noeud racine) {
		this.racine = racine;
	}

	public void ajouterNoeud(String nomAjout) {
		if(racine == null){//si arbre vide
		racine = new Noeud(nomAjout);
		}else {
			this.racine.ajouterNoeud(nomAjout);
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
	

}
