package model;

import java.util.List;

public class Launcer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stagiaire defaultSta = new Stagiaire();
			List<Stagiaire> stagiaires = defaultSta.readStagiairesFromResource("STAGIAIRES.txt");
			//to test
			Arbre arbre = new Arbre();
			 for (Stagiaire stagiaireFromList : stagiaires) {
			        arbre.ajouterStagiaire(stagiaireFromList, arbre.getRacine());
			    }
		}
	}


