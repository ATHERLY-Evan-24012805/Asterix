package person.types.Roman.charac;

import person.types.Roman.Roman;
import person.types.Fighter;
import person.types.Leader;

public class RomanGeneral extends Roman implements Fighter, Leader {
    public RomanGeneral(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }


    @Override
    public void Fighter() {

    }

    @Override
    public void lead(){

    }
}

