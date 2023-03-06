package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.trocenchere.bll.ArticleVenduManager;
import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bll.EnchereManager;
import fr.eni.projet.trocenchere.bll.RetraitManager;
import fr.eni.projet.trocenchere.bll.UtilisateurManager;
import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Enchere;
import fr.eni.projet.trocenchere.bo.Retrait;
import fr.eni.projet.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/DetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("Utilisateur") == null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
				rd.forward(request, response);
			}
		else
			{
				request.setCharacterEncoding("UTF-8");
				String nomArticle = request.getParameter("nomArticle");
				String pseudoVendeur = request.getParameter("pseudoVendeur");
				
				ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
				RetraitManager retraitManager = RetraitManager.getInstance();
		
				try 
					{
						ArticleVendu articleAAfficher = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
					int idArticle = articleAAfficher.getNoArticle();
						request.setAttribute("articleAAfficher", articleAAfficher);
						request.setAttribute("vendeur", pseudoVendeur);
						
						byte[] image = articleAAfficher.getImageArticle();
							if(image != null)
									request.setAttribute("image", articleAAfficher.getImageArticle());
							
						Retrait retraitArticleSelected = retraitManager.recupererRetraitByID(idArticle);
						
						request.setAttribute("retraitArticleSelected", retraitArticleSelected);
						
						if(articleAAfficher.getEtatVente() > 1)
							{
								EnchereManager enchereManager = EnchereManager.getInstance();
								Enchere enchere = enchereManager.recuperationEnchereByArticle(articleAAfficher);
								if(enchere != null)
									request.setAttribute("enchere", enchere);
							}
						
					} 
				catch (BusinessException e) 
					{
						e.ajouterErreur(40002);
					}
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp");
				rd.forward(request, response);
		
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getSession().getAttribute("Utilisateur") == null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
				rd.forward(request, response);
			}
		else
			{
					//Gere l'enchère submit par l'utilisateur
					
					request.setCharacterEncoding("UTF-8");
					String nomArticle = request.getParameter("nomArticle");
					String pseudoVendeur = request.getParameter("pseudoVendeur");
					String pseudoEncherisseur = (String) request.getSession().getAttribute("Utilisateur");
					if(!pseudoVendeur.equals(pseudoEncherisseur))
					{
					
					int montantEnchere = Integer.parseInt(request.getParameter("enchere"));
					try
						{
							//Récupération des informations de l'article pour traitement de l'enchère
						
							ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
							ArticleVendu articleAEncherir = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
							
							//Récupération des informations de l'enchérisseur
							UtilisateurManager userManager = UtilisateurManager.getInstance();
							Utilisateur encherisseur = userManager.recuperationUtilisateur(pseudoEncherisseur);
							boolean creditSuffisant = userManager.effectuerEnchere(encherisseur, montantEnchere);
							int idEncherisseur = encherisseur.getNoUtilisateur();
						if(creditSuffisant == true) 
						{
							EnchereManager enchereManager = EnchereManager.getInstance();
							boolean enchereExist = enchereManager.verificationEnchereExist(articleAEncherir);
								if (enchereExist == false) 
									{
										
											boolean execute = enchereManager.startEnchere(articleAEncherir,idEncherisseur,montantEnchere);
										if(execute==true)
											 {
												String resultat = "Bravo vous êtes le premier et le meilleur enchérisseur";
												request.setAttribute("resultatEnchere", resultat);

												RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
												rd.forward(request, response);
											 }
										else
											 {
													String resultat = "L'enchère n'a pas encore commencée";
											 		request.setAttribute("resultatEnchereError", resultat);

													RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
													rd.forward(request, response);
											 }
									}
								else
									{
									
										System.out.println("Encours");
										 String resultatEnchere = enchereManager.doNouvelleEnchere(articleAEncherir, encherisseur, montantEnchere);
										request.setAttribute("resultatEnchere", resultatEnchere);
										

										RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
										rd.forward(request, response);
										
									}
									
			
						}	
						else
							{
								String resultat = "Crédit non suffisant";
						 		request.setAttribute("resultatEnchereError", resultat);

								RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
								rd.forward(request, response);
							}
									
						}
					catch (BusinessException e)
						{
							e.ajouterErreur(40004);
						}
			}}
			}				
}
