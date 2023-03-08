package fr.eni.projet.trocenchere.bo;

public class Categorie {
	
<<<<<<< HEAD
=======
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
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
	private int numCategorie;
	private String libelle;
	
	public Categorie(
			int numCategorie, 
			String libelle) {
		this.numCategorie = numCategorie;
		this.libelle = libelle;
	}
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public Categorie() {
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
	public int getNumCategorie() {
		return numCategorie;
	}

	public void setNumCategorie(int numCategorie) {
		this.numCategorie = numCategorie;
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
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
		return "Categorie [numCategorie=" + numCategorie 
				+ ", libelle=" + libelle 
				+ "]";
=======
<<<<<<< HEAD
		return "Categorie [no_categorie=" + no_categorie + ", libelle=" + libelle + "]";
=======
		return "Categorie [numCategorie=" + numCategorie 
				+ ", libelle=" + libelle 
				+ "]";
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
	}

}
