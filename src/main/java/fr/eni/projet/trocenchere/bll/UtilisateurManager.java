package fr.eni.projet.trocenchere.bll;

import fr.eni.projet.trocenchere.bo.Retrait;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public class UtilisateurManager {
	
	private DAO<Utilisateur> utilisateurDAO;
	
	

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO;
	}

	public void ajouter(Utilisateur utilisateur) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		// Verification que les certains attributs ne soient pas null
		this.validerUtilisateurNotNull (utilisateur, exception);
		
		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		this.validerStringLenghtUtilisateur(utilisateur, exception);
		
		// Vérification que le crédit est positif
		this.validerCredit(utilisateur, exception);
		
		// Vérification du format de l'administrateur (0 ou 1)
		
		if(!exception.hasErreurs())
		{
			this.utilisateurDAO.insert(utilisateur);
		}
		else
		{
			throw exception;
		}
	}
	
	public void supprimer(Utilisateur utilisateur) {
		
		this.utilisateurDAO.delete(utilisateur);
	}
	
	public Utilisateur selectionnerUtilisateurParPseudo(Utilisateur utilisateur) {
		
		return this.utilisateurDAO.selectByPseudo(utilisateur);
	}
	
	public void validerUtilisateurNotNull (Utilisateur utilisateur, BusinessException businessException) {
		
		if (Integer.valueOf(utilisateur.getNo_utilisateur()) == null || 
			utilisateur.getPseudo() == null ||
			utilisateur.getNom() == null ||
			utilisateur.getPrenom() == null ||
			utilisateur.getEmail() == null ||
			utilisateur.getRue() == null ||
			utilisateur.getCodePostal() == null ||
			utilisateur.getVille() == null ||
			utilisateur.getMotDePasse() == null ||
			Integer.valueOf(utilisateur.getCredit()) == null ||
			Byte.valueOf(utilisateur.getAdministrateur()) == null)			
				{
					businessException.ajouterErreur(CodesErreurBLL.REGLE_ATTRIBUT_NON_NULL);
				}
	}
	
	private void validerCredit(Utilisateur utilisateur, BusinessException businessException) {
		
		if (utilisateur.getCredit() < 0) 
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_CREDIT);
			}
	}
	
	private void validerAdministrateur(Utilisateur utilisateur, BusinessException businessException) {
		
		if (utilisateur.getAdministrateur() != 0 && utilisateur.getAdministrateur() != 1) 
			{
				businessException.ajouterErreur(CodesErreurBLL.REGLE_ADMINISTRATEUR);
			}
	}
}
