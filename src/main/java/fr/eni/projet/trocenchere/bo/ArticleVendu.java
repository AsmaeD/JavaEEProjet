package fr.eni.projet.trocenchere.bo;


public class ArticleVendu {

	private int numArticle;
	private String nomArticle;
	private String description;
	private int dateDebutEncheres;
	private int dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;

	private int utilisateur;
	private int categorie;
	
	// Constructeur avec tous les attributs
	

	
	public ArticleVendu(
			int numArticle, 
			String nomArticle, 
			String description, 
			int dateDebutEncheres,
			int dateFinEncheres, 
			int miseAPrix, 
			int prixVente, 
			String etatVente,
			int uti,
			int cat) {
		
		this.numArticle = numArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	
	// Constructeur sans le numéro d'article


	public ArticleVendu(
			String nomArticle, 
			String description, 
			int dateDebutEncheres, 
			int dateFinEncheres,
			int miseAPrix, 
			int prixVente, 
			String etatVente,
			int uti,
			int cat) {

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = uti ;
		this.categorie = cat ;
	}

	public ArticleVendu() {
	}


	
	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}


	public int getNumArticle() {
		return numArticle;
	}

	public void setNumArticle(int numArticle) {
		this.numArticle = numArticle;
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

	public int getUtilisateur() {
		return utilisateur;
	}

	public int getCategorie() {
		return categorie;
	}


	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + numArticle 
				+ ", nomArticle=" + nomArticle 
				+ ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres 
				+ ", dateFinEncheres=" + dateFinEncheres 
				+ ", miseAPrix=" + miseAPrix 
				+ ", prixVente=" + prixVente 
				+ ", etatVente=" + etatVente 
				+ ", utilisateur=" + utilisateur
				+ ", categorie=" + categorie
				+ "]";
	}
	
}
