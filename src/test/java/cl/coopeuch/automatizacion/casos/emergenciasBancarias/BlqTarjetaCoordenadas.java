package cl.coopeuch.automatizacion.casos.emergenciasBancarias;

/**
 * Created by jcortes on 21-11-2017.
 */

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

@RunWith(value = Parameterized.class)

public class BlqTarjetaCoordenadas extends CoopeuchAutomatedWebTest{

    @Parameterized.Parameter(value = 0)
    public String rut;
    @Parameterized.Parameter(value = 1)
    public String pass;

    @Parameterized.Parameters
    public static Object[] data() {
        return SetDatos.getInstance().getData(BlqTarjetaCoordenadas.class);
    }

    @Test
    public void testBlqTarjetaCoordenadas() throws Exception {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        options.addArguments("--window-size]=1366,623");
        driver = new ChromeDriver(options);  //Bug de Chrome - es necesario maximizar por opciones de Chrome.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        BlqTarjetaCoordenadas();
    }

    public void BlqTarjetaCoordenadas() throws Exception {
        baseUrl = "http://10.21.61.94";
        driver.get(baseUrl + "/tef/#/");
        driver.findElement(By.id("rut")).clear();
        driver.findElement(By.id("rut")).sendKeys(rut);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(pass);
        screenshot.take (this, driver, "BlqTarjetaCoordenadas");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        Thread.sleep(3000);
        screenshot.take (this, driver, "BlqTarjetaCoordenadas");
        driver.findElement(By.xpath("//a[@id='sidebarMenuItem']/div[text()[contains(.,'Emergencias Bancarias')]]")).click();
        Thread.sleep(3000);
        screenshot.take (this, driver, "BlqTarjetaCoordenadas");
        /*
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div/div/table/tbody/tr/td[5]/button")).click();
        driver.findElement(By.xpath("//input[@type='password']")).clear();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("coop1234");
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
        */
        driver.findElement(By.linkText("Salir")).click();

        //Generación de reporte de evidencias
        Report.getInstance ().createReport (this, screenshot.getImages (),rut);
    }
}
