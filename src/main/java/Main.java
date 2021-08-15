import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        CitiesService service = new CitiesService();
        int count = 3;
        for (int i = 0; i < count; i++) {
            System.out.println("Введите  город");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                City city = new City(UUID.randomUUID().toString(), reader.readLine());
                service.addCity(city);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        service.printCitiesToConsole();
        List<City> list = service.getCities();
    }
}
