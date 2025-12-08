<<<<<<<< HEAD:src/main/java/person/types/Gaulish/charac/GaulishInnKeeper.java
package person.types.Gaulish.charac;
import person.types.Gaulish.Gaulish;
import person.types.Worker;
========
package person.Gaulish.charac;
import person.Gaulish.Gaulish;
import person.Worker;
>>>>>>>> origin/boutiques:src/main/java/person/Gaulish/charac/GaulishInnKeeper.java

public class GaulishInnKeeper extends Gaulish implements Worker {
    public GaulishInnKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void work() {

    }
}
