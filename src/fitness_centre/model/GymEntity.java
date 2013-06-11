/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.model;

/**
 *
 * @author Андрей
 */
public class GymEntity {
    
    private int gymId;
    private String gymNumber;
    private float gymArea;
    private String gymCover;
    private int condId;
    private String condAvailability;
    private int capacity;

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
     * @return the gymArea
     */
    public float getGymArea() {
        return gymArea;
    }

    /**
     * @param gymArea the gymArea to set
     */
    public void setGymArea(float gymArea) {
        this.gymArea = gymArea;
    }

    /**
     * @return the gymCover
     */
    public String getGymCover() {
        return gymCover;
    }

    /**
     * @param gymCover the gymCover to set
     */
    public void setGymCover(String gymCover) {
        this.gymCover = gymCover;
    }

    /**
     * @return the condId
     */
    public int getCondId() {
        return condId;
    }

    /**
     * @param condId the conditionerId to set
     */
    public void setCondId(int condId) {
        this.condId = condId;
    }

    /**
     * @return the condAvailability
     */
    public String getCondAvailability() {
        return condAvailability;
    }

    /**
     * @param condAvailability the condAvailability to set
     */
    public void setCondAvailability(String condAvailability) {
        this.condAvailability = condAvailability;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
