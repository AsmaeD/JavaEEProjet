package fr.eni.projet.trocenchere.dal;


public class DAOFactory {
	//TODO adapt to DAO generic type
	
	public static UtilisateurDAO getUtilisateurDAO()  {
	
	//TODO ADD OTHER DAOs
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
	
	public static ArticleVenduDAO getArticleVenduDAO()  {
		ArticleVenduDAO articleVenduDAO=null;
		try {
			articleVenduDAO=(ArticleVenduDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.ArticleVenduDAOJdbcImpl").newInstance();
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
		return articleVenduDAO; 
	}
	
	public static RetraitDAO getRtraitDAO()  {
		RetraitDAO retraitDAO=null;
		try {
			retraitDAO=(RetraitDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.RetraitDAOJdbcImpl").newInstance();
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
		return retraitDAO; 
	}
	
	public static EnchereDAO getEnchereDAO()  {
		EnchereDAO EnchereDAO=null;
		try {
			EnchereDAO=(EnchereDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.EnchereDAOJdbcImpl").newInstance();
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
		return EnchereDAO; 
	}
	

}
