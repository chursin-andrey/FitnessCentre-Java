/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.exceptions.DatabaseConnectionException;
import fitness_centre.main.DatabaseManager;
import fitness_centre.model.TrainerEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Андрей
 */
public class TrainerDAO {
    
    private static Logger logger = LoggerFactory.getLogger(TrainerDAO.class);
    
    private DatabaseManager connectionManager;
    
    public TrainerDAO(DatabaseManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    public List<TrainerEntity> findAll() throws SQLException {
        List<TrainerEntity> trainers = new ArrayList<TrainerEntity>();
        Connection connection = null;
        logger.debug("Getting all trainers from the database fitness_centre");
        try {
            connection = connectionManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM trainer";
            logger.debug("Executing query: " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                TrainerEntity trainer = new TrainerEntity();
                trainer.setTrainerId(rs.getInt("id"));
                trainer.setFullName(rs.getString("full_name"));
                trainer.setDateOfBirth(rs.getDate("birth_date"));
                trainer.setPassportNo(rs.getString("passp_no"));
                trainer.setAuthority(rs.getString("authority"));
                trainer.setDateOfIssue(rs.getDate("issue_date"));
                trainer.setSex(rs.getString("sex"));
                trainers.add(trainer);
            }
            rs.close();
            stmt.close();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } finally {
            DatabaseManager.closeConnection(connection);
        }
        return trainers;
    }
    
    public void addTrainer(TrainerEntity trainer) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;
        logger.debug("Inserting Trainer: " + trainer);
        try {
            connection = connectionManager.getConnection();
            ps = connection.prepareStatement(
                    "INSERT INTO trainer (full_name, birth_date, passp_no, authority, issue_date, sex) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, trainer.getFullName());
            ps.setDate(2, new Date(trainer.getDateOfBirth().getTime()));
            ps.setString(3, trainer.getPassportNo());
            ps.setString(4, trainer.getAuthority());
            ps.setDate(5, new Date(trainer.getDateOfIssue().getTime()));
            ps.setString(6, trainer.getSex());
            ps.execute();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } finally {
            if (ps != null) {
                ps.close();
            }  
            DatabaseManager.closeConnection(connection);
        }
    }

    public void deleteTrainer(TrainerEntity trainer) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;
        logger.debug("Deleting Trainer: " + trainer);
        try {
            connection = connectionManager.getConnection();
            ps = connection.prepareStatement("DELETE FROM trainer WHERE id = ?");
            ps.setInt(1, trainer.getTrainerId());
            ps.execute();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            DatabaseManager.closeConnection(connection);
        }
    }

    public void updateTrainer(TrainerEntity trainer) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;
        logger.debug("Updating Trainer: " + trainer);
        try {
           connection = connectionManager.getConnection();
           ps = connection.prepareStatement("UPDATE trainer SET full_name = ?,"
                   + " birth_date = ?, passp_no = ?, authority = ?, issue_date = ?,"
                   + " sex = ? WHERE id = ?");
           ps.setString(1, trainer.getFullName());
           ps.setDate(2, new Date(trainer.getDateOfBirth().getTime()));
           ps.setString(3, trainer.getPassportNo());
           ps.setString(4, trainer.getAuthority());
           ps.setDate(5, new Date(trainer.getDateOfIssue().getTime()));
           ps.setString(6, trainer.getSex());
           ps.setInt(7, trainer.getTrainerId());
           ps.execute();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            DatabaseManager.closeConnection(connection);
        }
    }

    public List<TrainerEntity> findByAnyField(String criteria) throws SQLException{
        logger.debug("Searching trainer by string: " + criteria);
        throw new NotImplementedException();
    }
}
