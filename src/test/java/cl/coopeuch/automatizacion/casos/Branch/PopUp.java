package cl.coopeuch.automatizacion.casos.Branch;
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



public class PopUp extends CoopeuchAutomatedWebTest{

    @Test
    public void testPopUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        options.addArguments("--window-size]=1366,623");
        driver = new ChromeDriver(options);  //Bug de Chrome - es necesario maximizar por opciones de Chrome.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PopUp();
    }


        baseUrl = "http://localhost";
        driver.get(baseUrl + "/pruebas/pocGestVentana/");
        driver.findElement(By.linkText("pocGestVentana/")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp |  | 30000]]
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.findElement(By.cssSelector("input[type=\"button\"]")).click();

    }

}
