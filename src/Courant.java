
public class Courant extends Compte {
	public double decouvert;

	public Courant(int idTitulaire) {
		super(idTitulaire);
		this.decouvert = 0;
	}
	@Override
	void situationCompte() {
		if (solde>0) System.out.println("solde est "+ solde+"\nvotre compte est crediteur");
		else System.out.println("solde est "+ solde+"\nvotre compte est debiteur");
	}
	 void operationCrateBancaire(double montant) {
		if (solde >= montant){
			solde -= montant;
			System.out.println("paiement avec carte Bancaire réalisé");
		}
		else {
			if (solde + decouvert >= montant) {
				solde -= montant*1.1;
				System.out.println("paiement avec carte Bancaire réalisé\nvous etes en decouvert avec des frais de 10%");
			}
			else System.out.println("solde insuffisant");
		}
	}
	void retrait(double montant){
		if (solde >= montant) {
			solde -= montant;
			System.out.println("vous avez retire "+montant+"\nretrait effectué avec succes");
		}
		else {
			if (solde + decouvert >= montant) {
				solde -= montant*1.1;
				System.out.println("vous avez retire "+montant+"\nretrait effectué\nvous etes en decouvert avec des frais de 10%");
			}
			else System.out.println("solde insuffisant : retrait impossible");
		}
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public double getDecouvert() {
		return decouvert;
	}
}