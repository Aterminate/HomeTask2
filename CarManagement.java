import java.io.*;
import java.util.ArrayList;

public class CarManagement {
    public static void main(String[] args) {
        // Create an array of Car objects
        Car[] cars = new Car[] {
                new Car(1, "Toyota", "Camry", 2020, "Red", 25000, "ABC123"),
                new Car(2, "Honda", "Civic", 2019, "Blue", 22000, "XYZ789"),
                new Car(3, "Ford", "F-150", 2018, "Silver", 35000, "DEF456"),
                // Add more cars as needed
        };

        // Separate cars by brand, model, and year with specific criteria
        ArrayList<Car> carsByBrand = filterCarsByBrand(cars, "Toyota");
        ArrayList<Car> carsByModelAndAge = filterCarsByModelAndAge(cars, "Civic", 3);
        ArrayList<Car> carsByYearAndPrice = filterCarsByYearAndPrice(cars, 2018, 30000);

        // Save the filtered lists to separate files
        saveCarsToFile(carsByBrand, "ToyotaCars.txt");
        saveCarsToFile(carsByModelAndAge, "CivicCarsOver3YearsOld.txt");
        saveCarsToFile(carsByYearAndPrice, "CarsManufacturedIn2018PriceOver30000.txt");
    }

    // Helper method to filter cars by brand
    private static ArrayList<Car> filterCarsByBrand(Car[] cars, String brand) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }

    // Helper method to filter cars by model and age
    private static ArrayList<Car> filterCarsByModelAndAge(Car[] cars, String model, int years) {
        ArrayList<Car> result = new ArrayList<>();
        int currentYear = 2023; // Assuming the current year is 2023
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYear() > years)) {
                result.add(car);
            }
        }
        return result;
    }

    // Helper method to filter cars by year and price
    private static ArrayList<Car> filterCarsByYearAndPrice(Car[] cars, int year, double price) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() == year && car.getPrice() > price) {
                result.add(car);
            }
        }
        return result;
    }

    // Helper method to save a list of cars to a file
    private static void saveCarsToFile(ArrayList<Car> cars, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Car car : cars) {
                writer.println(car.getMake() + ", " + car.getModel() + ", " + car.getYear() + ", " + car.getPrice());
            }
            System.out.println("Saved " + cars.size() + " cars to " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}