package Personnage.Roman.charac;

import Personnage.Roman.Roman;
import Personnage.Fighter;
import Personnage.Leader;

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

