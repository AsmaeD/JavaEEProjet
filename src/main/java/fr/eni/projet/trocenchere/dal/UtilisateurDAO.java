package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insert(Utilisateur user) throws BusinessException ;
	public void delete(Utilisateur user) throws BusinessException ;
	public List<Utilisateur> selectAll() throws BusinessException ;
	public Utilisateur selectById(int number) throws BusinessException ;
	


}
