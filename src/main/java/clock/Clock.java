package clock;

import java.util.ArrayList;
import java.util.List;

public class Clock {

    // Attributs
    private List<TemporalObject> subscribers = new ArrayList<>();

    //Methodes
    public void Subscribe(TemporalObject subscriber) {
        subscribers.add(subscriber);
    }
    public void Unsubscribe(TemporalObject subscriber) {
        subscribers.remove(subscriber);
    }
    public void notifySubscribers() {
        // On prévient tout le monde un par un
        for (TemporalObject obj : subscribers) {
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
