package org.food;

import org.clock.TemporalObject;

public abstract class Food {

    //Attribut
    private int bonus;

    //constructeur
    public Food(int bonus) {
        this.bonus = bonus;
    }
    public Food(){
        this.bonus = 15;
    }

    //Methodes
    public int getBonus(){
        return bonus;
    }

    public void ticsPassed(){}

}
