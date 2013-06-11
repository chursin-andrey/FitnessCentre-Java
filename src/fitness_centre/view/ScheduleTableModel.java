/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.model.SсheduleEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;

/**
 *
 * @author Андрей
 */
public class ScheduleTableModel extends AbstractTableModel {
    private static Logger logger = LoggerFactory.getLogger(ScheduleTableModel.class);
    private static final int COL_COUNT = 6;
    private List<SсheduleEntity> shedules;
    
    public ScheduleTableModel(List<SсheduleEntity> equips) {
        if (equips == null) {
            equips = new ArrayList<SсheduleEntity>(0);
        }
        this.shedules = equips;       
    }
    
    @Override
    public int getRowCount() {
        return shedules.size();
    }
    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : {
                return "Тренер";
            }
            case 1: {
                return "Зал";
            }
            case 2: {
                return "Занятие";
            }
            case 3: {
                return "День";
            }
            case 4: {
                return "Начало";
            }
            case 5: {
                return "Конец";
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
                return String.class;
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
        SсheduleEntity s = shedules.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return s.getTrainerFullName();
            }
            case 1: {
                return s.getGymNumber();
            }
            case 2: {
                return s.getTrainingName();
            }
            case 3: {
                return s.getDayOfweek();
            }
            case 4: {
                return s.getStartTime();
            }
            case 5: {
                return s.getEndTime();
            }    
            default: return "";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SсheduleEntity s = shedules.get(rowIndex);
        logger.debug("Changing data in model:" + shedules);
        switch (columnIndex) {
            case 0: {
                s.setTrainingName((String)aValue);
                break;
            }
            case 1: {
                s.setGymNumber((String)aValue);
                break;
            }
            case 2: {
                s.setTrainingName((String)aValue);
                break;
            }
            case 3: {
                s.setDayOfweek((String)aValue);
                break;
            }
            case 4: {
                s.setStartTime((Time)aValue);
                break;
            }
            case 5: {
                s.setEndTime((Time)aValue);
                break;
            }    
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
