import java.time.LocalDate;

public class Titulaire extends Personnes{
	public int idTitulaire;
	private static int inc;

	public Titulaire( String prenom,String nom,LocalDate dateNaissance){
		super(prenom, nom, dateNaissance);
		idTitulaire=++inc;
		userName = prenom.substring(0,2)+nom.substring(0,2)+"T";
	}



}
