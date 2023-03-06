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
 * Servlet implementation class ServletModifEnchere
 */
@WebServlet(
		urlPatterns = {"/ModifEnchere", "/Delete"}
				)
public class ServletModifEnchere extends HttpServlet {
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
		switch (request.getServletPath()) {
		case "/ModifEnchere":
			//Gestion de l'affichage du message selon les dates 
			request.setCharacterEncoding("UTF-8");
			
				String pseudoVendeur = request.getParameter("pseudoVendeur");
				String nomArticleAModifier = request.getParameter("nomArticle");
				
			try 
				{
					ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
					ArticleVendu articleAModifier = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticleAModifier, pseudoVendeur);
					System.out.println(articleAModifier);
					String pseudoUtilisateur = (String) request.getSession().getAttribute("Utilisateur");
					System.out.println(pseudoUtilisateur);
					System.out.println(pseudoVendeur);
					int etatVente = articleAModifier.getEtatVente();
					System.out.println(etatVente);
					//Verification du droit à la modification selon la date du jour et vérification que c'est bien l'article de l'utilisateur
					if(etatVente ==1 && pseudoVendeur.equals(pseudoUtilisateur))
						{
						System.out.println("j'ai le droit");
						UtilisateurManager userManager = UtilisateurManager.getInstance();
						Utilisateur user = userManager.recuperationUtilisateur(pseudoVendeur);
						System.out.println(user);
						
						RetraitManager retraitManager = RetraitManager.getInstance();
						Retrait retrait = retraitManager.recupererRetraitByID(articleAModifier.getNoArticle());
						System.out.println(retrait);
						
						//Récupération des catégories existantes pour les afficher dans un eliste déroulante
						try 
							{
								
								Set<Categorie> listeDeCategories = articleManager.getListCategories();
								request.setAttribute("listeDeCategories", listeDeCategories);
								System.out.println(listeDeCategories);
								
								String categorieArticleSelected = null;
								
								for (Categorie categorie : listeDeCategories) 
									{
										if(categorie.getNoCategorie() == articleAModifier.getNoCategorie())
											{
												categorieArticleSelected = categorie.getLibelle();
											}
									}
								System.out.println(categorieArticleSelected);
								request.setAttribute("categorieArticleSelected", categorieArticleSelected);
							} 
						catch (BusinessException e) 
							{
								e.ajouterErreur(40000);
							}
								
						byte[] image = articleAModifier.getImageArticle();
						if(image != null)
									{
										request.setAttribute("image", image);
									}
							request.setAttribute("retrait", retrait);
							request.setAttribute("articleAModifier", articleAModifier);
							request.setAttribute("user", user);
							
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifEnchere.jsp");
							rd.forward(request, response);
						}
					//Si pas le droit redirection accueil avec bandeau signalant la raison
					else
						{
							String nonDroitModif = "Désolé, vous ne pouvez pas modifier ou supprimer l'article, soit l'enchère est déjà en cours, soit vous n'avez pas les droits sur cet article";
							request.setAttribute("nonDroitModif", nonDroitModif);
							
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
							rd.forward(request, response);
						}
				} 
			catch (BusinessException e) 
				{
					e.ajouterErreur(40005);
					e.printStackTrace();
				}
				
				
			break;
		
		case "/Delete":
			
			String pseudoVendeur1 = request.getParameter("pseudoVendeur");
			String nomArticleAModifier1 = request.getParameter("nomArticle");
			
			try 
				{
					ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
					ArticleVendu articleAModifier = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticleAModifier1, pseudoVendeur1);
					int etatVente = articleAModifier.getEtatVente();
					
					//Verification du droit à la Suppression selon la date du jour
					if(etatVente<1)
						{
						boolean articleSuppressed = articleManager.cancelArticleVendu(articleAModifier);	
							if(articleSuppressed == true)
								{
									String suppressionArticle = "La suppression de l'article à la vente est effectuée";
									request.setAttribute("resultatEnchere", suppressionArticle);
									RetraitManager retraitManager = RetraitManager.getInstance();
									retraitManager.supprimerRetrait(articleAModifier.getNoArticle());
									RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
									rd.forward(request, response);
								}
							else
								{
								String nonDroitModif = "Désolé, l'enchère ayant débutée vous ne pouvez plus modifier ou supprimer l'article";
								
								request.setAttribute("nonDroitModif", nonDroitModif);
								
								RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
								rd.forward(request, response);
								}
						}
					//Si pas le droit redirection accueil avec bandeau signalant la raison
					else
						{
							String nonDroitModif = "Désolé, l'enchère ayant débutée vous ne pouvez plus modifier ou supprimer l'article";
							request.setAttribute("nonDroitModif", nonDroitModif);
							
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
							rd.forward(request, response);
						}
				} 
		catch (BusinessException e) 
			{
				e.ajouterErreur(40005);
				e.printStackTrace();
			}
			
			break;
		
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("Utilisateur") == null)
			{
			response.sendRedirect(request.getContextPath()+"/Accueil");
			}
		else
			{
				request.setCharacterEncoding("UTF-8");
				
				String pseudoVendeur = (String) request.getSession().getAttribute("Utilisateur");
				String nomArticle = request.getParameter("nomArticle");
				System.out.println(pseudoVendeur);
				System.out.println(nomArticle);
				
				// Récupération des dates et validation avant insertion de l'article
				
				LocalDate dateDebutEncheres = ((Date.valueOf(request.getParameter("dateDebutEnchere"))).toLocalDate());
				System.out.println(dateDebutEncheres);
				LocalDate dateFinEncheres = ((Date.valueOf(request.getParameter("dateFinEnchere"))).toLocalDate());
				System.out.println(dateFinEncheres);
				boolean validateDateDebut = validationDate(dateDebutEncheres, dateFinEncheres);
				System.out.println(validateDateDebut);
				System.out.println(LocalDate.now());
				
				
				//Récupération des entrées utilisateurs de l'article
				
				
				String description = request.getParameter("description").trim();
				String categorie = request.getParameter("categorie");
				int miseAPrix = Integer.parseInt(request.getParameter("map"));
				String rue = request.getParameter("nomRue").trim();
				String cp = request.getParameter("codePostal").trim();
				String ville = request.getParameter("ville").trim();
				byte[] images = request.getParameter("image").getBytes();
				
			
			//Construction de l'article pour pré remplissage en cas d'erreur
				
				ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix,images);
				
			//Construction du retrait pour la manipulation en dal pré remplissage en cas d'erreur
				
				Retrait retrait = new Retrait(rue,cp,ville);
				
				//Dates invalides
				
				if(validateDateDebut == false)
					{
					request.setAttribute("validateDateDebut", validateDateDebut);
					
						if(images != null)
							{
								request.setAttribute("image", images);
							}
					request.setAttribute("articleAModifier", newArticle);
					request.setAttribute("retrait", retrait);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifEnchere.jsp");
					rd.forward(request, response);
					}

				//Dates valides, traitement et ajour de l'article
				
				else
					{
					
					ArticleVenduManager articleManager =ArticleVenduManager.getInstance();
					ArticleVendu articleARenvoyer = null;
					try {
						articleARenvoyer = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
					} catch (BusinessException e1) {
						e1.ajouterErreur(40003);
						e1.printStackTrace();
					}
					int idArticleAModifier = articleARenvoyer.getNoArticle();
					
					//Mise à jour de l'article
					Set<Categorie> categories=null;
					try {
						categories = articleManager.getListCategories();
					} catch (BusinessException e1) {
						e1.ajouterErreur(40004);
						e1.printStackTrace();
					}
					int idCategorie = 0;
					for (Categorie categorie2 : categories) {
						if(categorie2.getLibelle() == categorie)
							{
								idCategorie = categorie2.getNoCategorie();
							}
					}
					
					
					articleARenvoyer.setDescription(description);
					articleARenvoyer.setMiseAPrix(miseAPrix);
					articleARenvoyer.setNoCategorie(idCategorie);
					articleARenvoyer.setDateDebutEncheres(dateDebutEncheres);
					articleARenvoyer.setDateFinEncheres(dateFinEncheres);
					if(images != null)
						articleARenvoyer.setImageArticle(images);
					
					System.out.println(articleARenvoyer);
					try {
						articleManager.updateArticle(articleARenvoyer);
					} catch (BusinessException e1) {
						e1.ajouterErreur(40002);
						e1.printStackTrace();
					}
					//Ajout du point de retrait de cette article nouvellement créé
						
						
						
						try 
							{
								RetraitManager retraitManager = RetraitManager.getInstance();
								retraitManager.updateRetrait(retrait, idArticleAModifier);
							} 
						catch (BusinessException e) 
							{
								e.ajouterErreur(40003);
							}
					
						//Affichage message ajout OK
					boolean validerAjout = true;
					request.setAttribute("validerAjout", validerAjout);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
					rd.forward(request, response);
					}
			}
	}
	
	
	private boolean validationDate(LocalDate datedebut, LocalDate dateFin) {
		boolean validationDate = false;
			if(datedebut.compareTo(LocalDate.now()) >= 0 || dateFin.compareTo(datedebut)<=0)
				validationDate = false;
			else
				validationDate = true;
		return validationDate;
	}

}
