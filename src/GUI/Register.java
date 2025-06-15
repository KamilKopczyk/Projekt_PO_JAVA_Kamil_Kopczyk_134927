package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Dao.AddUsers;

public class Register extends JFrame {
    private JTextField Login;
    private JPasswordField Hasło;
    private JPasswordField Hasło2;
    private JTextField e_mail;
    private JTextField Imię;
    private JTextField Nazwisko;
    private JTextField telefon;
    private JButton powrótButton;
    private JButton zarejestrujButton1;
    private JButton wyjdźButton;
    private JPanel panel2;

    public Register() {
        super("Strona główna");
        this.setContentPane(this.panel2);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 1980, height = 1080;
        this.setSize(width, height);

        String password = new String(Hasło.getPassword());
        String confirmPassword = new String(Hasło2.getPassword());

        zarejestrujButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = Login.getText();
                String email = e_mail.getText();
                String password = new String(Hasło.getPassword());
                String confirmPassword = new String(Hasło2.getPassword());
                String imię = Imię.getText();
                String nazwisko = Nazwisko.getText();
                String telefonText = telefon.getText();

                if (imię.isEmpty() || nazwisko.isEmpty() || login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || telefonText.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione!", "Błąd", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Hasła nie są takie same. Spróbuj ponownie.");
                    return;
                }

                if (AddUsers.isLoginOrEmailExists(login, email)) {
                    JOptionPane.showMessageDialog(null, "Login lub e-mail już istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (AddUsers.addUser(login, email, password, imię, nazwisko, telefonText)) {
                    JOptionPane.showMessageDialog(null, "Rejestracja zakończona sukcesem!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    MainPage mainPage = new MainPage();
                    mainPage.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas rejestracji!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
    }
}