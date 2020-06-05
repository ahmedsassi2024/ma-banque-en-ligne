import java.time.LocalDate;

public abstract class Personnes {
	protected String prenom; 
	protected String nom;
	protected LocalDate dateNaissance;
	protected String userName;
	protected String passWord;

	public Personnes(String prenom,String nom,LocalDate dateNaissance){
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
	}
	public Personnes(String prenom,String nom){
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public String toString(){
		return prenom+" "+nom+" né le "+dateNaissance+" bienvenue parmis notre clientele\nvotre username provisoire est "+userName;
	}
	public String getUserName(){
		return userName;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
