package person;

/**
 * Interface définissant la capacité d'un personnage à se battre.
 *
 * <p>Toute classe implémentant cette interface doit fournir une implémentation
 * de la méthode fight(), qui contient la logique spécifique de combat de ce
 * personnage (cible, dégâts, conditions de déclenchement).
 */
public interface Fighter {

    /**
     * Déclenche l'action de combat du personnage.
     *
     * <p>La méthode ne prend aucun paramètre, mais elle utilise la cible
     * interne du personnage ou les informations sur le lieu ({@link place.Place})
     * pour déterminer l'adversaire et les actions à effectuer.
     */
    void fight();
}
