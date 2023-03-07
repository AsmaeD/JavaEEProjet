package fr.eni.projet.trocenchere.dal;
import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//TODO change to DAO<ArticleVendu>
public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO{

/****************************************************************************************/
		/*BEGIN SQL QUERIES */
/****************************************************************************************/  
	//TODO Check all class attributes -> possibly changed
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS" ;
	private static final String SELECT_BY_ID = "SELECT " 
			+ "no_article, " 
			+ "nom_article, " 
			+ "description, " 
			+ "date_debut_encheres, "
			+ "date_fin_encheres, " 
			+ "prix_initial, " 
			+ "prix_fin_encheres, "
			+ "etat_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS "
			+ "WHERE no_article = ? "
			+ ";" ;
	
	private static final String SELECT_BY_CAT = "SELECT " 
			+ "no_article, " 
			+ "nom_article, " 
			+ "description, " 
			+ "date_debut_encheres, "
			+ "date_fin_encheres, " 
			+ "prix_initial, " 
			+ "prix_fin_encheres, "
			+ "etat_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS "
			+ "WHERE no_categorie = ? "
			+ ";" ;
	
	private static final String SELECT_BY_NOM = "SELECT " 
			+ "no_article, " 
			+ "nom_article, " 
			+ "description, " 
			+ "date_debut_encheres, "
			+ "date_fin_encheres, " 
			+ "prix_initial, " 
			+ "prix_fin_encheres, "
			+ "etat_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS "
			+ "WHERE nom_article LIKE ? "
			+ ";" ;
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS("
			+ "nom_article, " 
			+ "description, " 
			+ "date_debut_encheres, "
			+ "date_fin_encheres, " 
			+ "prix_initial, " 
			+ "prix_fin_encheres, "
			+ "etat_vente, "
			+ "no_utilisateur, "
			+ "no_categorie ) "
			+ "VALUES (?,?,?,?,?,?,?);" ;
	
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ? ;" ;
	
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET "
			+ "nom_article = ?, "
			+ "description = ?, "
			+ "date_debut_encheres = ?,"
			+ "date_fin_encheres = ?, "
			+ "prix_initial = ?, " 
			+ "prix_fin_encheres = ?, "
			+ "etat_vente = ?, "
			+ "no_utilisateur = ?, "
			+ "no_categorie = ? "
			+ "WHERE no_article = ? ;" ;
	
	
/****************************************************************************************/
		/*BEGIN METHODS SELECT*/
/****************************************************************************************/
	
	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>() ;
		//TODO ADDING OF USER AND CATEGORIE
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeArticles.add(new ArticleVendu(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						)
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
		return listeArticles;
	}

	
	@Override
	public ArticleVendu selectById(int i) throws BusinessException {
		ArticleVendu article = new ArticleVendu() ;
		//TODO ADDING OF USER AND CATEGORIE
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, i) ; //pu pointer on desired id
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				//check if order is tha same bo & db
				article = new ArticleVendu(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						) ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return article;
	}
	
	
	@Override
	public List<ArticleVendu> selectByCat(int i) throws BusinessException {
		List<ArticleVendu> article = new ArrayList<ArticleVendu>() ;
		//TODO ADDING OF USER AND CATEGORIE
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_CAT);
			pstmt.setInt(1, i) ;
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				article.add(new ArticleVendu( //add article with chosen category into list
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						)) ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return article;
	}
	
	@Override
	public List<ArticleVendu> selectByNom(String nom) throws BusinessException {
		List<ArticleVendu> article = new ArrayList<ArticleVendu>() ;
		//TODO ADDING OF USER AND CATEGORIE
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM);
			pstmt.setString(1, nom) ;
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				article.add(new ArticleVendu( //add article with similar names into list
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10)
						)) ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return article;
	}
	
	
/****************************************************************************************/
		/*BEGIN METHOD INSERT */
/****************************************************************************************/
	
	@Override
	public void insert(ArticleVendu article) throws BusinessException {
		//verify is article is null
		//verify if article already belongs to database
		if(article==null)
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
				pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				
				//TODO verification start with 1 or 2
				//database auto increment but in insert_article statement starts with pseudo
				pstmt.setString(1, article.getNomArticle()) ;
				pstmt.setString(2, article.getDescription()) ;
				pstmt.setInt(3, article.getDateDebutEncheres()) ;
				pstmt.setInt(4, article.getDateFinEncheres()) ;
				pstmt.setInt(5, article.getMiseAPrix()) ;
				pstmt.setInt(6, article.getPrixVente()) ;
				pstmt.setString(7, article.getEtatVente()) ;
				pstmt.setInt(8, article.getUtilisateur()) ;
				pstmt.setInt(9, article.getCategorie()) ;
				
				pstmt.executeUpdate();
				
//				//TODO check if necessary
//				rs = pstmt.getGeneratedKeys();
//				if(rs.next())
//				{
//					article.setNumUtilisateur(rs.getInt(1));
//				}
//				rs.close();
//				pstmt.close();
//				cnx.commit();
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

/****************************************************************************************/
		/*BEGIN METHOD DELETE */
/****************************************************************************************/
	
	@Override
	public void delete(ArticleVendu article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			//verify if starts with 1, arg in DELETE_USER or 2 rank in database
			pstmt.setInt(1, article.getNoArticle());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}
	}

	
/****************************************************************************************/
		/*BEGIN METHOD UPDATE */
/****************************************************************************************/
	
	@Override
	public void update(ArticleVendu article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				PreparedStatement pstmt;
				//must check if num article indeed exists
				//which error when it is'nt the case ?
				pstmt = cnx.prepareStatement(UPDATE_ARTICLE, article.getNoArticle());
				//veri if rank == rank in database or rank in string statement
				pstmt.setString(1,article.getNomArticle()) ;
				pstmt.setString(2, article.getDescription()) ;
				pstmt.setInt(3, article.getDateDebutEncheres()) ;
				pstmt.setInt(4, article.getDateFinEncheres()) ;
				pstmt.setInt(5, article.getMiseAPrix()) ;
				pstmt.setInt(6, article.getPrixVente()) ;
				pstmt.setString(7, article.getEtatVente()) ;
				pstmt.executeUpdate() ;
				pstmt.close() ;
				
				cnx.commit() ;
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



}
