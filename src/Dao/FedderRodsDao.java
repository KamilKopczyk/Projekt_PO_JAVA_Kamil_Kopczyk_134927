package Dao;

import Services.Wędki.Enum2;
import Services.Wędki.FedderRods;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FedderRodsDao {


    //Pobieranie wszystkich obiektow FedderRods z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<FedderRods> getAllFedderRods() {
        List<FedderRods> fedderRodsList = new ArrayList<>();
        String query = "SELECT l.*, f.* FROM fishingrods l JOIN fedderrods f ON l.id_wedki = f.id_wedki";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FedderRods fedderRod = new FedderRods(
                        resultSet.getInt("id_wedki"),
                        resultSet.getString("kolowrotek"),
                        resultSet.getString("wedzisko"),
                        resultSet.getString("zylka"),
                        resultSet.getDouble("stan"),
                        Enum2.fromValue(resultSet.getInt("rodzaj_wedki")),
                        resultSet.getDouble("cena_wypozyczenia"),
                        resultSet.getString("podajnik")
                );
                fedderRodsList.add(fedderRod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fedderRodsList;
    }
}
