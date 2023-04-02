package isika.cda24.Projet1.Projet1NwayDavidMarie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Noeud;
import model.Stagiaire;
import model.StagiaireBinaire;
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
		
		File rep = new File("src/main/java/Fichiers");
		// cr�e physiquement un repertoire � partir de l'objet sur l'ordinateur
		boolean caAmarche = rep.mkdir();
		System.out.println("ca a marche ? " + caAmarche);
		// cr�e un objet File qui est destin� � un �tre fichier
		File monFichier = new File("src/main/java/fichiers/Stagiaires.bin");
		try {
			// cr�e le fichier physiquement sur l'ordinateur
			monFichier.createNewFile();

			System.out.println("rep est un repertoire ? " + rep.isDirectory());
			System.out.println("monFichier est un fichier ? " + monFichier.isFile());

		} catch (IOException e) {
			System.out.println("�a s'est mal pass�");
			e.printStackTrace();
		}

		// test binaire
		StagiaireBinaire staBi = new StagiaireBinaire();
		ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();

		String nomFichier = "STAGIAIRE.DON";
		String fichierStagiaires = "Stagiaires.bin";

		try {
			staBi.ecrireFichier(fichierStagiaires, stagiaires);
			ArrayList<Stagiaire> readStagiaires = staBi.lireFichier(fichierStagiaires);
			for (Stagiaire s : readStagiaires) {
				System.out.println(s);
			}
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}


		for (Noeud node : noeuds) {
			arbre.ajouterStagiaire(node.getCle(), arbre.getRacine());
		}
			
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
		
		// Affichage de la racine :
		arbre.getRacine().affichageInfixeNoeud();
		

//		arbre.afficherArbre(); // affichage l'arbre
		
		//Suppression d'un stagiaire :
		arbre.supprimerStagiaire(new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008"));
		
		// Modification des informations d'un stagiaire
		arbre.modifierStagiaire(new Stagiaire("CHAVENEAU", "Kim Anh", "92", "ATOD 22", "2014"),"CHAVENEAU", "Kim Anh", "92", "AL 22", "2014");
		
		// affichage de l'arbre sous forme de tableau :
		arbre.tableauArbre(); 
		scanner.close();

//		Noeud noeud = arbre.chercherStagiaire("AGACIAK"); // tested
//		if (noeud != null) {
//			System.out.println(noeud.getCle().toString());
//		} else {
//			System.out.println("Stagiaire non trouve.");
//		}

	}
}
