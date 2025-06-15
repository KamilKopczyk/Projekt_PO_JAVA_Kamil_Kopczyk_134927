package Dao;

import Services.Houses.Houses;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HousesDao {

    //Pobieranie wszystkich obiektow Houses z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<Houses> getAllHouses() {
        List<Houses> housesList = new ArrayList<>();
        String query = "SELECT * FROM houses";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Houses houses = new Houses(
                        resultSet.getInt("id_domu"),
                        resultSet.getInt("ilu_osobowy"),
                        resultSet.getInt("przy_jakim_lowisku"),
                        resultSet.getDouble("cena_wynajmu")

                );
                housesList.add(houses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return housesList;
    }

}
