package persons.Roman.charac;

import persons.Roman.Roman;
import persons.Leader;

public class RomanPrefect extends Roman implements Leader{
    public RomanPrefect(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }


    @Override
    public void lead(){

    }
}
