package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletMotPasseOublie
 */
@WebServlet(
		urlPatterns = {"/MotDePasseOublie", "/Reinitialisation"}
)
public class ServletMotPasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getServletPath().equals("/MotDePasseOublie"))
		{
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
			rd.forward(request, response);
			
		}
		
		if (request.getServletPath().equals("/Reinitialisation"))
		{
			//récuparation du mail
			
			String userEmail = request.getParameter("userEmail");
			request.setAttribute("userEmail", userEmail);
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Reinitialisation.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean verifMail = false;
		if (request.getServletPath().equals("/MotDePasseOublie"))
		{
			try {
				UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
				
				//récuperation de l'adresse email
				String email = request.getParameter("email");
				
				//verification de l'existance du mail
				
				verifMail = utilisateurManager.verifmail(email);
				
				if (verifMail == false)
				{
					request.setAttribute("verifmail", verifMail);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
					rd.forward(request, response);
				}
				
				if (verifMail == true)
				{
					
					utilisateurManager.sentmail(email);
					
					request.setAttribute("verifmail", verifMail);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
					rd.forward(request, response);
				}

			} catch (Exception e) {
				//throw new ServletException("echec de l'envoi de l'email");
				e.printStackTrace();
			}
				
		}
		
		if (request.getServletPath().equals("/Reinitialisation"))
		{
			try {
				UtilisateurManager Utilisateur = UtilisateurManager.getInstance();
				
				//récuperation de l'adresse email et le mot de passe 
				String password = request.getParameter("password");
				String verifPassword = request.getParameter("verifPassword");
				String usermail = request.getParameter("userEmail");
				
				//vérification du mot des mots de passe
				
				boolean statusPassword = false;
				if (password.equals(verifPassword))
				{
					statusPassword = true;
				}

				//envoi d'un message d'erreur si les mots de passe ne sont pas identique 
				
				if (statusPassword == false)
				{
					request.setAttribute("statusPassword", statusPassword);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Reinitialisation.jsp");
					rd.forward(request, response);
					
				}
				
				if(statusPassword == true)
					
				{
					//utilisationd de la méthode pour changer le mot de passe
					
					Utilisateur.UpdatePassword(password, verifPassword, usermail);
					
					//affichage d'un message de validation
					
					request.setAttribute("statusPassword", statusPassword);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Reinitialisation.jsp");
					rd.forward(request, response);
				}
				
				
				
			} catch (BusinessException e) {
				throw new ServletException("Echec de la modification du mot de passe");
			}
			
		}
	}

}
