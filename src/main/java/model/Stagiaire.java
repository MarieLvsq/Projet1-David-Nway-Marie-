package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Stagiaire {

	
	private String nom;
	private String departement;
	private String promo;
	private String prenom;
	private String annee;

	public Stagiaire(String nom, String prenom, String departement, String promo, String annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
	}

	

	public Stagiaire() {
		// TODO Auto-generated constructor stub
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public List<Stagiaire> readStagiairesFromResource(String resourceName) {
		List<Stagiaire> stagiaires = new ArrayList<>();

		try (InputStream inputStream = Stagiaire.class.getClassLoader().getResourceAsStream(resourceName)) {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = reader.readLine();
			int lineNumber = 0;

			String nom = "";
			String prenom = "";
			String departement = "";
			String promo = "";
			String annee = "";

			while (line != null) {
				if (line.equals("*")) {
					line = reader.readLine();
					continue;
				}
				lineNumber++;
				switch (lineNumber) {
				case 1:
					nom = line;
					break;
				case 2:
					prenom = line;
					break;
				case 3:
					departement = line;
					break;
				case 4:
					promo = line;
					break;
				case 5:
					annee = line;
					stagiaires.add(new Stagiaire(nom, prenom, departement, promo, annee));
					lineNumber = 0; // reset the line number for the next set of values
					break;
				default:
					// handle invalid input
					break;
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			// handle the exception
		}

		
		for (Stagiaire stagiaire : stagiaires) {
            System.out.println(stagiaire);
        }
		
		return stagiaires;

	}

	@Override
	public String toString() {
	    return String.format("%s, %s, %s, %s, %s", nom, prenom, departement, promo, annee);
	}
	}


