package Dao;

import Services.Wędki.Enum2;
import Services.Wędki.FloatRods;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FloatRodsDao {


    //Pobieranie wszystkich obiektow FloatRods z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<FloatRods> getAllFloatRods() {
        List<FloatRods> floatRodsList = new ArrayList<>();
        String query = "SELECT l.*, f.* FROM fishingrods l JOIN floatrods f ON l.id_wedki = f.id_wedki";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FloatRods floatRod = new FloatRods(
                        resultSet.getInt("id_wedki"),
                        resultSet.getString("kolowrotek"),
                        resultSet.getString("wedzisko"),
                        resultSet.getString("zylka"),
                        resultSet.getDouble("stan"),
                        Enum2.fromValue(resultSet.getInt("rodzaj_wedki")),
                        resultSet.getDouble("cena_wypozyczenia"),
                        resultSet.getString("splawik")
                );
                floatRodsList.add(floatRod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return floatRodsList;
    }
}