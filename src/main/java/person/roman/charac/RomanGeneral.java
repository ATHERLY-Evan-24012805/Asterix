package person.roman.charac;

import person.Person;
import person.roman.Roman;
import person.Fighter;
import person.Leader;
import place.Place;
import place.types.BattleField;

import java.util.ArrayList;

/**
 * Représente un Général Romain.
 *
 * <p>Le Général est un personnage de haut rang qui implémente les rôles de {@link person.Fighter}
 * et de {@link person.Leader}. Il est principalement responsable de la coordination des troupes
 * romaines et ne se bat lui-même qu'en dernier recours.
 */
public class RomanGeneral extends Roman implements Fighter, Leader {

    //constructeur
    /**
     * Construit une nouvelle instance de RomanGeneral.
     *
     * @param name Le nom du Général.
     * @param gender Le genre du Général ('M','F' ou 'X').
     * @param height La taille du Général.
     * @param age L'âge du Général.
     * @param strength La force de base du Général.
     * @param endurance L'endurance de base du Général.
     */
    public RomanGeneral(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
        this.setTicBeforeAction(5);
    }

    /**
     * Crée une nouvelle instance de RomanGeneral avec les caractéristiques données.
     * Utilisé pour la potion Duplicate.
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de RomanGeneral.
     */
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new RomanGeneral(name, gender, height, age, strength, endurance);
    }

    /**
     * Implémentation du rôle Fighter. Le Général ne se bat que sous certaines conditions.
     *
     * <p>Le combat n'a lieu que si :
     * <ul>
     * <li>Il se trouve sur un champ de bataille ({@link place.types.BattleField}).</li>
     * <li>Il ne lui reste aucun allié Romain (instance de {@link person.roman.Roman}, excluant lui-même)
     * sur le lieu.</li>
     * </ul>
     * S'il combat, il cible sa cible actuelle ({@code getTarget()}).
     */
    @Override
    public void fight() {
        Place place = this.getPlace();

        // Pas de combat en dehors d'un BattleField
        if (!(place instanceof BattleField)) {
            return;
        }

        // S'il lui reste des alliés, il ne peut se battre
        for (Person person : place.getListOfPersons()){
            if (person == this) continue;

            if (person instanceof Roman){
                return;
            }
        }
        this.fight(this.getTarget());
    }

    /**
     * Implémentation du rôle Leader. Le Général coordonne les attaques de ses Légionnaires.
     *
     * <p>Si le Général est sur un champ de bataille, et s'il a au moins deux Légionnaires présents,
     * ou si son {@code ticBeforeAction} est à zéro (temps écoulé), il ordonne aux deux premiers
     * Légionnaires trouvés d'attaquer la cible du Général.
     *
     * <p>Après l'action, le délai d'action ({@code ticBeforeAction}) est réinitialisé à 5.
     */
    @Override
    public void lead() {
    // fait en sorte que deux personnages puissent taper en meme temps
        Place place = this.getPlace();

        // Pas de combat en dehors d'un BattleField
        if (!(place instanceof BattleField)) {
            return;
        }

        // Instanciation de la liste de legionnaire
        ArrayList<Person> legionnaries = new ArrayList<>();
        for (Person person : place.getListOfPersons()){
            if (person instanceof RomanLegionary){
                legionnaries.add(person);
            }
        }

        //S'il a plus de deux alliés legionnaires
        if (legionnaries.size()>=2 || this.getTicBeforeAction() == 0){
            //faire agir le general
            legionnaries.get(0).fight(this.getTarget());
            legionnaries.get(1).fight(this.getTarget());
            this.setTicBeforeAction(5);
        }
    }

    /**
     * Est appelé à chaque tic de l'horloge.
     *
     * <p>Décrémente le compteur d'action ({@code ticBeforeAction}). Puis, appelle la
     * méthode lead() pour vérifier si une action de coordination doit être effectuée.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();
        this.setTicBeforeAction(this.getTicBeforeAction()-1);
        this.lead();
    }
}

