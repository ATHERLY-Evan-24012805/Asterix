package person.roman.charac;

import clock.Clock;
import person.Person;
import person.roman.Roman;
import person.Leader;

/**
 * Représente un Préfet Romain (Roman Prefect).
 *
 * <p>Le Préfet est un personnage de haut rang qui implémente le rôle de Leader.
 * Sa principale fonction de leadership est de promouvoir un autre personnage ciblé
 * au rang de {@link RomanGeneral} lorsque son compteur d'action est écoulé.
 */
public class RomanPrefect extends Roman implements Leader{

    /**
     * Construit une nouvelle instance de RomanPrefect.
     *
     * <p>Le délai avant la première action est initialisé à 24 tics.
     *
     * @param name Le nom du Préfet.
     * @param gender Le genre du Préfet ('M', 'F' ou 'X').
     * @param height La taille du Préfet.
     * @param age L'âge du Préfet.
     * @param strength La force de base du Préfet.
     * @param endurance L'endurance de base du Préfet.
     */
    public RomanPrefect(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
        this.setTicBeforeAction(24);
    }

    /**
     * Crée une nouvelle instance de RomanPrefect}avec les caractéristiques données.
     * Utilisé pour la potion Dupliacte
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de RomanPrefect.
     */
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new RomanPrefect(name, gender, height, age, strength, endurance);
    }

    /**
     * Implémentation du rôle Leader.
     *
     * <p>Cette méthode effectue la promotion du personnage ciblé en {@link RomanGeneral}.
     * Le personnage cible est retiré de son lieu et du {@link clock.Clock}, puis
     * une nouvelle instance de RomanGeneral est créée en utilisant les anciennes
     * caractéristiques et est ajoutée au lieu et à la Clock.
     */
    @Override
    public void lead() {
        // Promeut un legionnaire en general
        Person replacement = null;
        replacement = new RomanGeneral(this.getTarget().getName(),
                this.getTarget().getGender(),
                this.getTarget().getHeight(),
                this.getTarget().getAge(),
                this.getTarget().getStrength(),
                this.getTarget().getEndurance()
        );

        // Supprimer l'ancienne personne
        Clock.getInstance().unsubscribe(this.getTarget());
        this.getPlace().removePerson(this.getTarget());

        // Ajouter la nouvelle personne
        this.getPlace().addPerson(replacement);
        Clock.getInstance().subscribe(replacement);
    }

    /**
     * Est appelé à chaque tic de l'horloge.
     *
     * <p>Il appelle la méthode parente, qui est responsable de la décrémentation du compteur d'action et
     * de l'appel de lead() lorsque le temps est écoulé.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();
    }
}
