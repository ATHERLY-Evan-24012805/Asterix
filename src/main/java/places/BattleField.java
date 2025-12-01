package places;

import persons.Person;

public class BattleField extends Place{

    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }

}
