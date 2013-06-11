/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 *
 * @author Андрей
 */
public class GymEquipDialog extends JDialog{
        
    public GymEquipDialog(String title, JTable data, boolean newGymEquip, JFrame owner) throws HeadlessException {
        
        setTitle(title);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(owner.getBounds().x + owner.getWidth()/4, owner.getBounds().y + owner.getHeight()/4, GymEquipDialog.WIDTH, GymEquipDialog.HEIGHT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new GymEquipDialog.LabelPanel(data, 0, "Номер зала:", 25));
        panel.add(new GymEquipDialog.LabelPanel(data, 1, "Наименование инвентаря:", 25));
        panel.add(new GymEquipDialog.LabelPanel(data, 2, "Количество:", 25));
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(new JButton("Сохранить"));
        buttonsPanel.add(Box.createHorizontalStrut(20));
        JButton btnCancel = new JButton();
        btnCancel.setAction(new GymEquipDialog.ExitAction("Отмена"));
        buttonsPanel.add(btnCancel);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 4, 10, 4));
        panel.add(buttonsPanel);

        add(panel);
        pack();
    }
    
   private class ExitAction extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            for (WindowListener lsnr : GymEquipDialog.this.getWindowListeners()) {
                lsnr.windowClosing(new WindowEvent(GymEquipDialog.this, 0));
            }
            GymEquipDialog.this.dispose();
        }

        private ExitAction(String name) {
            super(name);
        }
    }
    
    private class LabelPanel extends JPanel{
        private JTextField textField;

        private LabelPanel(JTable data, int columnIndex, String label, int boxWidth) {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            add(new JLabel(label));
            add(Box.createHorizontalStrut(15));
            
            int selRow = data.getSelectedRow();
            textField = new JTextField(boxWidth);
            textField.setText(data.getModel().getValueAt(selRow, columnIndex).toString());
            add(textField);
            setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        }

        public String getText(){
            return textField.getText();
        }

        public void setText(String t) {
            textField.setText(t);
        }
    }
}
