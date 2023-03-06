package fr.eni.projet.trocenchere.bll;

import java.util.List;

import fr.eni.projet.trocenchere.bo.Categorie;
import fr.eni.projet.trocenchere.bo.Enchere;

public class EnchereManager {
	
	private DAO<Enchere> enchereDAO;	

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO;
	}

	public void ajouter(Enchere enchere) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerEnchereNotNull (enchere, exception);
		
		// TODO Verification format date si option date dans la base de données
		
		if(!exception.hasErreurs())
		{
			this.enchereDAO.insert(enchere);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimer(Enchere enchere) {
		this.enchereDAO.delete(enchere);
	}
	
	public Enchere selectionnerEnchere(Enchere enchere) {
		return this.enchereDAO.selectByRef(enchere);
	}
	
	public List<Enchere> selectionnerToutesEncheres() {
		return this.enchereDAO.selectAll();
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
}
