
package person.gaulish.charac;

import person.Person;
import person.gaulish.Gaulish;
import person.Worker;


public class GaulishBlacksmith extends Gaulish implements Worker {

    private int worktimer = 20;

    public GaulishBlacksmith(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    public String getType(){
        return "Forgeron";
    }
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishBlacksmith(name, gender, height, age, strength, endurance);
    }

    @Override
    public void work() {
// faire une epée ou améliorer une épée

    }

    @Override
    public void ticsPassed() {
        this.worktimer--;

        if (worktimer == 0) {
            work();
            worktimer = 23;
        }
    }
}
