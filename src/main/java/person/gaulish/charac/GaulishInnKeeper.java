package person.gaulish.charac;
import person.Person;
import person.gaulish.Gaulish;
import person.Worker;
import place.Place;

/**
 * Représente un Aubergiste Gaulois (InnKeeper).
 *
 * <p>Un Aubergiste est un type de Gaulois qui implémente le rôle de Travailleur.
 * Son rôle principal est de soigner les habitants du lieu où il se trouve, en ciblant
 * la personne possédant le moins de points de vie.
 */
public class GaulishInnKeeper extends Gaulish implements Worker {

    private int worktimer = 20;

    /**
     * Construit une nouvelle instance de GaulishInnKeeper.
     *
     * @param name Le nom de l'Aubergiste.
     * @param gender Le genre de l'Aubergiste ('M', 'F' ou 'X').
     * @param height La taille de l'Aubergiste.
     * @param age L'âge de l'Aubergiste.
     * @param strength La force de base de l'Aubergiste.
     * @param endurance L'endurance de base de l'Aubergiste.
     */
    public GaulishInnKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    /**
     * Crée une nouvelle instance de GaulishInnKeeper avec les caractéristiques données.
     * Utilisé pour la potion Duplicate.
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de GaulishInnKeeper.
     */
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishInnKeeper(name, gender, height, age, strength, endurance);
    }

    /**
     * La fonction work pour l'Aubergiste consiste à soigner la personne la plus faible du lieu.
     *
     * <p>Elle parcourt tous les habitants du {@link Place} actuel pour identifier la personne
     * ayant le moins de points de vie ({@code health}), puis appelle la fonction de soin du lieu
     * ({@code healSomeone}) pour cette personne.
     */
    @Override
    public void work() {
        Place place = this.getPlace();

        // Cherche la personne avec le moins de PV
        Person weakest = null;

        for (Person p : place.getPeople()) {
            if (weakest == null || p.getHealth() < weakest.getHealth()) {
                weakest = p;
            }
        }

        // Si on a trouvé quelqu’un, on le soigne
        if (weakest != null) {
            place.healSomeone(weakest);
            System.out.println(getName() + " a soigné " + weakest.getName() +
                    " (" + weakest.getHealth() + " PV)");
        }
    }

    /**
     * Appelé à chaque tic de l'horloge. Gère le compteur de travail.
     *
     * <p>Décrémente le worktimer. Lorsque le compteur atteint 0,
     * la méthode work est appelée et le compteur est réinitialisé.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();

        this.worktimer--;

        if (worktimer == 0) {
            work();
            worktimer = 20;
        }
    }
}
