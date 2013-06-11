/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.model;

/**
 *
 * @author Андрей
 */
public class GymEquipEntity {
    
    private int gymEquipId;
    private int gymId;
    private String gymNumber;
    private int stockId;
    private String equipName;
    private int equipQuantity;

    /**
     * @return the id
     */
    public int getGymEquipId() {
        return gymEquipId;
    }
    
    /**
     * @param gymEquipId the gymEquipId to set
     */
    public void setGymEquipId(int gymEquipId) {
        this.gymEquipId = gymEquipId;
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
     * @return the equipment
     */
    public int getStockId() {
        return stockId;
    }

    /**
     * @param stockId the stockId to set
     */
    public void setStockId(int stockId) {
        this.stockId = stockId;
    }
    
        /**
     * @return the equipName
     */
    public String getEquipName() {
        return equipName;
    }

    /**
     * @param equipName the equipmentName to set
     */
    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    /**
     * @return the equipQuantity
     */
    public int getEquipQuantity() {
        return equipQuantity;
    }

    /**
     * @param equipQuantity the equipQuantity to set
     */
    public void setEquipQuantity(int equipQuantity) {
        this.equipQuantity = equipQuantity;
    }


}
