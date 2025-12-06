package places;

import persons.Lycanthrope;
import persons.Person;
import persons.Roman.Roman;

public class RomanCity extends Place {

    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Roman ||
                person instanceof Lycanthrope;
    }
}
