package GUI;

import Dao.DataBaseConnection;
import Dao.FedderLakesDao;
import Services.Lakes.FedderLakes;
import Services.SessionManager;
import java.sql.ResultSet;
import Dao.FloatLakesDao;
import Services.Lakes.FloatLakes;
import Dao.ReservationDao;
import Dao.SpiningLakesDao;
import Services.Lakes.SpiningLakes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Lake extends JFrame{
    private JButton powrótButton;
    private JButton rezerwujButton;
    private JButton wyjdźButton;
    private JPanel panel4;
    private JTabbedPane tabbedPane1;
    private JTable floattable;
    private JTable spiningtable;
    private JTable feddertable;
    private JPanel floatColumns;
    private JPanel spiningColumns;
    private JPanel fedderColumns;
    private JLabel zalogowano;
    private JSpinner endDatePicker;
    private JSpinner startDatePicker;
    private JButton szczegółyKontaButton;

    public Lake() {
        super("Wynajmowanie Łowisk");
        this.setContentPane(this.panel4);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1980, 1080);

        String formattedStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
        String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());

        int selectedRow = floattable.getSelectedRow();
        JTable selectedTable = floattable;

        selectedRow = spiningtable.getSelectedRow();
        selectedTable = spiningtable;

        selectedRow = feddertable.getSelectedRow();
        selectedTable = feddertable;

        startDatePicker.setModel(new SpinnerDateModel());
        endDatePicker.setModel(new SpinnerDateModel());

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDatePicker, "yyyy-MM-dd HH:mm:ss");
        startDatePicker.setEditor(startEditor);

        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDatePicker, "yyyy-MM-dd HH:mm:ss");
        endDatePicker.setEditor(endEditor);

        String[] floatColumns = {"ID Łowiska", "Powierzchnia", "Max Głębokość", "Liczba Stanowisk", "Rodzaj Ryb", "Rodzaj Łowiska", "Cena Wstępu", "Godziny Otwarcia", "Czy Można Łowić z Łódki", "Czy Haczyki z Zadziorami"};
        String[] spiningColumns = {"ID Łowiska", "Powierzchnia", "Max Głębokość", "Liczba Stanowisk", "Rodzaj Ryb", "Rodzaj Łowiska", "Cena Wstępu", "Godziny Otwarcia", "Czy Można Łowić z Łódki", "Czy Można Zabierać Ryby", "Typ Dna"};
        String[] fedderColumns = {"ID Łowiska", "Powierzchnia", "Max Głębokość", "Liczba Stanowisk", "Rodzaj Ryb", "Rodzaj Łowiska", "Cena Wstępu", "Godziny Otwarcia", "Czy Można Nęcić Łódką", "Zakazane Smaki Zanęty"};

        floattable.setModel(new DefaultTableModel(null, floatColumns));
        spiningtable.setModel(new DefaultTableModel(null, spiningColumns));
        feddertable.setModel(new DefaultTableModel(null, fedderColumns));

        JScrollPane floatScrollPane = new JScrollPane(floattable);
        JScrollPane spiningScrollPane = new JScrollPane(spiningtable);
        JScrollPane fedderScrollPane = new JScrollPane(feddertable);

        tabbedPane1.setComponentAt(0, floatScrollPane);
        tabbedPane1.setComponentAt(1, spiningScrollPane);
        tabbedPane1.setComponentAt(2, fedderScrollPane);


        loadSpiningLakesData();
        loadFloatLakesData();
        loadFedderLakesData();


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

                int idLowiska = (int) selectedTable.getValueAt(selectedRow, 0);
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
                boolean success = ReservationDao.addReservation(idUsers, idLowiska, startDate, endDate);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Rezerwacja udana!");
                    ((DefaultTableModel) selectedTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Rezerwacja nie powiodła się. Spróbuj ponownie.");
                }
            }
        });
    }


    public void loadFedderLakesData() {
        List<FedderLakes> fedderLakesList = FedderLakesDao.getAllFedderLakes();
        List<Integer> reservedLakes = ReservationDao.getCurrentlyReservedLakes();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Łowiska");
        model.addColumn("Powierzchnia");
        model.addColumn("Max Głębokość");
        model.addColumn("Liczba Stanowisk");
        model.addColumn("Rodzaj Ryb");
        model.addColumn("Rodzaj Łowiska");
        model.addColumn("Cena Wstępu");
        model.addColumn("Godziny Otwarcia");
        model.addColumn("Czy Można Nęcić Łódką");
        model.addColumn("Czy Zakazane Smaki Zanęty");

        for (FedderLakes lake : fedderLakesList) {
            if (!reservedLakes.contains(lake.getId_lowiska())) {
                model.addRow(new Object[]{
                        lake.getId_lowiska(),
                        lake.getPowierzchnia(),
                        lake.getMax_glebokosc(),
                        lake.getLiczba_stanowisk(),
                        lake.getRodzaj_ryb(),
                        lake.getRodzaj_lowiska(),
                        lake.getCena_wstepu(),
                        lake.getGodziny_otwarcia(),
                        lake.isCzy_mozna_necic_lodka() ? "Tak" : "Nie",
                        lake.getZakazane_smaki_zanety()
                });
            }
        }


        feddertable.setModel(model);
    }

    public void loadFloatLakesData() {
        List<FloatLakes> floatLakesList = FloatLakesDao.getAllFloatLakes();
        List<Integer> reservedLakes = ReservationDao.getCurrentlyReservedLakes();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Łowiska");
        model.addColumn("Powierzchnia");
        model.addColumn("Max Głębokość");
        model.addColumn("Liczba Stanowisk");
        model.addColumn("Rodzaj Ryb");
        model.addColumn("Rodzaj Łowiska");
        model.addColumn("Cena Wstępu");
        model.addColumn("Godziny Otwarcia");
        model.addColumn("Czy Można Łowić z Łódki");
        model.addColumn("Czy Haczyki z Zadziorami");

        for (FloatLakes lake : floatLakesList) {
            if (!reservedLakes.contains(lake.getId_lowiska())) {
                model.addRow(new Object[]{
                        lake.getId_lowiska(),
                        lake.getPowierzchnia(),
                        lake.getMax_glebokosc(),
                        lake.getLiczba_stanowisk(),
                        lake.getRodzaj_ryb(),
                        lake.getRodzaj_lowiska(),
                        lake.getCena_wstepu(),
                        lake.getGodziny_otwarcia(),
                        lake.getCzy_mozna_lowic_z_lodki()? "Tak" : "Nie",
                        lake.getCzy_haczyki_z_zadziorami()? "Tak" : "Nie"
                });
            }
        }

        floattable.setModel(model);
    }

    public void loadSpiningLakesData() {
        List<SpiningLakes> spiningLakesList = SpiningLakesDao.getAllSpiningLakes();
        List<Integer> reservedLakes = ReservationDao.getCurrentlyReservedLakes();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Łowiska");
        model.addColumn("Powierzchnia");
        model.addColumn("Max Głębokość");
        model.addColumn("Liczba Stanowisk");
        model.addColumn("Rodzaj Ryb");
        model.addColumn("Rodzaj Łowiska");
        model.addColumn("Cena Wstępu");
        model.addColumn("Godziny Otwarcia");
        model.addColumn("Czy Można Łowić z Łódki");
        model.addColumn("Czy Można Zabierać Ryby");
        model.addColumn("Typ Dna");

        for (SpiningLakes lake : spiningLakesList) {
            if (!reservedLakes.contains(lake.getId_lowiska())) {
                model.addRow(new Object[]{
                        lake.getId_lowiska(),
                        lake.getPowierzchnia(),
                        lake.getMax_glebokosc(),
                        lake.getLiczba_stanowisk(),
                        lake.getRodzaj_ryb(),
                        lake.getRodzaj_lowiska(),
                        lake.getCena_wstepu(),
                        lake.getGodziny_otwarcia(),
                        lake.getCzy_mozna_lowic_z_lodki()? "Tak" : "Nie",
                        lake.getCzy_mozna_zabierac_ryby()? "Tak" : "Nie",
                        lake.getTyp_dna()
                });
            }
        }

        spiningtable.setModel(model);
    }

    public void setLoginText(String login) {
        if (login.startsWith("Zalogowano jako: ")) {
            zalogowano.setText(login);
        } else {
            zalogowano.setText("Zalogowano jako: " + login);
        }
    }


}


