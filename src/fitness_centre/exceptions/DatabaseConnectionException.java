/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.exceptions;

/**
 *
 * @author Андрей
 */
public class DatabaseConnectionException extends Exception{

    public DatabaseConnectionException() {
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
    }
}
