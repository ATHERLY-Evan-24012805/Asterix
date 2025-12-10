package food;

import clock.TemporalObject;
import item.Item;

public abstract class Food extends Item {

    //Attribut
    private int bonus;

    //constructeur
    public Food(String name, int bonus) {
        super(name);
        this.bonus = bonus;
    }
    public Food(String name)
    {
        super(name);
        this.bonus = 15;
    }

    //Methodes
    public int getBonus(){
        return bonus;
    }

    public void ticsPassed(){}

}
