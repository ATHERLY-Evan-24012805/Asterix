package place.types;

import person.Person;

/**
 * Classe représentant un champ de bataille.
 *
 * <p>Un champ de bataille peut contenir tous les types de personnages.
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class BattleField extends place.Place {

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return true;
    }

    @Override
    public String getType() {
        return "Champ de bataille";
    }
}

