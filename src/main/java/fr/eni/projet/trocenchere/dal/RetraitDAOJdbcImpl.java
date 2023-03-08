package fr.eni.projet.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bo.Enchere;
import fr.eni.projet.trocenchere.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville)"
			+ "VALUES (?, ?, ?, ?)";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET "
			+ "rue = ?,"
			+ "code_postal = ?,"
			+ "ville = ?"
			+ "WHERE no_article = ?";
	private static final String DELETE_RETRAIT= "DELETE FROM RETRAITS WHERE no_article = ?";
	private static final String SELECT_BY_ARTICLE = "SELECT * FROM RETRAITS WHERE no_article = ?";

	@Override
	public void insert(Retrait retrait) throws BusinessException {

		if(retrait==null)
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
				pstmt = cnx.prepareStatement(INSERT_RETRAIT);

				pstmt.setInt(1, retrait.getArticleVendu()) ;
				pstmt.setString(2, retrait.getRue()) ;
				pstmt.setString(3, retrait.getCode_postal());
				pstmt.setString(4, retrait.getVille());

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

	@Override
	public void update(Retrait retrait) throws BusinessException {

		if(retrait==null)
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
				pstmt = cnx.prepareStatement(UPDATE_RETRAIT);

				pstmt.setInt(1, retrait.getArticleVendu()) ;
				pstmt.setString(2, retrait.getRue()) ;
				pstmt.setString(3, retrait.getCode_postal());
				pstmt.setString(4, retrait.getVille());
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

	@Override
	public void delete(Retrait retrait) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);

			pstmt.setInt(1, retrait.getArticleVendu());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}

	}

	@Override
	public List<Retrait> selectByArticle(Retrait retrait) throws BusinessException {
		List<Retrait> listeEncheres = new ArrayList<Retrait>() ;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
			pstmt.setInt(1, retrait.getArticleVendu());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeEncheres.add(new Retrait(
						rs.getString(4), 
						rs.getString(2),
						rs.getString(3),
						rs.getInt(1)
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
		return listeEncheres;
	}
}