package fr.eni.projet.trocenchere.bo;

public class Utilisateur {

<<<<<<< HEAD
	private int no_utilisateur;
=======
	private int numUtilisateur;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
<<<<<<< HEAD
	private byte administrateur;
	
	// Constructeur avec tous les attributs
	
	public Utilisateur(int no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, byte administrateur) {

		this.no_utilisateur = no_utilisateur;
=======
	private boolean administrateur;

	public Utilisateur(
			int numUtilisateur, 
			String pseudo, 
			String nom, 
			String prenom, 
			String email, 
			String telephone,
			String rue,
			String codePostal, 
			String ville, 
			String motDePasse, 
			int credit, 
			boolean administrateur) {

		this.numUtilisateur = numUtilisateur;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
<<<<<<< HEAD
	// Constructeur sans le numéro d'utilisateur
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, byte administrateur) {
=======
	public Utilisateur(
			String pseudo, 
			String nom, 
			String prenom, 
			String email,
			String telephone, 
			String rue,
			String codePostal,
			String ville, 
			String motDePasse, 
			int credit, 
			boolean administrateur) {
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371

		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public Utilisateur() {
	}

<<<<<<< HEAD
	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
=======
	public int getNumUtilisateur() {
		return numUtilisateur;
	}

	public void setNumUtilisateur(int numUtilisateur) {
		this.numUtilisateur = numUtilisateur;
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

<<<<<<< HEAD
	public byte getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(byte administrateur) {
=======
	public boolean getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Utilisateur [no_utilisateur=" + no_utilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
=======
		return "Utilisateur ["
				+ "numUtilisateur=" + numUtilisateur 
				+ ", pseudo=" + pseudo 
				+ ", nom=" + nom 
				+ ", prenom=" + prenom 
				+ ", email=" + email 
				+ ", telephone=" + telephone 
				+ ", rue=" + rue 
				+ ", codePostal=" + codePostal 
				+ ", ville=" + ville 
				+ ", motDePasse=" + motDePasse 
				+ ", credit=" + credit
				+ ", administrateur=" + administrateur 
				+ "]";
>>>>>>> 47966660e5bf5dd888181761c197c4402738d371
	}
	
}
