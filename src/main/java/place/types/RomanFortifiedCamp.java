package place.types;

import place.Place;
import person.Person;
import person.types.Roman.charac.RomanGeneral;
import person.types.Roman.charac.RomanLegionary;

public class RomanFortifiedCamp extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof RomanGeneral ||
        person instanceof RomanLegionary;
    }
}
