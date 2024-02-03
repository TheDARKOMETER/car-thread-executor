import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class I4Engine implements Engine {
    boolean isStarted = false;
    private static final float fuelConsumeRate = (float) 0.0043002;

    @Override
    public void engineRev() {
        System.out.println("Engine revving");
    }


    @Override
    public void engineStart(Fuel fuel) {
        this.isStarted = true;
        System.out.println("Engine starting");
        consumeFuel(fuel);
    }


    @Override
    public void engineStop() {
        this.isStarted = false;
        System.out.println("Engine stopped");
    }

    @Override
    public void consumeFuel(Fuel fuel) {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable decreaseFuel = () -> {
                if (isStarted) {
                    if (fuel.quantity <= 0) {
                        engineStop();
                        scheduler.shutdown();
                        this.isStarted = false;
                        System.out.println("Out of fuel. Engine shutdown.");
                    } else {
                        fuel.quantity -= fuelConsumeRate;
                        if (fuel.quantity < 0.00001) {
                            fuel.quantity = 0;
                        }
                    }
                    System.out.println("Current Fuel: " + fuel.quantity);
                } else {
                    scheduler.shutdownNow();
                    System.out.println("Engine stopped consumption");
                }
            };
            scheduler.scheduleAtFixedRate(decreaseFuel, 0, 1, TimeUnit.SECONDS);
    }

}



