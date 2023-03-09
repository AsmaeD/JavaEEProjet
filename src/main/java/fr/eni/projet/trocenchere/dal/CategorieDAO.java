package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.Categorie;

public interface CategorieDAO {
	
	public void insert(Categorie category) throws BusinessException ;
	public void delete(Categorie category) throws BusinessException ;
	public void update(Categorie category) throws BusinessException ;
	
	public List<Categorie> selectAll() throws BusinessException  ;
	public Categorie selectById(int i) throws BusinessException  ;

}
