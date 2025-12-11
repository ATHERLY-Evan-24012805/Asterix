package person.gaulish.charac;
import person.Person;
import person.gaulish.Gaulish;
import person.Worker;
import place.Place;

public class GaulishInnKeeper extends Gaulish implements Worker {

    private int worktimer = 20;

    public GaulishInnKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishInnKeeper(name, gender, height, age, strength, endurance);
    }

    public String getType(){
        return "Aubergiste";
    }

    @Override
    public void work() {
        Place place = this.getPlace();

        // Cherche la personne avec le moins de PV
        Person weakest = null;

        for (Person p : place.getPeople()) {
            if (weakest == null || p.getHealth() < weakest.getHealth()) {
                weakest = p;
            }
        }

        // Si on a trouvé quelqu’un, on le soigne
        if (weakest != null) {
            place.healSomeone(weakest);
            System.out.println(getName() + " a soigné " + weakest.getName() +
                    " (" + weakest.getHealth() + " PV)");
        }
    }

    @Override
    public void ticsPassed() {
        this.worktimer--;

        if (worktimer == 0) {
            work();
            worktimer = 20;
        }
    }
}
