package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import Dao.DataBaseConnection;







public class ReservationDao {



    //Dodaje rezerwację lake do tabeli reservation_history

    public static boolean addReservation(int userId, int lakeId, String startDate, String endDate) {
        String query = "INSERT INTO reservation_history (id_users, id_lowiska, cena_wynajmu, data_rozpoczecia, data_zakonczenia) " +
                "VALUES (?, ?, (SELECT cena_wstepu FROM lakes WHERE id_lowiska = ?) * GREATEST(DATEDIFF(?, ?), 1), ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, lakeId);
            preparedStatement.setInt(3, lakeId);
            preparedStatement.setString(4, endDate);
            preparedStatement.setString(5, startDate);
            preparedStatement.setString(6, startDate);
            preparedStatement.setString(7, endDate);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Pobiera historię rezerwacji dla danego użytkownika z tabeli reservation_history

    public static List<String[]> getReservationHistoryByUserId(int userId) {
        List<String[]> history = new ArrayList<>();
        String query = "SELECT id, id_users, id_lowiska, id_wedki, id_domu, cena_wynajmu, data_rozpoczecia, data_zakonczenia " +
                "FROM reservation_history WHERE id_users = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                history.add(new String[]{
                        String.valueOf(resultSet.getInt("id")),
                        String.valueOf(resultSet.getInt("id_users")),
                        String.valueOf(resultSet.getInt("id_lowiska")),
                        String.valueOf(resultSet.getInt("id_wedki")),
                        String.valueOf(resultSet.getInt("id_domu")),
                        String.valueOf(resultSet.getDouble("cena_wynajmu")),
                        resultSet.getString("data_rozpoczecia"),
                        resultSet.getString("data_zakonczenia")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    //Pobiera aktualnie zarezerwowane łowiska z tabeli reservation_history

    public static List<Integer> getCurrentlyReservedLakes() {
        List<Integer> reservedLakes = new ArrayList<>();
        String query = "SELECT DISTINCT id_lowiska FROM reservation_history WHERE NOW() BETWEEN data_rozpoczecia AND data_zakonczenia";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                reservedLakes.add(resultSet.getInt("id_lowiska"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservedLakes;
    }

    //Dodaje rezerwację rod do tabeli reservation_history

    public static boolean addRodReservation(int userId, int rodId, String startDate, String endDate) {
        String query = "INSERT INTO reservation_history (id_users, id_wedki, cena_wynajmu, data_rozpoczecia, data_zakonczenia) " +
                "VALUES (?, ?, (SELECT cena_wypozyczenia FROM fishingrods WHERE id_wedki = ?) * GREATEST(DATEDIFF(?, ?), 1), ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, rodId);
            preparedStatement.setInt(3, rodId);
            preparedStatement.setString(4, endDate);
            preparedStatement.setString(5, startDate);
            preparedStatement.setString(6, startDate);
            preparedStatement.setString(7, endDate);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Pobiera aktualnie zarezerwowane wędki z tabeli reservation_history

    public static List<Integer> getCurrentlyReservedRods(String startDate, String endDate) {
        List<Integer> reservedRods = new ArrayList<>();
        String query = "SELECT id_wedki FROM reservation_history " +
                "WHERE (data_rozpoczecia < ? AND data_zakonczenia > ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, endDate);
            preparedStatement.setString(2, startDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservedRods.add(resultSet.getInt("id_wedki"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservedRods;
    }


    //Dodaje rezerwację Houses do tabeli reservation_history

    public static boolean addHouseReservation(int userId, int houseId, String startDate, String endDate) {
        String query = "INSERT INTO reservation_history (id_users, id_domu, cena_wynajmu, data_rozpoczecia, data_zakonczenia) " +
                "VALUES (?, ?, (SELECT cena_wynajmu FROM houses WHERE id_domu = ?) * GREATEST(DATEDIFF(?, ?), 1), ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, houseId);
            preparedStatement.setInt(3, houseId);
            preparedStatement.setString(4, endDate);
            preparedStatement.setString(5, startDate);
            preparedStatement.setString(6, startDate);
            preparedStatement.setString(7, endDate);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Pobiera aktualnie zarezerwowane Houses z tabeli reservation_history

    public static List<Integer> getCurrentlyReservedHouses(String startDate, String endDate) {
        List<Integer> reservedHouses = new ArrayList<>();
        String query = "SELECT id_domu FROM reservation_history " +
                "WHERE (data_rozpoczecia < ? AND data_zakonczenia > ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, endDate);
            preparedStatement.setString(2, startDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservedHouses.add(resultSet.getInt("id_domu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservedHouses;
    }



}

