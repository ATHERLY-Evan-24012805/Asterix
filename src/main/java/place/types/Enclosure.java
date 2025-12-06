package place.types;

import place.Place;
import person.Person;

public class Enclosure extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }
}
