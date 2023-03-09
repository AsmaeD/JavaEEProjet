package fr.eni.projet.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)"
			+ "VALUES (?, ?, ?, ?)";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET "
			+ "no_article = ?,"
			+ "date_enchere = ?,"
			+ "montant_enchere = ?"
			+ "WHERE no_utilisateur = ?";
	private static final String DELETE_ENCHERE= "DELETE FROM ENCHERES WHERE no_article = ?";
	private static final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
	
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		
		if(enchere==null)
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
				pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				
				pstmt.setInt(1, enchere.getUtilisateur()) ;
				pstmt.setInt(2, enchere.getArticleVendu()) ;
				pstmt.setInt(3, enchere.getDateEnchere());
				pstmt.setInt(4, enchere.getMontant_enchere());
			
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
	public void update(Enchere enchere) throws BusinessException {
		
		if(enchere==null)
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
				pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				
				pstmt.setInt(1, enchere.getArticleVendu()) ;
				pstmt.setInt(2, enchere.getDateEnchere());
				pstmt.setInt(3, enchere.getMontant_enchere());
				pstmt.setInt(4, enchere.getUtilisateur());
			
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
	public void delete(Enchere enchere) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ENCHERE);
		
			pstmt.setInt(1, enchere.getArticleVendu());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}
		
	}
	
	@Override
	public List<Enchere> selectByArticle(Enchere enchere) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>() ;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
			pstmt.setInt(1, enchere.getArticleVendu());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeEncheres.add(new Enchere(
						rs.getInt(1), 
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4)
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
