package org.places;

import org.persons.Person;

public class GalloRomanVillage extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return false;
    }
}
