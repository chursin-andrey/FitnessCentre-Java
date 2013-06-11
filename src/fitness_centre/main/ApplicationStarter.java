/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.exceptions.DatabaseInitException;
import fitness_centre.view.AppFrame;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Андрей
 */
public class ApplicationStarter {

    private static Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);
    
    public static final String PROPERTIES_FILE_NAME = "Application.properties";
    public static final String DRIVER_PROPERTY_NAME = "jdbc.driver";
    public static final String URL_PROPERTY_NAME = "jdbc.url";
    public static final String USER_PROPERTY_NAME = "user";
    public static final String PASSWORD_PROPERTY_NAME = "password";
    public static final String DATABASE_SQL_DUMP = "init.script.path";
    
    private static Properties initDefaults() {
        Properties props = new Properties();
        props.put(DRIVER_PROPERTY_NAME, "com.mysql.jdbc.Driver");
        props.put(URL_PROPERTY_NAME, "jdbc:mysql://localhost/fitness_centre");
        props.put(USER_PROPERTY_NAME, "root");
        props.put(PASSWORD_PROPERTY_NAME, "mysql");
        props.put(DATABASE_SQL_DUMP, "FitnessCentre.sql");
        return props;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        logger.info("Starting application...");

        //Loading application settings
        File currDir = new File(".");
        Properties props = initDefaults();
        try {
            props.load(new FileInputStream(currDir.getCanonicalPath() +
                     "\\resources\\" + PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            logger.warn("Error loading properties, using defaults", e);
        }
                
        DatabaseManager dbm = new DatabaseManager(props);
        
        //Initing database
        File f = new File(props.getProperty("init.script.path"));
        try {
            dbm.initDatabase(f);
        } catch (DatabaseInitException e) {
            logger.error("Cannot recreate database", e);
            System.exit(1);
        }
        
        JFrame mainFrame = new AppFrame("Фитнес-центр", dbm);
        mainFrame.setVisible(true);
    }
}
