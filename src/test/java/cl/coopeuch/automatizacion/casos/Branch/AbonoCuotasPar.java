package cl.coopeuch.automatizacion.casos.Branch;

/**
 * Created by vnorambuena on 03-11-2017.
 * Modified  by jcortes on 15-11-2017.
 */

import java.awt.*;
import java.io.File;
import java.sql.DriverAction;
import java.util.*;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import java.*;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import org.openqa.selenium.Keys;
//JAVA

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import org.junit.Assert;


import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.data.SetDatos;
import cl.moveapps.util.init.Initializer;
import cl.moveapps.util.report.Report;
import com.sun.javafx.scene.EnteredExitedHandler;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import static java.awt.Event.ENTER;
import static java.awt.Event.MOUSE_ENTER;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(value = Parameterized.class)
public class AbonoCuotasPar extends CoopeuchAutomatedWebTest {

    @Parameterized.Parameter(value = 0)
    public String usuario;
    @Parameterized.Parameter(value = 1)
    public String pass;
    @Parameterized.Parameter(value = 2)
    public String numtrx;
    @Parameterized.Parameter(value = 3)
    public String cantCuotas;
    @Parameterized.Parameter(value = 4)
    public String efectivo;
    @Parameterized.Parameter(value = 5)
    public String cheque;
    @Parameterized.Parameter(value = 6)
    public String montoTotal;

    @Parameterized.Parameters
    public static Object[] data() {
        return SetDatos.getInstance().getData(AbonoCuotasPar.class);
    }

    @Test
    public void testAbonoCuotasPar  () throws Exception {
        driver = new InternetExplorerDriver ();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        AbonoCuotasPar ();
    }

    public void AbonoCuotasPar() throws Exception {

        baseUrl = "http://10.20.74.84";
        driver.get(baseUrl + "/BSBRANCHNET_COOPEUCH_FUN/WebTeller.htm");


        //-------------------------------------------------------------------------------------------------------------
        // Definimos dos variables para almacenar el momento en que comienza y finaliza la prueba
        long time_start, time_end;

        String web = "http://10.20.74.84/IBSBRANCHNET_COOPEUCH_FUN/WebTeller.htm";
        System.out.println("Entrando a: " + web);

        time_start = System.currentTimeMillis();
        driver.get(web);

        System.out.println("Carga de: " + web + "finalizada");
        //--------------------------------------------------------------------------------------------------------------
        driver.switchTo ().defaultContent ();
        driver.switchTo ().frame ("frmbody");
        // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | frmbody | ]]
        driver.findElement(By.id("TxbTELLUSERID")).clear();
        driver.findElement(By.id("TxbTELLUSERID")).sendKeys(usuario);
        driver.findElement(By.id("TxbUSERPASSW")).clear();
        driver.findElement(By.id("TxbUSERPASSW")).sendKeys(pass);
        driver.findElement(By.id("BtnSubmit")).click();
        driver.switchTo ().defaultContent ();
        driver.switchTo ().frame ("frmwebteller");
        driver.manage().window().maximize();
        driver.findElement(By.id("TxbTrnCode")).clear();
        driver.findElement(By.id("TxbTrnCode")).sendKeys(numtrx);
        driver.findElement(By.id("BntTrnList")).click();
        screenshot.take (this, driver, "0130 Pago Cuotas de Participacion ");
        WebDriverWait Wait = new WebDriverWait (driver,880000);
        driver.switchTo ().defaultContent ();
        driver.switchTo ().frame ("frmbody").switchTo ().frame ("main");
        driver.findElement(By.id("ctl17")).clear();
        driver.findElement(By.id("ctl17")).sendKeys("201403211984");
        driver.findElement(By.id("ctl95")).clear();
        driver.findElement(By.id("ctl95")).sendKeys(cantCuotas);
        driver.findElement(By.id("ctl95")).sendKeys(Keys.ENTER);
        driver.switchTo ().defaultContent ();
        driver.switchTo ().frame ("frmbody").switchTo ().frame ("main");
        //driver.findElement(By.id("ctl95")).sendKeys(Keys.ENTER);
        //driver.switchTo ().defaultContent ();
        ///driver.switchTo ().frame ("frmbody").switchTo ().frame ("main");
        driver.findElement(By.id("ctl107")).clear();
        driver.findElement(By.id("ctl107")).sendKeys(efectivo);

        driver.findElement(By.id("ctl113")).clear();
        driver.findElement(By.id("ctl113")).sendKeys(cheque);
        driver.findElement(By.id("ctl119")).clear();
        driver.findElement(By.id("ctl119")).sendKeys(montoTotal);
        driver.findElement(By.id("ctl119")).isSelected ();
        driver.findElement(By.id("ctl119")).sendKeys(Keys.ENTER);
        screenshot.take (this, driver, "Cuotas Pagadas 1");
        //driver.switchTo ().defaultContent ();
        //driver.switchTo ().frame ("frmbody").switchTo ().frame ("main");
/**        driver.findElement(By.id("ctl119")).isSelected ();
 driver.findElement(By.id("ctl119")).sendKeys(Keys.ENTER);
 driver.findElement(By.id("ctl119")).sendKeys(Keys.ENTER);
 driver.findElement(By.id("ctl119")).sendKeys(Keys.RETURN);
 screenshot.take (this, driver, "Cuotas Pagadas 1");
 driver.switchTo ().defaultContent ();
 driver.switchTo ().frame ("frmbody").switchTo ().frame ("main");**/

        //driver.findElement(By.id("ctl119")).sendKeys(Keys.chord (Keys.CONTROL,"a"));
        //DECLARAR VARIABLE ANTES DE INGRESAR A OTRO POP UP O OTRO WINDOWS Y PODER RETORNAR.
        //String Branch = driver.getWindowHandle();
        driver.findElement(By.id("ctl95")).sendKeys(Keys.ENTER);//gatillante del mensaje trancaccion ok.
        //Wait = new WebDriverWait (driver,880000);
//        driver.switchTo().window("ShowCode");
        //Wait = new WebDriverWait (driver,880000);
        screenshot.take (this, driver, "Mensaje Transaccion OK");
        driver.findElement(By.id("Button1")).click();

        // OTRA FORMA DE ALMACENAR LA ULTIMA VENTANA PRINCIPAL, CUANDO SIGUIENTE VENTANA ESTA NULL
        String parentWindowHandler = driver.getWindowHandle(); // Almacena tu ventana principal
        String subWindowHandler = null;


        //OBTENER VENTANAS ABIERTAS
      /*  Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        Wait.until (ExpectedConditions.alertIsPresent ( ));
        driver.switchTo().alert().accept ();driver.switchTo().alert();

        Wait = new WebDriverWait (driver,880000);

        //RETORNAR A PRINCIPAL
        driver.switchTo().window(parentWindowHandler);
        Wait = new WebDriverWait (driver,880000);
        driver.close ();
*/
        screenshot.take (this, driver, "Cuotas Pagadas");
        driver.switchTo ().defaultContent ();
        driver.switchTo ().frame ("frmbody").switchTo ().frame ("main");
        //REPORTE NO BORRAR.
        Report.getInstance ().createReport (this, screenshot.getImages (),usuario);
    }

}