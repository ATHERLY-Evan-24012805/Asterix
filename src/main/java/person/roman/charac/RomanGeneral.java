package person.roman.charac;

import person.roman.Roman;
import person.Fighter;
import person.Leader;

public class RomanGeneral extends Roman implements Fighter, Leader {
    public RomanGeneral(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }


    @Override
    public void fight() {
// se bat seulement s'il n'a plus d'allier avec lui sur le champ de bataille. c'est une nuke
    }

    @Override
    public void lead(){
// fait en sorte que deux personnages puissent taper en meme temps
    }

    @Override
    public void ticsPassed() {

    }
}

