package fr.eni.projet.trocenchere.bll;

import java.util.List;

import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;

public class TrocEnchereManager {
	
	private TrocEnchereDAO trocEnchereDAO;
	
	// Connection avec la DAO initialisée

	public TrocEnchereManager(TrocEnchereDAO trocEnchereDAO) {
		
		this.trocEnchereDAO = DAOFactory.getTrocEnchereDAO;
	}
	
	// méthodes pour la Classe ArticleVendu
	
	public void ajouterArticle(ArticleVendu articleVendu) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerArticleNotNull(articleVendu,exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtArticle(articleVendu, exception);
		
		// Vérification que les prix soient positifs (ou nuls) et que le prix de vente excéde ou est égal au prix initial
		this.validerPrix(articleVendu, exception);

		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insert(articleVendu);
		}
		else
		{
			throw exception;
		}

	}
	
	public void supprimerArticle(ArticleVendu articleVendu) {
		this.trocEnchereDAO.delete(articleVendu);
	}
	
	public List<ArticleVendu> selectionnerTousLesArticles() {
		this.trocEnchereDAO.selectAll();
	}
	
	public List<ArticleVendu> selectionnerUnArticle(int noArticle) {
		this.trocEnchereDAO.selectById(noArticle);
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
		this.validerCategorieNotNull (categorie, exception);
		
		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insert(categorie);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimerCategorie(Categorie categorie) {
		this.trocEnchereDAO.delete(categorie);
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
}
