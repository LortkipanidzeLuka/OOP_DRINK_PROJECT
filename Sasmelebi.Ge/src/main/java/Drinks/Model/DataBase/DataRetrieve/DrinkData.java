package Drinks.Model.DataBase.DataRetrieve;

import Drinks.Constants.Constants;
import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrinkData {
    private Connector connector;

    public DrinkData() {
        this.connector = Connector.getInstance();
    }

    public ArrayList<Drink> favourites(int user_id) throws SQLException {
        PreparedStatement statement = connector.getStatement(
                "select * from " + Constants.schema +
                        ".favourites " +
                        "join " + Constants.schema + ".drinks on " + Constants.schema +
                        ".favourites.drink_id = " + Constants.schema + ".drinks.drink_id " +
                        "where user_id = ?");
        return getDrinks(user_id, connector, statement);
    }

    public ArrayList<Drink> userDrinks(int user_id) throws SQLException {
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema + ".drinks " +
                "where author = ?");
        return getDrinks(user_id, connector, statement);
    }

    private ArrayList<Drink> getDrinks(int user_id, Connector connector, PreparedStatement statement) throws SQLException {
        ArrayList<Drink> drinks = new ArrayList<>();
        statement.setInt(1, user_id);
        ResultSet set = connector.executeQuery(statement);
        while (set.next())
            drinks.add(new Drink(set.getInt("drink_id"), set.getString("drink_name"),
                    "", set.getString("instruction"), set.getInt("parent_id"),
                    set.getInt("author"), getIngredients(set.getInt("drink_id"))));
        return drinks;
    }

    public ArrayList<Ingredient> getIngredients(int drink_id) throws SQLException {
        PreparedStatement statement = connector.getStatement("select * from " + Constants.schema +
                ".drinks_ingredients join " + Constants.schema + ".ingredients on " +
                Constants.schema + ".drinks_ingredients.ingredient_id = " + Constants.schema + ".ingredients.ingredient_id " +
                "where drink_id = ?");
        statement.setInt(1, drink_id);
        ResultSet set = statement.executeQuery();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (set.next())
            ingredients.add(new Ingredient(set.getInt("ingredient_id"), set.getString("ingredient_name")));
        return ingredients;
    }
}
