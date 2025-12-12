package person;

import person.lycanthrope.Lycanthrope;
import place.types.Enclosure;

/**
 * Classe maitre de zoo fantastique
 */
public class FantasyZooMaster {

    /**
     * Déplace un lycanthrope vers un autre enclos
     * @param l Lycanthrope à déplace
     * @param target Lieu cible
     */
    public void moveLycanthrope(Lycanthrope l, Enclosure target) {
        l.moveTo(target);
    }
}
