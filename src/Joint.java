
public class Joint extends Courant{
	public int idJoint;
	private double decouvert;
	
	public Joint(int idTitulaire,int idJoint) throws JointException{
		super(idTitulaire);
		if (idTitulaire != idJoint) this.idJoint = idJoint;
		else throw new JointException(); 
	}
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public int getIdJoint() {
		return idJoint;
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
	
	@Override
	void situationCompte() {
		if (solde>0) System.out.println("solde est "+ solde+"\nvotre compte est crediteur");
		else System.out.println("solde est "+ solde+"\nvotre compte est debiteur");
	}
	
	void retrait(double montant){
		if (solde >= montant){
			solde -= montant;
			System.out.println("retrait effectué avec succes");
		}
		else {
			if (solde + decouvert >= montant){
				solde -= montant*1.1;
				System.out.println("retrait effectué\nvous etes en decouvert avec des frais de 10%");
			}
			else System.out.println("solde insuffisant");
		}
	}
}
