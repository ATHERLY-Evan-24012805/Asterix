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

/**
 * Classe abstraite de base représentant tout personnage vivant dans la simulation.
 *
 * <p>Une Person est l'unité de base qui possède des attributs physiques, des statistiques
 * de vie (santé, faim, force) et la capacité de réagir à l'écoulement du temps (via {@link clock.TemporalObject}).
 */
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
    /**
     * Construit un nouveau personnage avec les caractéristiques de base spécifiées.
     *
     * @param name Le nom du personnage.
     * @param gender Le genre du personnage ('M', 'F' ou 'X').
     * @param height La taille du personnage.
     * @param age L'âge du personnage.
     * @param strength La force du personnage.
     * @param endurance L'endurance du personnage.
     */
    public Person(String name, char gender, double height, int age, int strength, int endurance) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.endurance = endurance;
    }

    // Méthodes
    /**
     * Crée une nouvelle instance de la sous-classe concrète actuelle avec les mêmes caractéristiques.
     *
     * <p>Cette méthode est utilisée pour la duplication (par exemple, suite à l'effet d'une potion).
     *
     * @param name Le nom du duplicata.
     * @param gender Le genre du duplicata.
     * @param height La taille du duplicata.
     * @param age L'âge du duplicata.
     * @param strength La force du duplicata.
     * @param endurance L'endurance du duplicata.
     * @return Une nouvelle instance de la sous-classe.
     */
    public abstract Person duplicate(String name, char gender, double height, int age, int strength, int endurance);

    /**
     * Simule l'action de manger.
     *
     * <p>Augmente le niveau de faim ({@link #hunger}) de 10 points.
     */
    public void eat(){
        hunger = hunger + 50;
    }

    /**
     * Simule l'action de se soigner.
     *
     * <p>Augmente les points de vie ({@link #health}) de 10 points.
     */
    public void heal(){
        health = health + 10;
    }

    /**
     * Simule l'action de boire une petite dose de potion non spécifiée.
     *
     * <p>Augmente le niveau d'effet de potion de 10 points.
     *
     * @param MagicPotion La potion à boire (utilisé pour la signature, mais non utilisé pour l'effet ici).
     */
    public void drinkPotion(MagicPotion MagicPotion){
        potion = clamp(potion + 10);
    }

    /**
     * Gère l'action de combat contre une cible.
     *
     * <p>Les dégâts infligés dépendent de la force de l'attaquant et de l'endurance
     * de la cible.
     *
     * @param target Le personnage ciblé qui subit les dégâts.
     */
    public void fight(Person target){
        target.health = target.health - Math.max(1, strength*(1- target.endurance/150)); // Formule à modifier si besoin
    }

    /**
     * Gère la transformation en pierre du personnage (pétrification).
     *
     * <p>Le personnage est désabonné de l'horloge et cesse ainsi de réagir au temps.
     */
    public void petrified() {
        Clock.getInstance().unsubscribe(this);
    }

    /**
     * Gère la mort du personnage.
     *
     * <p>Le personnage est retiré du lieu ({@link #place}) et est désabonné
     * de l'horloge.
     *
     * @param place Le lieu où le personnage se trouve et doit être retiré.
     */
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

    /**
     * Gère la consommation d'une quantité spécifique de doses d'une potion magique.
     *
     * <p>Vérifie si l'objet est une potion, consomme les doses, applique les différents
     * effets contenus dans la potion, et retire la potion du lieu si elle est vide.
     *
     * @param index L'index de l'objet dans l'inventaire du {@link place.Place} à consommer.
     * @param usedDoses La quantité de doses à utiliser.
     */
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
    /**
     * Définit le nombre de tics avant que le personnage ne puisse effectuer une action spécifique.
     *
     * @param ticBeforeAction Le nouveau délai en tics.
     */
    public void setTicBeforeAction(int ticBeforeAction) {
        this.ticBeforeAction = ticBeforeAction;
    }

    /**
     * Définit le lieu actuel du personnage.
     *
     * @param place Le nouveau {@link place.Place}.
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    /**
     * Définit les points de vie du personnage ({@link #health}).
     *
     * @param health Les nouveaux points de vie.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Définit la force du personnage ({@link #strength}).
     *
     * @param strength La nouvelle force.
     */
    public void setStrength(int strength) { this. strength = strength; }

    /**
     * Définit la cible actuelle du personnage pour l'action ou le combat.
     *
     * @param target La nouvelle cible ({@link person.Person}).
     */
    public void setTarget(Person target) {
        this.target = target;
    }

    // Getters
    /**
     * Retourne le nom du personnage.
     *
     * @return Le nom (String).
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retourne le genre du personnage.
     *
     * @return Le genre (char).
     */
    public char getGender() {
        return this.gender;
    }

    /**
     * Retourne la taille du personnage.
     *
     * @return La taille (double).
     */
    public double getHeight() {
        return height;
    }

    /**
     * Retourne l'âge du personnage.
     *
     * @return L'âge (int).
     */
    public int getAge() {
        return age;
    }

    /**
     * Retourne l'état de belligérance.
     *
     * @return L'état de belligérance (boolean).
     */
    public boolean getBelligerence() {
        return belligerence;
    }

    /**
     * Retourne le niveau de faim.
     *
     * @return Le niveau de faim (int).
     */    public int getHunger() {
        return hunger;
    }

    /**
     * Retourne l'endurance.
     *
     * @return L'endurance (int).
     */
    public int getEndurance() {
        return endurance;
    }

    /**
     * Retourne le niveau d'effet de potion.
     *
     * @return Le niveau d'effet de potion (int).
     */
    public int getPotion() {
        return potion;
    }

    /**
     * Retourne la force.
     *
     * @return La force (int).
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Retourne les points de vie.
     *
     * @return Les points de vie (int).
     */
    public int getHealth() {
        return health;
    }

    /**
     * Retourne le nombre de tics restants avant la prochaine action.
     *
     * @return Le nombre de tics (int).
     */
    public int getTicBeforeAction() {
        return ticBeforeAction;

    }

    /**
     * Retourne la cible actuelle du personnage.
     *
     * @return Le personnage ciblé ({@link person.Person}).
     */
    public Person getTarget() {
        return target;
    }

    /**
     * Retourne le lieu actuel du personnage.
     *
     * @return Le lieu ({@link place.Place}).
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Gère l'écoulement du temps pour le personnage.
     *
     * <p>À chaque tic :
     * <ul>
     * <li>Le niveau de faim ({@link #hunger}) diminue.</li>
     * <li>Si la faim atteint 0, la santé ({@link #health}) diminue.</li>
     * </ul>
     */
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

    /**
     * Fournit une représentation textuelle complète de l'état actuel du personnage.
     *
     * @return Une chaîne décrivant les attributs du personnage.
     */
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


