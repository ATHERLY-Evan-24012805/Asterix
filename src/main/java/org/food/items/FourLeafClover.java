package org.food.items;

import org.clock.TemporalObject;
import org.food.Food;

public class FourLeafClover extends Food implements TemporalObject {

    //Attributs
    private boolean peramption = true;
    private int isFreshFor = 48;

    //Constructeur
    public FourLeafClover() {
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
