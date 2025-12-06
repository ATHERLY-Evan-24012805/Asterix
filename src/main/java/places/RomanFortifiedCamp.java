package places;

import persons.Lycanthrope;
import persons.Person;
import persons.Roman.charac.RomanGeneral;
import persons.Roman.charac.RomanLegionary;

public class RomanFortifiedCamp extends Place{
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof RomanGeneral ||
                person instanceof RomanLegionary ||
                person instanceof Lycanthrope;
    }
}
