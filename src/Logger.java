import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {


    private static final String LOG_FILE = "CarRentalSystemLog.txt"; // Log file path

    public static void logTransaction(String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = formatter.format(new Date());
        String logMessage = timeStamp + " - " + message;

        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void initializeCarsData(CarRentalSystem carRentalSystem) {
        Car[] cars = {
                new Car("C001", "Toyota", "Camry", 60.0),
                new Car("C002", "Honda", "Accord", 70.0),
                new Car("C003", "Jeep", "Wrangler", 90.0),
                new Car("C004", "Ford", "Mustang", 80.0),
                new Car("C005", "Chevrolet", "Camaro", 85.0),
                new Car("C006", "BMW", "3 Series", 100.0),
                new Car("C007", "Mercedes-Benz", "E-Class", 110.0),
                new Car("C008", "Audi", "A4", 95.0),
                new Car("C009", "Subaru", "Outback", 75.0),
                new Car("C010", "Nissan", "Altima", 65.0),
                new Car("C011", "Volkswagen", "Golf", 70.0),
                new Car("C012", "Tesla", "Model S", 150.0),
                new Car("C013", "Lexus", "RX", 120.0),
                new Car("C014", "Hyundai", "Sonata", 70.0),
                new Car("C015", "Kia", "Optima", 75.0),
                new Car("C016", "Mazda", "Mazda6", 80.0),
                new Car("C017", "Buick", "Regal", 85.0),
                new Car("C018", "Lincoln", "MKZ", 100.0),
                new Car("C019", "Chrysler", "300", 90.0),
                new Car("C020", "Cadillac", "CTS", 110.0)
        };

        for (Car car : cars) {
            carRentalSystem.addCar(car);
        }
    }
}
