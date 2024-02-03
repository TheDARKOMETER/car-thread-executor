public class Main {
    public static void main(String[] args) {
        Car car1 = new Car(new I4Engine(), "red", "Toyota Crapper", 4, FuelFactory.createGasFuel(500));

        car1.startCar();
    }
}