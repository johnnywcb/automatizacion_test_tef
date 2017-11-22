package cl.moveapps.util.properties;

import cl.moveapps.exception.AppConfigException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by marcelomagana on 9/25/17.
 */
public class AppConfigUtil {
    private static final Logger logger = LogManager.getLogger(AppConfigUtil.class);
    private static AppConfigUtil instance;
    private static ClassLoader classLoader = null;
    private static String appName = null;
    private static String defaultConfigFile = "automation.properties";
    
    private Properties properties;
    
    private AppConfigUtil() throws AppConfigException {
        logger.info("AppConfig created for file: " + defaultConfigFile);
        classLoader = getClass().getClassLoader();
        this.properties = new Properties();
        loadProperties();
    }
    
    /**
     * Get the instance for the stored config file.
     *
     * @return The instance associated to the config file.
     *
     * @throws AppConfigException
     */
    public static synchronized AppConfigUtil getInstance() throws AppConfigException {
        if (null == instance) {
            instance = new AppConfigUtil();
        }
        return instance;
    }
    
    /**
     * Reload all the managed properties.
     *
     * @throws AppConfigException
     */
    public static synchronized void reloadProperties() throws AppConfigException {
        instance.loadProperties();
    }
    
    private static String getOperativeSystem() {
        String osName = System.getProperty("os.name");
        return osName.toLowerCase().contains("windows") ? "windows" : "unix";
    }
    
    /**
     * Return the Properties object for the AppConfig instance.
     *
     * @return
     */
    public Properties getProperties() {
        return this.properties;
    }
    
    private void loadProperties() throws AppConfigException {
        try {
            final InputStream is = classLoader.getResourceAsStream("properties/" + getOperativeSystem() + "/automation.properties");
            if (is == null) {
                throw new AppConfigException("Can't find the application properties file.(properties/" + getOperativeSystem() + "/automation.properties");
            }
            properties.load(is);
            logger.info("Successfully Loaded properties from " + getOperativeSystem() + "/automation.properties");
        } catch (IOException e) {
            throw new AppConfigException("An exception occurred while trying to read content of file " + getOperativeSystem() + "/automation.properties", e);
        }
    }
}
