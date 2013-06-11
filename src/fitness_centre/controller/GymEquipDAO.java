/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.exceptions.DatabaseConnectionException;
import fitness_centre.main.DatabaseManager;
import fitness_centre.model.GymEquipEntity;
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
public class GymEquipDAO {
    private static Logger logger = LoggerFactory.getLogger(GymEquipDAO.class);
    
    private DatabaseManager connectionManager;
    
    public GymEquipDAO(DatabaseManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    public List<GymEquipEntity> findAll() throws SQLException {
        logger.debug("Getting all gym equipment from the database fitness_centre");
        List<GymEquipEntity> equips = new ArrayList<GymEquipEntity>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT ge.id, ge.gym_id, g.gym_number, ge.stock_id,"
                    + " s.stock_name, ge.stock_quantity FROM gym_equipment ge,"
                    + " gym g, stock s WHERE ge.gym_id = g.id and"
                    + " ge.stock_id = s.id";
            logger.debug("Executing query: " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                GymEquipEntity gymEquip = new GymEquipEntity();
                gymEquip.setGymEquipId(rs.getInt("ge.id"));
                gymEquip.setGymId(rs.getInt("ge.gym_id"));
                gymEquip.setGymNumber(rs.getString("g.gym_number"));
                gymEquip.setStockId(rs.getInt("ge.stock_id"));
                gymEquip.setEquipName(rs.getString("s.stock_name"));
                gymEquip.setEquipQuantity(rs.getInt("ge.stock_quantity"));
                equips.add(gymEquip);
            }
            rs.close();
            stmt.close();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } finally {
            DatabaseManager.closeConnection(connection);
        }
        return equips;
    }
}
