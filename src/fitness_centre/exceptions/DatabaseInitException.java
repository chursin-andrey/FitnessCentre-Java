/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.exceptions;

/**
 *
 * @author Андрей
 */
public class DatabaseInitException extends Exception {

    public DatabaseInitException() {
    }

    public DatabaseInitException(String message) {
        super(message);
    }

    public DatabaseInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseInitException(Throwable cause) {
        super(cause);
    }
}
