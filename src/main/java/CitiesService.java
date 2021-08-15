import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitiesService {
    static String url = "jdbc:sqlite:homeworks.bd";

    public void addCity(City city) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        String query = "INSERT INTO cities VALUES('" + city.getId() + "','" + city.getName() + "');";
        statement.execute(query);
        statement.close();
        connection.close();
    }

    public void printCitiesToConsole() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM cities;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            System.out.println("ID: " + id + " name: " + name);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }


    public List<City> getCities() {
        List<City> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();) {
            String query = "SELECT * FROM cities;";
            try (ResultSet resultSet = statement.executeQuery(query);) {
                while (resultSet.next()) {
                    list.add(new City(resultSet.getString("id"), resultSet.getString("name")));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }


}
