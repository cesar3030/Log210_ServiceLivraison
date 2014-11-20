package ca.etsmtl.log210.Tests.WhiteBox;
import static com.heliumhq.API.*;

public class TestsConnectionPage 
{
	public static void main(String[] args) 
	{
		failIidentification();
	}
	
	public static void failIidentification()
	{
		startChrome();
		goTo("http://localhost:8080/Log210_ServiceLivraison/");
		write("failcesar.jeanroy@gmail.com", "email");
		press(ENTER);
		
		if (Text("L'utilisateur n'est pas connu. Veuillez verifier votre courriel et votre mot de passe").exists())
			System.out.println(" failIidentification Test passed!");
		else
			System.out.println(" failIidentification Test failed :(");
		killBrowser();
	}
}
