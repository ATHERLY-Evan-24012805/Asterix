package food.items;

import clock.TemporalObject;
import food.Food;

public class Fish extends Food implements TemporalObject {

    //Attributs
    private boolean peramption = true;
    private int isFreshFor = 24;

    //Constructeur
    public Fish(){
        super();
    }

    //Methodes
    public void ticsPassed() {
        this.isFreshFor--;

        if (this.isFreshFor < 0) {
            this.peramption = false;
        }
    }

    //Getters
    public boolean getPeramption() {
        return peramption;
    }
    public int getIsFreshFor() {
        return isFreshFor;
    }
}
