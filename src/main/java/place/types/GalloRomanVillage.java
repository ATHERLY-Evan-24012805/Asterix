package place.types;

import person.Person;
import person.types.Gaulish.Gaulish;
import person.types.Roman.Roman;
import place.Place;

/**
 * Classe représentant une ville gallo-romaine
 *
 * <p>Une ville gallo-romaine est un lieu où seuls certains types de personnages peuvent entrer :
 * – Roman
 * – Gaulish
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class GalloRomanVillage extends Place {

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Gaulish ||
                person instanceof Roman;
    }
}
