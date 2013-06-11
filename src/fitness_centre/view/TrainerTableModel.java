/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.model.TrainerEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import fitness_centre.model.TrainerEntity;

/**
 *
 * @author Андрей
 */

public class TrainerTableModel extends AbstractTableModel {
    
    private static Logger logger = LoggerFactory.getLogger(TrainerTableModel.class);
    private static final int COL_COUNT = 6;
    private List<TrainerEntity> trainers;
    
    public TrainerTableModel(List<TrainerEntity> trainers) {
        if (trainers == null) {
            trainers = new ArrayList<TrainerEntity>(0);
        }
        this.trainers = trainers;       
    }
    
    public TrainerEntity getTrainerByRowIndex(int rowIndex) {
        TrainerEntity trainer = trainers.get(rowIndex);
        return trainer;
    }
    
    @Override
    public int getRowCount() {
        return trainers.size();
    }
    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : {
                return "ФИО";
            }
            case 1: {
                return "Дата рождения";
            }
            case 2: {
                return "Номер паспорта";
            }
            case 3: {
                return "Кем выдан";
            }
            case 4: {
                return "Дата выдачи";
            }    
            case 5: {
                return "Пол";
            }
            default: return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: {
                return String.class;
            }
            case 1: {
                return Date.class;
            }
            case 2: {
                return String.class;
            }
            case 3: {
                return String.class;
            }
            case 4: {
                return String.class;
            } 
            case 5: {
                return String.class;
            } 
            default: return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainerEntity trainer = trainers.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return trainer.getFullName();
            }
            case 1: {
                return trainer.getDateOfBirth();
            }
            case 2: {
                return trainer.getPassportNo();
            }
            case 3: {
                return trainer.getAuthority();
            }
            case 4: {
                return trainer.getDateOfIssue();
            }
            case 5: {
                return trainer.getSex();
            }
            default: return "";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TrainerEntity trainer = trainers.get(rowIndex);
        logger.debug("Changing data in model:" + trainer);
        switch (columnIndex) {
            case 0: {
                trainer.setFullName((String)aValue);
                break;
            }
            case 1: {
                trainer.setDateOfBirth((Date)aValue);
                break;
            }
            case 2: {
                trainer.setPassportNo((String)aValue);
                break;
            }
            case 3: {
                trainer.setAuthority((String)aValue);
                break;
            }
            case 4: {
                trainer.setDateOfIssue((Date)aValue);
                break;
            }
            case 5: {
                trainer.setSex((String)aValue);
                break;
            }
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
