public class FuelFactory {

    public static Fuel createGasFuel(float quantity) {
        return new Fuel("gasoline", quantity);
    }

}
