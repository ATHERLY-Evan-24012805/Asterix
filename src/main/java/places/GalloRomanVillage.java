package places;

import persons.Gaulish.Gaulish;
import persons.Person;
import persons.Roman.Roman;

public class GalloRomanVillage extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Gaulish ||
                person instanceof Roman;
    }
}
