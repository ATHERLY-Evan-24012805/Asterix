package person;

import person.lycanthrope.Lycanthrope;
import place.types.Enclosure;

public class FantasyZooMaster {

    public void moveLycanthrope(Lycanthrope l, Enclosure target) {
        l.moveTo(target);
    }
}
