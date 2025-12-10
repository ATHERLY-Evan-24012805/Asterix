package person;

public class TransformedHuman extends Person {
    public TransformedHuman(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }
}
