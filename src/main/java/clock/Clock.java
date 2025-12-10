package clock;

import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe représentant l'horloge de toute la simulation.
 *
 *  <p>La Clock notifie périodiquement les objets temporels abonnés (implementant {@link TemporalObject})
 *  que le temps a avancé. La classe utilise le **singleton pattern**, ce qui garantit qu'il n'y a
 *  qu'une seule instance d'horloge dans l'application.
 */
public class Clock {

    // Attributs
    /**
     * Une seule instance de Clock (Singleton).
     */
    private static Clock instance;

    /**
     * Liste des objets abonnés qui doivent être notifiés lorsque le temps avance.
     */

    private List<TemporalObject> subscribers = new ArrayList<>();

    private final Object lock = new Object();

    //Methodes

    /**
     * Ajoute un objet temporel à la liste des abonnés.
     * Cet objet recevra des notifications de temps via la méthode {@link TemporalObject#ticsPassed()}.
     *
     * @param subscriber L'objet à abonner.
     */
    public void subscribe(TemporalObject subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Retire un objet temporel de la liste des abonnés.
     * Cet objet ne recevra plus de notification de temps.
     *
     * @param subscriber L'objet à désabonner.
     */
    public void unsubscribe(TemporalObject subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notifie tous les abonnés que le temps a avancé d'une unité.
     * <p>Pour chaque abonné, la méthode {@link TemporalObject#ticsPassed()} est appelée.
     */
    public void notifySubscribers() {
        // On prévient tout le monde un par un
        System.out.println("une heure est passée.");
        for (TemporalObject obj : subscribers) {
            obj.ticsPassed();
        }
    }

    /**
     * Démarre l'horloge dans un thread séparé.
     * <p>Un nouveau thread est créé pour appeler périodiquement {@link #notifySubscribers()}
     * après l'intervalle spécifié.
     *
     * @param intervalleSecondes L'intervalle de temps en secondes entre chaque tic de l'horloge.
     */
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

    /**
     * Fournit l'unique instance de la classe Clock (Singleton).
     * <p>Si aucune instance n'existe, elle en crée une, sinon elle retourne l'instance existante.
     *
     * @return L'unique instance de Clock.
     */
    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    //Getters

    /**
     * Retourne la liste actuelle des objets abonnés à l'horloge.
     *
     * @return Une List d'objets {@code TemporalObject} abonnés.
     */
    public List<TemporalObject> getSubscribers() {
        return subscribers;
    }


    //public void mainBusinessLogic() {}
    //public int get
}
