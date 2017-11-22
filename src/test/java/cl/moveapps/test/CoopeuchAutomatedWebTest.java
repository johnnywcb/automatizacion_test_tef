package cl.moveapps.test;

import cl.moveapps.util.init.Initializer;
import cl.moveapps.util.report.Screenshot;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;

import static org.junit.Assert.fail;

/**
 * Clase CoopeuchAutomatedWebTest.
 * Permite ejecutar cualquier Test Unitario para Selenium de forma automatica
 * Simplifica la construccion del codigo mediante el uso de elementos comunes para Selenium
 * <p>
 * El Usuario solo debe crear su test mediante el volcado del codigo fuente incorporando unicamente
 * el metodo que contiene su definicion de caso de prueba, sin incorporar la anotacion @Test.
 * <p>
 * Solo se debe agregar el metodo que corresponda al navegador a utilizar.
 * <pre>
 * @Test
 * public void testMyTestExplorer() throws Exception {
 * driver = new InternetExplorerDriver();
 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 * testMyTest();
 * }
 *
 * @Test
 * public void testMyTestChrome() throws Exception {
 * driver = new ChromeDriver();
 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 * testMyTest();
 * }
 * </pre>
 * <p>
 * Finalmente el codigo de selenium se debe agregar en el metodo <pre>testMyTest()</pre> como se muestra a continuacion.
 * <p>
 * <pre>
 * public void testFlujoTestEibs() throws Exception {
 * driver.get(baseUrl + "/eibs_coopeuch_web/pages/s/ESS0030_LogIn.jsp");
 * driver.findElement(By.name("UserId")).clear();
 * ...
 * ...
 * }
 * </pre>
 *
 * @author MoveApps SpA. (marcelomagana) on 7/24/17.
 */
public class CoopeuchAutomatedWebTest {
    protected WebDriver driver;
    protected String baseUrl;
    protected boolean acceptNextAlert = true;
    protected StringBuffer verificationErrors = new StringBuffer();
    protected Screenshot screenshot;
    
    @Before
    public void setUp() throws Exception {
        Initializer.getInstance().init();
        screenshot = new Screenshot();
    }
    
    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        screenshot = null;
    }
    
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
    
    
}
