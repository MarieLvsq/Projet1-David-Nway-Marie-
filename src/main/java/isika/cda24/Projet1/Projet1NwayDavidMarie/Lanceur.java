package isika.cda24.Projet1.Projet1NwayDavidMarie;

import java.util.List;
import java.util.Scanner;

import model.Noeud;
import model.Stagiaire;
import model.Arbre;

public class Lanceur {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Arbre arbre = new Arbre(null);

		List<Noeud> noeuds = null;
		try {
			noeuds = Stagiaire.readNoeudsFromResource("STAGIAIRES.DON");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Noeud node : noeuds) {
			arbre.ajouterStagiaire(node.getCle(), arbre.getRacine());
		}
//		boolean addMore = true;
//		int i = 0;
//		while (addMore && i < noeuds.size()) { 
//			Noeud noeud = noeuds.get(i);
//		
//			arbre.ajouterStagiaire(noeud.getCle(), arbre.getRacine());
//			
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
		arbre.ajouterStagiaire(new Stagiaire(nom, prenom, departement, promo, annee), arbre.getRacine());
//			System.out.print("Voulez-vous ajouter un autre stagiaire ? (O/N) ");
//		    String reponse = scanner.nextLine();
//		    addMore = reponse.equalsIgnoreCase("O");
//		    i++;
//	
//		}

		arbre.getRacine().affichageInfixeNoeud();// afficher racine //
		// List<Noeud> nouveauxNoeuds = arbre.getNoeuds();
		// System.out.println("Liste de nouveaux stagiaires ajoutés:");
		// for (Noeud noeud : nouveauxNoeuds) {
		// noeud.affichageInfixeNoeud();
		// }

//		arbre.afficherArbre(); // affichage l'arbre
		arbre.supprimerStagiaire(new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008"));
		arbre.tableauArbre(); // affichage de l'arbre sous forme de tableau
		scanner.close();

//		Noeud noeud = arbre.chercherStagiaire("AGACIAK"); // tested
//		if (noeud != null) {
//			System.out.println(noeud.getCle().toString());
//		} else {
//			System.out.println("Stagiaire non trouve.");
//		}

	}
}
