package fr.eni.projet.trocenchere.bo;

public class Enchere {
	
	private String dateEnchere;
	private int montant_enchere;
	
	private int utilisateur;
	private int articleVendu;
	
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
	// Constructeur avec tous les attributs
	
	public Enchere(String dateEnchere, int montant_enchere) {
=======
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
	public Enchere(
			iny dateEnchere, 
			int montant_enchere) {
<<<<<<< HEAD
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
=======
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
		
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public Enchere() {
	}

	public int getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(int dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public int getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(int articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
<<<<<<< HEAD
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", articleVendu="
				+ articleVendu + "]";
=======
=======
=======
<<<<<<< HEAD
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", articleVendu="
				+ articleVendu + "]";
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
		return "Enchere [dateEnchere=" + dateEnchere 
				+ ", montant_enchere=" + montant_enchere 
				+ ", articleVendu=" + articleVendu 
				+ "]";
<<<<<<< HEAD
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
=======
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
	}
	
}
