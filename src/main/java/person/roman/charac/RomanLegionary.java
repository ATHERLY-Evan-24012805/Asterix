package person.roman.charac;

import person.Person;
import person.roman.Roman;
import person.Fighter;

/**
 * Représente un Légionnaire Romain (Roman Legionary).
 *
 * <p>Le Légionnaire est l'unité de combat de base de l'armée romaine. Il implémente l'interface
 * {@link person.Fighter} et est programmé pour attaquer sa cible à chaque unité de temps.
 */
public non-sealed class RomanLegionary extends Roman implements Fighter{

    /**
     * Construit une nouvelle instance de {@code RomanLegionary}.
     *
     * @param name Le nom du Légionnaire.
     * @param gender Le genre du Légionnaire ('M', 'F' ou 'X').
     * @param height La taille du Légionnaire.
     * @param age L'âge du Légionnaire.
     * @param strength La force de base du Légionnaire.
     * @param endurance L'endurance de base du Légionnaire.
     */
    public RomanLegionary(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    /**
     * Crée une nouvelle instance de RomanLegionary avec les caractéristiques données.
     * Utilisé pour la potion duplicate
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de RomanLegionary.
     */
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new RomanLegionary(name, gender, height, age, strength, endurance);
    }
    public String getType(){
        return "legionnaire romain";
    }

    /**
     * Implémentation du rôle Fighter.
     *
     * <p>Le Légionnaire attaque immédiatement sa cible actuelle.
     */
    @Override
    public int fight(){
    // Tape et obeit au general
        if (this.getTarget() == null) {
            return 0;
        }
        return hit(getTarget());
    }

    /**
     * Est appelé à chaque tic de l'horloge.
     *
     * <p>Le Légionnaire exécute immédiatement son action de combat à chaque tic.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();
        this.fight();
    }
}
