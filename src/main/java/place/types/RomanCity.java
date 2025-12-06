package place.types;

import place.Place;
import person.Person;
import person.types.Roman.Roman;

public class RomanCity extends Place {

    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Roman;
    }
}
