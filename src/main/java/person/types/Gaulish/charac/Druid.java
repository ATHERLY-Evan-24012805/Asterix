package person.types.Gaulish.charac;

import person.types.Gaulish.Gaulish;
import person.types.Fighter;
import person.types.Leader;
import person.types.Worker;

public class Druid extends Gaulish implements Fighter, Leader, Worker{
    public Druid(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void fighte() {

    }

    @Override
    public void lead(){

    }

    @Override
    public void work(){

    }

    @Override
    public void ticsPassed() {

    }
}
