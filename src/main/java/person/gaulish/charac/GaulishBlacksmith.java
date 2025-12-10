
package person.gaulish.charac;

import person.Person;
import person.gaulish.Gaulish;
import person.Worker;
import place.Place;

import java.util.Random;


public class GaulishBlacksmith extends Gaulish implements Worker {

    private int worktimer = 20;
    private Random random = new Random();


    public GaulishBlacksmith(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishBlacksmith(name, gender, height, age, strength, endurance);
    }

    /**
     * La fonction work pour un forgeron représente le fait de forger et réparer des armes.
     * Cela augmente la force du personnage qui en bénéficie.
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
