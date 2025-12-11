package person;

/**
 * Interface définissant la capacité d'un personnage à exercer un rôle de leadership
 * ou de coordination
 *
 * <p>Les personnages implémentant cette interface (comme les Généraux Romains ou les Druides)
 * ont la responsabilité d'initier des actions ou de modifier l'état d'autres personnages.
 */
public interface Leader {

    /**
     * Déclenche l'action de leadership ou de commandement du personnage.
     *
     * <p>Cette méthode contient la logique spécifique au rôle de leader, comme
     * donner des ordres, coordonner des attaques, ou modifier le rôle d'autres
     * personnages.
     */
    default void lead() {
        System.out.println("I am leader");
    };
}
