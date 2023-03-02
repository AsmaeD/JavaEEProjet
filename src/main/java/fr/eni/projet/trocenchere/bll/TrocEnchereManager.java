package fr.eni.projet.trocenchere.bll;


public class TrocEnchereManager {
	
	private TrocEnchereDAO trocEnchereDAO;

	public TrocEnchereManager(TrocEnchereDAO trocEnchereDAO) {
		
		this.trocEnchereDAO = DAOFactory.getTrocEnchereDAO;
	}
	
	public ArticleVendu ajouter(ArticleVendu articleVendu) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		Avis avis = new Avis(description, note);
		
		this.validerNote(avis,exception);
		this.validerDescription(avis,exception);

		if(!exception.hasErreurs())
		{
			this.avisDAO.insert(avis);
		}
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		return avis;
	}

}
