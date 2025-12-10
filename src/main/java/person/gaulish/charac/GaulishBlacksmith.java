
package person.gaulish.charac;

import person.Person;
import person.gaulish.Gaulish;
import person.Worker;
import place.Place;

import java.util.Random;

/**
 * Représente un Forgeron Gaulois (Blacksmith).
 *
 * <p>Un Forgeron est un type de Gaulois qui implémente le rôle de Travailleur.
 * Son travail consiste à forger et réparer des équipements, ce qui est représenté par un bonus de force
 * à un habitant aléatoire de son lieu.
 */
public class GaulishBlacksmith extends Gaulish implements Worker {

    private int worktimer = 20;
    private Random random = new Random();


    /**
     * Construit une nouvelle instance de GaulishBlacksmith.
     *
     * @param name Le nom du Forgeron.
     * @param gender Le genre du Forgeron ('M', 'F' ou 'X').
     * @param height La taille du Forgeron.
     * @param age L'âge du Forgeron.
     * @param strength La force de base du Forgeron.
     * @param endurance L'endurance de base du Forgeron.
     */
    public GaulishBlacksmith(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    /**
     * Crée une nouvelle instance de GaulishBlacksmith avec les caractéristiques données.
     * Utilisé pour la potion Duplicate.
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de GaulishBlacksmith.
     */
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishBlacksmith(name, gender, height, age, strength, endurance);
    }

    /**
     * La fonction work pour un forgeron représente le fait de forger et réparer des armes.
     *
     * <p>Cette action sélectionne un habitant aléatoire dans le {@link Place} actuel
     * du forgeron et augmente sa force de 1 point.
     */
    @Override
    public void work() {
        Place place = this.getPlace();

        if (place == null) return;
        if (place.getPeople().isEmpty()) return;

        // Choisir un habitant au hasard
        Person target = place.getPeople().get(random.nextInt(place.getPeople().size()));

        // Augmenter sa force
        target.setStrength(target.getStrength() + 1);

        System.out.println(getName() + " a forgé une épée et renforcé la force de "
                + target.getName()
                + " (force = " + target.getStrength() + ")");

    }

    /**
     * Appelé à chaque tic de l'horloge. Gère le compteur de travail.
     *
     * <p>Décrémente le worktimer. Lorsque le compteur atteint 0,
     * la méthode work est appelée et le compteur est réinitialisé.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();

        this.worktimer--;

        if (worktimer == 0) {
            work();
            worktimer = 23;
        }
    }
}
