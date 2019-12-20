package projet_selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class testWaitImplicite {
	WebDriver driver;
	
	@Before
	public void prerequis() {
		driver = OutilsTechniques.choisirNavigateur(ENavigateur.chrome);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		//Se rendre sur le site
		driver.get("http://newtours.demoaut.com/");
		//S'identifier
		OutilsTechniques.remplirChamp(driver.findElement(By.name("userName")), "mercury");
		OutilsTechniques.remplirChamp(driver.findElement(By.name("password")), "mercury");		
		driver.findElement(By.name("login")).click();
		driver.findElement(By.xpath("//a[.='SIGN-OFF']")).isDisplayed();
	}
	
	
	@After
	public void quitter() {
		driver.quit();
	}
}
