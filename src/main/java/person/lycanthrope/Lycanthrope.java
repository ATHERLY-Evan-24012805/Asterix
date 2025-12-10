package person.lycanthrope;


import person.Fighter;
import person.Person;

public class Lycanthrope extends Person implements Fighter {

    public Lycanthrope(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }
    @Override
    public void fight(){

    }

    @Override
    public void ticsPassed() {

    }
}
