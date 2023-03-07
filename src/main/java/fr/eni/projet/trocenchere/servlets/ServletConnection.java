package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet(
		urlPatterns = {"/Connexion", "/deconnexion"}
				)
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getServletPath().equals("/deconnexion"))
		{
			
			if (request.getSession() != null)
			{
				Cookie[] cookies = request.getCookies();
				for(Cookie cookieConnection : cookies)
					if(cookieConnection.getName().equals("userPseudo")||cookieConnection.getName().equals("JSESSIONID"))
					{
						{
							cookieConnection.setMaxAge(0);
							response.addCookie(cookieConnection);
						}
					}
				
				request.getSession().invalidate();
				
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			
		}
		
		if (request.getServletPath().equals("/Connexion"))
		{
			if (request.getSession().getAttribute("Utilisateur") == null) {
				RequestDispatcher rd  = request.getRequestDispatcher("/Connexion.jsp");
				rd.forward(request, response);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean testConnection = false;

			try {
				UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			
				//récupération des données
				
				String utilisateur = request.getParameter("Identifiant");
				String password	= request.getParameter("Password");
				String souvenirMoi = request.getParameter("souvenirMoi");
				
				
				//Appel de la méthode de connection
				
				testConnection = utilisateurManager.connection(utilisateur, password);
				
				if (testConnection == false)
				{
					request.setAttribute("testConnection", testConnection);
					RequestDispatcher rd  = request.getRequestDispatcher("/Connexion.jsp");
					rd.forward(request, response);

				}
				
				if (testConnection == true)
				{
						
					HttpSession session = request.getSession(true);
					session.setAttribute("Utilisateur", utilisateur);
					if(souvenirMoi != null)
					{
						//création d'un cookie de connection pour une semaine
						Cookie cookieConnection	= new Cookie ("userPseudo", utilisateur);
						cookieConnection.setMaxAge(7*24*3600);
						response.addCookie(cookieConnection);
						
					}
					
					response.sendRedirect(request.getContextPath()+"/index.jsp");
	
				}

				
			} catch (NumberFormatException | BusinessException e) {
				throw new ServletException("La connexion à échouée");
				} 
		

	}

}
