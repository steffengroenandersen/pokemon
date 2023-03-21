package dk.kea.pokemon.repository;

import dk.kea.pokemon.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {


    // DATABASE PROPERTIES
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pokedex";
    private final static String UID = "root";
    private final static String PWD = "sesame80";

    public List<Pokemon> getAllPokemons(){

        List<Pokemon> pokemonList = new ArrayList<>();

        try{
            // ESTABLISH CONNECTION TO DATABASE
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();

            // CREATE SQL_QUERY AND EXECUTE
            final String SQL_QUERY = "SELECT * FROM pokemon";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            // READ resultSet AND ADD TO pokemonList
            while(resultSet.next()){

                // FETCH DATA
                int pokedex_number = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int speed = resultSet.getInt(3);
                int special_defence = resultSet.getInt(4);
                int special_attack = resultSet.getInt(5);
                int defence = resultSet.getInt(6);
                int attack = resultSet.getInt(7);
                int hp = resultSet.getInt(8);
                String primary_type = resultSet.getString(9);
                String secondary_type = resultSet.getString(10);

                // CONSTRUCT POKEMON
                Pokemon pokemon = new Pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type);
                pokemonList.add(pokemon);

                System.out.println(pokemon);

            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return pokemonList;
    }

}
