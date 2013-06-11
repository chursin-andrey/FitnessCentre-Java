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
public class ScheduleDialog extends JDialog {
    
    public ScheduleDialog(String title, JTable data, boolean newShedule, JFrame owner) throws HeadlessException {
        setTitle(title);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(owner.getBounds().x + owner.getWidth()/4, owner.getBounds().y + owner.getHeight()/4, ScheduleDialog.WIDTH, ScheduleDialog.HEIGHT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new ScheduleDialog.LabelPanel(data, 0, "ФИО тренера:", 25));
        panel.add(new ScheduleDialog.LabelPanel(data, 1, "Номер зала:", 25));
        panel.add(new ScheduleDialog.LabelPanel(data, 2, "Занятие:", 25));
        panel.add(new ScheduleDialog.LabelPanel(data, 3, "День недели:", 25));
        panel.add(new ScheduleDialog.LabelPanel(data, 4, "Начало:", 25));
        panel.add(new ScheduleDialog.LabelPanel(data, 5, "Конец:", 25));
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(new JButton("Сохранить"));
        buttonsPanel.add(Box.createHorizontalStrut(20));
        JButton btnCancel = new JButton();
        btnCancel.setAction(new ScheduleDialog.ExitAction("Отмена"));
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
            for (WindowListener lsnr : ScheduleDialog.this.getWindowListeners()) {
                lsnr.windowClosing(new WindowEvent(ScheduleDialog.this, 0));
            }
            ScheduleDialog.this.dispose();
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
