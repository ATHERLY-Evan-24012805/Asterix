package person;

import person.roman.charac.RomanLegionary;

public sealed interface Fighter permits person.roman.charac.RomanLegionary, person.roman.charac.RomanGeneral, person.gaulish.charac.Druid, person.lycanthrope.Lycanthrope {
    /*default void fighter(){
        System.out.println("I am fighter");
    };
     */
    void fight();
}
