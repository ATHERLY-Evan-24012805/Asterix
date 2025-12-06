package person.types;

public interface Leader {
    default void lead(){
        System.out.println("I am leader");
    };
}
