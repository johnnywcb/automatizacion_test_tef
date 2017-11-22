package cl.coopeuch.automatizacion.casos.transferencia;

import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.data.SetDatos;
import cl.moveapps.util.report.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by jcortes on 21-11-2017.
 */

@RunWith(value = Parameterized.class)


public class TransferenciaIntCtaVista extends CoopeuchAutomatedWebTest {

  @Parameterized.Parameter(value = 0)
  public String rut;
  @Parameterized.Parameter(value = 1)
  public String pass;
  @Parameterized.Parameter(value = 2)
  public String correoDest;
  @Parameterized.Parameter(value = 3)
  public String mensaje;
  @Parameterized.Parameter(value = 4)
  public String valor;
  @Parameterized.Parameter(value = 5)
  public String c1;
  @Parameterized.Parameter(value = 6)
  public String c2;
  @Parameterized.Parameter(value = 7)
  public String c3;

  @Parameterized.Parameters
  public static Object[] data() {
        return SetDatos.getInstance().getData(TransferenciaIntCtaVista.class);
    }

  @Test
  public void testTransferenciaIntCtaVista() throws Exception {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    driver = new ChromeDriver(options);  //Bug de Chrome - es necesario maximizar por opciones de Chrome.
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    TransferenciaIntCtaVista();
  }

  public void TransferenciaIntCtaVista() throws Exception {
    baseUrl = "http://10.21.61.94";
    driver.get(baseUrl + "/tef/#/");
    driver.findElement(By.id("rut")).clear();
    driver.findElement(By.id("rut")).sendKeys(rut);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(pass);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
    Thread.sleep(3000);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    //driver.findElement(By.xpath("(//a[@id='sidebarMenuItem']/div)[3]")).click();
    driver.findElement(By.xpath(" //a[@id='sidebarMenuItem']/div[text()[contains(.,'Transferencias')]]")).click();
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
    driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div/div[2]/table/tbody/tr[2]/td/table[2]/tbody/tr/td/p")).click();
    driver.manage().timeouts().implicitlyWait(8000, TimeUnit.SECONDS);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[2]/div/div[2]/button[2]")).click();
    Thread.sleep(3000);
    //Selección de Destinatario
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[2]/div[3]/div[2]/div/table/tbody/tr[2]/td/p/i")).click();
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(correoDest);
    driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div/ng-transclude/textarea")).clear();
    driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div/ng-transclude/textarea")).sendKeys(mensaje);
    driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(valor);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[3]/div[3]/div/div/div/div/button")).click();
    driver.manage().timeouts().implicitlyWait(8000, TimeUnit.SECONDS);
    driver.findElement(By.id("0")).clear();
    driver.findElement(By.id("0")).sendKeys(c1);
    driver.findElement(By.id("1")).clear();
    driver.findElement(By.id("1")).sendKeys(c2);
    driver.findElement(By.id("2")).clear();
    driver.findElement(By.id("2")).sendKeys(c3);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    driver.manage().timeouts().implicitlyWait(8000, TimeUnit.SECONDS);
    Thread.sleep(3000);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.linkText("Realizadas")).click();
    Thread.sleep(3000);
    screenshot.take (this, driver, "TrasfIntCtaVista");
    driver.findElement(By.linkText("Salir")).click();

    //Generación de reporte de evidencias
    Report.getInstance ().createReport (this, screenshot.getImages (),rut);
  }
}
