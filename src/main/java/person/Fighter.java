package person;

public interface Fighter {
    default void fighter(){
        System.out.println("I am fighter");
    };

    void Fighter();
}
