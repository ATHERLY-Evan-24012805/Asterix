package person.lycanthrope;

/**
 * Hurlement de la meute distinctif de celle-ci
 */
public class Howl {
    private String message;

    /**
     * Constructeur de hurlement
     * @param message
     */
    public Howl(String message) {
        this.message = message;
    }

    /**
     * Affichage du hurlement
     */
    public void display() {
        System.out.println("Hurlement" + message);
    }

    /**
     * Retourne le hurlement
     * @return hurlement
     */
    public String getMessage() {
        return message;
    }
}
