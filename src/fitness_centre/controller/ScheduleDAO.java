/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.exceptions.DatabaseConnectionException;
import fitness_centre.main.DatabaseManager;
import fitness_centre.model.SсheduleEntity;
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
public class ScheduleDAO {
     private static Logger logger = LoggerFactory.getLogger(ScheduleDAO.class);
     
     private DatabaseManager connectionManager;
     
     public ScheduleDAO(DatabaseManager connectionManager) {
        this.connectionManager = connectionManager;
     }
     
     
     public List<SсheduleEntity> findAll() throws SQLException {
        logger.debug("Getting all shedules from the database fitness_centre");
        List<SсheduleEntity> shedules = new ArrayList<SсheduleEntity>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT sch.id, sch.trainer_id, t.full_name, sch.gym_id,"
                    + " g.gym_number, sch.tt_id, tt.tt_name, sch.day_of_week,"
                    + " sch.start_time, sch.end_time FROM schedule sch, trainer t,"
                    + " type_of_training tt, gym g WHERE t.id = sch.trainer_id AND"
                    + " sch.gym_id = g.id AND sch.tt_id = tt.id";
            logger.debug("Executing query: " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                SсheduleEntity shedule = new SсheduleEntity();
                shedule.setTrainerId(rs.getInt("sch.trainer_id"));
                shedule.setTrainerFullName(rs.getString("t.full_name"));
                shedule.setGymId(rs.getInt("sch.gym_id"));
                shedule.setGymNumber(rs.getString("g.gym_number"));
                shedule.setTrainingId(rs.getInt("sch.tt_id"));
                shedule.setTrainingName(rs.getString("tt.tt_name"));
                shedule.setDayOfweek(rs.getString("sch.day_of_week"));
                shedule.setStartTime(rs.getTime("sch.start_time"));
                shedule.setEndTime(rs.getTime("sch.end_time"));
                shedules.add(shedule);
            }
            rs.close();
            stmt.close();
        } catch (DatabaseConnectionException e) {
            throw new SQLException("Cannot connect to database", e);
        } finally {
            DatabaseManager.closeConnection(connection);
        }
        return shedules;
    }
    
}
