package place.types;

import person.Person;
import place.Place;

public class BattleField extends Place {

    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }

}
