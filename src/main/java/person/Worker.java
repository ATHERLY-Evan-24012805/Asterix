package person;

/**
 * Interface définissant la capacité d'un personnage à effectuer un travail ou une tâche spécifique.
 *
 * <p>Toute classe implémentant cette interface (comme les Forgerons ou les Marchands Gaulois)
 * a la responsabilité de définir la logique métier de son rôle productif
 * dans la méthode work().
 */
public sealed interface Worker permits person.gaulish.charac.Druid, person.gaulish.charac.GaulishBlacksmith, person.gaulish.charac.GaulishInnKeeper, person.gaulish.charac.GaulishShopKeeper{

    /**
     * Déclenche l'action de travail ou de production du personnage.
     *
     * <p>Cette méthode contient la logique spécifique au rôle de travailleur, comme
     * forger des objets, produire de la nourriture, ou soigner des alliés.
     */
    default void work(){
        System.out.println("I am a worker");
    };
}
