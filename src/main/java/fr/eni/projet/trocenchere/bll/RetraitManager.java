package fr.eni.projet.trocenchere.bll;

import fr.eni.projet.trocenchere.bo.Enchere;
import fr.eni.projet.trocenchere.bo.Retrait;

public class RetraitManager {
	
	private DAO<Retrait> retraitDAO;

	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO;
	}

	public void ajouter(Retrait retrait) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerRetraitNotNull (retrait, exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtRetrait(retrait, exception);
		
		if(!exception.hasErreurs())
		{
			this.retraitDAO.insert(retrait);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimer(Retrait retrait) {
		
		this.retraitDAO.delete(retrait);
	}
	
	public Retrait selectionnerRetrait(Retrait retrait) {
		return this.retraitDAO.selectByRef(retrait);
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
}
