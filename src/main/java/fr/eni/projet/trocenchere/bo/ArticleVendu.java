package fr.eni.projet.trocenchere.bo;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
<<<<<<< HEAD
	private String dateDebutEncheres;
	private String dateFinEncheres;
=======
	private int dateDebutEncheres;
	private int dateFinEncheres;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	
	private Utilisateur utilisateur;
<<<<<<< HEAD
	int numeroCategorie;
	
	// Constructeur avec tous les attributs
	
	public ArticleVendu(int noArticle, String nomArticle, String description, String dateDebutEncheres,
			String dateFinEncheres, int miseAPrix, int prixVente, String etatVente) {
=======
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
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
		
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}
<<<<<<< HEAD
	
	// Constructeur sans le numéro d'article

	public ArticleVendu(String nomArticle, String description, String dateDebutEncheres, String dateFinEncheres,
			int miseAPrix, int prixVente, String etatVente) {
=======

	public ArticleVendu(
			String nomArticle, 
			String description, 
			int dateDebutEncheres, 
			int dateFinEncheres,
			int miseAPrix, 
			int prixVente, 
			String etatVente) {
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371

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

<<<<<<< HEAD
	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public String getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(String dateFinEncheres) {
=======
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
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
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

<<<<<<< HEAD
	public int getNumeroCategorie() {
		return numeroCategorie;
	}

	public void setNumeroCategorie(int numeroCategorie) {
		this.numeroCategorie = numeroCategorie;
=======
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", categorie=" + numeroCategorie
=======
		return "ArticleVendu [noArticle=" + noArticle 
				+ ", nomArticle=" + nomArticle 
				+ ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres 
				+ ", dateFinEncheres=" + dateFinEncheres 
				+ ", miseAPrix=" + miseAPrix 
				+ ", prixVente=" + prixVente 
				+ ", etatVente=" + etatVente 
				+ ", categorie=" + categorie
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
				+ "]";
	}

	
		
}
