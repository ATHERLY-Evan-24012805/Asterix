package person.gaulish.charac;

import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import clock.Clock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;
import place.types.BattleField;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        Person target = new Druid("Asterix", 'M', 1.6, 35, 8, 7);
        battlefield.addPerson(target);
        druid.setPlace(battlefield);

        Clock clock = mock(Clock.class);

        druid.changeRole(target, "BLACKSMITH", clock);

        // Vérifie que l'ancienne personne a été retirée et la nouvelle ajoutée
        assertEquals(1, battlefield.getPeople().size());
        assertEquals("Asterix", battlefield.getPeople().get(0).getName());
        assertEquals(person.gaulish.charac.GaulishBlacksmith.class, battlefield.getPeople().get(0).getClass());

        verify(clock).unsubscribe(target);
        verify(clock).subscribe(battlefield.getPeople().get(0));
    }

    @Test
    void testFightInBattleField() {
        Person roman = new person.roman.charac.RomanLegionary("Julius", 'M', 1.8, 30, 10, 10);
        roman.setHealth(50);

        battlefield.addPerson(roman);
        druid.setPlace(battlefield);

        // On teste juste que le druide attaque sans exception
        assertDoesNotThrow(() -> druid.fight());
        // Le romain devrait avoir moins de PV après l'attaque
        assertTrue(roman.getHealth() < 50);
    }
}
