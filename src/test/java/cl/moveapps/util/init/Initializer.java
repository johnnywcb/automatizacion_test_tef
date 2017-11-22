package cl.moveapps.util.init;

import java.io.File;

/**
 * Created by marcelomagana on 5/26/17.
 */
public class Initializer {
    
    private static Initializer INSTANCE = new Initializer();
    
    private Initializer() {
    
    }
    
    public static Initializer getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new Initializer();
        }
        return INSTANCE;
        
    }
    
    public void init() {
        ClassLoader classLoader = getClass().getClassLoader();
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            File file = new File(classLoader.getResource("drivers/windows/IEDriverServer.exe").getFile());
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            file = new File(classLoader.getResource("drivers/windows/chromedriver.exe").getFile());
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            file = new File(classLoader.getResource("drivers/windows/geckodriver.exe").getFile());
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
        } else if (osName.toLowerCase().contains("mac")) {
            File file = new File(classLoader.getResource("drivers/osx/chromedriver").getFile());
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            file = new File(classLoader.getResource("drivers/osx/geckodriver").getFile());
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
            file = new File(classLoader.getResource("drivers/osx/operadriver").getFile());
            System.setProperty("webdriver.opera.driver", file.getAbsolutePath());
        } else {
        
        }
        
    }
}
