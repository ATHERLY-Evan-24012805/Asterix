
package person.gaulish.charac;

import person.Person;
import person.gaulish.Gaulish;
import person.Worker;


public class GaulishBlacksmith extends Gaulish implements Worker {
    public GaulishBlacksmith(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }

    @Override
    public void work() {
// faire une epée ou ameliorer une epée
    }

    @Override
    public void ticsPassed() {

    }
}
