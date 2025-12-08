package person.gaulish.charac;
import person.gaulish.Gaulish;
import person.Worker;

public class GaulishShopKeeper extends Gaulish implements Worker {
    public GaulishShopKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void work() {
// Crée de la bouffe reste a decider de la frequence

    }

    @Override
    public void ticsPassed() {}
}
