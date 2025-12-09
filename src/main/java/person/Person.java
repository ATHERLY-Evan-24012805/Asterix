package person;

import MagicPotion.MagicPotion;
import clock.TemporalObject;
import place.Place;

public abstract class Person implements TemporalObject {
    private String name;
    private char gender;
    private double height;
    private int age;
    private int strength;
    private int endurance;
    private int health = 100;
    private int hunger = 100;
    private boolean belligerence = false;
    private int potion = 0;
    private Place place;
    private int ticBeforeAction;
    private Person target;

    /**
     * Permet de limiter les valeurs entre 0 et 100.
     *
     * @param value La valeur que l'on veut limiter
     * @return La valeur max ou min
     */
    private int clamp(int value) {
        return Math.max(0, Math.min(100, value));
    }

    public Person(String name, char gender, double height, int age, int strength, int endurance) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.endurance = endurance;
    }


    public void eat(){
        hunger = clamp(hunger + 10);
    }

    public void heal(){
        health = clamp(health + 10);
    }

    public void drinkPotion(MagicPotion MagicPotion){
        potion = clamp(potion + 10);
    }

    public void fight(Person target){
        target.health = target.health - Math.max(1, strength*(1- target.endurance/150)); // Formule à modifier si besoin
    }

    public Person getTarget() {
        return target;
    }
    public void setTarget(Person target) {
        this.target = target;
    }

    //Getters
    public int getTicBeforeAction() {
        return ticBeforeAction;
    }

    //Setters
    public void setTicBeforeAction(int ticBeforeAction) {
        this.ticBeforeAction = ticBeforeAction;
    }

    public int getHunger(){
        return hunger;
    }
    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public int getStrength() {
        return strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public Place getPlace() {
        return place;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPlace(Place place) {
        this.place = place;
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

    @Override
    public void ticsPassed() {
        // 1. On perd de la nourriture
        this.hunger -= 5; // Par exemple -5 par heure

        // 2. Si on a trop faim, on perd de la vie
        if (this.hunger <= 0) {
            this.hunger = 0; // On ne descend pas en négatif
            this.health -= 10;
            System.out.println(this.getName() + " meurt de faim ! PV restants : " + this.health);
        }
    }

    public void die(){
        // à compléter plus tard (soit gestionnaire de personnage, soit variable booléenne, soit remove(this)
    }

}
