package org.places;

import org.persons.Person;

public class Enclosure extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return false;
    }
}
