package place.types;

import person.Person;
import person.TransformedHuman;
import person.gaulish.*;
import person.lycanthrope.Lycanthrope;
import place.Place;

/**
 * Classe représentant un village gaulois
 *
 * <p>Un village gaulois est un lieu où seuls certains types de personnages peuvent entrer :
 * – Gaulish
 * – Lycanthrope
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class GallicVillage extends Place {

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Gaulish ||
                person instanceof Lycanthrope || person instanceof TransformedHuman;
    }
}
