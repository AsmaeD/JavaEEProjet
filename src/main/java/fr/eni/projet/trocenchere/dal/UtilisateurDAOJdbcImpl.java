package fr.eni.projet.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.gestionlistescourses.bo.ListeCourse;
import fr.eni.javaee.gestionlistescourses.dal.CodesResultatDAL;
import fr.eni.javaee.gestionlistescourses.dal.ConnectionProvider;
import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	//declaration of all constants of sql requests
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS" ;
	private static final String SELECT_BY_ID = ("SELECT " + "no_utilisateur, " 
												+ "pseudo, " 
												+ "nom, " 
												+ "prenom, "
												+ "email, " 
												+ "telephone, " 
												+ "rue, "
												+ "code_postal, "
												+ "ville, " 
												+ "mot_de_passe, "
												+ "credit, "
												+ "administrateur "
												+ "FROM UTILISATEURS ;") ;
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS("
			+ "numUtilisateur, "
			+ "pseudo, "
			+ "nom, "
			+ "prenom, "
			+ "email, "
			+ "telephone, "
			+ "rue, "
			+ "codePostal, "
			+ "ville, "
			+ "motDePasse, "
			+ "credit, "
			+ "administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);" ;
	
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE id = ? ;" ;
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET "
			+ "pseudo = ?, "
			+ "nom = ?, "
			+ "prenom = ?, "
			+ "email = ?, "
			+ "telephone = ?, "
			+ "rue = ?,"
			+ "code_postale = ?, "
			+ "ville = ?, " 
			+ "mot_de_passe = ?, "
			+ "credit = ?, "
			+ "administrateur = ? "
			+ " where no_utilisateur = ? ;" ;
	
	@Override
	public void delete(int number) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}
		
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>() ;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeUtilisateurs.add(new Utilisateur(
						rs.getInt("no_utilisateur"), 
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postale"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur")
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
		return listeUtilisateurs;
	}

	@Override
	public Utilisateur selectById(int number) throws BusinessException {
		return null;
	}

	@Override
	public void insert(Utilisateur user) throws BusinessException {
		
	}

	@Override
	public void update(Utilisateur user) throws BusinessException {
		
	}



	

}
