<<<<<<<< HEAD:src/main/java/person/types/Roman/charac/RomanPrefect.java
package person.types.Roman.charac;

import person.types.Roman.Roman;
import person.types.Leader;
========
package person.Roman.charac;

import person.Roman.Roman;
import person.Leader;
>>>>>>>> origin/boutiques:src/main/java/person/Roman/charac/RomanPrefect.java

public class RomanPrefect extends Roman implements Leader{
    public RomanPrefect(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }


    @Override
    public void lead(){

    }
}
