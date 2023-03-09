package fr.eni.projet.trocenchere.bll;


//import java.net.Authenticator; //non utilisé
//import java.net.PasswordAuthentication; //non utilisé
//import java.util.Properties; //non utilisé

//import javax.websocket.Session; //non utilisé

//import fr.eni.projet.trocenchere.bo.Retrait; //non utilisé
//import fr.eni.projet.trocenchere.bo.Utilisateur; //non utilisé
import fr.eni.projet.trocenchere.dal.DAOFactory;
import fr.eni.projet.trocenchere.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager instance = null;


	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static synchronized UtilisateurManager getInstance() throws BusinessException {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	// Méthode qui vérifie si le mail ne soit pas déjà existant
	public boolean validerMail(String nomMail) throws BusinessException {
		boolean statusValidation = false;
		if(nomMail == null | nomMail.length() > 60) {
			statusValidation = true;
		}
		int emailValide =0;
		try {
			emailValide = this.utilisateurDAO.selectEmail(nomMail);
			if(emailValide >= 1) {
				statusValidation = true;
			}
			else if(emailValide == 0) {
				statusValidation = false;
			}

		} catch (BusinessException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreurBLL.REGLE_MAIL_DEJA_UTIL_ERREUR);
			throw businessException;
		}
		return statusValidation;
	}

	// Méthode qui vérifie que le pseudo ne soit pas déjà existant et ne dépasse pas les 30 caractères
	public boolean validerPseudo(String nomPseudo) throws BusinessException {
		boolean statusValidation = false;
		if(nomPseudo == null | nomPseudo.length() > 30) {
			statusValidation = true;
		}
		int pseudoValide = 0;
		try {
			pseudoValide = this.utilisateurDAO.selectPseudo(nomPseudo); //changer le nom
			if(pseudoValide >= 1) {
				statusValidation = true;
			}			
			else if(pseudoValide == 0) {
				statusValidation = false;
			}
		} catch (BusinessException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreurBLL.REGLE_PSEUDO_DEJA_UTIL_ERREUR);
			throw businessException;
		}
		return statusValidation;
	}
	
	
	// Méthode de verif pseudo
	private boolean verifierPseudo(String identifiant) throws BusinessException {


		boolean statusValidation = false;
		int PseudoValide = 0; //pas ma meilleure façon de faire TODO changer le type vers BOOL

		if (identifiant != null) {
			try {
				PseudoValide  = this.utilisateurDAO.selectPseudo(identifiant);
				if(PseudoValide == 0 ) {
					statusValidation = false;
				}
				else if(PseudoValide != 0) {
					statusValidation = true;
				}

				System.out.println("passes-tu la");

			} catch (Exception e) {
				BusinessException businessException = new BusinessException(); 
				businessException.ajouterErreur(CodesErreurBLL.CHECK_VALIDATIONMDP_ECHEC);
				throw businessException;



				//			System.out.println(e.getClass().getCanonicalName());
				//			System.out.println("pas there");
				//		}
				//		finally { System.out.println("there"); // TODO


			}
		}

		return statusValidation;
	}



	// Méthode de verif MPD :
	public boolean validerMDP(String password) throws BusinessException {
		boolean statusValidation = false;
		int passwordValide = 0;
		try {
			passwordValide  = this.utilisateurDAO.selectPassword(password);
			if(passwordValide >= 1) {
				statusValidation = true;
			}
			else if(passwordValide == 0) {
				statusValidation = false;
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreurBLL.CHECK_VALIDATIONMDP_ECHEC);
			throw businessException;
		}

		return statusValidation;
	}

	//Méthode de connection
	public boolean connection(String identifiant, String password) throws BusinessException{
		
		boolean testConnection = false;
		//testConnection = verifierPseudo(identifiant);
		testConnection = verifierPseudo("Montesqieu");
		System.out.println(testConnection);
		if (testConnection == true)
				
		{
		//	testConnection = validerMDP(password);
			testConnection = validerMDP("lolodelach235");
			
		}
		return testConnection;	
	//	return true; // hard test
	}


/*

	public void ajouter(Utilisateur utilisateur) throws BusinessException {

		BusinessException exception = new BusinessException();

		// Verification que les certains attributs ne soient pas null
		this.validerUtilisateurNotNull (utilisateur, exception);

		// Verification que la longueur des chaines de caractères ne dépasse pas celle enregistrée en base de données
		// this.validerPseudo(utilisateur, exception);

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

	// Méthode de BLL pour inserer une nouvelle inscription	
	public boolean AjouterInscription(Utilisateur utilisateur) throws BusinessException {

		boolean inscriValideP = false;
		boolean inscriValideE = false;

		inscriValideP = validerPseudo(utilisateur.getPseudo());
		inscriValideE = validerMail(utilisateur.getEmail());


		if(inscriValideP == false & inscriValideE == false) {
			this.utilisateurDAO.insertInscription(utilisateur);
			return true;
		}
		else {
			return false;
		}
	}

	//méthode de récuperation d'un utilisateur

	public Utilisateur recuperationUtilisateur(String pseudo) throws BusinessException {
		Utilisateur u = null;
		u = this.utilisateurDAO.SelectUser(pseudo);
		return u;
	}

	//Methode de suppression du profil
	public void suppressionProfil(String pseudo) throws BusinessException {
		this.utilisateurDAO.deleteProfil(pseudo);
	}

	//Méthode modification profil
	public void modificationProfil(Utilisateur utilisateur, String pseudo) throws BusinessException {
		this.utilisateurDAO.updateProfil(utilisateur, pseudo);
	}

	//Méthode pour modifier le crédit de l'utilisateur au moment d'une enchère

	public boolean effectuerEnchere(Utilisateur encherisseur, int montantEnchere) throws BusinessException {
		boolean creditSuffisant = false;
		int creditEncherisseur = encherisseur.getCredit();
		if(creditEncherisseur-montantEnchere >= 0)
		{
			String pseudoEncherisseur = encherisseur.getPseudo();
			int nouveauCredit = creditEncherisseur-montantEnchere;

			try 
			{
				utilisateurDAO.updateCreditUtilisateur(pseudoEncherisseur, nouveauCredit);
			}
			catch(BusinessException e)
			{
				e.ajouterErreur(20005);
				creditSuffisant = false;
			}
			creditSuffisant = true;
		}
		else
		{
			creditSuffisant = false;
		}
		return creditSuffisant;
	}
	//Méthode pour update mot de passe
	public void UpdatePassword(String password, String verifPassword, String userEmail)throws BusinessException {

		this.utilisateurDAO.updatePassword(password, userEmail);

	}
	
	//méthode vérification de mail
	
			public boolean verifmail(String email) throws BusinessException 
			{
				return validerMail(email);
			}
			
		//méthode pour envoyer un mail oublie mot de passe
			
			public void sentmail(String userEmail) throws Exception{
				Properties properties = new Properties();

				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				
				// Méthode de récupration oubli 
				final String monEmail = "";
				final String password = "";
				
				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(monEmail, password);
					}
				});*/
}
