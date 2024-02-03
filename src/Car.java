import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Car {
    Engine engine;
    String color, name;
    int noOfWheels;
    Fuel fuel;

    public Car(Engine engine, String color, String name, int noOfWheels, Fuel fuel) {
        this.engine = engine;
        this.color = color;
        this.name = name;
        this.noOfWheels = noOfWheels;
        this.fuel = fuel;
    }

    public void startCar() {
        engine.engineStart(this.fuel);
        checkFuel();
    }

    public void stopCar() {
        engine.engineStop();
    }

    public void checkFuel() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable logFuelLevel = () -> {
            if (fuel.quantity <= 0) {
                scheduler.shutdown();
                System.out.println("Car class debug: Out of fuel " + this.fuel.quantity);
            } else {
                System.out.println("Car class debug fuel: " + this.fuel.quantity);
            }
        };

        scheduler.scheduleAtFixedRate(logFuelLevel, 0, 1, TimeUnit.SECONDS);
    }

}
