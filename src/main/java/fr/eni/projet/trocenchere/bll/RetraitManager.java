package fr.eni.projet.trocenchere.bll;

import fr.eni.projet.trocenchere.bo.Enchere;
import fr.eni.projet.trocenchere.bo.Retrait;

public class RetraitManager {
	
	private static RetraitManager instance;
	private DAO<Retrait> retraitDAO;

	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO;
	}
	
	public static synchronized RetraitManager getInstance() {
		if(instance==null)
		{
			instance = new RetraitManager();
		}
		return instance;
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
	
	public void supprimer(int i) {
		
		this.retraitDAO.delete(i);
	}
	
	//Supprimer le retrait par l'id d'un article
	public void supprimerRetrait(int noArticle) throws BusinessException{
		
		retraitDAO.deleteRetrait(noArticle);
		
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
	
	//Récupérer un point de retrait pour un article
	
	public Retrait recupererRetraitByID(int idArticle) throws BusinessException{
		Retrait retrait = null;
		retrait = retraitDAO.getRetraitByID(idArticle);
		return retrait;
	}
	
	public void updateRetrait(Retrait retrait, int idArticleAModifier) throws BusinessException {
		retraitDAO.updateRetrait(retrait, idArticleAModifier);
		
	}
	
	//Ajout d'un lieu de retrait à l'ajout de l'article
	
	public void ajouterRetrait(Retrait retrait, int idArticle) throws BusinessException{
		String ville = retrait.getVille();
		String rue = retrait.getRue();
		String cp = retrait.getCodePostal();
		
		if(ville !=null && rue != null && cp != null)
			{
				retraitDAO.addRetrait(retrait, idArticle);
			}
	}
}
