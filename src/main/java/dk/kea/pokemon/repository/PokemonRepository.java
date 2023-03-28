package dk.kea.pokemon.repository;

import dk.kea.pokemon.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {


    // DATABASE PROPERTIES
    /*
    private final static String DB_URL = "jdbc:mysql://kea2023-server.mysql.database.azure.com:3306/pokedex";
    private final static String UID = "root";
    private final static String PWD = "sesame80";
    */

    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String UID;
    @Value("${spring.datasource.password}")
    private String PWD;


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

    public void addPokemon(Pokemon pokemon){

        try{
            // CONNECT TO DATABASE
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            final String ADD_QUERY = "INSERT INTO pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY);

            // SET ATTRIBUTES IN PREPAREDSTATEMENT
            preparedStatement.setInt(1, pokemon.getPokedex_number());
            preparedStatement.setString(2, pokemon.getName());
            preparedStatement.setInt(3, pokemon.getSpeed());
            preparedStatement.setInt(4, pokemon.getSpecial_defence());
            preparedStatement.setInt(5, pokemon.getSpecial_attack());
            preparedStatement.setInt(6, pokemon.getDefence());
            preparedStatement.setInt(7, pokemon.getAttack());
            preparedStatement.setInt(8, pokemon.getHp());
            preparedStatement.setString(9, pokemon.getPrimary_type());
            preparedStatement.setString(10, pokemon.getSecondary_type());

            // EXECUTE STATEMENT
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println("Failure! Could not add object to database!");
            e.printStackTrace();
        }
    }

    public void updatePokemon(Pokemon pokemon){
        final String UPDATE_QUERY = "UPDATE pokemon SET" +
                "pokedex_number = ?," +
                "name = ?," +
                "speed = ?," +
                "special_defence = ?," +
                "special_attack = ?," +
                "defence = ?," +
                "attack = ?," +
                "hp = ?," +
                "primary_type = ?," +
                "secondary_type = ?" +
                " WHERE pokedex_number = ?";

        try{
            // CONNECT TO DATABASE
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD):
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            // SET PARAMETERS
            int pokedex_number = pokemon.getPokedex_number();
            String name = pokemon.getName();
            int speed = pokemon.getSpeed();
            int special_defence = pokemon.getSpecial_defence();
            int special_attack = pokemon.getSpecial_attack();
            int defence = pokemon.getDefence();
            int attack = pokemon.getAttack();
            int hp = pokemon.getHp();
            String primary_type = pokemon.getPrimary_type();
            String secondary_type = pokemon.getSecondary_type();

            preparedStatement.setInt(1, pokedex_number);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, speed);
            preparedStatement.setInt(4, special_defence);
            preparedStatement.setInt(5, special_attack);
            preparedStatement.setInt(6, defence);
            preparedStatement.setInt(7, attack);
            preparedStatement.setInt(8, hp);
            preparedStatement.setString(9, primary_type);
            preparedStatement.setString(10, secondary_type);

            // EXECUTE STATEMENT
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println("Failure! Could not update pokemon.");
            e.printStackTrace();
        }
    }


}
