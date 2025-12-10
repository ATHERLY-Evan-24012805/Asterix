package place.types;

import person.TransformedHuman;
import place.Place;
import person.Person;
import person.roman.charac.RomanGeneral;
import person.roman.charac.RomanLegionary;
import person.lycanthrope.Lycanthrope;

/**
 * Classe représentant un camp fortifié romain.
 *
 * <p>Un camp fortifié romain est un lieu où seuls certains types de personnages peuvent entrer :
 * – RomanGeneral
 * – RomanLegionary
 * – Lycanthrope.
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class RomanFortifiedCamp extends Place {

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof RomanGeneral ||
                person instanceof RomanLegionary ||
                person instanceof Lycanthrope || person instanceof TransformedHuman;
    }
}
