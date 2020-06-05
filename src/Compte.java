import java.time.LocalDate;

public abstract class Compte {
	protected int idCompte;
	private LocalDate dateOuverture;
	protected double solde; 
	private int idTitulaire;
	public static int inc ;
	
	
	public Compte(int idTitulaire){
		idCompte = ++inc;
		dateOuverture = LocalDate.now();
		this.idTitulaire =  idTitulaire;
		this.solde = 0;
	}	

	public LocalDate getDateOuverture() {
		return dateOuverture;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}

	public int getIdTitulaire() {
		return idTitulaire;
	}

	public boolean virement(double montant){
		if (solde >= montant) {
			solde-=montant;
	
			return true;
		}
		else {
			
			return false;
		}
		}
	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	void depot(double montant){
		solde += montant;
		System.out.println("vous avez depose " + montant);
		System.out.println("depot effectée avec succes");
		}
	
	abstract void situationCompte();
}
