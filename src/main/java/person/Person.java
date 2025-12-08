package person;

import MagicPotion.MagicPotion;
import clock.TemporalObject;

public abstract class Person implements TemporalObject {
    private String name;
    private char gender;
    private double height;
    private int age;
    private int strength;
    private int endurance;
    private int health = 100;
    private int hunger = 100;
    private int belligerence = 100;
    private int potion = 0;

    public Person(String name, char gender, double height, int age, int strength, int endurance) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.endurance = endurance;
    }


    public void eat(){
        hunger = hunger + 10;
    }

    public void heal(){
        health = health + 10;
    }

    public void drinkPotion(MagicPotion MagicPotion){
        potion = potion + 10;
    }

    public void fight(Person adversaire){
        adversaire.health = adversaire.health - Math.max(1, strength*(1-adversaire.endurance/150)); // Formule à modifier si besoin
    }

    public int getHealth() {
        return health;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", height=" + height +
                ", age=" + age +
                ", strength=" + strength +
                ", endurance=" + endurance +
                ", health=" + health +
                ", hunger=" + hunger +
                ", belligerence=" + belligerence +
                ", potion=" + potion +
                '}';
    }

    public void die(){
        // à compléter plus tard (soit gestionnaire de personnage, soit variable booléenne, soit remove(this)
    }

}
