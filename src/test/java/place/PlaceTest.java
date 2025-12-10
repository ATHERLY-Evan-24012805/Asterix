package place;

import food.Food;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import person.Person;
import person.roman.Roman;
import person.roman.charac.RomanGeneral;
import person.roman.charac.RomanLegionary;
import person.roman.charac.RomanPrefect;
import person.gaulish.Gaulish;
import person.gaulish.charac.Druid;
import person.gaulish.charac.GaulishBlacksmith;
import person.gaulish.charac.GaulishInnKeeper;
import person.gaulish.charac.GaulishShopKeeper;
import person.lycanthrope.Lycanthrope;
import place.Place;
import place.*;
import place.types.*;

import java.util.List;

/**
 * Tests unitaires pour la classe Place et ses sous-classes.
 * Vérifie l'ajout, la suppression, les doublons, l'alimentation et les soins des personnages.
 */
public class PlaceTest {

    // Lieux à tester
    Place romanCity;
    Place battleField;
    Place enclosure;
    Place gallicVillage;
    Place galloRomanVillage;
    Place romanFortifiedCamp;

    // Mocks romains
    Person romanMock;
    Person romanMock2;
    Person romanGeneralMock;
    Person romanLegionaryMock;
    Person romanPrefectMock;

    // Mocks gaulois
    Person gaulishMock;
    Person gaulishBlacksmithMock;
    Person gaulishInnKeeperMock;
    Person gaulishShopKeeperMock;
    Person druidMock;

    // Mock créature fantastique
    Person lycanthropeMock;

    /**
     * Initialise les lieux et les mocks de personnages avant chaque test.
     * - Crée les différentes instances de Place.
     * - Crée des mocks pour les personnages romains, gaulois et lycanthropes.
     *
     * <p>Cette méthode est exécutée avant chaque méthode annotée @Test pour
     * garantir que les tests ne partagent pas de données entre eux.</p>
     */
    @BeforeEach
    void setup() {
        romanCity = new RomanCity();
        battleField = new BattleField();
        enclosure = new Enclosure("Enclos Sud");
        gallicVillage = new GallicVillage();
        galloRomanVillage = new GalloRomanVillage();
        romanFortifiedCamp = new RomanFortifiedCamp();

        romanMock = mock(Roman.class);
        romanMock2 = mock(Roman.class);
        romanGeneralMock = mock(RomanGeneral.class);
        romanLegionaryMock = mock(RomanLegionary.class);
        romanPrefectMock = mock(RomanPrefect.class);

        gaulishMock = mock(Gaulish.class);
        gaulishBlacksmithMock = mock(GaulishBlacksmith.class);
        gaulishInnKeeperMock = mock(GaulishInnKeeper.class);
        gaulishShopKeeperMock = mock(GaulishShopKeeper.class);
        druidMock = mock(Druid.class);

        lycanthropeMock = mock(Lycanthrope.class);
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

        romanCity.addPerson(lycanthropeMock);
        assertTrue(people.contains(lycanthropeMock));
        assertEquals(2, people.size());
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
    void addPersonGallicVillage() {
        gallicVillage.addPerson(romanMock);
        List<Person> people = gallicVillage.getPeople();
        assertFalse(people.contains(romanMock));
        assertEquals(0, people.size());

        gallicVillage.addPerson(gaulishBlacksmithMock);
        assertTrue(people.contains(gaulishBlacksmithMock));
        assertEquals(1, people.size());
    }

    @Test
    void addPersonGalloRomanVillage() {
        galloRomanVillage.addPerson(gaulishInnKeeperMock);
        List<Person> people = galloRomanVillage.getPeople();
        assertTrue(people.contains(gaulishInnKeeperMock));
        assertEquals(1, people.size());

        galloRomanVillage.addPerson(romanMock);
        assertTrue(people.contains(romanMock));
        assertEquals(2, people.size());

        galloRomanVillage.addPerson(lycanthropeMock);
        assertFalse(people.contains(lycanthropeMock));
        assertEquals(2, people.size());
    }

    @Test
    void addPersonEnclosure() {
        enclosure.addPerson(gaulishMock);
        List<Person> people = enclosure.getPeople();
        assertFalse(people.contains(gaulishMock));
        assertEquals(0, people.size());

        enclosure.addPerson(romanMock);
        assertFalse(people.contains(romanMock));
        assertEquals(0, people.size());

        enclosure.addPerson(lycanthropeMock);
        assertTrue(people.contains(lycanthropeMock));
        assertEquals(1, people.size());
    }

    @Test
    void addPersonBattlefield() {
        battleField.addPerson(gaulishMock);
        battleField.addPerson(romanMock);
        battleField.addPerson(lycanthropeMock);
        List<Person> people = battleField.getPeople();
        assertTrue(people.contains(gaulishMock));
        assertTrue(people.contains(romanMock));
        assertTrue(people.contains(lycanthropeMock));
        assertEquals(3, people.size());
    }

    @Test
    void addSamePersonTwice() {
        romanCity.addPerson(romanMock);
        romanCity.addPerson(romanMock);
        assertEquals(1, romanCity.getPeople().size());

        romanCity.addPerson(romanMock2);
        assertEquals(2, romanCity.getPeople().size());
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
    void testAddFood() {
        Food food1 = mock(Food.class);
        Food food2 = mock(Food.class);

        romanCity.addFood(food1);
        romanCity.addFood(food2);

        List<Food> foods = romanCity.getFood();
        assertEquals(2, foods.size(), "Il doit y avoir 2 aliments dans la place");
        assertTrue(foods.contains(food1), "Le premier aliment doit être présent");
        assertTrue(foods.contains(food2), "Le second aliment doit être présent");
    }

    @Test
    void testHealSomeone() {
        romanCity.addPerson(romanMock);
        romanCity.healSomeone(romanMock);
        verify(romanMock).heal();
    }

    @Test
    void testFeedSomeone() {
        romanCity.addPerson(romanMock);
        romanCity.feedSomeone(romanMock);
        verify(romanMock).eat();
    }
}