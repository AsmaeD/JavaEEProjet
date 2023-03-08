package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocenchere.bo.Retrait;

public interface RetraitDAO {
	public void insert(Retrait retrait) ;
	public void delete(Retrait retrait) ;
	public void update(Retrait retrait);
	
	public List<Retrait> selectAll() ;
	public Retrait selectById() ;
}
