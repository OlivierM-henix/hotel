package projet_selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TestJpetstore {
Logger logger = LoggerFactory.getLogger(TestJpetstore.class);
	
	// Déclaration d'une variable de type webdriver (non instanciée)
	WebDriver driver;
	
	@Test
	public void testFirefox() {
		
		driver = OutilsTechniques.choisirNavigateur(ENavigateur.chrome);
		driver.get("http://localhost:8090/jpetstore-1.0.5-env2/shop/signonForm.do");
		
		//S'identifier
		WebElement e = driver.findElement(By.name("username"));
		OutilsTechniques.remplirChamp(e, "j2ee");
		
		e = driver.findElement(By.name("password"));
		OutilsTechniques.remplirChamp(e, "j2ee");
		
		driver.findElement(By.name("update")).click();
		
		assertTrue(driver.findElement(By.name("img_signout")).isEnabled());
		assertEquals("Welcome ABC!",driver.findElement(By.xpath("//font")).getText());
		
		//Cliquer sur la rubrique fish
		driver.findElement(By.xpath("//img[contains(@src,'fish.gif')]")).click();
		
		//Cliquer sur un item de la liste fish
		driver.findElement(By.xpath("//font[text()=\"FI-FW-01\"]")).click();
		
		//Ajouter au panier
		driver.findElement(By.xpath("//a[@href='/jpetstore-1.0.5-env2/shop/addItemToCart.do?workingItemId=EST-4']/*")).click();
		
		//Modifier la quantité de 1 à 2
		e = driver.findElement(By.name("EST-4"));
		OutilsTechniques.remplirChamp(e, "2");
		
		//Rafraichir le panier
		driver.findElement(By.name("update")).click();
		
		//Contrôler
		e = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[7] "));
		assertEquals("$37,00",e.getText());
		
		//Contrôler bis
		String PU = driver.findElement(By.xpath("//tr[2]/td[6]")).getText();
		String PT = driver.findElement(By.xpath("//tr[2]/td[7]")).getText();
			//Transformer $37,00 en 37,00 puis 37.00
		PU = PU.substring(1).replace(",", ".");
		PT = PT.substring(1).replace(",", ".");
		System.out.println(PU+" "+PT);
			//Transformer les String en Double
		double PUtoDouble = Double.parseDouble(PU);
		double PTtoDouble = Double.parseDouble(PT);
		System.out.println(PUtoDouble+" "+PTtoDouble);
		assertEquals("Le prix total n'est pas conforme",PUtoDouble*2,PTtoDouble,0.0);

	}
//	@Test
//	public void testLeMonde() {
//		driver  = OutilsTechniques.choisirNavigateur(ENavigateur.chrome);
//		driver.get("https://www.lemonde.fr");
//		WebElement e = driver.findElement(By.linkText("Se connecter"));
//		e.click();
//		e = driver.findElement(By.id("connection_mail"));
//		e.sendKeys("olivier.monjauze@gmail.com");
//		e = driver.findElement(By.id("connection_password"));
//		e.sendKeys("xxxxxxxx");
//		e = driver.findElement(By.id("connection_save"));
//		e.click();
//		e = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/a/span[2]"));
//		e.click();
//		e = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[3]/div/div/div/div[2]/div[5]/a/div/span[2]"));
//		e.click();
//	}

	@After
	public void quitter() {
		driver.quit();
	}
}