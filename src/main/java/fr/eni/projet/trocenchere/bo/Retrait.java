package fr.eni.projet.trocenchere.bo;

public class Retrait {

		//TODO Check if attribut id exists
	private String rue;
	private String code_postal;
	private String ville;
	private int articleVendu;

	
	// Constructeur avec tous les attributs

	public Retrait(
			String rue, 
			String code_postal, 
			String ville,
			int article) {
		
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.articleVendu = article ;
	}

	//constructeur sans attribut id

	public Retrait() {

	} ;

	public int getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(int articleVendu) {
		this.articleVendu = articleVendu;
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
	//TODO ADD TOString
	//			+ ", code_postal=" + code_postal 
	//			+ ", ville=" + ville
	//			+ ", articleVendu=" + articleVendu 
	//			+ "]";

	
}
