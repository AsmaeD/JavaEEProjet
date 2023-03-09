package fr.eni.projet.trocenchere.dal;

import java.util.List;

import fr.eni.projet.trocenchere.bll.BusinessException;
import fr.eni.projet.trocenchere.bo.Enchere;

public interface EnchereDAO {
	
	public void insert(Enchere enchere) throws BusinessException;
	public void update(Enchere enchere) throws BusinessException;
	public void delete(Enchere enchere) throws BusinessException;
	public List<Enchere> selectByArticle(Enchere enchere) throws BusinessException;
}
