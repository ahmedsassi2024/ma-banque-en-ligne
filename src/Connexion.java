
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Connexion {
	private Statement statement;
	private Connection connection;
	private ResultSet result;
	public int LastId;

	//private int id_Titulaire, id_Joint;private String userNamec, userNameJoint, passWordc, passWordJoint, type;private LocalDate date_Ouverture;private double solde, decouvert;

	public String nomBase, userName, passWord;
	private String type;

	public Connexion(String nomBase, String userName, String passWord) throws Exception {
		this.nomBase = nomBase;
		this.userName = userName;
		this.passWord = passWord;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection("jdbc:mysql://localhost/" + nomBase, userName, passWord);
		statement = connection.createStatement();
		System.out.println("con ok");
	}
	public void getData(String query) throws Exception {
		result = statement.executeQuery(query);
		while (result.next()) {
			System.out.println(" nom : " + result.getString("nom") + " prenom " + result.getString("prenom"));
		}
	}

	public void historique(int id ) throws Exception {
		result = statement.executeQuery("SELECT realisateur,type, valeur FROM historique where id_compte = '" + id + "'");
		while (result.next()) {
			System.out.println(" realisateur : " + result.getString("realisateur") + " type " + result.getString("type")+ " valeur "+ result.getDouble("valeur"));
		}
	}
	
	public void getId() throws Exception {
		result = statement.executeQuery("SELECT id FROM titulaire ");
		while (result.next()) {

			this.LastId = result.getInt("id");
			// System.out.println( " nom : " + result.getString("nom") + "
			// prenom " + result.getString("prenom"));
		}
	}

	public void setData(String query) throws Exception {
		statement.execute(query);
	}

	public int connecterAgent(String a, String b) throws Exception {
		// System.out.println(query);
		result = statement.executeQuery("SELECT id_agent,nom, prenom FROM agent where(( userName = '" + a + "' )&( "
				+ "passWord = '" + b + "'))");
		while (result.next()) {
		//	System.out.println(" id : " + result.getInt("id_agent") + " nom : " + result.getString("nom") + " prenom "
				//	+ result.getString("prenom"));
			if (result.getInt("id_agent") != 0){ 
				return result.getInt("id_agent");
			}
		}
		return 0;
		
	}

	public Object connecterTitulaire(String userName, String passWord) throws Exception {
		result = statement.executeQuery(
				"SELECT id,id_Titulaire,id_Joint,userName,userNameJoint,passWord,passWordJoint,date_Ouverture,solde,decouvert,type"
						+ " FROM compte where((( userName = '" + userName + "' )&( " + "passWord = '" + passWord
						+ "'))||(( userNameJoint = '" + userName + "' )&( " + "passWordJoint = '" + passWord + "')))");

		if (result.next()) {
			if (result.getString("type").equals("courant")){
				type = "courant";
				Courant courant = new Courant(result.getInt("id_Titulaire"));
				courant.setIdCompte(result.getInt("id"));
				courant.setSolde(result.getDouble("solde"));
				courant.setDecouvert(result.getDouble("decouvert"));
				return courant;
			}
			else if (result.getString("type").equals("epargne")){
				type = "epargne";
				Epargne epargne = new Epargne(result.getInt("id_Titulaire"));
				epargne.setIdCompte(result.getInt("id"));
				epargne.setSolde(result.getDouble("solde"));
				return epargne ; 
			}
			else if (result.getString("type").equals("joint")){
				type = "joint";
				Joint joint = new Joint(result.getInt("id_Titulaire"),result.getInt("id_Joint"));
				joint.setIdCompte(result.getInt("id"));
				joint.setSolde(result.getDouble("solde"));
				joint.setDecouvert(result.getDouble("decouvert"));
				return joint;
			}
			else return null;

		}
		return null;
	}

	public String getTypeCompte() {
		return type;
	}

}