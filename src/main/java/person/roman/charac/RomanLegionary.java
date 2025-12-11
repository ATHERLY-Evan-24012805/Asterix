package person.roman.charac;

import person.Person;
import person.roman.Roman;
import person.Fighter;

public class RomanLegionary extends Roman implements Fighter{
    public RomanLegionary(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new RomanLegionary(name, gender, height, age, strength, endurance);
    }
    public String getType(){
        return "legionnaire romain";
    }

    @Override
    public int fight(){
    // Tape et obeit au general
        if (this.getTarget() == null) {
            return 0;
        }
        return hit(getTarget());
    }

    @Override
    public void ticsPassed() {
        super.ticsPassed();
        this.fight();
    }
}
