package org.places;

import org.persons.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Place {
    private String name;
    private int surface;
    private ClanLeader chief;
    private int census;
    private List<Person> ListOfPeople;
    private List<Food> ListOfFood;

    protected List<Person> people = new ArrayList<>();
    public void addPerson(Person person) {
        people.add(person);
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
                "Liste de personnes : " + ListOfPeople + "\n" +
                "Liste d'aliments : " + ListOfFood;
    }



}
