package places;

import persons.Person;

public class Enclosure extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }
}
