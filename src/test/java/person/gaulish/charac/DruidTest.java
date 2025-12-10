package person.gaulish.charac;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import person.Person;
import person.roman.charac.RomanLegionary;
import place.types.BattleField;
import clock.Clock;

import java.util.ArrayList;
import java.util.List;


class DruidTest {

    private Druid druid;
    private BattleField battlefield;

    @BeforeEach
    void setUp() {
        druid = new Druid("Panoramix", 'M', 1.75, 150, 10, 10);
        battlefield = new BattleField();
    }

    @Test
    void testFindWeakestRoman() {
        // Utilisation de vraies instances de romains
        Person roman1 = new person.roman.charac.RomanLegionary("Julius", 'M', 1.8, 30, 10, 10);
        Person roman2 = new person.roman.charac.RomanLegionary("Brutus", 'M', 1.7, 40, 10, 10);

        roman1.setHealth(50);
        roman2.setHealth(30);

        List<Person> people = new ArrayList<>();
        people.add(roman1);
        people.add(roman2);

        Person weakest = druid.findWeakestRoman(people);
        assertEquals(roman2, weakest, "Le romain le plus faible doit être retourné");
    }

    @Test
    void testIsRoman() {
        Person roman = new person.roman.charac.RomanLegionary("Caesar", 'M', 1.8, 45, 12, 12);
        Person gaul = new Druid("Panoramix", 'M', 1.75, 150, 10, 10);

        assertTrue(druid.isRoman(roman));
        assertFalse(druid.isRoman(gaul));
    }

    @Test
    void testChangeRole() {

        // --- Arrange ---
        Person target = new Druid("Asterix", 'M', 1.6, 35, 8, 7);
        battlefield.addPerson(target);
        druid.setPlace(battlefield);

        // Mock du Clock singleton
        try (MockedStatic<Clock> mockedClock = Mockito.mockStatic(Clock.class)) {

            Clock mockClock = mock(Clock.class);
            mockedClock.when(Clock::getInstance).thenReturn(mockClock);

            // --- Act ---
            druid.changeRole(target, "BLACKSMITH");

            // --- Assert : Vérifie changement dans Battlefield ---
            assertEquals(1, battlefield.getPeople().size());
            Person newPerson = battlefield.getPeople().get(0);

            assertEquals("Asterix", newPerson.getName());
            assertEquals(GaulishBlacksmith.class, newPerson.getClass());

            // --- Assert : Vérifie interactions avec Clock ---
            verify(mockClock).unsubscribe(target);
            verify(mockClock).subscribe(newPerson);
        }
    }

    @Test
    void testFightInBattleField() {
        Person roman = new RomanLegionary("Julius", 'M', 1.8, 30, 10, 10);
        roman.setHealth(50);

        battlefield.addPerson(roman);
        druid.setPlace(battlefield);

        // On teste juste que le druide attaque sans exception
        assertDoesNotThrow(() -> druid.fight());
        // Le romain devrait avoir moins de PV après l'attaque
        assertTrue(roman.getHealth() < 50);
    }
}
