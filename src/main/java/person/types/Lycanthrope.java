package person.types;


import person.Person;

public class Lycanthrope extends Person implements Fighter {

    public Lycanthrope(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void Fighter(){

    }
}
