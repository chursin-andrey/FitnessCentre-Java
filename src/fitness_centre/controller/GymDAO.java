/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.exceptions.DatabaseConnectionException;
import fitness_centre.main.DatabaseManager;
import fitness_centre.model.GymEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Андрей
 */
public class GymDAO {
    
    private static Logger logger = LoggerFactory.getLogger(GymDAO.class);
    
    private DatabaseManager connectionManager;
    
    public GymDAO(DatabaseManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    public List<GymEntity> findAll() throws SQLException {
        logger.debug("Getting all gyms from the database fitness_centre");
        List<GymEntity> gyms = new ArrayList<GymEntity>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT g.id, g.gym_number, g.area, g.cover,"
                + " g.cond_id, g.capacity, c.availability FROM gym g,"
                + " conditioner c WHERE c.id = g.cond_id";
            logger.debug("Executing query: " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                GymEntity gym = new GymEntity();
                gym.setGymId(rs.getInt("g.id"));
                gym.setGymNumber(rs.getString("g.gym_number"));
                gym.setGymArea(rs.getFloat("g.area"));
                gym.setGymCover(rs.getString("g.cover"));
                gym.setCondId(rs.getInt("g.cond_id"));
                gym.setCapacity(rs.getInt("g.capacity"));
                gym.setCondAvailability(rs.getString("c.availability"));
                gyms.add(gym);
            }
            rs.close();
            stmt.close();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } catch (SQLException e) {
            throw new SQLException("Error: " + e.getMessage());
        } finally {
            DatabaseManager.closeConnection(connection);
        }
        return gyms;
    }
}