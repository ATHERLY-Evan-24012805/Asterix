package place.types;

import place.Place;
import person.types.Gaulish.Gaulish;
import person.Person;

public class GallicVillage extends Place{


    @Override
    public boolean canAddPerson(Person person) {
        return false;
    }
}
