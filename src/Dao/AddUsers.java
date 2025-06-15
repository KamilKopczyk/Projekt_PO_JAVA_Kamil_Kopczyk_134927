package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//dodwanie użytkowników do bazy danych

public class AddUsers {
    public static boolean addUser(String Login, String Hasło, String e_mail, String Imię, String Nazwisko, String nr_telefonu) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "INSERT INTO Users (Login, Hasło, e_mail, Imię, Nazwisko, nr_telefonu) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Login);
            preparedStatement.setString(3, Hasło);
            preparedStatement.setString(2, e_mail);
            preparedStatement.setString(4, Imię);
            preparedStatement.setString(5, Nazwisko);
            preparedStatement.setString(6, nr_telefonu);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//sprawdzanie czy login lub email sie nie powtarza w bazie danych

    public static boolean isLoginOrEmailExists(String Login, String e_mail) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE Login = ? OR e_mail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Login);
            preparedStatement.setString(2, e_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //sprawdzanie czy konto istnieje w bazie danych

    public static boolean isAccountValid(String login, String password) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE Login = ? AND Hasło = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //pobieranie id użytkownika na podstawie loginu

    public static int getUserIdByLogin(String login) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT id_users FROM Users WHERE Login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



}