package fr.eni.projet.trocenchere.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;
import fr.eni.projet.trocenchere.bo.Enchere;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public class EnchereManager {
	
	private DAO<Enchere> enchereDAO;	
	private static EnchereManager instance;

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO;
	}
	
	public static synchronized EnchereManager getInstance() {
		if(instance==null)
		{
			instance = new EnchereManager();
		}
		return instance;
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
	
	//Récupérer une enchère par l'article
	
	public Enchere recuperationEnchereByArticle(ArticleVendu article) throws BusinessException{
		Enchere enchere = null;
		int idArticle = article.getNoArticle();
		int etatVente = article.getEtatVente();
			if(etatVente <3)
			{
				enchere = enchereDAO.getEnchereByIDArticle(idArticle);
			}
			
		return enchere;
	}
	
	//Vérifie si une enchère existe pour l'article
		public boolean verificationEnchereExist(ArticleVendu article) throws BusinessException{
			boolean enchereExist = false;
			int idArticle = article.getNoArticle();
			
			int resultatCount = enchereDAO.checkEnchereExist(idArticle);
				if(resultatCount>0)
					enchereExist = true;
					
					return enchereExist;
		}
		
		//Debut d'une enchère
		public boolean startEnchere(ArticleVendu article,int idEncherisseur,int montantEnchere) throws BusinessException{
			boolean execute = true;
			if(article.getEtatVente() == 2)
				{
					enchereDAO.ajouterEnchereEnCours(article,idEncherisseur, montantEnchere);
					
				}
			else
				{
					execute = false;
				}
			return execute;
			}
		
		//UPDATE enchere
		public String doNouvelleEnchere(ArticleVendu article, Utilisateur encherisseur, int nouvelleEnchere) throws BusinessException{
			String resultatEnchere = null;
			int idArticle = article.getNoArticle();
			Enchere enchereEnCours = enchereDAO.getEnchereByIDArticle(idArticle);
			int montantEnchere = enchereEnCours.getMontant_enchere();
			if(LocalDate.now().compareTo(enchereEnCours.getDateEnchere())<0)
				{
					
					if(nouvelleEnchere > montantEnchere )
						{
						int idEncherisseur = encherisseur.getNo_utilisateur();
							enchereDAO.updateEnchere(idArticle, idEncherisseur, nouvelleEnchere);;
							resultatEnchere = "Bravo, vous êtes le meilleur enchérisseur";
						}
					else
						{
							resultatEnchere = "Le montant de l'enchère doit être supérieur à l'enchère en cours";
						}
		
				}
			else
				{
					resultatEnchere = "l'enchère est terminée";
				}
			
			return resultatEnchere;
		}
}


