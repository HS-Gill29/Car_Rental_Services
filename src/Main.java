
class Main {

    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Jeep", "Wrangler", 90.0);
        Car car4 = new Car("C004", "Ford", "Mustang", 80.0);
        Car car5 = new Car("C005", "Chevrolet", "Camaro", 85.0);
        Car car6 = new Car("C006", "BMW", "3 Series", 100.0);
        Car car7 = new Car("C007", "Mercedes-Benz", "E-Class", 110.0);
        Car car8 = new Car("C008", "Audi", "A4", 95.0);
        Car car9 = new Car("C009", "Subaru", "Outback", 75.0);
        Car car10 = new Car("C010", "Nissan", "Altima", 65.0);
        Car car11 = new Car("C011", "Volkswagen", "Golf", 70.0);
        Car car12 = new Car("C012", "Tesla", "Model S", 150.0);
        Car car13 = new Car("C013", "Lexus", "RX", 120.0);
        Car car14 = new Car("C014", "Hyundai", "Sonata", 70.0);
        Car car15 = new Car("C015", "Kia", "Optima", 75.0);
        Car car16 = new Car("C016", "Mazda", "Mazda6", 80.0);
        Car car17 = new Car("C017", "Buick", "Regal", 85.0);
        Car car18 = new Car("C018", "Lincoln", "MKZ", 100.0);
        Car car19 = new Car("C019", "Chrysler", "300", 90.0);
        Car car20 = new Car("C020", "Cadillac", "CTS", 110.0);

        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);
        carRentalSystem.addCar(car4);
        carRentalSystem.addCar(car5);
        carRentalSystem.addCar(car6);
        carRentalSystem.addCar(car7);
        carRentalSystem.addCar(car8);
        carRentalSystem.addCar(car9);
        carRentalSystem.addCar(car10);
        carRentalSystem.addCar(car11);
        carRentalSystem.addCar(car12);
        carRentalSystem.addCar(car13);
        carRentalSystem.addCar(car14);
        carRentalSystem.addCar(car15);
        carRentalSystem.addCar(car16);
        carRentalSystem.addCar(car17);
        carRentalSystem.addCar(car18);
        carRentalSystem.addCar(car19);
        carRentalSystem.addCar(car20);
        carRentalSystem.menu();
    }
}
