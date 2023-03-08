package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.trocenchere.bll.ArticleVenduManager;
import fr.eni.projet.trocenchere.bo.ArticleVendu;

/**
 * Servlet implementation class ServletAcceuil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			ArticleVenduManager manager = ArticleVenduManager.getInstance();


			Cookie[] cookies = request.getCookies();


			if(cookies != null)
			{
				try {
					String userPseudo=null;
					for(Cookie cookieConnection : cookies)

					{
						if(cookieConnection.getName().equals("userPseudo"))
						{
							userPseudo = cookieConnection.getValue();
							HttpSession session = request.getSession(true);
							session.setAttribute("Utilisateur", userPseudo);
						}
					}



				}catch (NumberFormatException e) {
					e.printStackTrace();
				} 

			} 

			//récupérer la liste des articles.


			/*String article = "%%";
			int etatVente = 2;
			int categorie = 0;
			int categorieMax = 1000;
			String pseudoAchat = null;*/

			String pseudoVente ="visiteur";
			if (request.getSession().getAttribute("Utilisateur")!=null)
			{
				pseudoVente = (String) request.getSession().getAttribute("Utilisateur");
			}

			List<ArticleVendu> listeEnchereEnCours = manager.selectionnerTousLesArticles();
			request.setAttribute("listeEnchereEnCours", listeEnchereEnCours);

			//afficher la page accueil

			RequestDispatcher rd  = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	

		} catch (Exception e) {
			throw new ServletException ("La liste des articles n'a pas pu être récupéré"+e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ArticleVenduManager manager = ArticleVenduManager.getInstance();

			//récupere mes infos

			//recup du mot clé
			String motcle = "%"+request.getParameter("motcle")+"%";

			//récup des catégories
			int categories = Integer.parseInt(request.getParameter("categorie"));
			int categoriesMax;
			if (categories == 0) {
				categoriesMax = 1000;
			}
			else 
			{
				categoriesMax = categories;
			}

			//application de la méthode pour retourner la liste des articles selon le nom

			ArticleVendu article = new ArticleVendu();
			article.setNomArticle(motcle);
			List<ArticleVendu> articlesParNomPartiel = new ArrayList<>();
			articlesParNomPartiel = manager.selectionnerArticlesNomPartiel(article);
			request.setAttribute("listeEnchereEnCours", articlesParNomPartiel);
			
			

		} catch (NumberFormatException e) {
			throw new ServletException ("La liste des articles n'a pas pu être récupéré"+e);
		}


		// afficher la page

		RequestDispatcher rd  = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);	

	}

}