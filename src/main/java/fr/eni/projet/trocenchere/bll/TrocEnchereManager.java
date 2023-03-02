package fr.eni.projet.trocenchere.bll;

import java.util.List;

import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;
import fr.eni.projet.trocenchere.bo.Enchere;
import fr.eni.projet.trocenchere.bo.Retrait;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public class TrocEnchereManager {
	
	private TrocEnchereDAO trocEnchereDAO;
	
	// Connection avec la DAO initialisée

	public TrocEnchereManager(TrocEnchereDAO trocEnchereDAO) {
		
		this.trocEnchereDAO = DAOFactory.getTrocEnchereDAO;
	}
	
	// méthodes pour la Classe ArticleVendu
	
	public void ajouterArticle(ArticleVendu articleVendu) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// A réflechir : si le paramétre de la méthode est une instance de la classe alors instance à créer dans Servlet sinon tous les attributs nécessaires en paramètre et création de l'instance dans la BLL
		
		// Verification que les certains attributs ne soient pas null
		this.validerArticleNotNull(articleVendu,exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtArticle(articleVendu, exception);
		
		// Vérification que les prix soient positifs (ou nuls) et que le prix de vente excéde ou est égal au prix initial
		this.validerPrix(articleVendu, exception);
		
		// TODO Vérification des dates en fonction de l'option choisie

		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insertArticle(articleVendu);
		}
		else
		{
			throw exception;
		}

	}
	
	public void supprimerArticle(ArticleVendu articleVendu) {
		this.trocEnchereDAO.deleteArticle(articleVendu);
	}
	
	public List<ArticleVendu> selectionnerTousLesArticles() {
		return this.trocEnchereDAO.selectAllArticles();
	}
	
	public List<ArticleVendu> selectionnerUnArticle(int noArticle) {
		return this.trocEnchereDAO.selectArticleById(noArticle);
	}
	
	private void validerArticleNotNull (ArticleVendu articleVendu, BusinessException businessException) {
		
		if (Integer.valueOf(articleVendu.getNoArticle()) == null || 
			articleVendu.getNomArticle() == null ||
			articleVendu.getDescription() == null ||
			articleVendu.getDateDebutEncheres() == null ||
			articleVendu.getDateFinEncheres() == null)
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
			}
			
	}
	
	private void validerStringLenghtArticle(ArticleVendu articleVendu, BusinessException businessException) {
		
		if (articleVendu.getNomArticle().length() > 30 ||
			articleVendu.getDescription().length() > 300)
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_LONGUEUR_MAX);
			}
	}
	
	private void validerPrix(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getMiseAPrix() < 0 ||
			articleVendu.getPrixVente() < 0 ||
			articleVendu.getPrixVente() < articleVendu.getMiseAPrix()) 
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_PRIX);
			}
	}

	// Méthodes pour la Classe Categorie
	
	public void ajouterCategorie(Categorie categorie) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtCategorie(categorie, exception);
		
		// Verification que les certains attributs ne soient pas null
		this.validerCategorieNotNull (categorie, exception);
		
		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insertCategorie(categorie);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimerCategorie(Categorie categorie) {
		this.trocEnchereDAO.deleteCategorie(categorie);
	}

	private void validerStringLenghtCategorie(Categorie categorie, BusinessException businessException) {
			
		if (categorie.getLibelle().length() > 30)
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_LONGUEUR_MAX);
			}
	}
	
	private void validerCategorieNotNull (Categorie categorie, BusinessException businessException) {
		
		if (Integer.valueOf(categorie.getNo_categorie()) == null || 
			categorie.getLibelle() == null)			
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
			}
	}
	
	// Méthodes pour la Classe Enchere
	
	public void ajouterEnchere(Enchere enchere) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerEnchereNotNull (enchere, exception);
		
		// TODO Verification format date si option date dans la base de données
		
		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insertEnchere(enchere);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimerEnchere(Enchere enchere) {
		this.trocEnchereDAO.deleteEnchere(enchere);
	}
	
	public Enchere selectionnerEnchereSurArticle(int noArticle) {
		return this.trocEnchereDAO.selectEnchereById(noArticle);
	}
	
	public List<Enchere> selectionnerAllEncheres() {
		return this.trocEnchereDAO.selectAllEncheres();
	}
	
	public void validerEnchereNotNull (Enchere enchere, BusinessException businessException) {
		
		if (Integer.valueOf(enchere.getUtilisateur().getNo_utilisateur()) == null || 
			Integer.valueOf(enchere.getArticleVendu().getNoArticle()) == null ||
			Integer.valueOf(enchere.getMontant_enchere()) == null ||
			enchere.getDateEnchere() == null)			
				{
					businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
				}
	}
	
	// Méthodes pour la classe Retrait
	
	public void ajouterRetrait(Retrait retrait) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerRetraitNotNull (retrait, exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtRetrait(retrait, exception);
		
		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insertRetrait(retrait);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimerRetrait(Retrait retrait) {
		
		this.trocEnchereDAO.deleteRetrait(retrait);
	}
	
	public Retrait selectionnerRetrait(int noArticle) {
		return this.trocEnchereDAO.selectRetraitById(noArticle);
	}
	
	public void validerRetraitNotNull (Retrait retrait, BusinessException businessException) {
		
		if (Integer.valueOf(retrait.getArticleVendu().getNoArticle()) == null || 
			retrait.getRue() == null ||
			retrait.getCode_postal() == null ||
			retrait.getVille() == null)			
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
			}
	}
	
	public void validerStringLenghtRetrait(Retrait retrait, BusinessException businessException) {
		
		if (retrait.getRue().length() > 30 ||
			retrait.getCode_postal().length() > 15 ||
			retrait.getVille().length() > 30)
		{
			businessException.ajouterErreur(CodesErreurBLL.REGLE_LONGUEUR_MAX);
		}
	}
	
	// Méthodes pour la classe Utilisateur
	
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerUtilisateurNotNull (utilisateur, exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtUtilisateur(utilisateur, exception);
		
		// Vérification que le crédit est positif
		this.validerCredit(utilisateur, exception);
		
		// Vérification du format de l'administrateur (0 ou 1)
		
		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insertUtilisateur(utilisateur);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		
		this.trocEnchereDAO.deleteUtilisateur(utilisateur);
	}
	
	public Utilisateur selectionnerUtilisateur(int noUtilisateur) {
		
		return this.trocEnchereDAO.selectUtilisateurById(noUtilisateur);
	}
	
	public void validerUtilisateurNotNull (Utilisateur utilisateur, BusinessException businessException) {
		
		if (Integer.valueOf(utilisateur.getNo_utilisateur()) == null || 
			utilisateur.getPseudo() == null ||
			utilisateur.getNom() == null ||
			utilisateur.getPrenom() == null ||
			utilisateur.getEmail() == null ||
			utilisateur.getRue() == null ||
			utilisateur.getCodePostal() == null ||
			utilisateur.getVille() == null ||
			utilisateur.getMotDePasse() == null ||
			Integer.valueOf(utilisateur.getCredit()) == null ||
			Byte.valueOf(utilisateur.getAdministrateur()) == null)			
				{
					businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
				}
	}
	
	private void validerCredit(Utilisateur utilisateur, BusinessException businessException) {
		
		if (utilisateur.getCredit() < 0) 
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_CREDIT);
			}
	}
	
	private void validerAdministrateur(Utilisateur utilisateur, BusinessException businessException) {
		
		if (utilisateur.getAdministrateur() != 0 && utilisateur.getAdministrateur() != 1) 
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_ADMINISTRATEUR);
			}
	}
}
