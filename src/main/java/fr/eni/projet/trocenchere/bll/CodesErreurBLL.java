package fr.eni.projet.trocenchere.bll;

public abstract class CodesErreurBLL {

	public static final int REGLE_ATTRIBUT_NON_NULL = 20000;
	public static final int REGLE_LONGUEUR_MAX = 20001;
	public static final int REGLE_PRIX = 20002;
	public static final int REGLE_CREDIT = 20003;
	public static final int REGLE_ADMINISTRATEUR = 20004;
	
	/*
	 * Echec le mot de passe n'est pas correct
	 */
	public static final int CHECK_VALIDATIONMDP_ECHEC = 30001;
	/*
	 * Echec le pseudo est déja utilisé
	 */
	public static final int REGLE_PSEUDO_DEJA_UTIL_ERREUR = 30002;
	/*
	* Echec le mail est déja utilisé
	*/
	public static final int REGLE_MAIL_DEJA_UTIL_ERREUR = 30002;
	
	
}
