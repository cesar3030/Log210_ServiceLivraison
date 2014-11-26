package ca.etsmtl.log210.Tests.WhiteBox;

import static com.heliumhq.API.*;

public class TestAddNemMenuPage {
	

	
	public static void AddMenu()
	{
		startChrome();
		goTo("http://localhost:8080/Log210_ServiceLivraison/");
		write("rcesar@gmail.com", "email");
		write("aaaaaa", "password");
		press(ENTER);
		goTo("http://localhost:8080/Log210_ServiceLivraison/ShowAllMenuResto?idRestaurant=1");
		
		click(Button("Ajouter un menu"));
		
		write("menu du test", "Nom du Menu:*");
		write("description test", "Description de Menu:");
		
		click(Button("Ajouter"));
		
		if (Text("L'utilisateur n'est pas connu. Veuillez verifier votre courriel et votre mot de passe").exists())
			System.out.println(" failIidentification Test passed!");
		else
			System.out.println(" failIidentification Test failed :(");
		killBrowser();
		
	}
	public static void main(String[] args) 
	{
		TestAddNemMenuPage test = new TestAddNemMenuPage();
		test.AddMenu();
	}
}
