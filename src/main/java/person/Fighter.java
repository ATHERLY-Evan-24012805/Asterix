package person;

import person.gaulish.charac.Druid;
import person.lycanthrope.Lycanthrope;
import person.roman.charac.RomanGeneral;
import person.roman.charac.RomanLegionary;

public sealed interface Fighter permits RomanLegionary, RomanGeneral, Druid, Lycanthrope {

    /**
     * Déclenche l'action de combat du personnage.
     *
     * <p>La méthode ne prend aucun paramètre, mais elle utilise la cible
     * interne du personnage ou les informations sur le lieu ({@link place.Place})
     * pour déterminer l'adversaire et les actions à effectuer.
     */
    int fight();
}
