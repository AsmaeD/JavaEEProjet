package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocenchere.bo.Categorie;

public interface CategorieDAO {
	
	public void insert(Categorie category) ;
	public void delete(Categorie category) ;
	
	public List<Categorie> selectAll() ;
	public Categorie selectById(int i) ;

}
