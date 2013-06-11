/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.model;

import java.sql.Time;
/**
 *
 * @author Андрей
 */
public class SсheduleEntity {
    
    private int sheduleId;
    private int trainerId;
    private String trainerFullName;
    private int gymId;
    private String gymNumber;
    private int trainingId;
    private String trainingName;
    private String dayOfweek;
    private Time startTime;
    private Time endTime;

    /**
     * @return the sheduleId
     */
    public int getSheduleId() {
        return sheduleId;
    }

    /**
     * @param sheduleId the sheduleId to set
     */
    public void setSheduleId(int sheduleId) {
        this.sheduleId = sheduleId;
    }
    
    /**
     * @return the trainerId
     */
    public int getTrainerId() {
        return trainerId;
    }

    /**
     * @param trainerId the trainerId to set
     */
    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    /**
     * @return the trainerFullName
     */
    public String getTrainerFullName() {
        return trainerFullName;
    }

    /**
     * @param trainerFullName the trainerFullName to set
     */
    public void setTrainerFullName(String trainerFullName) {
        this.trainerFullName = trainerFullName;
    }

    /**
     * @return the gymId
     */
    public int getGymId() {
        return gymId;
    }

    /**
     * @param gymId the gymId to set
     */
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    /**
     * @return the gymNumber
     */
    public String getGymNumber() {
        return gymNumber;
    }

    /**
     * @param gymNumber the gymNumber to set
     */
    public void setGymNumber(String gymNumber) {
        this.gymNumber = gymNumber;
    }

    /**
     * @return the trainingId
     */
    public int getTrainingId() {
        return trainingId;
    }

    /**
     * @param trainingId the trainingId to set
     */
    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    /**
     * @return the trainingName
     */
    public String getTrainingName() {
        return trainingName;
    }

    /**
     * @param trainingName the trainingName to set
     */
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    /**
     * @return the dayOfweek
     */
    public String getDayOfweek() {
        return dayOfweek;
    }

    /**
     * @param dayOfweek the dayOfweek to set
     */
    public void setDayOfweek(String dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

    /**
     * @return the startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
