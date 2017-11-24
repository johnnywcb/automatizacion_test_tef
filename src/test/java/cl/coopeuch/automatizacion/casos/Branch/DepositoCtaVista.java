package cl.coopeuch.automatizacion.casos.Branch;

import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.data.SetDatos;
import cl.moveapps.util.report.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by jcortes on 22-11-2017.
 */

@RunWith(value = Parameterized.class)

public class DepositoCtaVista extends CoopeuchAutomatedWebTest {
    @Parameterized.Parameter(value = 0)
    public String user;
    @Parameterized.Parameter(value = 1)
    public String pass;
    @Parameterized.Parameter(value = 2)
    public String trans;
    @Parameterized.Parameter(value = 3)
    public String cuenta;
    @Parameterized.Parameter(value = 4)
    public String monto;
    @Parameterized.Parameter(value = 5)
    public String deposito;
    @Parameterized.Parameter(value = 6)
    public String total;

    @Parameterized.Parameters
    public static Object[] data() {
        return SetDatos.getInstance().getData(DepositoCtaVista.class);
    }

    @Test
    public void testDepositoCtaVista() throws Exception {
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        DepositoCtaVista();
    }

    public void DepositoCtaVista() throws Exception {
        baseUrl = "http://brs1coreqa-01.coopeuch1.cl";
        driver.get(baseUrl + "/IBSBranchnet_COOPEUCH_Q5A/WebTeller.htm");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frmbody");
        driver.findElement(By.id("TxbTELLUSERID")).clear();
        driver.findElement(By.id("TxbTELLUSERID")).sendKeys("CERONDI01");
        driver.findElement(By.id("TxbUSERPASSW")).clear();
        driver.findElement(By.id("TxbUSERPASSW")).sendKeys("falcon#26");
        driver.findElement(By.id("BtnSubmit")).click();
        screenshot.take (this, driver, "DepositoCtaVista");

        //Generación de reporte de evidencias
        Report.getInstance ().createReport (this, screenshot.getImages (),user);
    }
}
