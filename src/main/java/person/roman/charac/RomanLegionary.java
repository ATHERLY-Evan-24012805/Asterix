package person.roman.charac;

import person.Person;
import person.roman.Roman;
import person.Fighter;

public class RomanLegionary extends Roman implements Fighter{
    public RomanLegionary(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }

    @Override
    public void fight(){
    // Tape et obeit au general
        fight(getTarget());
    }

    @Override
    public void ticsPassed() {
        super.ticsPassed();
        this.fight();
    }
}
