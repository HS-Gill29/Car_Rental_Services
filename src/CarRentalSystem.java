import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

  private List<Car> cars = new ArrayList<>();
  private List<Customer> customers = new ArrayList<>();
  private List<Rental> rentals = new ArrayList<>();
  private Scanner scanner = new Scanner(System.in);

  public CarRentalSystem() {}

  public CarRentalSystem(
    List<Car> cars,
    List<Customer> customers,
    List<Rental> rentals
  ) {
    this.cars = cars;
    this.customers = customers;
    this.rentals = rentals;
  }

  public void addCar(Car car) {
    cars.add(car);
  }

  public void addCustomer(Customer customer) {
    customers.add(customer);
  }

  public void rentCar(Car car, Customer customer, int days) {
    if (car.isAvailable()) {
      car.rent();
      rentals.add(new Rental(car, customer, days));
    } else {
      System.out.println("Car is not available for rent.");
    }
  }

  public void rentalCarMenu() {

  }

  public void returnCar(Car car) {
    Rental rentalToRemove = null;
    for (Rental rental : rentals) {
      if (rental.getCar() == car) {
        rentalToRemove = rental;
        car.returnCar();
        break;
      }
    }
    if (rentalToRemove != null) {
      rentals.remove(rentalToRemove);
      System.out.println("Car returned successfully");
    } else {
      System.out.println("Car was not removed");
    }
  }

  public void returnCarMenu() {

  }

  public void displayMainMenu() {
    System.out.println("====== Car Rental System =====");
    System.out.println("1. Rent a Car");
    System.out.println(" ");
    System.out.println("2. Return a Car");
    System.out.println(" ");
    System.out.println("3. Exit");
    System.out.println(" ");
    System.out.print("Enter your choice: ");
  }
  private int attemptRemaining = 3;

  public int getUserInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }
  private boolean inLoop = true;
  public void menu() {
    while (attemptRemaining > 0 && inLoop) {
      displayMainMenu();
      if (getUserInput() == 1) {
        System.out.println("\n== Rent a Car ==\n");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        if (customerName.equals(" ") || customerName.equals("")) {
          System.out.println("Enter the valid name: ");
          attemptRemaining--;
          System.out.println("Number of attempt remaining: " + attemptRemaining);
          if (attemptRemaining > 0){

          }
        }

        System.out.println("\n Available Cars:");
        for (Car car : cars) {
          if (car.isAvailable()) {
            System.out.println(
                    car.getCarId() + " " + car.getBrand() + car.getModel()
            );
          }
        }
        System.out.print("\n Enter the Car ID you want to rent: ");
        String carId = scanner.nextLine();

        System.out.print("\n Enter the number of days for rental: ");
        int rentalDays = scanner.nextInt();
        scanner.nextLine();

        Customer newCustomer = new Customer(
                "cus" + (customers.size() + 1),
                customerName
        );
        addCustomer(newCustomer);

        Car selectedCar = null;
        for (Car car : cars) {
          if (car.getCarId().equals(carId) && car.isAvailable()) {
            selectedCar = car;
            break;
          }
        }
        if (selectedCar != null) {
          double totalPrice = selectedCar.calculatePrice(rentalDays);
          System.out.println("\n ======= Rental Information ========");
          System.out.println("Customer Id: " + newCustomer.getCustomerId());
          System.out.println("Customer Name: " + newCustomer.getName());
          System.out.println(
                  "Car: " + selectedCar.getBrand() + " " + selectedCar.getModel()
          );
          System.out.println("Rental Days :" + rentalDays);
          System.out.println("Total Price: " + totalPrice);

          System.out.print("\nConfirm rental (Y/N): ");
          String confirm = scanner.nextLine();
          if (confirm.equalsIgnoreCase("Y")) {
            rentCar(selectedCar, newCustomer, rentalDays);
            System.out.println("\nCar rented successfully");
          } else {
            System.out.println("\nRental canceled.");
          }
        } else {
          System.out.println(
                  "Invalid car selection or car not available not for rent."
          );
          attemptRemaining--;
          System.out.println("Number of attempt remaining: " + attemptRemaining);
        }
      } else if (getUserInput() == 2) {
        System.out.println("\n=== Return a Car ====\n");
        System.out.print("Enter the Car Id you want to return: ");
        String carId = scanner.nextLine();

        Car carToReturn = null;
        for (Car car : cars) {
          if (car.getCarId().equals(carId) && !car.isAvailable()) {
            carToReturn = car;
            break;
          }
        }
        if (carToReturn != null) {
          Customer customer = null;
          for (Rental rental : rentals) {
            if (rental.getCar() == carToReturn) {
              customer = rental.getCustomer();
              break;
            }
          }
          if (customer != null) {
            returnCar(carToReturn);
            System.out.println(
                    "Car returned successfully by " + customer.getName()
            );
          } else {
            System.out.println("Car was rented or rental information is missing.");
          }
        } else {
          System.out.println("Invalid car id or car is not returned.");
          attemptRemaining--;
          System.out.println("Number of attempt remaining: " + attemptRemaining);
        }
      } else if (getUserInput() == 3) {
        break;
      } else {
        System.out.println(" ");
        System.out.println("Invalid choice. Please enter a valid option.");
        attemptRemaining--;
        System.out.println(" ");
        System.out.println("Number of attempt remaining: " + attemptRemaining);
      }
    }
    System.out.println(" ");
    System.out.println("Thank you for using the Car Rental system");
  }
}
class Main {
   public static void main(String[] args) {
    CarRentalSystem carRentalSystem = new CarRentalSystem();
    Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
    Car car2 = new Car("C002", "Honda", "Accord", 70.0);
    Car car3 = new Car("C003", "Jeep", "Wrangler", 90.0);

    carRentalSystem.addCar(car1);
    carRentalSystem.addCar(car2);
    carRentalSystem.addCar(car3);
    carRentalSystem.menu();
  }
}
