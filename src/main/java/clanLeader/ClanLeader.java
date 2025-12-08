package clanLeader;

import MagicPotion.MagicPotion;
import person.Gaulish.charac.Druid;
import person.Person;
import place.Place;


/**
 * Représente le chef d'un clan, responsable de la gestion d'un lieu et des personnes qui s'y trouvent.
 * Le ClanLeader peut examiner un lieu, créer ou transférer des personnages, ainsi que
 * nourrir ou soigner des individus.
 */
public class ClanLeader {

    private String name;
    private String gender;
    private int age;
    private Place place;

    /**
     * Constructeur permettant d'initialiser un chef de clan.
     *
     * @param name   nom du chef
     * @param gender genre du chef
     * @param age    age du chef
     * @param place  lieu que le chef dirige
     */
    public ClanLeader(String name, String gender, int age, Place place) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.place = place;
    }

    /**
     * Affiche les informations du lieu dirigé par le chef.
     */
    public void examinePlace() {
        System.out.println(place.toString());
    }

    /**
     * Ajoute une personne au lieu dirigé.
     *
     * @param person la personne à ajouter
     */
    public void createPerson(Person person) {
        place.addPerson(person);
    }


    /**
     * Soigne une personne spécifique dans le lieu.
     *
     * @param person la personne à soigner
     */
    public void healSomeone(Person person) {
        place.healSomeone(person);
    }

    /**
     * Soigne toutes les personnes présentes dans le lieu.
     */
    public void healAll() {
        for (Person p : place.getPeople()) {
            p.heal();
        }
    }

    /**
     * Nourrit une personne spécifique dans le lieu.
     *
     * @param person la personne à nourrir
     */
    public void feedSomeone(Person person) {
        place.feedSomeone(person);
    }

    /**
     * Nourrit toutes les personnes présentes dans le lieu.
     */
    public void feedAll() {
        for (Person p : place.getPeople()) {
            p.eat();
        }
    }

    public void askDruidForPotion(Druid druid) {}

    public void givePotion(Person person, MagicPotion potion) {}

    /**
     * Transfère une personne du lieu actuel vers une destination.
     *
     * <p>La méthode vérifie d'abord que la personne est bien présente dans le lieu actuel, puis
     * s'assure que la destination autorise cette personne. Si c'est le cas, elle est retirée
     * du lieu actuel puis ajoutée à la destination.
     *
     * @param person      la personne à transférer
     * @param destination le lieu de destination
     */
    public void transferPerson(Person person, Place destination) {

        // Vérifie si la personne à déplacer est bien présente dans ce lieu.
        if (!place.getPeople().contains(person)) {
            System.out.println("La personne n'est pas dans ce lieu.");
            return;
        }

        // Vérifie que la personne puisse bien être ajoutée vers la destination.
        if (destination.canAddPerson(person)) {
            place.removePerson(person);
            destination.addPerson(person);
        }
        else {
            System.out.println("Cette personne n'est pas autorisée vers cette destination.");
        }
    }
}
