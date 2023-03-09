package fr.eni.projet.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {//TODO ADD FILE TO PROJECT

	
	
/****************************************************************************************/
	/*BEGIN SQL QUERIES */
/****************************************************************************************/

	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS" ;
	
	private static final String SELECT_BY_ID = "SELECT " 
			+ "no_utilisateur, " 
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
			+ "FROM UTILISATEURS "
			+ "WHERE no_utilisateur = ? "
			+ ";" ;
	
	private static final String SELECT_BY_PSEUDO = "SELECT " 
			+ "no_utilisateur, " 
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
			+ "FROM UTILISATEURS "
			+ "WHERE pseudo = ? "
			+ ";" ;
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS("
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
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);" ;
	
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE pseudo = ? ;" ;
	
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET "
			+ "email = ?, "
			+ "telephone = ?, "
			+ "rue = ?,"
			+ "code_postale = ?, "
			+ "ville = ?, " 
			+ "mot_de_passe = ?, "
			+ "credit = ?, "
			+ "administrateur = ? "
			+ "WHERE pseudo = ? ;" ;
	
	private static final String SELECT_EMAIL = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = ?";
	
	private static final String SELECT_PASSWORD = "SELECT COUNT(*) FROM UTILISATEURS WHERE mot_de_passe = ?";
	
	private static final String SELECT_PSEUDO = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?";
	
	
/****************************************************************************************/
	/*BEGIN METHODS SELECT*/
/****************************************************************************************/

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
						rs.getString("ville"),
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
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
		//initialisation
		//might not be a good idea
		Utilisateur user = null ;
		System.out.println("Hello"); //TODO
		
		try {
			
			
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt ;
			pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			//in SELECT_BY_PSEUDO only one variable passes : pseudo
			//set variable to pseudo
			pstmt.setString(1, pseudo ) ;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new Utilisateur(
					rs.getInt("no_utilisateur"), 
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					rs.getString("rue"),
					rs.getString("code_postale"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					rs.getInt("credit"),
					rs.getBoolean("administrateur")
					);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public Utilisateur selectById(int idUser) throws BusinessException {
		//initialisation
		//might not be a good idea
		Utilisateur user = null ;
		try {
			
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt ;
			pstmt = cnx.prepareStatement(SELECT_BY_ID );
			//in SELECT_BY_PSEUDO only one variable passes : pseudo
			//set variable to pseudo
			pstmt.setInt(1, idUser ) ;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new Utilisateur(
					rs.getInt("no_utilisateur"), 
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					rs.getString("rue"),
					rs.getString("code_postale"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					rs.getInt("credit"),
					rs.getBoolean("administrateur")
					);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	public int selectEmail(String email) throws BusinessException {
		int nbLignes = 0;
		try {
			
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt ;
			pstmt = cnx.prepareStatement(SELECT_EMAIL);
		
			pstmt.setString(1, email) ;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				nbLignes = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
		
			throw businessException;
		}
		
		return nbLignes;
	}


/****************************************************************************************/
	/*BEGIN METHOD INSERT */
/****************************************************************************************/
	
	@Override
	public void insert(Utilisateur user) throws BusinessException {
		//verify is user is null
		//verify if user already belongs to database
		if(user==null)
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
				ResultSet rs;
				pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
				
				//TODO verification start with 1 or 2
				//database auto increment but in insert_user statement starts with pseudo
				pstmt.setString(1, user.getPseudo()) ;
				pstmt.setString(2, user.getNom()) ;
				pstmt.setString(3, user.getPrenom()) ;
				pstmt.setString(4, user.getEmail()) ;
				pstmt.setString(5, user.getTelephone()) ;
				pstmt.setString(6, user.getRue()) ;
				pstmt.setString(7, user.getCodePostal()) ;
				pstmt.setString(8, user.getVille()) ;
				pstmt.setString(9, user.getMotDePasse()) ;
				pstmt.setInt(10, user.getCredit()) ;
				pstmt.setBoolean(11, user.getAdministrateur()) ;
				
				
				
				
				pstmt.executeUpdate();
//				//TODO check if necessary
//				rs = pstmt.getGeneratedKeys();
//				if(rs.next())
//				{
//					user.setNumUtilisateur(rs.getInt(1));
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
	
	@Override//check arg 
	public void delete(String pseudo) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			//verify if starts with 1, arg in DELETE_USER or 2 rank in database
			pstmt.setString(1, pseudo);
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
	public void update(Utilisateur user) throws BusinessException {
		//verify is user is null
		//verify is user is in base through pseudo
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				PreparedStatement pstmt;
				//must check if num user indeed exists
				//which error when it is'nt the case ?
				//be careful here check if it is the correct syntaxe : see select id for reference
				pstmt = cnx.prepareStatement(UPDATE_USER, user.getNumUtilisateur());
				//veri if rank == rank in database or rank in string statement
				pstmt.setString(1,user.getEmail()) ;
				pstmt.setString(2, user.getTelephone()) ;
				pstmt.setString(3, user.getRue()) ;
				pstmt.setString(4, user.getCodePostal()) ;
				pstmt.setString(5, user.getVille()) ;
				pstmt.setString(6, user.getMotDePasse()) ;
				pstmt.setInt(7, user.getCredit()) ;
				pstmt.setBoolean(8, user.getAdministrateur()) ;
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
	
	@Override
	public int selectPassword(String motDePasse) throws BusinessException {
		int nbLignes = 0;
		try {
				
			Connection cnx = ConnectionProvider.getConnection();
				
			PreparedStatement pstmt ;
			pstmt = cnx.prepareStatement(SELECT_PASSWORD);
			
			pstmt.setString(1, motDePasse) ;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				nbLignes = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
			
				throw businessException;
			}
			
			return nbLignes;
		}

	
	@Override
	public int selectPseudo(String pseudo) throws BusinessException {
		int nbLignes = 0;
		System.out.println("testdao2");
		try {
			
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt ;
//			pstmt = cnx.prepareStatement(SELECT_PSEUDO);
//			pstmt.setString(1, pseudo) ;
//			ResultSet rs = pstmt.executeQuery();
			pstmt = cnx.prepareStatement("SELECT * FROM UTILISATEURS WHERE pseudo = 'Montesqieu'");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				nbLignes = rs.getInt(1);
				
			System.out.println("testdao1");
			}
			System.out.println("testdao2");
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
		
			throw businessException;
		}
				
		return nbLignes;
	}
}
