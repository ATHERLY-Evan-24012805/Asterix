package places;

import persons.Person;

public class GalloRomanVillage extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }
}
