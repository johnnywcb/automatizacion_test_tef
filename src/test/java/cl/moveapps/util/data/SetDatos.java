package cl.moveapps.util.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

/**
 * Created by marcelomagana on 6/23/17.
 */
public class SetDatos {
    private static final Logger logger = LogManager.getLogger(SetDatos.class);
    private static SetDatos INSTANCE = new SetDatos();
    private Object[][] result;
    private int x = 0;
    
    private SetDatos() {
    
    }
    
    public static SetDatos getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new SetDatos();
        }
        return INSTANCE;
        
    }
    
    public Object[][] getData(Class clazz) {
        ClassLoader classLoader = getClass().getClassLoader();
        Path path = null;
        try {
            String name = clazz.getSimpleName() + ".txt";
            path = Paths.get(classLoader.getResource("data/" + name).toURI());
        } catch (NullPointerException | URISyntaxException e) {
            logger.error(e);
        }
        try {
            Stream<String> stream = Files.lines(path);
            List<String> stream2 = new ArrayList<>();
            stream.forEach((String s) -> stream2.add(s));
            int count = toIntExact(stream2.size());
            stream2.forEach(s -> initializeArray(count, fieldCount(s)));
            x = 0;
            stream2.forEach(s -> assign(field(s)));
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }
    
    private void initializeArray(int x, int y) {
        result = new Object[x][y];
    }
    
    private int fieldCount(String line) {
        return line.split(";").length;
    }
    
    private void assign(String[] fields) {
        result[x] = fields;
        x++;
    }
    
    private String[] field(String line) {
        return line.split(";");
    }
}
