package fr.eni.projet.trocenchere.bo;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private int dateDebutEncheres;
	private int dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	
	private Utilisateur utilisateur;
	private Categorie categorie;
	
	public ArticleVendu(
			int noArticle, 
			String nomArticle, 
			String description, 
			int dateDebutEncheres,
			int dateFinEncheres, 
			int miseAPrix, 
			int prixVente, 
			String etatVente) {
		
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	public ArticleVendu(
			String nomArticle, 
			String description, 
			int dateDebutEncheres, 
			int dateFinEncheres,
			int miseAPrix, 
			int prixVente, 
			String etatVente) {

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	public ArticleVendu() {
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(int dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public int getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(int dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle 
				+ ", nomArticle=" + nomArticle 
				+ ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres 
				+ ", dateFinEncheres=" + dateFinEncheres 
				+ ", miseAPrix=" + miseAPrix 
				+ ", prixVente=" + prixVente 
				+ ", etatVente=" + etatVente 
				+ ", categorie=" + categorie
				+ "]";
	}

	
		
}
