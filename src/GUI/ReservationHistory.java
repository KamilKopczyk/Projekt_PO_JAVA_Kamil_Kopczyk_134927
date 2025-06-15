package GUI;

import Services.SessionManager;
import Dao.DataBaseConnection;
import Dao.ReservationDao;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ReservationHistory extends JFrame{
    private JLabel zalogowano;
    private JTabbedPane tabbedPane1;
    private JPanel reservationsColumns;
    private JTable reservationstable;
    private JButton powrótButton;
    private JButton wyjdźButton;
    private JPanel panel7;
    private JFrame previousFrame;

    public ReservationHistory() {
        super("Historia Rezerwacji");
        this.previousFrame = previousFrame;
        this.setContentPane(this.panel7);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1980, 1080);

        String[] reservationsColumns = {"id","id_users", "id_lowiska", "id_wedki", "id_domu", "cena_wynajmu", "data_rozpoczecia", "data_zakonczenia"};

        reservationstable.setModel(new DefaultTableModel(null, reservationsColumns));

        JScrollPane reservationsScrollPane = new JScrollPane(reservationstable);

        tabbedPane1.setComponentAt(0, reservationsScrollPane);


        loadReservationHistory();


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
    }


    public void loadReservationHistory() {
        int userId = SessionManager.getCurrentUserId();
        if (userId == 0) {
            JOptionPane.showMessageDialog(null, "Aby zobaczyć historię rezerwacji, musisz się zalogować.");
            return;
        }

        List<String[]> history = ReservationDao.getReservationHistoryByUserId(userId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("ID Users");
        model.addColumn("ID Łowiska");
        model.addColumn("ID Wędki");
        model.addColumn("ID Domu");
        model.addColumn("Cena Wynajmu");
        model.addColumn("Data Rozpoczęcia");
        model.addColumn("Data Zakończenia");

        for (String[] row : history) {

            row[6] = formatDate(row[6]);
            row[7] = formatDate(row[7]);
            model.addRow(row);
        }

        reservationstable.setModel(model);
    }

    private String formatDate(String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            return outputFormat.format(inputFormat.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }



    private int getCurrentUserId() {

        return SessionManager.getCurrentUserId();
    }


    public void setLoginText(String login) {
        if (login.startsWith("Zalogowano jako: ")) {
            zalogowano.setText(login);
        } else {
            zalogowano.setText("Zalogowano jako: " + login);
        }
    }

}
