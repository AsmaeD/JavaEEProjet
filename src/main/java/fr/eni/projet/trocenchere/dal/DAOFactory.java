package fr.eni.projet.trocenchere.dal;


public class DAOFactory {
	//TODO adapt to DAO generic type
	
	public static UtilisateurDAO getUtilisateurDAO()  {
		UtilisateurDAO utilisateurDAO=null;
		try {
			utilisateurDAO=(UtilisateurDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.UtilisateurDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateurDAO; 
	}
	
	public static CategorieDAO getCategorieDAO()  {
		CategorieDAO categorieDAO=null;
		try {
			categorieDAO=(CategorieDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.CategorieDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorieDAO; 
	}
	

}