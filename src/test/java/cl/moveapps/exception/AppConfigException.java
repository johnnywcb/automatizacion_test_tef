package cl.moveapps.exception;

/**
 * Created by marcelomagana on 9/25/17.
 */
public class AppConfigException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public AppConfigException() {
        super();
    }
    
    public AppConfigException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AppConfigException(String message) {
        super(message);
    }
    
    public AppConfigException(Throwable cause) {
        super(cause);
    }
    
}
