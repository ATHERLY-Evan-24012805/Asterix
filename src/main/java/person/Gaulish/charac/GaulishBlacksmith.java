
package person.Gaulish.charac;

import person.Gaulish.Gaulish;
import person.Worker;


public class GaulishBlacksmith extends Gaulish implements Worker {
    public GaulishBlacksmith(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void work() {

    }

    @Override
    public void ticsPassed() {

    }
}
