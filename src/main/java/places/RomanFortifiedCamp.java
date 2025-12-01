package places;

import persons.Person;
import persons.Roman.Roman;
import persons.Roman.charac.RomanGeneral;
import persons.Roman.charac.RomanLegionary;

public class RomanFortifiedCamp extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof RomanGeneral ||
        person instanceof RomanLegionary;
    }
}
