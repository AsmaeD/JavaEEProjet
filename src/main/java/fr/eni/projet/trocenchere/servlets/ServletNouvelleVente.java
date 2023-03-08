package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.trocenchere.bll.ArticleVenduManager;
import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bll.RetraitManager;
import fr.eni.projet.trocenchere.bll.UtilisateurManager;
import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;
import fr.eni.projet.trocenchere.bo.Retrait;
import fr.eni.projet.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getSession().getAttribute("Utilisateur") == null)
				{
				response.sendRedirect(request.getContextPath()+"/Accueil");
				}
			else
			{
					//Récupération des données utilisateur
					try 
						{
						String pseudoUtilisateur = (String) request.getSession().getAttribute("Utilisateur");
							UtilisateurManager userManager = UtilisateurManager.getInstance();
							Utilisateur user = userManager.recuperationUtilisateur(pseudoUtilisateur);
							request.setAttribute("user", user);
						} 
					catch (BusinessException e1) 
						{
							e1.ajouterErreur(40001);
						}
					
					//Récupération des catégories existantes pour les afficher dans un eliste déroulante
					try 
						{
							ArticleVenduManager manager = ArticleVenduManager.getInstance();
							Set<Categorie> listeDeCategories = manager.getListCategories();
							request.setAttribute("listeDeCategories", listeDeCategories);
						} 
					catch (BusinessException e) 
						{
							e.ajouterErreur(40000);
						}
					
				
						RequestDispatcher rd = request.getRequestDispatcher("NouvelleVente.jsp");
						rd.forward(request, response);
			}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean validerAjout = false;
		request.setCharacterEncoding("UTF-8");
		
		//Récupération de l'utilisateur pour utilisation en DAL

		Utilisateur user = null;
		try {
			UtilisateurManager userManager = UtilisateurManager.getInstance();
			String pseudoUtilisateur = (String) request.getSession().getAttribute("Utilisateur");
			 user = userManager.recuperationUtilisateur(pseudoUtilisateur);
		} catch (BusinessException e1) {
			e1.ajouterErreur(40001);
		}
		
		int idUtilisateur = user.getNoUtilisateur();
		
		// Récupération des dates et validation avant insertion de l'article
		
		LocalDate dateDebutEncheres = ((Date.valueOf(request.getParameter("dateDebutEnchere"))).toLocalDate());
		LocalDate dateFinEncheres = ((Date.valueOf(request.getParameter("dateFinEnchere"))).toLocalDate());
		Boolean validateDateDebut = validationDate(dateDebutEncheres, dateFinEncheres);
		
		//Récupération des entrées utilisateurs de l'article
		
		String nomArticle = request.getParameter("nomArticle").trim();
		String description = request.getParameter("description").trim();
		String categorie = request.getParameter("categorie");
		System.out.println(categorie);
		int miseAPrix = Integer.parseInt(request.getParameter("map"));
		String rue = request.getParameter("nomRue").trim();
		String cp = request.getParameter("codePostal").trim();
		String ville = request.getParameter("ville").trim();
		byte[] images = request.getParameter("image").getBytes();
		
		
	//Construction de l'article pour sa manipulation en dal ou pré remplissage en cas d'erreur
		
		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, images );
		
	//Construction du retrait pour la manipulation en dal pré remplissage en cas d'erreur
		
		Retrait retrait = new Retrait(rue,cp,ville);
		
		//Dates invalides
		
		if(validateDateDebut == false)
			{
			
			//Récupération des catégories existantes pour les afficher dans un eliste déroulante
			try 
				{
					ArticleVenduManager manager = ArticleVenduManager.getInstance();
					Set<Categorie> listeDeCategories = manager.getListCategories();
					request.setAttribute("listeDeCategories", listeDeCategories);
				} 
			catch (BusinessException e) 
				{
					e.ajouterErreur(40000);
				}
			request.setAttribute("newArticle", newArticle);
			request.setAttribute("retrait", retrait);
			request.setAttribute("validateDateDebut", validateDateDebut);
			RequestDispatcher rd = request.getRequestDispatcher("NouvelleVente.jsp");
			rd.forward(request, response);
			}

		//Dates valides, traitement et ajour de l'article
		
		else
			{
			
			int idNouvelleVente = 0;
			
			//Ajout de l'article à la base de donnée et récupére tous les articles
			//mis en vente par cet utilisateur
				try 
					{
						ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
						
						idNouvelleVente = (int) articleManager.ajoutArticle(newArticle, idUtilisateur, categorie);
					}
				catch (BusinessException e) 
					{
						e.ajouterErreur(40002);
					}
				
				//Ajout du point de retrait de cette article nouvellement créé
				
				
				
				try 
					{
						RetraitManager retraitManager = RetraitManager.getInstance();
						retraitManager.ajouterRetrait(retrait, idNouvelleVente);
					} 
				catch (BusinessException e) 
					{
						e.ajouterErreur(40003);
					}
			
				//Affichage message ajout OK
			validerAjout = true;
			request.setAttribute("validerAjout", validerAjout);
			
			RequestDispatcher rd = request.getRequestDispatcher("Accueil.jsp");
			rd.forward(request, response);
			}
	}

	
	private boolean validationDate(LocalDate datedebut, LocalDate dateFin) {
		boolean validationDate = false;
			if(datedebut.compareTo(LocalDate.now())<=0||dateFin.compareTo(datedebut)<=0)
				validationDate = false;
			else
				validationDate = true;
		return validationDate;
		
	}
	
}
