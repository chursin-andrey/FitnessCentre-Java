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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author Андрей
 */
public class ApplicationStarter {

    private static Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);
    
    public static final String PROPERTIES_FILE_NAME = "Application.properties";
    public static final String DRIVER_PROPERTY_NAME = "jdbc.driver";
    public static String URL_PROPERTY_NAME = "jdbc.url";
    public static final String USER_PROPERTY_NAME = "user";
    public static final String PASSWORD_PROPERTY_NAME = "password";
    public static final String DATABASE_SQL_INIT = "init.script.path";
    public static final String DATABASE_SQL_DUMP = "dump.script.path";
    
    private static Properties initDefaults(String str) throws IOException {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(str + "\\resources\\" + PROPERTIES_FILE_NAME));
            props.put(DRIVER_PROPERTY_NAME, "com.mysql.jdbc.Driver");
            props.put(URL_PROPERTY_NAME, "jdbc:mysql://localhost:3306");
            props.put(USER_PROPERTY_NAME, "root");
            props.put(PASSWORD_PROPERTY_NAME, "root");
            props.put(DATABASE_SQL_INIT, str + "\\resources\\" + "FitnessCentre.sql");
            props.put(DATABASE_SQL_DUMP, str + "\\resources\\" + "dump.sql");
            return props;
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApplicationStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String str = null;
        
        //Loading application settings
        File currDir = new File(".");
        
        Properties props = null;
        try {
            str = currDir.getCanonicalPath();
            props = initDefaults(str);
        } catch (IOException e) {
            logger.warn("Error loading properties, using defaults", e);
        }
        
        logger.info("Starting application. Canonical path: " + str);
        logger.info(props.getProperty("jdbc.driver"));
        logger.info(props.getProperty("jdbc.url"));
        logger.info(props.getProperty("user"));
        logger.info(props.getProperty("init.script.path"));
        logger.info(props.getProperty("dump.script.path"));
       
        DatabaseManager dbm = new DatabaseManager(props);
        
        //Initing database
        
        
        try {
            File f = null;
            if (dbm.isDBCreated()) {
                props.put(URL_PROPERTY_NAME, "jdbc:mysql://localhost:3306/fitness_java");
            }
            else {
                f = new File(props.getProperty("init.script.path"));
                dbm.initDatabase(f);
                props.put(URL_PROPERTY_NAME, "jdbc:mysql://localhost:3306/fitness_java");
            }
        } catch (DatabaseInitException e) {
            logger.error("Cannot recreate database", e);
            System.exit(1);
        }
        
        JFrame mainFrame = new AppFrame("Фитнес-центр", dbm);
        mainFrame.setVisible(true);
    }
}
