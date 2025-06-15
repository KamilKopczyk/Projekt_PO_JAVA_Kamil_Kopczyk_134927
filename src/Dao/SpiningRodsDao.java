package Dao;

import Services.Wędki.Enum2;
import Services.Wędki.SpiningRods;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SpiningRodsDao {


    //Pobieranie wszystkich obiektow SpiningRods z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<SpiningRods> getAllSpiningRods() {
        List<SpiningRods> spiningRodsList = new ArrayList<>();
        String query = "SELECT l.*, f.* FROM fishingrods l JOIN spiningrods f ON l.id_wedki = f.id_wedki";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SpiningRods spiningRod = new SpiningRods(
                        resultSet.getInt("id_wedki"),
                        resultSet.getString("kolowrotek"),
                        resultSet.getString("wedzisko"),
                        resultSet.getString("zylka"),
                        resultSet.getDouble("stan"),
                        Enum2.fromValue(resultSet.getInt("rodzaj_wedki")),
                        resultSet.getDouble("cena_wypozyczenia"),
                        resultSet.getString("przyneta")
                );
                spiningRodsList.add(spiningRod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spiningRodsList;
    }
}
