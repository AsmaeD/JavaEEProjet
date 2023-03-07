package fr.eni.projet.trocenchere.dal;
import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO{
	//TODO Check all class attributes -> possibly changed
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS" ;
	private static final String SELECT_BY_NOM = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE '%?%'";
	private static final String SELECT_BY_CAT = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie=?";
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
			+ "WHERE  = ? "
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
	
	@Override
	public void insert(ArticleVendu article) {
		
	}

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

	@Override
	public ArticleVendu selectById(int i) throws BusinessException {
		ArticleVendu article = new ArticleVendu() ;
		//TODO ADDING OF USER AND CATEGORIE
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				article = new ArticleVendu(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8)
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
						rs.getString(8)
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
	public List<ArticleVendu> selectByNom(ArticleVendu article) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>() ;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM);
			pstmt.setString(1, article.getNomArticle());
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
						rs.getString(8)
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

	public List<ArticleVendu> selectByCat(ArticleVendu article) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>() ;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_CAT);
			pstmt.setInt(1, article.getNumeroCategorie());
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
						rs.getString(8)
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
