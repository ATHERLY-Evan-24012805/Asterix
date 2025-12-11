package clock;

/**
 * Interface que tout objet capable de réagir aux avancées du temps doit implémenter.
 */
public interface TemporalObject {

    /**
     * Cette méthode est appelée par l'horloge chaque fois
     * qu'une unité de temps s'est écoulée dans la simulation.
     *
     * <p>Les objets implémentant cette méthode doivent contenir la logique
     * métier nécessaire pour mettre à jour leur état en fonction du temps écoulé
     * (par exemple, vieillir, se déplacer, changer de statut, etc.).
     */
    public void ticsPassed();
}
