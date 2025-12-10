package person.lycanthrope;

public class Howl {
    private String message;

    public Howl(String message) {
        this.message = message;
    }

    public void display() {
        System.out.println("Hurlement" + message);
    }

    public String getMessage() {
        return message;
    }
}
