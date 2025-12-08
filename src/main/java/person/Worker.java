package person;

public interface Worker {
    default void work(){
        System.out.println("I am a worker");
    };
}
