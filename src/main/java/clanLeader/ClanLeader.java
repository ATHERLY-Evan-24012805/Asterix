package clanLeader;

import MagicPotion.MagicPotion;
import persons.Gaulish.charac.Druide;
import persons.Person;
import place.Place;


public class ClanLeader {
    private String name;
    private String gender;
    private int age;
    private Place place;

    public ClanLeader(String name, String gender, int age, Place place) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.place = place;
    }

    public void examinePlace() {
        System.out.println(place.toString());
    }

    public void createPerson(Person person) {
        place.addPerson(person);
    }

    public void healSomeone(Person person) {
        place.healSomeone(person);
    }

    public void healAll() {
        for (Person p : place.getPeople()) {
            p.heal();
        }
    }

    public void feedSomeone(Person person) {
        place.feedSomeone(person);
    }

    public void feedAll() {
        for (Person p : place.getPeople()) {
            p.eat();
        }
    }

    public void askDruidForPotion(Druide druid) {}

    public void givePotion(Person person, MagicPotion potion) {}

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
