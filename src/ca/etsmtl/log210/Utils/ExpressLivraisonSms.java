package ca.etsmtl.log210.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
/**
 * source pour etablir la requ�te Post Http : http://blog.idleman.fr/snippet-7-java-faire-une-requete-http-en-post-et-en-get/
 * source pour l'envoie de Textos : http://textbelt.com/canada
 */

public class ExpressLivraisonSms {
	private ArrayList<String> keys;
	private String CENTRE_DE_SMS = "http://textbelt.com/canada";
	
	/**
	 * Constructeur du service de sms
	 * qui qui cree les noms des parametres
	 * devant etre indiqu�s pour envoyer un sms
	 * soit : 
	 * 		String number
	 * 		String message 
	 */
	public ExpressLivraisonSms(){
		keys = new ArrayList<String>();
		keys.add("number");
		keys.add("message");
	}
	
	/**
	 * Methode qui permet d'envoyer un sms au client des la creation dun nouveau comtpe
	 * @param numeroTel (le numero de telephone du client)
	 * @param nom Le nom du nouvel utilisateur
	 * @param prenom le prenom du clien
	 * @param addressMailClient (l'adresse courriel du client)
	 * @throws IOException
	 */
	public void envoyerSmsConfirmationInsciptionAuSite(String numeroTel,String nom, String prenom, String addressMailClient ) throws IOException{
		ArrayList<String> values = new ArrayList<String>();
		values.add(numeroTel);

		values.add("Bonjour : "+prenom+" "+nom+", merci d'avoir procede a votre inscription. Desormais,"
					+ " vous pouvez profitez de notre service en toute liberte en vous connectant avec votre adresse mail: "+addressMailClient+" . L'equipe de Express livraison");
		this.post(CENTRE_DE_SMS, this.keys, values);
	}
	
	/**
	 * Methode qui permet d'envoyer un sms au client apres avoir passe une commande
	 * ceci est un genre de recapitulatif
	 * @param numeroTel (le num�ro de telephone du client)
	 * @param numeroCommande (le numero de la commande)
	 * @param i (l'adresse du client)
	 * @throws IOException
	 */
	public void envoyerSmsConfirmationCommande(String numeroTel,String numeroCommande) throws IOException{
		ArrayList<String> values = new ArrayList<String>();
		values.add(numeroTel);
		values.add("Bonjour, votre commande a ete transfere au restaurant concerne."
					+ " Votre numero de commande est : "+numeroCommande);

		this.post(CENTRE_DE_SMS, this.keys, values);
	}
	
	/**
	 * Methode qui permet d'envoyer un sms au client lui indiquant 
	 * que sa commande est en preparation
	 * @param numeroTel (le num�ro de telephone du client)
	 * @throws IOException
	 */
	public void envoyerSmsCommandeEnPreparation(String numeroTel) throws IOException{
		ArrayList<String> values = new ArrayList<String>();
		values.add(numeroTel);
		values.add("Info Express Livraison : Votre commande est en preparation");
		this.post(CENTRE_DE_SMS, this.keys, values);
	}
	
	/**
	 * Methode qui permet d'envoyer un sms au client lui indiquant 
	 * que sa commande est terminee
	 * @param numeroTel  (le num�ro de telephone du client)
	 * @throws IOException
	 */
	public void envoyerSmsCommandeTerminee(String numeroTel) throws IOException{
		ArrayList<String> values = new ArrayList<String>();
		values.add(numeroTel);
		values.add("Info Express Livraison : Votre commande est terminee est sera livree sous peu");
		this.post(CENTRE_DE_SMS, this.keys, values);
	}
	
	
	/**
	 * Methode qui permet d'envoyer un sms au client lui indiquant 
	 * que sa commande est en livraison vers son domicile
	 * @param numeroTel  (le num�ro de telephone du client)
	 * @param adresseDomicile (l'adresse domiciliaire du client)
	 * @throws IOException
	 */
	public void envoyerSmsCommandeEnLivraison(String numeroTel,String adresseDomicile) throws IOException{
		ArrayList<String> values = new ArrayList<String>();
		values.add(numeroTel);
		values.add("Info Express Livraison : Votre commande est en livraison vers : "+adresseDomicile);
		this.post(CENTRE_DE_SMS, this.keys, values);
	}
	
	
	
	/**
	 * Methode qui permet d'envoyer la requete HTTP post vers le service de sms
	 * @param address
	 * @param keys
	 * @param values
	 * @return
	 * @throws IOException
	 */
	public String post(String address,ArrayList<String> keys, ArrayList<String> values) throws IOException{
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		
		try {

		//encodage des param�tres de la requ�te

		String data="";
			for(int i=0;i<keys.size();i++){
				if (i!=0) data += "&";
				data +=URLEncoder.encode(keys.get(i), "UTF-8")
						+"="+URLEncoder.encode(values.get(i), "UTF-8");
			}

		//cr�ation de la connection vers l'adresse url du site internet

		URL url = new URL(address);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		//on envoie la requ�te pour envoyer le texto selon les parametre fourni

		writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(data);
		writer.flush();



		//lecture de la r�ponse

		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String ligne;
		while ((ligne = reader.readLine()) != null) {
			result+=ligne;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{writer.close();}catch(Exception e){}
			try{reader.close();}catch(Exception e){}
		}
		return result;
		}

	
}
