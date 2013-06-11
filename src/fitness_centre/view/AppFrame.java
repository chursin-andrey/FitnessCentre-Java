/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness_centre.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fitness_centre.model.*;
import fitness_centre.controller.*;
import fitness_centre.main.DatabaseManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author Андрей
 */
public class AppFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 900;
    
    private static Logger logger = LoggerFactory.getLogger(AppFrame.class);
    
    private List<TrainerEntity> trainers = new ArrayList<TrainerEntity>();
    private List<GymEntity> gyms = new ArrayList<GymEntity>();
    private List<GymEquipEntity> equips = new ArrayList<GymEquipEntity>();
    private List<SсheduleEntity> shedules = new ArrayList<SсheduleEntity>();
    
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem exitItem;
    private JTabbedPane tabbedPane;
    private JPanel panelTrainer;
    private JPanel panelGym;
    private JPanel panelGymEquip;
    private JPanel panelSchedule;
    private JTable tblTrainer;
    private JTable tblGym;
    private JTable tblGymEquip;
    private JTable tblSchedule;
    
    public TrainerDAO trainerDAO;
    public GymDAO gymDAO;
    public GymEquipDAO equipDAO;
    public ScheduleDAO sheduleDAO;
    
    private TrainerTableModel tmTrainer;
    private GymTableModel tmGym;
    private GymEquipTableModel tmGymEquip;
    private ScheduleTableModel tmSchedule;
    
    public AppFrame(String title, DatabaseManager cm) {
        super(title);
        setResizable(true);
        defineCloseBehaviour();
        
        trainerDAO = new TrainerDAO(cm);
        gymDAO = new GymDAO(cm);
        equipDAO = new GymEquipDAO(cm);
        sheduleDAO = new ScheduleDAO(cm);
        
        initMainMenu();

        tabbedPane = new JTabbedPane();
        tabbedPane.setVisible(false);

        panelTrainer = new JPanel();
        panelTrainer.setLayout(new BorderLayout());

        panelGym = new JPanel();
        panelGym.setLayout(new BorderLayout());
        
        panelGymEquip = new JPanel();
        panelGymEquip.setLayout(new BorderLayout());

        panelSchedule = new JPanel();
        panelSchedule.setLayout(new BorderLayout());

        tabbedPane.addTab("Тренеры", panelTrainer);
        tabbedPane.addTab("Залы", panelGym);
        tabbedPane.addTab("Инвентарь", panelGymEquip);
        tabbedPane.addTab("Расписание", panelSchedule);

        tmTrainer = new TrainerTableModel(trainers);
        tblTrainer = new JTable(tmTrainer);
        
        tblTrainer.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    TrainerDialog trf = new TrainerDialog("Тренер: редактирование",
                            tmTrainer.getTrainerByRowIndex(tblTrainer.getSelectedRow()),
                            false, AppFrame.this);
                    trf.setVisible(true);
                }
            }
        });
    
        tmGym = new GymTableModel(gyms);
        tblGym = new JTable(tmGym);

        tblGym.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    GymDialog gfr = new GymDialog("Зал: редактирование",
                            tblGym, false, AppFrame.this);
                    gfr.setVisible(true);
                }
            }
        });
        
        tmGymEquip = new GymEquipTableModel(equips);
        tblGymEquip = new JTable(tmGymEquip);
        
        tblGymEquip.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    GymEquipDialog gef = new GymEquipDialog("Инвентарь: редактирование",
                            tblGymEquip, false, AppFrame.this);
                    gef.setVisible(true);
                }
            }
        });
        
        tmSchedule = new ScheduleTableModel(shedules);
        tblSchedule = new JTable(tmSchedule);
        
        tblSchedule.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    ScheduleDialog shf = new ScheduleDialog("Расписание: редактирование", tblSchedule, false, AppFrame.this);
                    shf.setVisible(true);
                }
            }
        });        
        
        panelTrainer.add(new JScrollPane(tblTrainer), BorderLayout.CENTER);
        panelGym.add(new JScrollPane(tblGym), BorderLayout.CENTER);
        panelGymEquip.add(new JScrollPane(tblGymEquip), BorderLayout.CENTER);
        panelSchedule.add(new JScrollPane(tblSchedule), BorderLayout.CENTER);
        getContentPane().add(tabbedPane);
    }

    private void initMainMenu() {
        menuBar = new JMenuBar();
        Font font = new Font("Verdana", Font.PLAIN, 11);
        
        fileMenu = new JMenu("Файл");
        fileMenu.setFont(font);
        openItem = new JMenuItem(new LoadDataAction("Открыть"));
        fileMenu.add(openItem);
        openItem.setFont(font);
        exitItem = new JMenuItem(new ExitAction("Выход"));
        exitItem.setFont(font);
        fileMenu.add(new JSeparator(JSeparator.HORIZONTAL));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        add(menuBar, BorderLayout.NORTH);
       
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((d.width - AppFrame.WIDTH) / 2, (d.height - AppFrame.HEIGHT) / 2,
                AppFrame.WIDTH, AppFrame.HEIGHT);
    }
    
    public void reloadTrainers() {        
        LoadDataAction lda = new LoadDataAction("Обновить");
        try {
            trainers.clear();
            trainers.addAll(trainerDAO.findAll());  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(AppFrame.this, "Cannot load data: "
                + ex.getMessage(), "Data Load Error", JOptionPane.ERROR_MESSAGE);
        }
        tmTrainer.fireTableDataChanged();
    }
    
    private class LoadDataAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                trainers.clear();
                trainers.addAll(trainerDAO.findAll());
                gyms.clear();
                gyms.addAll(gymDAO.findAll());
                equips.clear();
                equips.addAll(equipDAO.findAll());
                shedules.clear();
                shedules.addAll(sheduleDAO.findAll());
                tabbedPane.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(AppFrame.this, "Cannot load data: "
                        + ex.getMessage(), "Data Load Error", JOptionPane.ERROR_MESSAGE);
            }
            tmTrainer.fireTableDataChanged();
            tmGym.fireTableDataChanged();
            tmGymEquip.fireTableDataChanged();
            tmSchedule.fireTableDataChanged();
        }

        public LoadDataAction(String name) {
            super(name);
        }
    }
   
    private class ExitAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (WindowListener lsnr : AppFrame.this.getWindowListeners()) {
                lsnr.windowClosing(new WindowEvent(AppFrame.this, 0));
            }
        }

        private ExitAction(String name) {
            super(name);
        }
    }

    private void defineCloseBehaviour() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Да", "Нет"};
                int n = JOptionPane.showOptionDialog(e.getWindow(),
                        "Вы уверены, что хотите выйти?", "Фитнес-центр",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                if (n == 0) {
                    e.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
        });
    }
}
