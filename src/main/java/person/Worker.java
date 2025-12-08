<<<<<<<< HEAD:src/main/java/person/types/Worker.java
package person.types;
========
package person;
>>>>>>>> origin/boutiques:src/main/java/person/Worker.java

public interface Worker {
    default void work(){
        System.out.println("I am a worker");
    };
}
