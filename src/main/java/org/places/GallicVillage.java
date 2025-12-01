package org.places;

import org.persons.Person;

public class GallicVillage extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return false;
    }
}
