package org.food;

public abstract class Food {

    //Attribut
    private int bonus;

    //constructor
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

}
