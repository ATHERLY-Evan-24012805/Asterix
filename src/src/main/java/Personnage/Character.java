package Personnage;

public abstract class Character {
    private String name;
    private char gender;
    private double height;
    private int age;
    private int strength;
    private int endurance;
    private int health = 100;
    private int hunger = 100;
    private int belligerence;
    private int potion = 0;

    public Character(String name, char gender, double height, int age, int strength, int endurance) {
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

    public void drinkPotion(){
        potion = potion + 10;
    }

    public void fight(Character adversaire){
        adversaire.health = adversaire.health - Math.max(1, strength*(1-adversaire.endurance/150)); // Formule à modifier si besoin
    }

    public void die(){
        // à compléter plus tard (soit gestionnaire de personnage, soit variable booléenne, soit remove(this)
    }
}
