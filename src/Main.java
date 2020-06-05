import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//compte connecté avec la base Mysql
public class Main {

	public static void main(String[] args) throws Exception {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu/M/d");

		Connexion connexion = new Connexion("banque", "root", "");

		/*
		 * Agent a = new Agent("manon", "proust",
		 * LocalDate.parse("05/05/1973",f)); connexion.setData(
		 * "INSERT INTO agent(nom, prenom,userName,passWord) VALUES " +
		 * "('"+a.getNom()+"','"+a.getPrenom()+"','"+a.getUserName()+
		 * "','admin')");
		 * 
		 * Agent a1 = new Agent("lionel", "rechotte",
		 * LocalDate.parse("20/07/1969",f)); connexion.setData(
		 * "INSERT INTO agent( nom, prenom,userName,passWord) VALUES " +
		 * "('"+a1.getNom()+"','"+a1.getPrenom()+"','"+a1.getUserName()+
		 * "','admin')");
		 * 
		 * Agent a2 = new Agent("sahbi", "laherrt"); connexion.setData(
		 * "INSERT INTO agent(nom, prenom,userName,passWord) VALUES " +
		 * "('"+a2.getNom()+"','"+a2.getPrenom()+"','"+a2.getUserName()+
		 * "','admin')");
		 */

		// connexion.setData("INSERT INTO `titulaire`(`nom`, `prenom`,
		// `date_naissance`) VALUES ('ahrgfgvj','zfrgsdf','2012/05/13')");
		// connexion.getData("SELECT * FROM agent ");

		// +a.getNom()+","+a.getPrenom()+","+a.getUserName()+","+a.getDateNaissance()
		Scanner clavier = new Scanner(System.in);
		System.out.println("1   Ttitulaire ?\n2   Agent  ?");

		Object connecte;
		int quitter = 2;
		boolean test;
		int user = clavier.nextInt();
		clavier.nextLine();
		String userName;
		switch (user) {
		
		case 1: { // connexion Ttitulaire
			do {
				System.out.println("saisir userName titulaire");
				 userName = clavier.nextLine();
				System.out.println("saisir pass titulaire");
				String passWord = clavier.nextLine();
				connecte = connexion.connecterTitulaire(userName, passWord);
				if (connecte == null) {
					System.out.println("erreur d'identification\n0 : quitter\n1 : ressayer");
					quitter = clavier.nextInt();
					clavier.nextLine();
				}
			} while (connecte == null && (quitter != 0));
			if (connecte != null) {
				switch (connexion.getTypeCompte()) {
				case "courant": {															// courant
					Courant courant = (Courant) connecte;
					System.out.println(courant.idCompte);
					System.out.println("connecté à votre compte courant");
					courant.situationCompte();
					System.out.println("quelle operation voulez vous effectuer ?");
					System.out.println("1 : operation avec carte bancaire ");
					System.out.println("2 : depot d'espece ");
					System.out.println("3 : retrait d'espece ");
					System.out.println("4 : virement ");
					System.out.println("5 : Consulter extrait de mon compte ");
					System.out.println("0 : Quitter ");
					int choix = clavier.nextInt();
					do{
					switch (choix) {
					case 1: {// operation avec carte bancaire
						System.out.println("donner le montant de votre achat avec carte B");
						double valeur = clavier.nextDouble();
						courant.operationCrateBancaire(valeur);
						connexion.setData(" update compte set solde =   '" + courant.solde + "'  where ( id = '" + courant.idCompte + "' )");
						courant.situationCompte();
						
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + courant.idCompte + "','"+userName+"','carte Bancaire','"+valeur+"')");
						
						break;}
					case 2: {// depot d'espece
						System.out.println("donner le montant à deposer");
						double valeur = clavier.nextDouble();
						courant.depot(valeur);
						connexion.setData(" update compte set solde =   '" + courant.solde + "'  where ( id = '" + courant.idCompte + "' )");
						courant.situationCompte();
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + courant.idCompte + "','"+userName+"','depot','"+valeur+"')");
						break;}
					case 3: {// retrait d'espece
						System.out.println("donner le montant à retirer");
						double valeur = clavier.nextDouble();
						courant.retrait(valeur);
						connexion.setData(" update compte set solde =   '" + courant.solde + "'  where ( id = '" + courant.idCompte + "' )");
						courant.situationCompte();
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + courant.idCompte + "','"+userName+"','retrait','"+valeur+"')");
						break;}
					case 4: {// virement
						System.out.println("donner le montant à virer");
						double montant = clavier.nextDouble();
						if (courant.virement(montant)) {
							connexion.setData(" update compte set solde =   '" + courant.solde + "'  where ( id = '" + courant.idCompte + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + courant.idCompte + "','"+userName+"','virement sortant','"+(-montant)+"')");							
							System.out.println("donner ID du benificaire");
							double id = clavier.nextInt();
							connexion.setData(" update compte set solde =  solde + '" + montant + "'  where ( id = '" + id + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + id + "','"+userName+"','virement reçu','"+montant+"')");							
							System.out.println("virement realise avec succes");
						}
						else {
							System.out.println("solde insuffisant, virement impossible");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +courant.idCompte + "','"+userName+"','echec de virement ','"+montant+"')");	
						}
						
				
						courant.situationCompte();
						break;
					}
					case 5: {// Extrait de compte
						
						//System.out.println("insérer votre ID");
					//	int id = clavier.nextInt();
						System.out.println("les operations realisé sur votre compte");
						
						connexion.historique(courant.idCompte);
					

break;
					}
					
					
					}
					System.out.println("\nquelle operation voulez vous effectuer ?");
					System.out.println("1 : operation avec carte bancaire ");
					System.out.println("2 : depot d'espece ");
					System.out.println("3 : retrait d'espece ");
					System.out.println("4 : virement ");
					System.out.println("5 : Consulter extrait de mon compte ");
					System.out.println("0 : Quitter ");
					choix = clavier.nextInt();
					}while (choix != 0);
						System.out.println(" A bientot chez nous !!");
					break;
				}
				case "epargne": {															 // epargne
					Epargne epargne = (Epargne) connecte;
					System.out.println("connecte à votre compte epargne");
			//		System.out.println(epargne.idCompte);
					epargne.situationCompte();
					System.out.println("quelle operation voulez vous effectuer ?");
					System.out.println("1 : depot d'espece ");
					System.out.println("2 : retrait d'espece ");
					System.out.println("3 : virement ");
					System.out.println("4 : Consulter extrait de mon compte ");
					int choix = clavier.nextInt();
					do{
					switch (choix) {
					case 1: {
						System.out.println("donner le montant à deposer");
						double valeur = clavier.nextDouble();
						epargne.depot(valeur);
						connexion.setData(" update compte set solde =   '" + epargne.solde + "'  where ( id = '" + epargne.idCompte + "' )");
						epargne.situationCompte();
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + epargne.idCompte + "','"+userName+"','depot','"+valeur+"')");	
						break;}
					case 2: {
						System.out.println("donner le montant à retirer");
						double valeur = clavier.nextDouble();
						epargne.retrait(valeur);
						connexion.setData(" update compte set solde =   '" + epargne.solde + "'  where ( id = '" + epargne.idCompte + "' )");
						epargne.situationCompte();
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + epargne.idCompte + "','"+userName+"','retrait','"+valeur+"')");	
						epargne.situationCompte();
						break;}
					case 3: {
					System.out.println("donner le montant à virer");
					double montant = clavier.nextDouble();
					if (epargne.virement(montant)) {
						connexion.setData(" update compte set solde =   '" + epargne.solde + "'  where ( id = '" + epargne.idCompte + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + epargne.idCompte + "','"+userName+"','virement sortant','"+(-montant)+"')");						
						System.out.println("donner ID du benificaire");
						int id = clavier.nextInt();
						connexion.setData(" update compte set solde =  solde + '" + montant + "'  where ( id = '" + id + "' )");

connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +id + "','"+userName+"','virement reçu','"+montant+"')");
						System.out.println("virement realise avec succes");
					}
					else {
						System.out.println("solde insuffisant, virement impossible");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +epargne.idCompte + "','"+userName+"','echec de virement ','"+montant+"')");						
					}
					epargne.situationCompte();
					break;}
					case 4: {
						System.out.println("les operations realisé sur votre compte");
						
						connexion.historique(epargne.idCompte);
						break;}
					
					}	
					System.out.println("quelle operation voulez vous effectuer ?");
					System.out.println("1 : depot d'espece ");
					System.out.println("2 : retrait d'espece ");
					System.out.println("3 : virement ");
					System.out.println("4 : Consulter extrait de mon compte ");
					System.out.println("0 : Quitter ");
					 choix = clavier.nextInt();
					}while (choix != 0);
					System.out.println(" A bientot chez nous !!");
					break;}
				case "joint": {														 // joint
					System.out.println("connecte à votre compte joint ");
					Joint joint = (Joint) connecte;
					joint.situationCompte();
					System.out.println("quelle operation voulez vous effectuer ?");
					System.out.println("1 : operation avec carte bancaire ");
					System.out.println("2 : depot d'espece ");
					System.out.println("3 : retrait d'espece ");
					System.out.println("4 : virement ");
					System.out.println("5 : Consulter extrait de mon compte ");
					System.out.println("0 : Quitter ");
					int choix = clavier.nextInt();
					do{
					switch (choix) {
					case 1: {
						System.out.println("donner le montant de votre achat avec carte B");
						double valeur = clavier.nextDouble();
						joint.operationCrateBancaire(valeur);
						connexion.setData(" update compte set solde =   '" + joint.solde + "'  where ( id = '" + joint.idCompte + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +joint.idCompte + "','"+userName+"','Operation CB ','"+valeur+"')");	
						joint.situationCompte();
						break;}
					case 2: {
						System.out.println("donner le montant à deposer");
						double valeur = clavier.nextDouble();
						joint.depot(valeur);
						connexion.setData(" update compte set solde =   '" + joint.solde + "'  where ( id = '" + joint.idCompte + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +joint.idCompte + "','"+userName+"','depot','"+valeur+"')");	
						joint.situationCompte();
						break;}
					case 3: {
						System.out.println("donner le montant à retirer");
						double valeur = clavier.nextDouble();
						joint.retrait(valeur);
						connexion.setData(" update compte set solde =   '" + joint.solde + "'  where ( id = '" + joint.idCompte + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +joint.idCompte + "','"+userName+"','retrait ','"+valeur+"')");	
						joint.situationCompte();
						break;}
					case 4: {
						System.out.println("donner le montant à virer");
						double montant = clavier.nextDouble();
						if (joint.virement(montant)) {
							connexion.setData(" update compte set solde =   '" + joint.solde + "'  where ( id = '" + joint.idCompte + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" + joint.idCompte + "','"+userName+"','virement sortant','"+(-montant)+"')");
							System.out.println("donner ID du benificaire");
							double id = clavier.nextInt();
							connexion.setData(" update compte set solde =  solde + '" + montant + "'  where ( id = '" + id + "' )");
	connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +id + "','"+userName+"','virement reçu','"+montant+"')");
							System.out.println("virement realise avec succes");
						}
						else {
							System.out.println("solde insuffisant, virement impossible");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +joint.idCompte + "','"+userName+"','echec de virement ','"+montant+"')");	
						}
						joint.situationCompte();
						break;}
					case 5: {
						System.out.println("les operations realisé sur votre compte");
						
						connexion.historique(joint.idCompte);
						break;}
					}
					System.out.println("quelle operation voulez vous effectuer ?");
					System.out.println("1 : operation avec carte bancaire ");
					System.out.println("2 : depot d'espece ");
					System.out.println("3 : retrait d'espece ");
					System.out.println("4 : virement ");
					System.out.println("5 : situation de compte ");
					System.out.println("0 : Quitter");
					 choix = clavier.nextInt();
					}while (choix != 0);	
				}
					break;}}
				break;}
		case 2: {																 // Agent
			String a,b;
			do {
				System.out.println("saisir userName agent");
				a = clavier.nextLine();
				System.out.println("saisir pass agent");
				 b = clavier.nextLine();
				if (connexion.connecterAgent(a, b) == 0) {
					System.out.println("erreur d'identification");
					System.out.println("0 : quitter");
					System.out.println("1 : ressayer");
					quitter = clavier.nextInt();
					clavier.nextLine();
				}
			} while ((connexion.connecterAgent(a, b) == 0) && (quitter != 0));
			do{
			if (connexion.connecterAgent(a, b)!=0) { 
				System.out.println("quelle operation voulez vous effectuer ?");
				System.out.println("1 : crer nouveau compte");
				System.out.println("2 : autres operations");
				int choix = clavier.nextInt();
				clavier.nextLine();
				if (choix == 1) { // nouveau titulaire
					System.out.println("saisir prenom");
					String prenom = clavier.nextLine();
					System.out.println("saisir nom");
					String nom = clavier.nextLine();
					System.out.println("saisir date naissance sous format uuuu/MM/dd");
					String date = clavier.nextLine();
					Titulaire t = new Titulaire(prenom, nom, LocalDate.parse(date, f));
					connexion.setData("INSERT INTO titulaire(nom, prenom,date_naissance) VALUES ('" + t.getNom() + "','"
							+ t.getPrenom() + "','" + t.getDateNaissance() + "')");
					connexion.getId();
					System.out.println("quel type de compte ?");
					System.out.println("1 : compte courant");
					System.out.println("2 : compte epargne");
					System.out.println("3 : compte joint");
					int choixTypeCompte = 0;
					choixTypeCompte = clavier.nextInt();
					clavier.nextLine();
					switch (choixTypeCompte) {
					case 1: { // new courant
						Courant c = new Courant(connexion.LastId);
						System.out.println("creation courant");
						connexion.setData("INSERT INTO compte(id_Titulaire,userName,passWord,date_Ouverture) VALUES"
								+ " ('" + c.getIdTitulaire() + "','" + t.getUserName() + "','user','" + LocalDate.now()
								+ "')");
						break;
					}
					case 2: { // new eparge
						Epargne e = new Epargne(connexion.LastId);
						connexion.setData(
								"INSERT INTO compte(id_Titulaire,userName,passWord,date_Ouverture,type) VALUES " + "('"
										+ e.getIdTitulaire() + "','" + t.getUserName() + "','user','" + LocalDate.now()
										+ "','epargne')");
						System.out.println("creation epargne");
						break;
					}
					case 3: { // new joint
						int firstJointId = connexion.LastId;
						String firstUserName = t.getUserName();
						System.out.println("saisir prenom");
						prenom = clavier.nextLine();
						System.out.println("saisir nom");
						nom = clavier.nextLine();
						System.out.println("saisir date naissance sous format Annee / Mois / Jour");
						date = clavier.nextLine();
						t = new Titulaire(prenom, nom, LocalDate.parse(date, f));
						connexion.setData("INSERT INTO titulaire(nom, prenom,date_naissance) VALUES ('" + t.getNom()
								+ "','" + t.getPrenom() + "','" + t.getDateNaissance() + "')");
						connexion.getId();
						Joint j = new Joint(firstJointId, connexion.LastId);
						connexion.setData(
								"INSERT INTO compte(id_Titulaire,id_Joint,userName,userNameJoint,passWord,passWordJoint,date_Ouverture,type) VALUES"
										+ " ('" + j.getIdTitulaire() + "','" + j.getIdJoint() + "','" + firstUserName
										+ "','" + t.getUserName() + "','user','user','" + LocalDate.now()
										+ "','joint')");
						System.out.println("creation joint");
						break;
					}
					}
				}
				else if  (choix == 2) { //autres operations
				System.out.println("quelle operation voulez vous effectuer ?");
				System.out.println("1 : depot espece pour un Titulaire");
				System.out.println("2 : Retrait espece pour un Titulaire");
				System.out.println("3 : modification decouvert ");
				System.out.println("0 : se deconnecter");
				int choixoperation = clavier.nextInt();
					switch (choixoperation){
					case 0 : {
						
						break;
					}
					case 1 : {
						System.out.println("veuillez saisir ID du compte pour le depot ");
						int id = clavier.nextInt();
						System.out.println("saisir le montant");
						double montant = clavier.nextDouble();
						connexion.setData(" update compte set solde = solde +  '" + montant + "'  where ( id = '" + id + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +id + "','"+a+"','Depot ','"+montant+"')");						
						break;
					}
					case 2 : {
						System.out.println("veuillez saisir ID du compte ");
						int id = clavier.nextInt();
						System.out.println("saisir le montant");
						double montant = clavier.nextDouble();
						connexion.setData(" update compte set solde = solde -  '" + montant + "'  where ( id = '" + id + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +id + "','"+a+"','Retrait ','"+montant+"')");
						break;
					}
					case 3 : {
						System.out.println("veuillez saisir ID du compte ");
						int id = clavier.nextInt();
						System.out.println("saisir la valeur du decouvert");
						double montant = clavier.nextDouble();
						connexion.setData(" update compte set decouvert = '" + montant + "'  where ( id = '" + id + "' )");
connexion.setData("INSERT INTO historique(id_compte, realisateur,type,valeur) VALUES ('" +id + "','"+a+"','modif decouvert ','"+montant+"')");
						break;
					}
					}
				}
			}
			System.out.println("1 : autres operation ?");
			System.out.println("0 : quitter ");
			
			}while (clavier.nextInt()!=0);
			}
		break;
		}
	}
}