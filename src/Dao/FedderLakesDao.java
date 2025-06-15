package Dao;

import Services.Lakes.Enum;
import Services.Lakes.FedderLakes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FedderLakesDao {



     //Pobieranie wszystkich obiektow FedderLake z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<FedderLakes> getAllFedderLakes() {
        List<FedderLakes> fedderLakesList = new ArrayList<>();
        String query = "SELECT l.id_lowiska, l.powierzchnia, l.max_glebokosc, l.liczba_stanowisk, l.rodzaj_ryb, l.rodzaj_lowiska, l.cena_wstepu, l.godziny_otwarcia, " +
                "f.czy_mozna_necic_lodka, f.zakazane_smaki_zanety " +
                "FROM lakes l " +
                "JOIN fedder_lakes f ON l.id_lowiska = f.id_lowiska";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FedderLakes fedderLake = new FedderLakes(
                        resultSet.getInt("id_lowiska"),
                        resultSet.getDouble("powierzchnia"),
                        resultSet.getDouble("max_glebokosc"),
                        resultSet.getInt("liczba_stanowisk"),
                        resultSet.getString("rodzaj_ryb"),
                        Enum.fromValue(resultSet.getInt("rodzaj_lowiska")),
                        resultSet.getDouble("cena_wstepu"),
                        resultSet.getString("godziny_otwarcia"),
                        resultSet.getBoolean("czy_mozna_necic_lodka"),
                        resultSet.getString("zakazane_smaki_zanety")
                );
                fedderLakesList.add(fedderLake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fedderLakesList;
    }



}