package cl.coopeuch.automatizacion.casos.idEnvioEmail;

import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.init.Initializer;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EnvioEmailTest extends CoopeuchAutomatedWebTest {

  @Test
  public void testEnvioEmailExplorer() throws Exception {
    driver = new ChromeDriver();
    //driver = new InternetExplorerDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    testEnvioEmail();
  }


  public void testEnvioEmail() throws Exception {
    //baseUrl = "http://jbosseibs2qa-01:8080";
    baseUrl = "http://10.21.61.94";
    driver.get(baseUrl + "/tef/#/");
    driver.findElement(By.id("rut")).clear();
    driver.findElement(By.id("rut")).sendKeys("175424296");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("coop1234");
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    //driver.findElement(By.cssSelector("a.readlink.ng-isolate-scope > small")).click();
    //WebDriverWait Wait = new WebDriverWait (driver,1800000);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    //driver.findElement(By.cssSelector("a[href='#'][state='root.perfil'][class='readlink ng-isolate-scope']")).click();
    driver.findElement(By.cssSelector("a.readlink.ng-isolate-scope > small")).click();
    driver.findElement(By.linkText("Datos de Contacto")).click();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.findElement(By.id("check2")).click();
    //driver.findElement(By.cssSelector("input[id='check2'][type='checkbox'][name='check'][value='check2'")).click();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.findElement(By.linkText("Salir")).click();
  }

}
