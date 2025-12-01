package org.places;

import org.clanLeader.ClanLeader;
import org.foods.Food;
import org.persons.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Place {
    private String name;
    private int surface;
    private ClanLeader chief;
    private int census;
    protected List<Food> food = new ArrayList<>();
    protected List<Person> people = new ArrayList<>();

    public abstract boolean canAddPerson(Person person);

    public void addPerson(Person person) {
        if (canAddPerson(person)) {
            people.add(person);
        } else {
            System.out.println("Impossible d'ajouter cette personne ici : " + person);
        }
    }

    public void removePerson(Person person) {
        people.remove(person);
    }
    public List<Person> getPeople() {
        return people;
    }

    public void healSomeone(Person person) {
        person.heal();
    }

    public void feedSomeone(Person person) {
        person.eat();
    }

    @Override
    public String toString() {
        return "Nom : " + name + "\n" +
                "Superficie : " + surface + "\n" +
                "Chef de clan : " + chief + "\n" +
                "Recensement : " + census + "\n" +
                "Liste de personnes : " + people + "\n" +
                "Liste d'aliments : " + food;
    }



}
