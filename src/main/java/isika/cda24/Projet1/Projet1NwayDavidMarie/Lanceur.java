package model;

import java.util.List;
import java.util.Scanner;

import model.Noeud;
import model.Arbre;

public class Launcer {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Arbre arbre = new Arbre(null);

		List<Noeud> noeuds = null;
		try {
			noeuds = Stagiaire.readNoeudsFromResource("stagiaires.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean addMore = true;
		int i = 0;
		while (addMore && i < noeuds.size()) { 
			Noeud noeud = noeuds.get(i);
		
			arbre.ajouterStagiaire(noeud.getCle(), arbre.getRacine());
			
			System.out.print("Entrez le nom du nouveau stagiaire: ");
			String nom = scanner.nextLine();
			
			System.out.print("Entrez son prenom: ");
			String prenom = scanner.nextLine();

			System.out.print("Entrez son departement: ");
			String departement = scanner.nextLine();

			System.out.print("Entrez son promo: ");
			String promo = scanner.nextLine();

			System.out.print("Entrez l' annee: ");
			String annee = scanner.nextLine();
			System.out.print("Voulez-vous ajouter un autre stagiaire ? (O/N) ");
		    String reponse = scanner.nextLine();
		    addMore = reponse.equalsIgnoreCase("O");
		    i++;
	
		}

		List<Noeud> nouveauxNoeuds = arbre.getNoeuds();
		System.out.println("Liste de nouveaux stagiaires ajoutés:");
		for (Noeud noeud : nouveauxNoeuds) {
			noeud.affichageInfixeNoeud();
		}
		scanner.close();

//		Noeud noeud = arbre.chercherStagiaire("AGACIAK"); // tested
//		if (noeud != null) {
//			System.out.println(noeud.getCle().toString());
//		} else {
//			System.out.println("Stagiaire non trouve.");
//		}

	}
}
