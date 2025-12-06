package place.types;

import place.Place;
import person.Person;

public class GalloRomanVillage extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }
}
