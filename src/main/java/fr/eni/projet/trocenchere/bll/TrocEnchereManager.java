package fr.eni.projet.trocenchere.bll;

import fr.eni.projet.trocenchere.bo.ArticleVendu;

public class TrocEnchereManager {
	
	private TrocEnchereDAO trocEnchereDAO;

	public TrocEnchereManager(TrocEnchereDAO trocEnchereDAO) {
		
		this.trocEnchereDAO = DAOFactory.getTrocEnchereDAO;
	}
	
	public ArticleVendu ajouter(ArticleVendu articleVendu) throws BusinessException {
		BusinessException exception = new BusinessException();
		int maxLenght;
		
		// Verification que les certains attributs ne soient pas null
		this.validerAttributsNotNull(articleVendu,exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistré en base de données
		this.validerStringLenghtAttributs(articleVendu, maxLenght, exception);
		
		// Vérification que les prix soient positifs (ou nuls) et que le prix de vente excéde (ou est égal) le prix initial
		this.validerPrix(articleVendu);

		if(!exception.hasErreurs())
		{
			this.trocEnchereDAO.insert(articleVendu);
		}
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		return articleVendu;
	}
	
	private void validerAttributsNotNull (ArticleVendu articleVendu, BusinessException businessException) {
		
		if (Integer.valueOf(articleVendu.getNoArticle()) == null || 
			articleVendu.getNomArticle() == null ||
			articleVendu.getDescription() == null ||
			articleVendu.getDateDebutEncheres() == null ||
			articleVendu.getDateFinEncheres() == null)
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
			}
			
	}

}
