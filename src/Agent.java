import java.time.LocalDate;

public class Agent extends Personnes{
	public int idAgent;
	private static int inc;
	public Agent( String prenom,String nom){
		super(prenom, nom);
		idAgent=++inc;
		userName = prenom.substring(0,2)+nom.substring(0,2)+"A";
	}
	
}
