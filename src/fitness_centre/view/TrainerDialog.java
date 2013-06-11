/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;

import fitness_centre.controller.TrainerDAO;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import fitness_centre.model.TrainerEntity;
import java.util.Enumeration;
import fitness_centre.view.AppFrame;
import java.security.acl.Owner;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Андрей
 */
public class TrainerDialog extends JDialog implements ActionListener {
    
    private static final int D_WIDTH = 450;
    private static final int D_HEIGHT = 270;
    private static final int L_X = 10; // левая граница метки для поля
    private static final int L_W = 100; // ширина метки для поля
    private static final int C_W = 300; // ширина поля
    private AppFrame frameOwner;
    private boolean result = false;
    private int trainerID = 0;
    private JLabel lblFullName;
    private JLabel lblDateOfBirth;
    private JLabel lblPassportNo;
    private JLabel lblPasspAuthority;
    private JLabel lblDateOfIssue;
    private JLabel lblSex;
    private JTextField tfPassportNumber;
    private JTextField tfFullName;
    private JTextField tfPasspAuthority;
    private JSpinner dateOfBirth;
    private JSpinner dateOfIssue;
    private ButtonGroup bgSex;
    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private JButton btnOK = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    
    public TrainerDialog(String title, TrainerEntity trainer, boolean newTrainer,
            AppFrame owner) throws HeadlessException {
        frameOwner = owner;
        setTitle(title);
        setSize(D_WIDTH, D_HEIGHT);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        getContentPane().setLayout(null);
        lblFullName = new JLabel("ФИО:", JLabel.RIGHT);
        lblFullName.setBounds(L_X, 10, L_W, 20);
        getContentPane().add(lblFullName);
        tfFullName = new JTextField();
        tfFullName.setBounds(L_X + L_W + 10, 10, C_W, 20);
        getContentPane().add(tfFullName);
        
        lblDateOfBirth = new JLabel("Дата рождения:", JLabel.RIGHT);
        lblDateOfBirth.setBounds(L_X, 40, L_W, 20);
        getContentPane().add(lblDateOfBirth);
        dateOfBirth = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        dateOfBirth.setBounds(L_X + L_W + 10, 40, C_W, 20);
        getContentPane().add(dateOfBirth);
        
        lblPassportNo = new JLabel("Номер паспорта:", JLabel.RIGHT);
        lblPassportNo.setBounds(L_X, 70, L_W, 20);
        getContentPane().add(lblPassportNo);
        tfPassportNumber = new JTextField();
        tfPassportNumber.setBounds(L_X + L_W + 10, 70, C_W, 20);
        getContentPane().add(tfPassportNumber);
        
        lblPasspAuthority = new JLabel("Кем выдан:", JLabel.RIGHT);
        lblPasspAuthority.setBounds(L_X, 100, L_W, 20);
        getContentPane().add(lblPasspAuthority);
        tfPasspAuthority = new JTextField();
        tfPasspAuthority.setBounds(L_X + L_W + 10, 100, C_W, 20);
        getContentPane().add(tfPasspAuthority);
        
        lblDateOfIssue = new JLabel("Дата выдачи:", JLabel.RIGHT);
        lblDateOfIssue.setBounds(L_X, 130, L_W, 20);
        getContentPane().add(lblDateOfIssue);
        dateOfIssue = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        dateOfIssue.setBounds(L_X + L_W + 10, 130, C_W, 20);
        getContentPane().add(dateOfIssue);
        
        lblSex = new JLabel("Пол:", JLabel.RIGHT);
        lblSex.setBounds(L_X, 160, L_W, 20);
        getContentPane().add(lblSex);
        rbtnMale = new JRadioButton("Мужской");
        rbtnFemale = new JRadioButton("Женский");
        rbtnMale.setActionCommand("Мужской");
        rbtnFemale.setActionCommand("Женский");
        bgSex = new ButtonGroup();
        bgSex.add(rbtnMale);
        bgSex.add(rbtnFemale);
        rbtnMale.setBounds(L_X + L_W + 10, 160, 100, 20);
        getContentPane().add(rbtnMale);
        rbtnFemale.setBounds(L_X + L_W + 10 + C_W / 2, 160, 100, 20);
        getContentPane().add(rbtnFemale);
        btnOK.setName("OK");
        btnOK.addActionListener(this);
        btnOK.setBounds(L_X + L_W + 10, 205, 100, 20);
        getContentPane().add(btnOK);
        btnCancel.setName("Cancel");
        btnCancel.setBounds(L_X + L_W + 10 + C_W / 2, 205, 100, 20);
        btnCancel.addActionListener(this);
        getContentPane().add(btnCancel);
        
        if (newTrainer)
        {
            JButton btnNew = new JButton("Добавить");
            btnNew.setName("New");
            btnNew.addActionListener(this);
            btnNew.setBounds(L_X + L_W + C_W + 60, 70, 100, 25);
            getContentPane().add(btnNew);
        }
        else {
            setTrainer(trainer);
        }
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((d.width - TrainerDialog.D_WIDTH) / 2, (d.height - TrainerDialog.D_HEIGHT) / 2,
            TrainerDialog.D_WIDTH, TrainerDialog.D_HEIGHT);
    }
    
    public void setTrainer(TrainerEntity trainer) {
        trainerID = trainer.getTrainerId();
        tfFullName.setText(trainer.getFullName());
        dateOfBirth.getModel().setValue(trainer.getDateOfBirth());
        tfPassportNumber.setText(trainer.getPassportNo());
        tfPasspAuthority.setText(trainer.getAuthority());
        dateOfIssue.getModel().setValue(trainer.getDateOfIssue());
        for (Enumeration e = bgSex.getElements(); e.hasMoreElements();) {
            AbstractButton ab = (AbstractButton) e.nextElement();
            ab.setSelected(ab.getActionCommand().equals(new String("" + trainer.getSex())));
        }
    }
    
    public TrainerEntity getTrainer() {
        TrainerEntity trainer = new TrainerEntity();
        trainer.setTrainerId(trainerID);
        trainer.setFullName(tfFullName.getText());
        Date dob = ((SpinnerDateModel) dateOfBirth.getModel()).getDate();
        trainer.setDateOfBirth(dob);
        trainer.setPassportNo(tfPassportNumber.getText());
        trainer.setAuthority(tfPasspAuthority.getText());
        Date doi = ((SpinnerDateModel) dateOfIssue.getModel()).getDate();
        trainer.setDateOfIssue(doi);
        for (Enumeration e = bgSex.getElements(); e.hasMoreElements();) {
            AbstractButton ab = (AbstractButton) e.nextElement();
            if (ab.isSelected()) {
                trainer.setSex(ab.getActionCommand());
            }
        }
        return trainer;
    }
    
    public boolean getResult() {
        return result;
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        if (src.getName().equals("New")) {
            result = true;
            try {
                frameOwner.trainerDAO.addTrainer(getTrainer());
                //frameOwner.reloadTrainers();
                
                tfFullName.setText("");
                tfPassportNumber.setText("");
                tfPasspAuthority.setText("");
            } catch (Exception sql_e) {
                JOptionPane.showMessageDialog(this, sql_e.getMessage());
            }
        }
        if (src.getName().equals("OK")) {
            result = true;
            try {
                frameOwner.trainerDAO.updateTrainer(getTrainer());
            }
            catch (SQLException ex) {
                Logger.getLogger(TrainerDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (src.getName().equals("Cancel")) {
            result = false;
        }
        setVisible(false);
    }
        
    private class ExitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (WindowListener lsnr : TrainerDialog.this.getWindowListeners()) {
                lsnr.windowClosing(new WindowEvent(TrainerDialog.this, 0));
            }
            TrainerDialog.this.dispose();
        }

        private ExitAction(String name) {
            super(name);
        }
    }
    
}
