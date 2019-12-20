package projet_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


class OutilsTechniques {

	static WebDriver driver;

	// METHODE POUR INSTANCIER LE DRIVER D'UN NAVIGATEUR
	// Cette méthode retourne une instance de classe (class FirefoxDriver(),...)
	// qui elle-meme implemente l'interface Webdriver()
	// On utilise une �num�ration pour choisir le type de navigateur
	static WebDriver choisirNavigateur (ENavigateur x) {
		switch(x) {
		case firefox :
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
			return driver;
		case chrome :
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			driver = new ChromeDriver(); 
			return driver;
		case ie :
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			return driver;
		default : return null;
		}
	}
	
	//METHODE POUR VIDER PUIS REMPLIR UN CHAMP
	public static void remplirChamp (WebElement e, String s) {
		e.clear();
		e.sendKeys(s);
	}
}
