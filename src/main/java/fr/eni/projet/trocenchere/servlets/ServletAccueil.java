package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;
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
import fr.eni.projet.trocenchere.bll.BusinessException;
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
			
			//récupérer la liste des articles en status en cours .
			
			
			String motcle = "%%";
			int etatVente = 2;
			int categorie = 0;
			int categorieMax = 1000;
			String pseudoAchat = null;
			String pseudoVente ="visiteur";
			if (request.getSession().getAttribute("Utilisateur")!=null)
			{
				pseudoVente = (String) request.getSession().getAttribute("Utilisateur");
			}
			
			List<ArticleVendu> listeEnchereEnCours = manager.getListeEtatVente(motcle, etatVente, null, null, categorie, categorieMax, pseudoAchat, pseudoVente);
			request.setAttribute("listeEnchereEnCours", listeEnchereEnCours);
						
			//afficher la page accueil

			
			
			RequestDispatcher rd  = request.getRequestDispatcher("/index.jsp");
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
			
			//récup des boutons radio
			String pseudoAchat=null;
			String pseudoVente=null;
			 
		
			if (request.getParameter("status").equals("Achats")) {
				if (request.getSession().getAttribute("Utilisateur")!=null) {
					pseudoAchat = (String) request.getSession().getAttribute("Utilisateur");
				}
				else
				{
					pseudoAchat= "visiteur";
				}
				
				pseudoVente = null;
			}
			
			if (request.getParameter("status").equals("Ventes")) {
				pseudoAchat = null;
				if (request.getSession().getAttribute("Utilisateur")!=null) {
					pseudoVente = (String) request.getSession().getAttribute("Utilisateur");
				}
				else {
					pseudoVente = "visiteur";
				}
				
			}	
			
			Integer ouvertes;
			Integer encours;
			Integer terminees;
			if ((request.getParameter("ouvertes")) != null) {
				
				ouvertes = Integer.parseInt(request.getParameter("ouvertes"));
			}
			else {
				ouvertes = null;
			}
			if ((request.getParameter("encours")) != null) {
				
				 encours = Integer.parseInt(request.getParameter("encours"));
			}
			else {
				 encours = null;
			}
			
			if ((request.getParameter("remportees")) != null) {
				
				terminees = Integer.parseInt(request.getParameter("remportees"));
			}
			else {
				terminees = null;
			}
			
			
			
			//application de la méthode pour retourner la liste des articles
			
			List<ArticleVendu> listeEnchereEnCours = manager.getListeEtatVente(motcle, ouvertes, encours, terminees, categories, categoriesMax, pseudoVente,pseudoAchat);
			request.setAttribute("listeEnchereEnCours", listeEnchereEnCours);
			
		} catch (NumberFormatException | BusinessException e) {
			throw new ServletException ("La liste des articles n'a pas pu être récupéré"+e);
		}

		
		// afficher la page
		
		RequestDispatcher rd  = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);	
		
	}

}
