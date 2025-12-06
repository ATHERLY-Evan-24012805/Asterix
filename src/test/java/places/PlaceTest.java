package places;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import person.types.Gaulish.Gaulish;
import person.types.Gaulish.charac.Druide;
import person.types.Gaulish.charac.GaulishBlacksmith;
import person.types.Gaulish.charac.GaulishInnKeeper;
import person.types.Gaulish.charac.GaulishShopKeeper;
import person.Person;
import person.types.Roman.Roman;
import person.types.Roman.charac.RomanGeneral;
import person.types.Roman.charac.RomanLegionary;
import person.types.Roman.charac.RomanPrefect;
import place.Place;
import place.types.*;

import java.util.List;

public class PlaceTest {

    Place romanCity;
    Place battleField;
    Place enclosure;
    Place gallicVillage;
    Place galloRomanVillage;
    Place romanFortifiedCamp;

    Person romanMock;
    Person romanGeneralMock;
    Person romanLegionaryMock;
    Person romanPrefectMock;

    Person gaulishMock;
    Person gaulishBlacksmithMock;
    Person gaulishInnKeeperMock;
    Person gaulishShopKeeperMock;
    Person druidMock;

    @BeforeEach
    void setup() {
        romanCity = new RomanCity();
        battleField = new BattleField();
        enclosure = new Enclosure();
        gallicVillage = new GallicVillage();
        galloRomanVillage = new GalloRomanVillage();
        romanFortifiedCamp = new RomanFortifiedCamp();

        romanMock = mock(Roman.class);
        romanGeneralMock = mock(RomanGeneral.class);
        romanLegionaryMock = mock(RomanLegionary.class);
        romanPrefectMock = mock(RomanPrefect.class);

        gaulishMock = mock(Gaulish.class);
        gaulishBlacksmithMock = mock(GaulishBlacksmith.class);
        gaulishInnKeeperMock = mock(GaulishInnKeeper.class);
        gaulishShopKeeperMock = mock(GaulishShopKeeper.class);
        druidMock = mock(Druide.class);
    }

    @Test
    void testAddPersonRomanCity() {
        romanCity.addPerson(romanMock);
        List<Person> people = romanCity.getPeople();
        assertTrue(people.contains(romanMock));
        assertEquals(1, people.size());

        romanCity.addPerson(gaulishMock);
        assertFalse(people.contains(gaulishMock));
        assertEquals(1, people.size());

        romanCity.addPerson(druidMock);
        assertFalse(people.contains(druidMock));
        assertEquals(1, people.size());
    }

    @Test
    void testAddPersonRomanFortifiedCamp() {
        romanFortifiedCamp.addPerson(romanMock);
        List<Person> people = romanFortifiedCamp.getPeople();
        assertFalse(people.contains(romanMock));
        assertEquals(0, people.size());

        romanFortifiedCamp.addPerson(romanPrefectMock);
        assertFalse(people.contains(romanPrefectMock));
        assertEquals(0, people.size());

        romanFortifiedCamp.addPerson(gaulishShopKeeperMock);
        assertFalse(people.contains(gaulishShopKeeperMock));
        assertEquals(0, people.size());

        romanFortifiedCamp.addPerson(romanGeneralMock);
        assertTrue(people.contains(romanGeneralMock));
        assertEquals(1, people.size());

        romanFortifiedCamp.addPerson(romanLegionaryMock);
        assertTrue(people.contains(romanLegionaryMock));
        assertEquals(2, people.size());



    }

    @Test
    void testRemovePerson() {
        romanCity.addPerson(romanMock);
        List<Person> people = romanCity.getPeople();
        assertTrue(people.contains(romanMock));

        romanCity.removePerson(romanMock);

        assertFalse(people.contains(romanMock));
        assertEquals(0, people.size());
    }

    @Test
    void testHealSomeone() {
        romanCity.addPerson(romanMock);
        romanCity.healSomeone(romanMock);
        verify(romanMock).heal();
    }

    @Test
    void testFeedPeople() {
        romanCity.addPerson(romanMock);
        romanCity.feedSomeone(romanMock);
        verify(romanMock).eat();
    }
}