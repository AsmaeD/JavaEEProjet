package fr.eni.projet.trocenchere.bll;

import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;

	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO;
	}

	public void ajouter(Categorie categorie) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtCategorie(categorie, exception);
		
		// Verification que les certains attributs ne soient pas null
		this.validerCategorieNotNull (categorie, exception);
		
		if(!exception.hasErreurs())
		{
			this.categorieDAO.insert(categorie);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimer(Categorie categorie) {
		this.categorieDAO.delete(categorie);
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
