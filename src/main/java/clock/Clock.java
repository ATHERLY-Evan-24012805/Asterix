package clock;

import java.util.ArrayList;
import java.util.List;

public class Clock {

    // Attributs
    private List<TemporalObject> subscribers = new ArrayList<>();

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
    //Getters
    public List<TemporalObject> getSubscribers() {
        return subscribers;
    }




    //public void mainBusinessLogic() {}
    //public int get
}
