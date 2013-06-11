/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.model.GymEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Андрей
 */
public class GymTableModel extends AbstractTableModel {
    
    private static Logger logger = LoggerFactory.getLogger(GymTableModel.class);
    private static final int COL_COUNT = 5;
    private List<GymEntity> gyms;
    
    public GymTableModel(List<GymEntity> gyms) {
        if (gyms == null) {
            gyms = new ArrayList<GymEntity>(0);
        }
        this.gyms = gyms;       
    }
    
    @Override
    public int getRowCount() {
        return gyms.size();
    }
    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : {
                return "Номер";
            }
            case 1: {
                return "Площадь, кв.м";
            }
            case 2: {
                return "Покрытие";
            }
            case 3: {
                return "Кондиционер";
            }
            case 4: {
                return "Кол-во человек";
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
                return float.class;
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
            default: return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GymEntity gym = gyms.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return gym.getGymNumber();
            }
            case 1: {
                return gym.getGymArea();
            }
            case 2: {
                return gym.getGymCover();
            }
            case 3: {
                return gym.getCondAvailability();
            }
            case 4: {
                return gym.getCapacity();
            }
            default: return "";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        GymEntity gym = gyms.get(rowIndex);
        logger.debug("Changing data in model:" + gym);
        switch (columnIndex) {
            case 0: {
                gym.setGymNumber((String)aValue);
                break;
            }
            case 1: {
                gym.setGymArea((float)aValue);
                break;
            }
            case 2: {
                gym.setGymCover((String)aValue);
                break;
            }
            case 3: {
                gym.setCondAvailability((String)aValue);
                break;
            }
            case 4: {
                gym.setCapacity((int)aValue);
                break;
            }
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
