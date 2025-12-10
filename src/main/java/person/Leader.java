package person;

public sealed interface Leader permits person.roman.charac.RomanGeneral, person.gaulish.charac.Druid, person.roman.charac.RomanPrefect{
    default void lead() {
        System.out.println("I am leader");
    };
}
