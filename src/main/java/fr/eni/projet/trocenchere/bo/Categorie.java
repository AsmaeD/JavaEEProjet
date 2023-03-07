package fr.eni.projet.trocenchere.bo;

public class Categorie {
	
<<<<<<< HEAD
	private int no_categorie;
	private String libelle;
	
	// Constructeur avec tous les attributs
	
	public Categorie(int no_categorie, String libelle) {
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}
	
	// Constructeur sans le numéro de catégorie
=======
	private int numCategorie;
	private String libelle;
	
	public Categorie(
			int numCategorie, 
			String libelle) {
		this.numCategorie = numCategorie;
		this.libelle = libelle;
	}
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public Categorie() {
	}

<<<<<<< HEAD
	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
=======
	public int getNumCategorie() {
		return numCategorie;
	}

	public void setNumCategorie(int numCategorie) {
		this.numCategorie = numCategorie;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Categorie [no_categorie=" + no_categorie + ", libelle=" + libelle + "]";
=======
		return "Categorie [numCategorie=" + numCategorie 
				+ ", libelle=" + libelle 
				+ "]";
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	}

}
