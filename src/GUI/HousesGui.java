package GUI;

import Dao.*;
import Services.Houses.Houses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import Services.Lakes.FedderLakes;
import Services.Lakes.FloatLakes;
import Services.SessionManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class HousesGui extends JFrame{
    private JLabel zalogowano;
    private JTabbedPane tabbedPane1;
    private JPanel housesColumns;
    private JTable housestable;
    private JButton powrótButton;
    private JButton rezerwujButton;
    private JButton wyjdźButton;
    private JPanel panel6;
    private JButton szczegółyKontaButton;
    private JSpinner endDatePicker;
    private JSpinner startDatePicker;

    public HousesGui() {

        super("Rezerwacja Domów");
        this.setContentPane(this.panel6);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1980, 1080);

        startDatePicker.setModel(new SpinnerDateModel());
        endDatePicker.setModel(new SpinnerDateModel());

        String formattedStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
        String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDatePicker, "yyyy-MM-dd HH:mm:ss");
        startDatePicker.setEditor(startEditor);

        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDatePicker, "yyyy-MM-dd HH:mm:ss");
        endDatePicker.setEditor(endEditor);


        String[] housesColumns = {"ID domu", "Ilu osobowy", "Przy jakim łowisku", "Cena wynajmu"};

        housestable.setModel(new DefaultTableModel(null,housesColumns));


        JScrollPane housesScrollPane = new JScrollPane(housestable);


        tabbedPane1.setComponentAt(0, housesScrollPane);


        loadHousesData(formattedStartDate, formattedEndDate);



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
                int selectedRow = housestable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Nie wybrano wiersza. Wybierz wiersz, aby kontynuować.");
                    return;
                }

                Object idDomuValue = housestable.getValueAt(selectedRow, 0);
                int idDomu;
                try {
                    idDomu = Integer.parseInt(idDomuValue.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Nieprawidłowy format house ID.");
                    return;
                }

                int idUsers = SessionManager.getCurrentUserId();
                if (idUsers == 0) {
                    JOptionPane.showMessageDialog(null, "Aby dokonać rezerwacji musisz być zalogowany.");
                    return;
                }

                String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDatePicker.getValue());
                String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDatePicker.getValue());

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

                boolean success = ReservationDao.addHouseReservation(idUsers, idDomu, startDate, endDate);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Rezerwacja udana!");
                    ((DefaultTableModel) housestable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Rezerwacja nie powiodła się. Spróbuj ponownie.");
                }
            }
        });
    }


    public void loadHousesData(String startDate, String endDate) {
        List<Houses> housesList = HousesDao.getAllHouses();
        List<Integer> reservedHouses = ReservationDao.getCurrentlyReservedHouses(startDate, endDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Domu");
        model.addColumn("Ilu Osobowy");
        model.addColumn("Przy Jakim Łowisku");
        model.addColumn("Cena Wynajmu");

        for (Houses house : housesList) {
            if (!reservedHouses.contains(house.getId_domu())) {
                model.addRow(new Object[]{
                        house.getId_domu(),
                        house.getIlu_osobowy(),
                        house.getPrzy_jakim_lowisku(),
                        house.getCena_wynajmu()
                });
            }
        }

        housestable.setModel(model);
    }

    private String formatDate(String date) {
        if (date == null || date.trim().isEmpty() || date.equals("0")) {
            return "Invalid Date";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            return outputFormat.format(inputFormat.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }



    public void setLoginText(String login) {
        if (login.startsWith("Zalogowano jako: ")) {
            zalogowano.setText(login);
        } else {
            zalogowano.setText("Zalogowano jako: " + login);
        }
    }


}
