package fr.eni.projet.trocenchere.bo;

public class Retrait {

	private String rue;
	private String code_postal;
	private String ville;
	
<<<<<<< HEAD
	private int articleVendu;
=======
	private ArticleVendu articleVendu;
<<<<<<< HEAD
	
	// Constructeur avec tous les attributs

	public Retrait(String rue, String code_postal, String ville) {
=======
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04

	public Retrait(
			String rue, 
			String code_postal, 
<<<<<<< HEAD
			String ville,
			int article) {
=======
			String ville) {
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
		
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.articleVendu = article ;
	}

	public int getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(int articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Retrait() {
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Retrait "
				+ "[rue=" + rue 
=======
<<<<<<< HEAD
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", articleVendu="
				+ articleVendu + "]";
=======
	@Override
	public String toString() {
		return "Retrait [rue=" + rue 
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
				+ ", code_postal=" + code_postal 
				+ ", ville=" + ville
				+ ", articleVendu=" + articleVendu 
				+ "]";
<<<<<<< HEAD
=======
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
>>>>>>> df708d518552591eb2c74cddcceeed2abd011d04
	}
	
}
