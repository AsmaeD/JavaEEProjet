package fr.eni.projet.trocenchere.bo;

public class Categorie {

	private int numCategorie;
	private String libelle;
	
	// Constructeur avec tous les attributs
	
		public Categorie(
			int numCategorie, 
			String libelle) {
		this.numCategorie = numCategorie;
		this.libelle = libelle;
	}

	// Constructeur sans le numéro de catégorie

	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	//constructeur vide
	public Categorie() {
	}


	public int getNumCategorie() {
		return numCategorie;
	}

	public void setNumCategorie(int numCategorie) {
		this.numCategorie = numCategorie;

	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {

		return "Categorie [numCategorie=" + numCategorie 
				+ ", libelle=" + libelle 
				+ "]";
	}

}