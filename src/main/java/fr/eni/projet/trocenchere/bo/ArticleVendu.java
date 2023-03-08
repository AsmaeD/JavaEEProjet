package fr.eni.projet.trocenchere.bo;

<<<<<<< HEAD
import java.time.LocalDate;

=======
>>>>>>> 8a6e696111f64b69e47d7852d96bbbf313302aa0
public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private int dateDebutEncheres;
	private int dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	
	private int utilisateur;
	private int categorie;
	
	public ArticleVendu(
			int noArticle, 
			String nomArticle, 
			String description, 
			int dateDebutEncheres,
			int dateFinEncheres, 
			int miseAPrix, 
			int prixVente, 
			String etatVente,
			int uti,
			int cat) {
		
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}
	
	// Constructeur sans le numéro d'article

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, int etatVente) {
		this.utilisateur = uti ;
		this.categorie = cat ;
	}

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

	public ArticleVendu(String nomArticle2, String description2, LocalDate dateDebutEncheres2,
			LocalDate dateFinEncheres2, int miseAPrix2, byte[] images) {
		// TODO Auto-generated constructor stub
	}

	
	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
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

	public int getUtilisateur() {
		return utilisateur;
	}

	public int getCategorie() {
		return categorie;
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
