package person;

/**
 * Classe humain transformé après la transformation d'un lycanthrope
 */
public class TransformedHuman extends Person {
    /**
     * Constructeur de humain transformé
     * @param name nom
     * @param gender genre
     * @param height taille
     * @param age age
     * @param strength force
     * @param endurance endurance
     */
    public TransformedHuman(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    /**
     * Duplication de personnage pour potion magique
     * @param name nom
     * @param gender genre
     * @param height taille
     * @param age age
     * @param strength force
     * @param endurance endurance
     * @return nouvelle instance de personnage
     */
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }
}
