package fr.eni.projet.trocenchere.bo;

public class Enchere {
	
	private int dateEnchere;
	private int montant_enchere;
	
	private int noUtilisateur;
	private int noArticleVendu;
	
	
	
	public Enchere(int dateEnchere, int montant_enchere, int noUtilisateur, int noArticleVendu) {
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.noUtilisateur = noUtilisateur;
		this.noArticleVendu = noArticleVendu;
	}

	public Enchere(int dateEnchere, int montant_enchere) {
		
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
		return noUtilisateur;
	}

	public void setUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getArticleVendu() {
		return noArticleVendu;
	}

	public void setArticleVendu(int noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", articleVendu="
				+ noArticleVendu + "]";
	}
	
}
