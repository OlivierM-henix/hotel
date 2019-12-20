package projet_selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testWaitExplicite {
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void prerequis() {
		driver = OutilsTechniques.choisirNavigateur(ENavigateur.chrome);
		wait = new WebDriverWait(driver, 1);

	}
	
	@Test
	public void test() {
		//Se rendre sur le site
		driver.get("http://newtours.demoaut.com/");
		//S'identifier
		OutilsTechniques.remplirChamp(driver.findElement(By.name("userName")), "mercury");
		OutilsTechniques.remplirChamp(driver.findElement(By.name("password")), "mercury");		
		driver.findElement(By.name("login")).click();
		
		//Vérifier (et attendre) que le bouton sign-off est bien visible ou cliquable
		assertNotNull(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='SIGN-OFF']")))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[.='SIGN-OFF']"))));
	}
	
	@After
	public void quitter() {
		driver.quit();
	}
}
