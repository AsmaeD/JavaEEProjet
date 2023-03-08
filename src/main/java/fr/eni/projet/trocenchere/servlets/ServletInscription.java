package fr.eni.projet.trocenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bll.UtilisateurManager;
import fr.eni.projet.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Inscription.jsp") ;
		rd.forward(request, response);
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean validationMandP = false;
		Boolean validationMDP = false;
		
		try {
			UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			
			// Récuperation de l'ensemble des données du formulaire
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String tel = request.getParameter("tel");
			String cp = request.getParameter("cp");
			String mdp = request.getParameter("mdp");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String rue = request.getParameter("rue");
			String ville = request.getParameter("ville");
			String mdpConfirm = request.getParameter("confirm");
				
			// Construction utilisateur
			Utilisateur utilisateurU = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdp);
			System.out.println(utilisateurU.toString());	
						
			// If permettant de savoir si les deux mdps du formulaire sont identiques
			if(mdpConfirm.equals(mdp)) {				
				// Ajoute les données dans la bdd si pseudo et mail retourne : false
				validationMandP = utilisateurManager.AjouterInscription(utilisateurU);
				if(validationMandP == false) {		
					request.setAttribute("validationMandP", validationMandP);
					request.setAttribute("utilisateurU", utilisateurU);
					RequestDispatcher rd = request.getRequestDispatcher("Inscription.jsp");
					rd.forward(request, response);
				}
			} 
			// Si mdp pas identiques : renvoie d'erreur pas de création d'objet et renvoie message erreur sur la jsp
			if(!mdpConfirm.contentEquals(mdp)) {
				validationMDP = true;
				request.setAttribute("validationMDP", validationMDP);
				request.setAttribute("utilisateurU", utilisateurU);
				RequestDispatcher rd = request.getRequestDispatcher("Inscription.jsp") ;
				rd.forward(request, response);
			}
			
		
			HttpSession session = request.getSession(true);
			session.setAttribute("Utilisateur", utilisateurU.getPseudo());
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp") ;
			rd.forward(request, response);
			
			
		} catch (BusinessException e) {
			throw new ServletException("Erreur du chargement de la page : Inscription ");
		}
		

		
	}

	
	
}
