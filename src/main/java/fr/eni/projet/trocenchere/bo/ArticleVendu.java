package fr.eni.projet.trocenchere.bo;

import java.time.LocalDate;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int etatVente;
	private byte[] imageArticle;
	private String image;
	
	private Utilisateur utilisateur;
	int numeroCategorie;
	
	// Constructeur avec tous les attributs
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente) {
		
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

	public ArticleVendu(String nomArticle2, String description2, LocalDate dateDebutEncheres2,
			LocalDate dateFinEncheres2, int miseAPrix2, byte[] images) {
		// TODO Auto-generated constructor stub
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

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres2) {
		this.dateDebutEncheres = dateDebutEncheres2;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
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

	public int getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getNumeroCategorie() {
		return numeroCategorie;
	}

	public void setNumeroCategorie(int numeroCategorie) {
		this.numeroCategorie = numeroCategorie;
	}
	
	/**
	 * @return the imageArticle
	 */
	public byte[] getImageArticle() {
		return imageArticle;
	}

	/**
	 * @param imageArticle the imageArticle to set
	 */
	public void setImageArticle(byte[] imageArticle) {
		this.imageArticle = imageArticle;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", categorie=" + numeroCategorie
				+ "]";
	}

	
		
}
