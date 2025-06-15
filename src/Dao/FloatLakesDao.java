package Dao;

import Services.Lakes.Enum;
import Services.Lakes.FloatLakes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FloatLakesDao {


    //Pobieranie wszystkich obiektow FloatLakes z bazy danych i zwrocenie ich jako lista obiektow.

    public static List<FloatLakes> getAllFloatLakes() {
        List<FloatLakes> floatLakesList = new ArrayList<>();
        String query = "SELECT l.id_lowiska, l.powierzchnia, l.max_glebokosc, l.liczba_stanowisk, l.rodzaj_ryb, l.rodzaj_lowiska, l.cena_wstepu, l.godziny_otwarcia, " +
                "f.czy_mozna_lowic_z_lodki, f.czy_haczyki_z_zadziorami " +
                "FROM lakes l " +
                "JOIN float_lakes f ON l.id_lowiska = f.id_lowiska";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FloatLakes floatLake = new FloatLakes(
                        resultSet.getInt("id_lowiska"),
                        resultSet.getDouble("powierzchnia"),
                        resultSet.getDouble("max_glebokosc"),
                        resultSet.getInt("liczba_stanowisk"),
                        resultSet.getString("rodzaj_ryb"),
                        Enum.fromValue(resultSet.getInt("rodzaj_lowiska")),
                        resultSet.getDouble("cena_wstepu"),
                        resultSet.getString("godziny_otwarcia"),
                        resultSet.getBoolean("czy_mozna_lowic_z_lodki"),
                        resultSet.getBoolean("czy_haczyki_z_zadziorami")
                );
                floatLakesList.add(floatLake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return floatLakesList;
    }



}