package ca.etsmtl.log210.Utils;

import java.util.HashMap;
import java.util.Map;

import com.voxeo.tropo.Tropo;
//http://www.nowsms.com/nowsms-java-example-send-sms-message


/**
 * Classe permetant d'envoyer des sms sur le numero de telephone des clients
 * Utilisation du service https://www.tropo.com/
 * @author Cesar Jeanroy
 *
 */
public class SmsUtility 
{

	/**
	 * Methode qui envoie un sms
	 * @param content		Le contenu du SMS
	 * @param phoneNumber	Le numero de telephone du client
	 */
	public static void sendTextMessage(String content, String phoneNumber)
	{
		// Not that long
				System.out.println("Sleeping 2 seconds");
				try {
					Thread.sleep(2000);

					// This coude launches your Tropo application. You can configure your tropo application to 
					// do things like sending an SMS, calliing a phone, etc. 
					String token = "02fbfcc29e0936499598766c2f52141236a41f704dfd03b9bfd638ecee5dd45b655841e30572d53488a6c09d";
					Tropo tropo = new Tropo();
					Map<String, String> params = new HashMap<String, String>();
					params.put("message",content);
					params.put("numberToDial", phoneNumber);
					tropo.launchSession(token, params);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
	}

 
}
