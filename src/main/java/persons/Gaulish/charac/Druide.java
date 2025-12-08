package persons.Gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import persons.Gaulish.Gaulish;
import persons.Fighter;
import persons.Leader;
import persons.Worker;

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
