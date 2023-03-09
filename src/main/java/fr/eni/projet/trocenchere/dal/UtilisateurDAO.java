package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insert(Utilisateur user) throws BusinessException ;
	public void delete(String pseudo) throws BusinessException ;
	
	public void update(Utilisateur user) throws BusinessException ;
	//later add update status in case a user wants to deactivate profile
	
	public int selectEmail(String email) throws BusinessException;
	public List<Utilisateur> selectAll() throws BusinessException ;
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException ;
	public int selectPseudo(String pseudo) throws BusinessException;
	
	//create possibly a cocher and decocher style functions to allow suppression as in tp 6
	


}
