/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.model;

/**
 *
 * @author Андрей
 */
public class TrainingEntity {
    
    private int trainingId;
    private String trainingName;
    private double trainingDuration;
    private int trainerId;
    private String trainerFullName;

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
     * @return the trainingDuration
     */
    public double getTrainingDuration() {
        return trainingDuration;
    }

    /**
     * @param trainingDuration the trainingDuration to set
     */
    public void setTrainingDuration(double trainingDuration) {
        this.trainingDuration = trainingDuration;
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
    
}
