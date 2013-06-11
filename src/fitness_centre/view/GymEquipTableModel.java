/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fitness_centre.model.GymEquipEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Андрей
 */
public class GymEquipTableModel extends AbstractTableModel {
    private static Logger logger = LoggerFactory.getLogger(GymEquipTableModel.class);
    private static final int COL_COUNT = 3;
    private List<GymEquipEntity> equips;
    
    public GymEquipTableModel(List<GymEquipEntity> equips) {
        if (equips == null) {
            equips = new ArrayList<GymEquipEntity>(0);
        }
        this.equips = equips;       
    }
    
    @Override
    public int getRowCount() {
        return equips.size();
    }
    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : {
                return "Номер зала";
            }
            case 1: {
                return "Наименование";
            }
            case 2: {
                return "Количество";
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
                return int.class;
            }
            default: return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GymEquipEntity e = equips.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return e.getGymNumber();
            }
            case 1: {
                return e.getEquipName();
            }
            case 2: {
                return e.getEquipQuantity();
            }
            default: return "";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        GymEquipEntity e = equips.get(rowIndex);
        logger.debug("Changing data in model:" + equips);
        switch (columnIndex) {
            case 0: {
                e.setGymNumber((String)aValue);
                break;
            }
            case 1: {
                e.setEquipName((String)aValue);
                break;
            }
            case 2: {
                e.setEquipQuantity((int)aValue);
                break;
            }
        }
    }
    
    /*
    public ResultSet q(String preparedQuery, Object... args)
            throws SQLException
    {
        PreparedStatement p = null;
        /////////////////////////////////////////////////////////////////здесь получаем p
        for(int i = 0; i < args.length; i++)
        {
            p.setObject(i, args[i]);
        }
        //......................................................
        return p.executeQuery();
    }
    
    public void test()
            throws SQLException
    {
        q("select full_name from trainer where id = ?", 100);
    }
    */
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
