package org.places;

import org.persons.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Place {
    private String name;
    private int surface;

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
}
