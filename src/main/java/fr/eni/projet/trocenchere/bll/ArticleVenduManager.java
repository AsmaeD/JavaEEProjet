package fr.eni.projet.trocenchere.bll;

import java.util.List;

import fr.eni.projet.trocenchere.bo.ArticleVendu;

public class ArticleVenduManager {

	private DAO<ArticleVendu> articleDAO;

	public ArticleVenduManager() {
		this.articleDAO = DAOFactory.getArticleVenduDAO;
	}
	
	public void ajouter(ArticleVendu articleVendu) throws BusinessException {
		
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
			this.articleDAO.insert(articleVendu);
		}
		else
		{
			throw exception;
		}

	}
	
	public void supprimer(ArticleVendu articleVendu) {
		this.articleDAO.delete(articleVendu);
	}
	
	public List<ArticleVendu> selectionnerTousLesArticles() {
		return this.articleDAO.selectAll();
	}
	
	public List<ArticleVendu> selectionnerArticlesNomPartiel(ArticleVendu article) {
		return this.articleDAO.selectByNom(article);
	}
	
	public List<ArticleVendu> selectionnerArticlesParCategorie(ArticleVendu article) {
		return this.articleDAO.selectByCat(article);
	}
	
	public ArticleVendu selectionnerUnArticle(ArticleVendu article) {
		return this.articleDAO.selectByRef(article);
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

}
