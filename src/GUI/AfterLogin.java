package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AfterLogin extends JFrame{
    private JPanel panel3;
    private JButton lake;
    private JButton rod;
    private JButton house;
    private JButton powrótButton;
    private JButton wyjdźButton;
    public JLabel zalogowano;
    private JButton szczegółyKontaButton;

    public AfterLogin() {
        super("Strona główna");
        this.setContentPane(this.panel3);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 1980, height = 1080;
        this.setSize(width, height);


        powrótButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);

            }
        });

        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        lake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                Lake lakeForm = new Lake();
                lakeForm.setLoginText(zalogowano.getText());
                lakeForm.setVisible(true);



            }
        });


        rod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Rods rodsForm = new Rods();
                rodsForm.setLoginText(zalogowano.getText());
                rodsForm.setVisible(true);

            }
        });

        house.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HousesGui housesGui = new HousesGui();
                housesGui.setLoginText(zalogowano.getText());
                housesGui.setVisible(true);
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
    }

    public void setLoginText(String login) {
        if (login.startsWith("Zalogowano jako: ")) {
            zalogowano.setText(login);
        } else {
            zalogowano.setText("Zalogowano jako: " + login);
        }
    }
}
