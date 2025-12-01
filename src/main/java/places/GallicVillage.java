package places;

import persons.Gaulish.Gaulish;
import persons.Person;

public class GallicVillage extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Gaulish;
    }
}
