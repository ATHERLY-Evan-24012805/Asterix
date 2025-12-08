package MagicPotion;

import Foods.*;

import java.util.ArrayList;

public class MagicPotion {
    private int doses;
    private MagicEffect effect;

    public MagicPotion(int doses, MagicEffect effect) {
        this.doses = doses;
        this.effect = effect;
    }

    public MagicEffect getEffects(){
        return effect;
    }

    public boolean isEmpty(){
        return doses == 0;
    }

    public int getDoses() {
        return doses;
    }
}
