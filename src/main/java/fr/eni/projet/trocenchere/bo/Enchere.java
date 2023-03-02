package fr.eni.projet.trocenchere.bo;

public class Enchere {
	
	private String dateEnchere;
	private int montant_enchere;
	
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	
	// Constructeur avec tous les attributs
	
	public Enchere(String dateEnchere, int montant_enchere) {
		
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public Enchere() {
	}

	public String getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(String dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", articleVendu="
				+ articleVendu + "]";
	}
	
}
