package fr.eni.projet.trocenchere.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;

public class ArticleVenduManager {
	
	private static ArticleVenduManager instance;
	private ArticleVenduDAO articleDAO;

	public ArticleVenduManager() {
		this.articleDAO = DAOFactory.getArticleVenduDAO;
	}
	
	
	public static synchronized ArticleVenduManager getInstance() {
		if(instance==null)
		{
			instance = new ArticleVenduManager();
		}
		return instance;
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
	
	/*Suppression de l'article avant la date de fin  de l'enchère n'est pas terminée
		public  boolean cancelArticleVendu(ArticleVendu article) throws BusinessException {
			boolean articleSupprime = false;
			int idarticle = article.getNoArticle();
			
			if(LocalDate.now().compareTo(article.getDateDebutEncheres())<0) {
				articleSupprime = articleVenduDAO.removeArticleVendu(idarticle);
				
			}
			return articleSupprime;
		}*/
	
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
			Integer.valueOf(articleVendu.getDateDebutEncheres()) == null ||
			Integer.valueOf(articleVendu.getDateFinEncheres()) == null)
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
	
	//Récupération de tous les articles selon le filtre de l'accueil
	/*public List<ArticleVendu> getListeEtatVente(String motcle ,Integer ouvertes , Integer encours, Integer terminees, int numCategorie, int categoriesMax, String pseudoAchat, String pseudoVente) throws BusinessException{
		List<ArticleVendu> lstArticle = new ArrayList<ArticleVendu>();
		//int idEtatVente= transcriptEtatVenteToID(etatVente);

		lstArticle = articleVenduDAO.recupListeArticleSelonFiltreAccueil(motcle, ouvertes, encours, terminees, numCategorie, categoriesMax, pseudoAchat,pseudoVente);
		
		return lstArticle;
		
	}

	//Récupération des catégories
	public Set<Categorie> getListCategories() throws BusinessException{
		Set<Categorie> lstCat = articleVenduDAO.getListCategorie();
		
		return lstCat;
		
	}
	
	//Récupération d'un article son nom et le pseudo du vendeur
	
	public ArticleVendu recupererArticleParNomArticleEtNomVendeur(String nomArticle, String pseudoVendeur) throws BusinessException{
		ArticleVendu articleAGenerer = articleVenduDAO.recupArticleBYNomEtPseudoVendeur(nomArticle, pseudoVendeur);
				
		return articleAGenerer;
	}
	
	//Permet la modification de l'article s'il est encore temps
	
	public void updateArticle(ArticleVendu article) throws BusinessException{
		if(article.getEtatVente() == 1)
			articleVenduDAO.updateArticleVendu(article);
	}
	
	//Ajout d'un article en vente par l'utilisateur et renvoi l'id de l'article nouvellement créé
		public int ajoutArticle(ArticleVendu article, int idvendeur, String categorie) throws BusinessException {
			int idNouvelleVente = 0;
			
			
			idNouvelleVente = articleVenduDAO.addArticleVendu(article, idvendeur, categorie);
			
			return idNouvelleVente;
		}*/
}
