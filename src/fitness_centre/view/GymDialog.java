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
public class GymDialog extends JDialog {
   
    public GymDialog(String title, JTable data, boolean newGym, JFrame owner) throws HeadlessException {
        setTitle(title);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(owner.getBounds().x + owner.getWidth()/4, owner.getBounds().y + owner.getHeight()/4, GymDialog.WIDTH, GymDialog.HEIGHT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new GymDialog.LabelPanel(data, 0, "Номер:", 25));
        panel.add(new GymDialog.LabelPanel(data, 1, "Площадь:", 25));
        panel.add(new GymDialog.LabelPanel(data, 2, "Покрытие:", 25));
        panel.add(new GymDialog.LabelPanel(data, 3, "Кондиционер:", 25));
        panel.add(new GymDialog.LabelPanel(data, 4, "Количество человек:", 25));
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(new JButton("Сохранить"));
        buttonsPanel.add(Box.createHorizontalStrut(20));
        JButton btnCancel = new JButton();
        btnCancel.setAction(new GymDialog.ExitAction("Отмена"));
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
            for (WindowListener lsnr : GymDialog.this.getWindowListeners()) {
                lsnr.windowClosing(new WindowEvent(GymDialog.this, 0));
            }
            GymDialog.this.dispose();
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
