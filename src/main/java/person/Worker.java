package person;

public sealed interface Worker permits person.gaulish.charac.Druid, person.gaulish.charac.GaulishBlacksmith, person.gaulish.charac.GaulishInnKeeper, person.gaulish.charac.GaulishShopKeeper{
    default void work(){
        System.out.println("I am a worker");
    };
}
