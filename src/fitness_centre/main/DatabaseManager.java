/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.exceptions.DatabaseConnectionException;
import fitness_centre.exceptions.DatabaseInitException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static fitness_centre.main.ApplicationStarter.*;
import fitness_centre.view.AppFrame;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Андрей
 */
public class DatabaseManager {
    
    private static Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private Properties properties;
    
    public DatabaseManager(Properties properties) {
        this.properties = properties;
        String className = properties.getProperty(DRIVER_PROPERTY_NAME);
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            String message = String.format("Error loading class with name: %s", className);
            logger.error(message);
            throw new IllegalArgumentException(message, e);
        }
    }

    public Connection getConnection() throws DatabaseConnectionException {
        logger.debug("Creating connection to the database");
        Connection connection = null;
        try {
            String url = properties.getProperty(URL_PROPERTY_NAME);
            logger.debug(String.format("Connection to the database at: %s", url));
            connection = DriverManager.getConnection(url, properties.getProperty(USER_PROPERTY_NAME),
                    properties.getProperty(PASSWORD_PROPERTY_NAME));
            logger.debug("Connection created");
        } catch (SQLException e) {
            String message = "Cannot connect to database";
            logger.error(message, e);
            throw new DatabaseConnectionException(message, e);
        }
        return connection;
    }
    
    public boolean isDBCreated() {
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SHOW DATABASES;");
            while(rs.next()){
                if(rs.getString("Database").equals("fitness_java")){    
                    return true;
                }
            }
            rs.close();
            st.close();
        } catch (DatabaseConnectionException ex) { 
            java.util.logging.Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   
    }
    
    public void initDatabase(File scriptFile) throws DatabaseInitException {
        if (scriptFile == null || !scriptFile.exists()){
            throw new DatabaseInitException("Cannot create database. Script file doesn't exist or not found!");
        }
        Connection conn = null;
        
        try {
            conn = getConnection();
            conn.setAutoCommit(true);
            BufferedReader reader = new BufferedReader(new FileReader(scriptFile));
            String statementString = "";
            while ((statementString = reader.readLine()) != null){
                Statement stmt = conn.createStatement();
                logger.debug(String.format("Executing statement: %s", statementString));
                stmt.executeUpdate(statementString);
                stmt.close();
            }
            reader.close();
        } catch (IOException e) {
            String message = "Cannot read script";
            logger.error(message, e);
            throw new DatabaseInitException(message, e);
        } catch (DatabaseConnectionException e) {
            String message = "Cannot connect to database";
            logger.error(message, e);
            throw new DatabaseInitException(message, e);
        } catch (SQLException e) {
            String message = "Cannot execute SQL statement";
            logger.error(message, e);
            throw new DatabaseInitException(message, e);
        } finally {
            closeConnection(conn);
        }

    }
    
    public static void closeConnection(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                logger.warn("Error closing connection", e);
            }
        }
    }
}
