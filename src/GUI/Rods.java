package GUI;

import Dao.SpiningRodsDao;
import Services.Wędki.SpiningRods;
import Dao.FloatRodsDao;
import Services.Wędki.FloatRods;
import Dao.FedderRodsDao;
import Services.Wędki.FedderRods;
import Dao.ReservationDao;
import Services.SessionManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import Dao.DataBaseConnection;

public class Rods extends JFrame{
    private JLabel zalogowano;
    private JTabbedPane tabbedPane1;
    private JPanel floatColumns;
    private JTable floattable;
    private JPanel spiningColumns;
    private JTable spiningtable;
    private JPanel fedderColumns;
    private JTable feddertable;
    private JButton powrótButton;
    private JButton rezerwujButton;
    private JButton wyjdźButton;
    private JPanel panel5;
    private JButton szczegółyKontaButton;
    private JSpinner endDatePicker;
    private JSpinner startDatePicker;

    public Rods() {

        super("Rezerwacja Wędek");
        this.setContentPane(this.panel5);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1980, 1080);

        startDatePicker.setModel(new SpinnerDateModel());
        endDatePicker.setModel(new SpinnerDateModel());

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDatePicker, "yyyy-MM-dd HH:mm:ss");
        startDatePicker.setEditor(startEditor);

        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDatePicker, "yyyy-MM-dd HH:mm:ss");
        endDatePicker.setEditor(endEditor);

        String[] floatColumns = {"ID Wedki", "Kołowrotek", "Wędzisko", "Żyłka", "Stan", "Rodzaj Wędki", "Cena Wypożyczenia", "Spławik"};
        String[] spiningColumns = {"ID Wedki", "Kołowrotek", "Wędzisko", "Żyłka", "Stan", "Rodzaj Wędki", "Cena Wypożyczenia", "Przynęta"};
        String[] fedderColumns = {"ID Wedki", "Kołowrotek", "Wędzisko", "Żyłka", "Stan", "Rodzaj Wędki", "Cena Wypożyczenia", "Podajnik"};

        floattable.setModel(new DefaultTableModel(null, floatColumns));
        spiningtable.setModel(new DefaultTableModel(null, spiningColumns));
        feddertable.setModel(new DefaultTableModel(null, fedderColumns));

        JScrollPane floatScrollPane = new JScrollPane(floattable);
        JScrollPane spiningScrollPane = new JScrollPane(spiningtable);
        JScrollPane fedderScrollPane = new JScrollPane(feddertable);

        tabbedPane1.setComponentAt(0, floatScrollPane);
        tabbedPane1.setComponentAt(1, spiningScrollPane);
        tabbedPane1.setComponentAt(2, fedderScrollPane);


        loadSpiningRodsData();
        loadFloatRodsData();
        loadFedderRodsData();


        powrótButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AfterLogin afterLogin = new AfterLogin();
                afterLogin.setLoginText(zalogowano.getText());
                afterLogin.setVisible(true);
            }
        });

        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        szczegółyKontaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ReservationHistory reservationHistory = new ReservationHistory();
                reservationHistory.setLoginText(zalogowano.getText());
                reservationHistory.setVisible(true);

            }
        });

        rezerwujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable selectedTable;
                int selectedTabIndex = tabbedPane1.getSelectedIndex();
                if (selectedTabIndex == 0) {
                    selectedTable = floattable;
                } else if (selectedTabIndex == 1) {
                    selectedTable = spiningtable;
                } else {
                    selectedTable = feddertable;
                }

                int selectedRow = selectedTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Nie wybrano wiersza. Wybierz wiersz, aby kontynuować.");
                    return;
                }

                int idWedki = (int) selectedTable.getValueAt(selectedRow, 0);
                int idUsers = SessionManager.getCurrentUserId();
                if (idUsers == 0) {
                    JOptionPane.showMessageDialog(null, "Aby dokonać rezerwacji musisz być zalogowany.");
                    return;
                }

                String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
                String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());

                // Validate date range
                try {
                    Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
                    Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
                    if (!end.after(start)) {
                        JOptionPane.showMessageDialog(null, "Data zakończenia musi być późniejsza niż data rozpoczęcia.");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nieprawidłowy format daty.");
                    return;
                }

                // Add reservation
                boolean success = ReservationDao.addRodReservation(idUsers, idWedki, startDate, endDate);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Rezerwacja udana!");
                    ((DefaultTableModel) selectedTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Rezerwacja nie powiodła się. Spróbuj ponownie.");
                }
            }
        });
    }

    public void loadSpiningRodsData() {
        List<SpiningRods> spiningRodsList = SpiningRodsDao.getAllSpiningRods();
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());
        List<Integer> reservedRods = ReservationDao.getCurrentlyReservedRods(startDate, endDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Wedki");
        model.addColumn("Kolowrotek");
        model.addColumn("Wedzisko");
        model.addColumn("Zylka");
        model.addColumn("Stan");
        model.addColumn("Rodzaj Wędki");
        model.addColumn("Cena Wypożyczenia");
        model.addColumn("Przynęta");

        for (SpiningRods rods : spiningRodsList) {
            if (!reservedRods.contains(rods.getId_wedki())) {
                model.addRow(new Object[]{
                        rods.getId_wedki(),
                        rods.getKolowrotek(),
                        rods.getWedzisko(),
                        rods.getZylka(),
                        rods.getStan(),
                        rods.getRodzaj_wedki(),
                        rods.getCena_wypozyczenia(),
                        rods.getPrzyneta()
                });
            }
        }

        spiningtable.setModel(model);
    }

    public void loadFloatRodsData() {
        List<FloatRods> floatRodsList = FloatRodsDao.getAllFloatRods();
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());
        List<Integer> reservedRods = ReservationDao.getCurrentlyReservedRods(startDate, endDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Wedki");
        model.addColumn("Kolowrotek");
        model.addColumn("Wedzisko");
        model.addColumn("Zylka");
        model.addColumn("Stan");
        model.addColumn("Rodzaj Wędki");
        model.addColumn("Cena Wypożyczenia");
        model.addColumn("Spławik");

        for (FloatRods rods : floatRodsList) {
            if (!reservedRods.contains(rods.getId_wedki())) {
                model.addRow(new Object[]{
                        rods.getId_wedki(),
                        rods.getKolowrotek(),
                        rods.getWedzisko(),
                        rods.getZylka(),
                        rods.getStan(),
                        rods.getRodzaj_wedki(),
                        rods.getCena_wypozyczenia(),
                        rods.getSpławik()
                });
            }
        }

        floattable.setModel(model);
    }

    public void loadFedderRodsData() {
        List<FedderRods> fedderRodsList = FedderRodsDao.getAllFedderRods();
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());
        List<Integer> reservedRods = ReservationDao.getCurrentlyReservedRods(startDate, endDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Wedki");
        model.addColumn("Kolowrotek");
        model.addColumn("Wedzisko");
        model.addColumn("Zylka");
        model.addColumn("Stan");
        model.addColumn("Rodzaj Wędki");
        model.addColumn("Cena Wypożyczenia");
        model.addColumn("Podajnik");

        for (FedderRods rods : fedderRodsList) {
            if (!reservedRods.contains(rods.getId_wedki())) {
                model.addRow(new Object[]{
                        rods.getId_wedki(),
                        rods.getKolowrotek(),
                        rods.getWedzisko(),
                        rods.getZylka(),
                        rods.getStan(),
                        rods.getRodzaj_wedki(),
                        rods.getCena_wypozyczenia(),
                        rods.getPodajnik()
                });
            }
        }

        feddertable.setModel(model);
    }


    public void setLoginText(String login) {
        if (login.startsWith("Zalogowano jako: ")) {
            zalogowano.setText(login);
        } else {
            zalogowano.setText("Zalogowano jako: " + login);
        }
    }

}
