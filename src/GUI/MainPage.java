package GUI;

import Dao.AddUsers;
import Services.SessionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame{
    private JPanel panel1;
    private JTextField Login;
    private JButton zalogujButton;
    private JButton zarejestrujButton;
    private JButton kontynuujBezLogowaniaButton;
    private JButton wyjdźButton;
    private JPasswordField Hasło;


    public MainPage() {
        super("Logowanie");
        this.setContentPane(this.panel1);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 1980, height = 1080;
        this.setSize(width, height);


        zarejestrujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register register = new Register();
                register.setVisible(true);
            }
        });

        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = Login.getText();
                String password = new String(Hasło.getPassword());

                if (login.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Proszę wprowadzić login i hasło.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (AddUsers.isAccountValid(login, password)) {
                    int userId = AddUsers.getUserIdByLogin(login);
                    SessionManager.setCurrentUserId(userId);
                    dispose();
                    AfterLogin afterLogin = new AfterLogin();
                    afterLogin.setLoginText(login);
                    Lake lake = new Lake();
                    lake.setLoginText(login);
                    Rods rods = new Rods();
                    rods.setLoginText(login);
                    HousesGui housesGui = new HousesGui();
                    housesGui.setLoginText(login);
                    ReservationHistory reservationhistory = new ReservationHistory();
                    reservationhistory.setLoginText(login);
                    afterLogin.setVisible(true);



                } else {
                    ImageIcon customIcon = new ImageIcon("C:\\Users\\kamil\\IdeaProjects\\ProjektPO_Kamil_Kopczyk_134927\\src\\Figures\\pytajnik.png");
                    int response = JOptionPane.showOptionDialog(
                            null,
                            "Konto nie istnieje. Czy chcesz się zarejestrować?",
                            "Nie znaleziono konta",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            customIcon,
                            new String[]{"Zarejestruj", "Kontynuuj logowanie"},
                            null
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        dispose();
                        Register register = new Register();
                        register.setVisible(true);
                    }
                }
            }
        });


        kontynuujBezLogowaniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AfterLogin afterLogin = new AfterLogin();
                afterLogin.setLoginText("Gość");
                afterLogin.setVisible(true);


            }
        });
    }


}

