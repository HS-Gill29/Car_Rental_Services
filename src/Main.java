
class Main {
    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        Logger.initializeCarsData(carRentalSystem);
        carRentalSystem.menu();
    }
}
