package fr.eni.projet.trocenchere.dal;

//DELETE used as an example in another project
import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.*;
import fr.eni.projet.trocenchere.dal.*;

public class AppliTestDAL {

	public static void main(String[] args) {
		//create instance dao abbreviation
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		
		//create instance of utilisateur (bo)
		Utilisateur user = new Utilisateur(
				"GayLordDu73", 
				"Gay", "Lord", 
				"Lord@jpp.com", 
				"066554884", 
				"44 rue de la coderie", 
				"78000", 
				"a definir", 
				"jesuislemeilleur123$", 
				2, 
				false ) ;
		
		//insertion of instance into database
		try {
			utilisateurDAO.insert(user);
		} catch (BusinessException e) {
			System.out.println("erreur d zo");
			e.printStackTrace();
		}
		
		

	}

}
