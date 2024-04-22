import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CarRentalSystem {
    private static List<Car> cars;
    private static List<Customer> customers;
    private static List<Rental> rentals;
    private static Scanner scanner = new Scanner(System.in);

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public static List<Rental> getRentals() {
        return rentals;
    }

    public static void setRentals(List<Rental> rentals) {
        CarRentalSystem.rentals = rentals;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public  void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public  void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
            Logger.logTransaction("Car rented: " + car.getCarId() + " to customer " + customer.getName() + " for " + days + " days.");
        } else {
            System.out.println("Car is not available for rent.");
            Logger.logTransaction("Failed rental attempt: Car " + car.getCarId() + " is not available.");
        }
    }

    public  void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            Logger.logTransaction("Car returned: " + car.getCarId() + " by customer " + rentalToRemove.getCustomer().getName());
        } else {
            System.out.println("Car was not rented.");
            Logger.logTransaction("Failed return attempt: Car " + car.getCarId() + " was not rented.");
        }
    }

    public void menu() {


        while (true) {
            printMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    rentACar();
                    break;
                case 2:
                    returnACar();
                    break;
                case 3:
                    customerInformation();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Car Rental System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public  void customerInformation() {
        System.out.println("\n== Customer Information ==\n");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("Invalid Username. Please enter a non-empty name.");
            return;
        }
        boolean customerFound = false;
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                System.out.println("Customer Id: " + customer.getCustomerId());
                System.out.println("Customer Name: " + customer.getName());
                customerFound = true;

                boolean rentalFound = false;
                for (Rental rental : rentals) {
                    if (rental.getCustomer().equals(customer)) {
                        Car car = rental.getCar();
                        System.out.println(" - " + car.getBrand() + " " + car.getModel() + ", Rental Days: " + rental.getDays() + ", Car ID: " + car.getCarId());
                        rentalFound = true;
                    }
                } if (!rentalFound){
                    System.out.println("No cars currently rented");
                }
                break;
            }
        } if (!customerFound){
            System.out.println("No customer found with name: " + customerName);
        }
    }

    public  void rentACar() {
        System.out.println("\n== Rent a Car ==\n");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("Invalid Username. Please enter a non-empty name.");
            return;
        }

        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
            }
        }

        System.out.print("\nEnter the car ID you want to rent: ");
        String carId = scanner.nextLine();

        System.out.print("Enter the number of days for rental: ");
        int rentalDays = scanner.nextInt();
        scanner.nextLine();

        Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
        addCustomer(newCustomer);

        Car selectedCar = null;
        for (Car car : cars) {
            if (car.getCarId().toLowerCase().equals(carId) && car.isAvailable()) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar != null) {
            double totalPrice = selectedCar.calculatePrice(rentalDays);
            System.out.println("\n== Rental Information ==\n");
            System.out.println("Customer ID: " + newCustomer.getCustomerId());
            System.out.println("Customer Name: " + newCustomer.getName());
            System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
            System.out.println("Rental Days: " + rentalDays);
            System.out.printf("Total Price: $%.2f%n", totalPrice);

            System.out.print("\nConfirm rental (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                rentCar(selectedCar, newCustomer, rentalDays);
                System.out.println("\nCar rented successfully.");
            } else {
                System.out.println("\nRental canceled.");
            }
        } else {
            System.out.println("\nInvalid car selection or car not available for rent.");
        }

    }

    public  void returnACar() {
        System.out.println("\n== Return a Car ==\n");
        System.out.print("Enter the car ID you want to return: ");
        String carId = scanner.nextLine();

        Car carToReturn = null;
        for (Car car : cars) {
            if (car.getCarId().toLowerCase().equals(carId) && !car.isAvailable()) {
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
                System.out.println("Car returned successfully by " + customer.getName());
            } else {
                System.out.println("Car was not rented or rental information is missing.");
            }
        } else {
            System.out.println("Invalid car ID or car is not rented.");
        }
    }

    private static void printMainMenu() {
        System.out.println("===== Car Rental System =====");
        System.out.println("1. Rent a Car");
        System.out.println(" ");
        System.out.println("2. Return a Car");
        System.out.println(" ");
        System.out.println("3. Customer Information");
        System.out.println(" ");
        System.out.println("4. Exit");
        System.out.println(" ");
        System.out.print("Enter your choice: ");
    }

}