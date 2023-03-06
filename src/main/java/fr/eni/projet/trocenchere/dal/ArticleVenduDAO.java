package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocenchere.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	public void insert(ArticleVendu article) ;
	public void delete(ArticleVendu article) ;
	
	public ArticleVendu selectById(int i);
	public List<ArticleVendu> selectAll() ;
	
	//function change status only
	//function 

}
