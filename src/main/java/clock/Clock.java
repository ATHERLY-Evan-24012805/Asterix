package clock;

import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {

    // Attributs
    private static final Clock instance = new Clock();
    private List<TemporalObject> subscribers = new ArrayList<>();
    private final Object lock = new Object();

    //Methodes
    public void subscribe(TemporalObject subscriber) {
        subscribers.add(subscriber);
    }
    public void unsubscribe(TemporalObject subscriber) {
        subscribers.remove(subscriber);
    }
    public void notifySubscribers() {
        // On prévient tout le monde un par un
        for (TemporalObject obj : subscribers) {
            System.out.println("une heure est passée.");
            obj.ticsPassed();
        }
    }
    public void start(int intervalleSecondes) {
        Runnable tacheHorloge = () -> {
            while (true) {
                try {
                    Thread.sleep(intervalleSecondes * 1000);

                    this.notifySubscribers();

                } catch (InterruptedException e) {
                    System.out.println("Arrêt de l'horloge");
                    break;
                }
            }
        };
        Thread monThread = new Thread(tacheHorloge);
        monThread.start();
    }
    public static Clock getInstance() {
        return instance;
    }
    //Getters
    public List<TemporalObject> getSubscribers() {
        return subscribers;
    }


    //public void mainBusinessLogic() {}
    //public int get
}
