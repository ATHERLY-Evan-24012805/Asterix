package person;

import MagicPotion.*;
import clock.TemporalObject;
import place.Place;
import item.Item;
import person.lycanthrope.Lycanthrope;
import theatres.TheatreOfInvasion;
import place.Place;
import java.util.ArrayList;
import java.util.List;
import clock.Clock;

public abstract class Person implements TemporalObject {

    //Attributs
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

    // Constructeurs
    public Person(String name, char gender, double height, int age, int strength, int endurance) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.endurance = endurance;
    }

    // Méthodes
    // Cette methode pourra etre utilisé pour faire des enfants par exemple.
    public abstract Person duplicate(String name, char gender, double height, int age, int strength, int endurance);

    public void eat(){
        hunger = hunger + 10;
    }

    public void heal(){
        health = health + 10;
    }

    public void drinkPotion(MagicPotion MagicPotion){
        potion = clamp(potion + 10);
    }

    public void fight(Person target){
        target.health = target.health - Math.max(1, strength*(1- target.endurance/150)); // Formule à modifier si besoin
    }


    public void petrified() {
        Clock.getInstance().unsubscribe(this);
    }

    public void die(Place place){
        place.removePerson(this);
        Clock.getInstance().unsubscribe(this);
    }

    // interaction Potion
    public void drinkPotion(MagicEffect effect) {
        switch (effect) {
            case DUPLICATION:
                this.duplicate(this.getName(), this.getGender(), this.getHeight(), this.getAge(), this.getStrength(), this.getEndurance());
                System.out.println("Vous avez bû une potion de duplication. Amusez-vous vous et vôtre double.");
                break;
            case TURN_TO_STONE:
                this.petrified();
                System.out.println("Vous avez été transformé en pierre. Désolé...");
                break;
            default:
                break;
        }
    }

    public void die(){
        // à compléter plus tard (soit gestionnaire de personnage, soit variable booléenne, soit remove(this)
    }


    public void drinkPotion ( int index, int usedDoses){
        // On récupère l'item depuis place
        Item item = place.getItem(index);

        // Vérification que l'objet récupéré est une potion
        if (!(item instanceof MagicPotion)) {
            System.out.println("Cet objet n'est pas une potion magique.");
            return;
        }

        MagicPotion potion = (MagicPotion) item;

        // Consommation des doses
        potion.consumeDoses(usedDoses);


        // Application des effets
        for (MagicEffect effect : potion.getEffects()) {
            switch (effect) {
                case DUPLICATION : {
                    this.duplicate(
                            this.getName(),
                            this.getGender(),
                            this.getHeight(),
                            this.getAge(),
                            this.getStrength(),
                            this.getEndurance()
                    );
                    System.out.println("Vous buvez une potion de duplication !");
                    break;
                }

                case TURN_TO_STONE : {
                    this.petrified();
                    System.out.println("Vous êtes transformé en pierre...");
                    break;
                }

                case PERMANENT :
                        System.out.println("Un effet permanent vous affecte.");
                        break;

                case WEREWOLF : {
                    TheatreOfInvasion.createWerewolfFrom(this, this.place);
                    break;
                }

                default :
                        System.out.println("Effet inconnu.");
            }
        }

        this.potion = 100;

        // Suppression si potion vide
        if (potion.isEmpty()) {
            place.removeItem(potion);
            System.out.println("La potion est terminée.");
        }
    }

    // Setters
    public void setTicBeforeAction(int ticBeforeAction) {
        this.ticBeforeAction = ticBeforeAction;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setStrength(int strength) { this. strength = strength; }

    // Getters
    public String getName() {
        return this.name;
    }

    public char getGender() {
        return this.gender;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public boolean getBelligerence() {
        return belligerence;
    }

    public int getHunger() {
        return hunger;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getPotion() {
        return potion;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }
    public int getTicBeforeAction() {
        return ticBeforeAction;

    }
    public Person getTarget() {
        return target;
    }
    public void setTarget(Person target) {
        this.target = target;
    }
    public Place getPlace() {
        return place;
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
}


