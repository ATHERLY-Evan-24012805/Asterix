package places;

import persons.Lycanthrope;
import persons.Person;

public class Enclosure extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Lycanthrope;
    }
}
