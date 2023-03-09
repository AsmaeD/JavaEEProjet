package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocechere.BusinessException;
import fr.eni.projet.trocenchere.bo.ArticleVendu;
import fr.eni.projet.trocenchere.bo.Categorie;

public interface ArticleVenduDAO {
	
	//TODO ADD DAO generic to implement 
	public void insert(ArticleVendu article) throws BusinessException ;
	public void delete(ArticleVendu article) throws BusinessException ;

	public void update(ArticleVendu article) throws BusinessException ;
	
	public ArticleVendu selectById(int i) throws BusinessException ;
	public List<ArticleVendu> selectAll() throws BusinessException ;
	
	//renvoi liste noms similaires
	public List<ArticleVendu> selectByNom(String nom) throws BusinessException ;
	
	public List<ArticleVendu> selectByCat(int i) throws BusinessException ;
	
}
