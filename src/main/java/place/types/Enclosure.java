package place.types;


import person.Person;

import person.lycanthrope.Lycanthrope;
import place.Place;

/**
 * Classe représentant un enclos
 *
 * <p>Un enclos ne peut contenir que des créatures fantastiques.
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class Enclosure extends Place {

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Lycanthrope;
    }
}
