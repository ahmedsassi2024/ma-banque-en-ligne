import java.time.LocalDate;

public class Epargne extends Compte{
	private double decouvert;
	public Epargne(int idTitulaire){
		super( idTitulaire);		
	}

	@Override
	void situationCompte() {
		System.out.println("solde compte epargne "+this.solde);
	}
	void retrait(double montant){
		if (solde >= montant) {
			solde -= montant;
			System.out.println("retrait effectué avec succes");
		}
		else {
			if (solde + decouvert >= montant) {
				solde -= montant*1.1;
				System.out.println("retrait effectué\nvous etes en decouvert avec des frais de 10%");
			}
			else System.out.println("solde insuffisant : retrait impossible");
		}
	}

}
