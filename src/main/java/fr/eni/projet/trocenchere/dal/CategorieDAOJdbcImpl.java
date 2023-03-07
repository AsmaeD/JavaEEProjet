package fr.eni.projet.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES("
			+ "libelle) "
			+ "VALUES (?);"  ;
	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES" ;
	private static final String SELECT_BY_ID = "SELECT " 
			+ "no_categorie, " 
			+ "libelle " 
			+ "FROM CATEGORIES "
			+ "WHERE no_categorie = ? "
			+ ";" ;
	private static final String DELETE_CATEGORIE = "DELETE FROM UTILISATEURS WHERE no_categorie = ? ;"  ;


	@Override
	public void insert(Categorie category) throws BusinessException {
		//verify is user is null
		//TODO verify if user already belongs to database
		if(category==null)
		{
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				pstmt = cnx.prepareStatement(INSERT_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS);
				
				//TODO verification start with 1 or 2
				//database auto increment but in insert_user statement starts with pseudo
				pstmt.setString(1, category.getLibelle()) ;
			
				pstmt.executeUpdate();
				
				pstmt.close();
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override//check if arg is n
	public void delete(Categorie category) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE);
			//verify if starts with 1, arg in DELETE_USER or 2 rank in database
			pstmt.setInt(1, category.getNumCategorie());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}
		
	}


	@Override
	public List<Categorie> selectAll() throws BusinessException {
		List<Categorie> listeCategories = new ArrayList<Categorie>() ;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeCategories.add(new Categorie(rs.getInt(1), rs.getString(2))
					);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return listeCategories;
	}

	@Override//check which arg is passed in server
	public Categorie selectById(int i) {
		Categorie category = null ;
		try {
			
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt ;
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			//in SELECT_BY_PSEUDO only one variable passes : pseudo
			//set variable to pseudo
			pstmt.setInt(1, i) ;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				category = new Categorie(
					rs.getInt("no_categorie"), 
					rs.getString("libelle")
					);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return category;
	}

	@Override
	public void update(Categorie category) throws BusinessException {
		// TODO 
		
	}

}


