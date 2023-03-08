package fr.eni.projet.trocenchere.bo;

public class Enchere {
	
	private String dateEnchere;
	private int montant_enchere;
	
	private int utilisateur;
	private int articleVendu;
	
	public Enchere(
			iny dateEnchere, 
			int montant_enchere) {
		
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
		return "Enchere [dateEnchere=" + dateEnchere 
				+ ", montant_enchere=" + montant_enchere 
				+ ", articleVendu=" + articleVendu 
				+ "]";
	}
	
}
