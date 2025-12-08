package person.roman.charac;

import person.roman.Roman;
import person.Fighter;
import person.Leader;

public class RomanGeneral extends Roman implements Fighter, Leader {
    public RomanGeneral(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }


    @Override
    public void fighte() {

    }

    @Override
    public void lead(){

    }

    @Override
    public void ticsPassed() {

    }
}

