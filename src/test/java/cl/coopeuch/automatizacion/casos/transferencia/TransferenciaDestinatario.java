package cl.coopeuch.automatizacion.casos.transferencia;

import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.data.SetDatos;
import cl.moveapps.util.report.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


/**
 * Created by jcortes on 21-11-2017.
 */

@RunWith(value = Parameterized.class)

public class TransferenciaDestinatario extends CoopeuchAutomatedWebTest {

    @Parameterized.Parameter(value = 0)
    public String rut;
    @Parameterized.Parameter(value = 1)
    public String pass;
    @Parameterized.Parameter(value = 2)
    public String rutDest;
    @Parameterized.Parameter(value = 3)
    public String bancoDest;
    @Parameterized.Parameter(value = 4)
    public String cuentaDest;
    @Parameterized.Parameter(value = 5)
    public String correoDest;
    @Parameterized.Parameter(value = 6)
    public String msjDest;
    @Parameterized.Parameter(value = 7)
    public String c1;
    @Parameterized.Parameter(value = 8)
    public String c2;
    @Parameterized.Parameter(value = 9)
    public String c3;

    @Parameterized.Parameters
    public static Object[] data() {
        return SetDatos.getInstance().getData(TransferenciaDestinatario.class);
    }

    @Test
    public void testTransferenciaDestinatario() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);  //Bug de Chrome - es necesario maximizar por opciones de Chrome.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        TransferenciaDestinatario();
    }

    public void TransferenciaDestinatario() throws Exception {
        baseUrl = "http://10.21.61.94";
        driver.get(baseUrl + "/tef/#/");
        driver.findElement(By.id("rut")).clear();
        driver.findElement(By.id("rut")).sendKeys(rut);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(pass);
        screenshot.take (this, driver, "TrasfDestinatario");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        Thread.sleep(3000);
        screenshot.take (this, driver, "TrasfDestinatario");
        //driver.findElement(By.xpath("(//a[@id='sidebarMenuItem']/div)[3]")).click();
        driver.findElement(By.xpath("//a[@id='sidebarMenuItem']/div[text()[contains(.,'Transferencias')]]")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div/div[2]/table/tbody/tr[2]/td/table//*[text()[contains(.,'Cuenta Vista')]]")).click();
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[2]/div/div[2]/button[2]")).click();
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/div/div[2]/button")).click();
        Thread.sleep(3000);
        screenshot.take (this, driver, "TrasfDestinatario");

        // Ingreso de Datos del Destinatario
        driver.findElement(By.id("inputRut")).clear();
        driver.findElement(By.id("inputRut")).sendKeys(rutDest);
        driver.findElement(By.id("inputBanco")).clear();
        driver.findElement(By.id("inputBanco")).sendKeys(bancoDest);
        driver.findElement(By.linkText(bancoDest)).click();
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
        driver.findElement(By.linkText("CUENTA VISTA")).click();
        driver.findElement(By.id("inputNumCuenta")).clear();
        driver.findElement(By.id("inputNumCuenta")).sendKeys(cuentaDest);
        driver.findElement(By.id("inputEmail")).clear();
        driver.findElement(By.id("inputEmail")).sendKeys(correoDest);
        driver.findElement(By.id("inputDestinatario")).clear();
        driver.findElement(By.id("inputDestinatario")).sendKeys(msjDest);

        // Ingreso de Contraseña
        driver.findElement(By.xpath("//input[@type='password']")).clear();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pass);
        driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
        screenshot.take (this, driver, "TrasfDestinatario");

        // Validación de Desafío
        driver.findElement(By.id("0")).clear();
        driver.findElement(By.id("0")).sendKeys(c1);
        driver.findElement(By.id("1")).clear();
        driver.findElement(By.id("1")).sendKeys(c2);
        driver.findElement(By.id("2")).clear();
        driver.findElement(By.id("2")).sendKeys(c3);
        screenshot.take (this, driver, "TrasfDestinatario");
        driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
        Thread.sleep(3000);
        screenshot.take (this, driver, "TrasfDestinatario");

        driver.findElement(By.linkText("Salir")).click();

        //Generación de reporte de evidencias
        Report.getInstance ().createReport (this, screenshot.getImages (),rut);
    }

}
