<<<<<<<< HEAD:src/main/java/person/types/Roman/charac/RomanLegionary.java
package person.types.Roman.charac;

import person.types.Roman.Roman;
import person.types.Fighter;
========
package person.Roman.charac;

import person.Roman.Roman;
import person.Fighter;
>>>>>>>> origin/boutiques:src/main/java/person/Roman/charac/RomanLegionary.java

public class RomanLegionary extends Roman implements Fighter{
    public RomanLegionary(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void Fighter(){

    }
}
