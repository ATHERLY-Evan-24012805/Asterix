package person;

public interface Fighter {

    /**
     * Déclenche l'action de combat du personnage.
     *
     * <p>La méthode ne prend aucun paramètre, mais elle utilise la cible
     * interne du personnage ou les informations sur le lieu ({@link place.Place})
     * pour déterminer l'adversaire et les actions à effectuer.
     */
    int fight();
}
