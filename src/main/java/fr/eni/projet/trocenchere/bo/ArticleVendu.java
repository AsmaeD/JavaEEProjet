package fr.eni.projet.trocenchere.bo;

<<<<<<< HEAD
import java.time.LocalDate;

=======
>>>>>>> 8a6e696111f64b69e47d7852d96bbbf313302aa0
public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
<<<<<<< HEAD
<<<<<<< HEAD
=======
	private int dateDebutEncheres;
	private int dateFinEncheres;
=======
<<<<<<< HEAD
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
	private String dateDebutEncheres;
	private String dateFinEncheres;
=======
	private int dateDebutEncheres;
	private int dateFinEncheres;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	
<<<<<<< HEAD
	private int utilisateur;
	private int categorie;
=======
	private Utilisateur utilisateur;
<<<<<<< HEAD
	int numeroCategorie;
	
	// Constructeur avec tous les attributs
	
	public ArticleVendu(int noArticle, String nomArticle, String description, String dateDebutEncheres,
			String dateFinEncheres, int miseAPrix, int prixVente, String etatVente) {
=======
	private Categorie categorie;
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
	
	public ArticleVendu(
			int noArticle, 
			String nomArticle, 
			String description, 
			int dateDebutEncheres,
			int dateFinEncheres, 
			int miseAPrix, 
			int prixVente, 
<<<<<<< HEAD
			String etatVente,
			int uti,
			int cat) {
=======
			String etatVente) {
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
		
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
<<<<<<< HEAD
=======
	
	// Constructeur sans le numéro d'article

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, int etatVente) {
		this.utilisateur = uti ;
		this.categorie = cat ;
	}
=======
<<<<<<< HEAD
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
	
	// Constructeur sans le numéro d'article

	public ArticleVendu(String nomArticle, String description, String dateDebutEncheres, String dateFinEncheres,
			int miseAPrix, int prixVente, String etatVente) {
=======
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06

	public ArticleVendu(
			String nomArticle, 
			String description, 
			int dateDebutEncheres, 
			int dateFinEncheres,
			int miseAPrix, 
			int prixVente, 
<<<<<<< HEAD
			String etatVente,
			int uti,
			int cat) {
=======
			String etatVente) {
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06

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

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
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
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
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
<<<<<<< HEAD
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
=======
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
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

<<<<<<< HEAD
	public int getCategorie() {
		return categorie;
	}


	@Override
	public String toString() {
=======
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
<<<<<<< HEAD
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
		return "ArticleVendu [noArticle=" + noArticle 
				+ ", nomArticle=" + nomArticle 
				+ ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres 
				+ ", dateFinEncheres=" + dateFinEncheres 
				+ ", miseAPrix=" + miseAPrix 
				+ ", prixVente=" + prixVente 
				+ ", etatVente=" + etatVente 
				+ ", categorie=" + categorie
<<<<<<< HEAD
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
=======
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
>>>>>>> bddbbfa0a94d3f9e1f6107c095b003f326413f06
				+ "]";
	}

	
		
}
