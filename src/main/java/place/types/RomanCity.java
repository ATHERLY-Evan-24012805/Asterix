package place.types;

import person.Person;
import person.roman.Roman;
import person.lycanthrope.Lycanthrope;
import place.Place;

/**
 * Classe représentant une ville romaine
 *
 * <p>Une ville romaine est un lieu où seuls certains types de personnages peuvent entrer :
 * – Roman
 * – Lycanthrope
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class RomanCity extends Place {

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Roman ||
                person instanceof Lycanthrope;
    }
}
