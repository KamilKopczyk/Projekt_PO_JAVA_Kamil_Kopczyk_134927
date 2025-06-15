package Dao;

import Services.Lakes.Enum;
import Services.Lakes.SpiningLakes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpiningLakesDao {

    //Pobieranie wszystkich obiektow SpiningLakes z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<SpiningLakes> getAllSpiningLakes() {
        List<SpiningLakes> spiningLakesList = new ArrayList<>();
        String query = "SELECT l.id_lowiska, l.powierzchnia, l.max_glebokosc, l.liczba_stanowisk, l.rodzaj_ryb, l.rodzaj_lowiska, l.cena_wstepu, l.godziny_otwarcia, " +
                "f.czy_mozna_lowic_z_lodki, f.czy_mozna_zabierac_ryby, f.typ_dna " +
                "FROM lakes l " +
                "JOIN spining_lakes f ON l.id_lowiska = f.id_lowiska";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                SpiningLakes spiningLake = new SpiningLakes(
                        resultSet.getInt("id_lowiska"),
                        resultSet.getDouble("powierzchnia"),
                        resultSet.getDouble("max_glebokosc"),
                        resultSet.getInt("liczba_stanowisk"),
                        resultSet.getString("rodzaj_ryb"),
                        Enum.fromValue(resultSet.getInt("rodzaj_lowiska")),
                        resultSet.getDouble("cena_wstepu"),
                        resultSet.getString("godziny_otwarcia"),
                        resultSet.getBoolean("czy_mozna_lowic_z_lodki"),
                        resultSet.getBoolean("czy_mozna_zabierac_ryby"),
                        resultSet.getString("typ_dna")
                );
                spiningLakesList.add(spiningLake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spiningLakesList;
    }



}