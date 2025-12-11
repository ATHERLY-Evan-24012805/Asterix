package item.weapon;

import item.Item;

public abstract class Weapon extends Item {

    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
