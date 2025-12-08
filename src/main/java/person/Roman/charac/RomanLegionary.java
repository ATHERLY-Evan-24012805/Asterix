package person.Roman.charac;

import person.Roman.Roman;
import person.Fighter;

public class RomanLegionary extends Roman implements Fighter{
    public RomanLegionary(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void Fighter(){

    }

    @Override
    public void ticsPassed() {

    }
}
