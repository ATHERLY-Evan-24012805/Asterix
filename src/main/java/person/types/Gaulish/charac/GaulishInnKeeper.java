package person.types.Gaulish.charac;
import person.types.Gaulish.Gaulish;
import person.types.Worker;

public class GaulishInnKeeper extends Gaulish implements Worker {
    public GaulishInnKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void work() {

    }
}
