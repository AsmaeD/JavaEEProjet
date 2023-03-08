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
 * Servlet implementation class ServletProfil
 */
@WebServlet(
		urlPatterns = {"/Profil", "/ModifProfil", "/Suppression"}
		)
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		String utilisateurGeneral = (String) request.getSession().getAttribute("Utilisateur");		
		if(request.getSession().getAttribute("Utilisateur") != null)
		{
			if (request.getServletPath().equals("/Profil")) {
				
					try {
						boolean valideP = false;
						UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			
						
						// Récupération du pseudo lorsqu'on clique sur le nom du vendeur. A finir lorsque la page accueil sera présente.	
						String pseudoRecup = request.getParameter("pseudo");
						
						if(pseudoRecup.equals(utilisateurGeneral)) {
							// Récupération du pseudo de l'utilisateur pour traiter par la suite	
							Utilisateur utilisateurInconnu = utilisateurManager.recuperationUtilisateur(utilisateurGeneral);
							request.setAttribute("utilisateurInconnu", utilisateurInconnu);
						}
						if(!pseudoRecup.equals(utilisateurGeneral)) {
							Utilisateur utilisateurInconnu = utilisateurManager.recuperationUtilisateur(pseudoRecup);
							request.setAttribute("utilisateurInconnu", utilisateurInconnu);
						}
		
						// Pseudo récupéré meme que celui de la session : bouton modif profil activé
						if(pseudoRecup.equals(utilisateurGeneral)) {
						valideP = true;	
						request.setAttribute("valideP", valideP);
						}
						request.setAttribute("valideP", valideP);
						RequestDispatcher rd  = request.getRequestDispatcher("Profil.jsp");
						rd.forward(request, response);
						
					
		
						
					} catch ( BusinessException e) {
						throw new ServletException("Erreur de chargement de la page : Profil ");
					}
		
				}
			
	
			if (request.getServletPath().equals("/ModifProfil")) {
				// Permettant de récupérer les informations de l'utilisateur lorqu'on clique sur le bouton modif
				try {
					UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
					Utilisateur utilisateurProfil = utilisateurManager.recuperationUtilisateur(utilisateurGeneral);
	
					request.setAttribute("utilisateurProfil", utilisateurProfil);
					RequestDispatcher rd  = request.getRequestDispatcher("ModificationProfil.jsp");
					rd.forward(request, response);
				} catch (BusinessException e) {
					throw new ServletException("Erreur de chargement de la page : Modification profil ");
				}
							
			}
			
			if (request.getServletPath().equals("/Suppression")) {
							// Méthode pour supprimer le compte et de le déconnecter.
					try {
						UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
						utilisateurManager.suppressionProfil(utilisateurGeneral);
						request.getSession().invalidate();
						RequestDispatcher rd  = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
						
					} catch (BusinessException e) {
						throw new ServletException("Erreur de chargement de la page : Suppression profil ");
					}	
			}
			
		}
		else {
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Boolean permettant de vérifier si le mdp est bon, la similitude des deux nvx mdp,
		// de vérifier qu'il n'existe pas de pseudo et mail déjà existant si modif de ceux-ci et verification si pas de case vide dans le formulaire
		boolean validationMdp = false;
		boolean validationMdpAc = false;
		boolean verifMdp = false;
		boolean erreurvide = false;
		boolean verifPseudo = false;
		boolean verifMail = false;
		
		
		try {

			UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			String utilisateurGener = (String) request.getSession().getAttribute("Utilisateur");
			Utilisateur utilisateurProfil = utilisateurManager.recuperationUtilisateur(utilisateurGener);
			
			// Récupération des nouvelles données
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String rue = request.getParameter("rue");
			String cp = request.getParameter("cp");
			String ville = request.getParameter("ville");
			String mdpAc = request.getParameter("mdp");
			String newMdp = request.getParameter("newmdp");
			String confirmMdp = request.getParameter("confirm");
			
			// Verification si case vide dans le formulaire, si oui : message erreur
			if(pseudo.length()==0 || prenom.length()==0  || tel.length()==0  || cp.length()==0  || 
					nom.length()==0  || email.length()==0  || rue.length()==0  || ville.length()==0 ) {
				erreurvide = true;
				request.setAttribute("erreurvide", erreurvide);
				request.setAttribute("utilisateurProfil", utilisateurProfil);
				RequestDispatcher rd = request.getRequestDispatcher("ModificationProfil.jsp") ;
				rd.forward(request, response);
			}
			
			
			// Vérification du mot de passe : résultat en boolean
			verifMdp = utilisateurManager.validerMDP(mdpAc);
			
			
			//if verifie si mdp bon
 			if(verifMdp == true) {
 				// reucpération mail
 				String mailUtil = utilisateurManager.recuperationUtilisateur(utilisateurGener).getEmail();
 				// Vérification que mail et pseudo ne soient pas déjà existant hormis celui de l'utilisateur
				verifMail = utilisateurManager.validerMail(email);
				verifPseudo = utilisateurManager.validerPseudo(pseudo);
				
				// Vérifie qu'il n'y ait pas de mail ou pseudo déjà existant à part celui de l'utilisateur.
				if(verifMail == false || email.equals(mailUtil)) {
					if(verifPseudo == false || pseudo.equals(utilisateurGener)) {
									
						//mÃ©thode verifie si les nouveaux mdp ne sont pas : null
						//et verifie si les deux nouveaux mdp sont egaux alors ok
	 					if(newMdp.length()>0 && confirmMdp.length()>0 && newMdp.equals(confirmMdp)) {
	 							
	 							Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, newMdp);
	 							utilisateurManager.modificationProfil(utilisateur, utilisateurGener);
	 							HttpSession session = request.getSession(true);
	 	 						session.setAttribute("Utilisateur", utilisateur.getPseudo());
	 	 						RequestDispatcher rd = request.getRequestDispatcher("index.jsp") ;
	 	 						rd.forward(request, response);	 						
	 					}
						// VÃ©rifie si les deux new mdps sont : null alors ok 
	 					if(newMdp.length()==0 && confirmMdp.length()==0) {
	 						Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdpAc);			
	 						utilisateurManager.modificationProfil(utilisateur, utilisateurGener);
	 						HttpSession session = request.getSession(true);
	 						session.setAttribute("Utilisateur", utilisateur.getPseudo());
	 						RequestDispatcher rd = request.getRequestDispatcher("index.jsp") ;
		 					rd.forward(request, response);
	 					}
						// si mdp les deux mdp ne sont pas egaux : message erreur
	 					else if(!newMdp.equals(confirmMdp)) {
	 						validationMdp = true;
	 						request.setAttribute("validationMdp", validationMdp);
							request.setAttribute("utilisateurProfil", utilisateurProfil);
	 						RequestDispatcher rd = request.getRequestDispatcher("ModificationProfil.jsp") ;
	 						rd.forward(request, response);
	 					}
	
				}
				}
				//vérifie si le mail et le pseudo existe déjà. Si oui : message erreur
				if(verifMail == true || verifPseudo == true) {
					verifMail = true;
					request.setAttribute("verifMail", verifMail);
					request.setAttribute("utilisateurProfil", utilisateurProfil);
					RequestDispatcher rd = request.getRequestDispatcher("ModificationProfil.jsp") ;
					rd.forward(request, response);
				}
				// Sinon erreur et envoit message erreur
				else {
					request.setAttribute("verifMail", verifMail);
					request.setAttribute("utilisateurProfil", utilisateurProfil);
					RequestDispatcher rd = request.getRequestDispatcher("ModificationProfil.jsp") ;
					rd.forward(request, response);
				}
 			}
 			
			// Si le mot de passe actuel n'est pas bon alors message d'erreur sur la page
 			 if(verifMdp == false) {
 				validationMdpAc = true;
				request.setAttribute("validationMdpAc", validationMdpAc);
				request.setAttribute("utilisateurProfil", utilisateurProfil);
				RequestDispatcher rd = request.getRequestDispatcher("ModificationProfil.jsp") ;
				rd.forward(request, response);	
 			 }
 			 
		
		} catch (BusinessException e) {
			throw new ServletException("Erreur de la modification de la page profil ");
		}
		
		
	}

}
