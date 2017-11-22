package cl.moveapps.util.report;


import cl.moveapps.exception.AppConfigException;
import cl.moveapps.test.CoopeuchAutomatedWebTest;
import cl.moveapps.util.properties.AppConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by marcelomagana on 8/2/17.
 */
public class Screenshot {
    private static final Logger logger = LogManager.getLogger(Screenshot.class);
    private static List<String> images;
    private Properties prop = null;
    private String path = "";
    private String prefix = "";
    private String extension = "";
    private String time = "";
    
    public Screenshot() {
        images = new ArrayList<>();
    }
    
    public void take(CoopeuchAutomatedWebTest clazz, WebDriver driver, String name) {
        prepareVariables();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pathname = path + name + "_" + time + extension;
        try {
            org.apache.commons.io.FileUtils.copyFile(scrFile, new File(pathname));
            images.add(pathname);
            logger.info("ArrayList Size : " + images.size());
        } catch (IOException e) {
            logger.error(e);
        }
    }
    
    private void prepareVariables() {
        try {
            prop = AppConfigUtil.getInstance().getProperties();
        } catch (AppConfigException e) {
            logger.error(e);
        }
        path = (String) prop.get("screenshot.path");
        prefix = (String) prop.get("screenshot.prefix");
        extension = (String) prop.get("screenshot.extension");
        LocalDateTime localDateTime = LocalDateTime.now();
        time = "" + localDateTime.getYear() + localDateTime.getMonthValue() + localDateTime.getDayOfMonth() + localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond() + localDateTime.getNano();
    }
    
    public List<String> getImages() {
        return images;
    }
}
