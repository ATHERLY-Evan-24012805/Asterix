package person.types.Gaulish.charac;

import person.types.Gaulish.Gaulish;
import person.types.Fighter;
import person.types.Leader;
import person.types.Worker;

import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;



public class Druide extends Gaulish implements Fighter, Leader, Worker{
    public Druide(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void Fighter() {
    }

    @Override
    public void lead(){

    }

    @Override
    public void work(){

    }

    public MagicPotion createMagicPotion(){
        return new MagicPotion(3, MagicEffect.NORMAL);
    }
}
