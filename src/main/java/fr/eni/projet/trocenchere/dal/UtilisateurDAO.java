package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insert(Utilisateur user) throws BusinessException ;
	public void delete(String pseudo) throws BusinessException ;
	
	public void update(Utilisateur user) throws BusinessException ;
	//later add update status in case a user wants to deactivate profile
	
	
	public List<Utilisateur> selectAll() throws BusinessException ;
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException ;
	public Utilisateur selectById(int i) throws BusinessException ;
	public int selectEmail(String nomMail) throws BusinessException;
	int selectPassword(String motDePasse) throws BusinessException;
	int selectPseudo(String pseudo) throws BusinessException;
	
	
	//create possibly a cocher and decocher style functions to allow suppression as in tp 6
	


}