package clock;

import food.items.Fish;
import food.items.FourLeafClover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * Tests unitaires pour la classe Clock.
 */
public class TestClock {
    private Clock clock;
    private Fish fish = new Fish();
    private FourLeafClover fourLeafClover = new FourLeafClover();

    @Mock
    TemporalObject FakeSub1;
    TemporalObject FakeSub2;

    @BeforeEach
    void initClock() {
        this.clock = new Clock();
    }

    @Test
    void testSubscibtion(){
        Assertions.assertTrue(clock.getSubscribers().isEmpty());
        clock.subscribe(FakeSub1);
        clock.subscribe(FakeSub2);
        Assertions.assertFalse(clock.getSubscribers().isEmpty());
    }

    @Test
    void testUnsubscribe(){
        Assertions.assertTrue(clock.getSubscribers().isEmpty());
        clock.subscribe(FakeSub1);
        Assertions.assertFalse(clock.getSubscribers().isEmpty());
        clock.unsubscribe(FakeSub1);
        Assertions.assertTrue(clock.getSubscribers().isEmpty());
    }

    @Test
    void testNotifySubscribers(){
        clock.subscribe(fish);
        clock.subscribe(fourLeafClover);
        clock.notifySubscribers();
        int resultFish = fish.getIsFreshFor();
        int resultClover = fourLeafClover.getIsFreshFor();
        Assertions.assertEquals(23, resultFish);
        Assertions.assertEquals(47, resultClover);
    }

    @Test
    void testFoodsPassed(){
        Assertions.assertTrue(fish.getPeremption());
        Assertions.assertTrue(fourLeafClover.getPeremption());

        clock.subscribe(fish);
        clock.subscribe(fourLeafClover);
        clock.notifySubscribers();
        for (int i = 0; i < 24; i++ ){
            clock.notifySubscribers();
        }
        //le poisson doit être perimé
        Assertions.assertFalse(fish.getPeremption());

        //le trèfle ne doit pas être perimé
        Assertions.assertTrue(fourLeafClover.getPeremption());
    }

}
