package person.gaulish.charac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;
import place.Place;
import place.types.GallicVillage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GaulishInnKeeperTest {

    private GaulishInnKeeper innKeeper;
    private Place testPlace;
    private Person p1;
    private Person p2;

    @BeforeEach
    void setUp() {
        innKeeper = new GaulishInnKeeper("InnKeeperix", 'M', 1.7, 50, 8, 7);

        // Création d'une place de test
        testPlace = new GallicVillage() {
            private List<Person> people = new ArrayList<>();
            private List<Person> healed = new ArrayList<>();

            @Override
            public void addPerson(Person person) {
                people.add(person);
            }

            @Override
            public List<Person> getListOfPersons() {
                return people;
            }

            @Override
            public void healSomeone(Person person) {
                healed.add(person);
                person.setHealth(person.getHealth() + 20); // soin arbitraire
            }

            public List<Person> getHealed() {
                return healed;
            }
        };

        innKeeper.setPlace(testPlace);

        // Créer deux personnes avec différentes PV
        p1 = new GaulishShopKeeper("Asterix", 'M', 1.6, 35, 10, 10);
        p1.setHealth(50);

        p2 = new GaulishBlacksmith("Obelix", 'M', 1.9, 35, 12, 12);
        p2.setHealth(30);

        testPlace.addPerson(p1);
        testPlace.addPerson(p2);
    }

    @Test
    void testWorkHealsWeakestPerson() {
        double initialHealthP1 = p1.getHealth();
        double initialHealthP2 = p2.getHealth();

        innKeeper.work();

        // Obelix (le plus faible) doit avoir plus de PV après le soin
        assertTrue(p2.getHealth() > initialHealthP2, "La personne la plus faible doit être soignée");
        // Asterix (plus fort) ne doit pas avoir été modifié
        assertEquals(initialHealthP1, p1.getHealth(), "La personne la plus forte ne doit pas être soignée");
    }

    @Test
    void testTicsPassedTriggersWork() {
        double initialHealthP2 = p2.getHealth();

        // Faire 19 tics : work() ne doit pas encore être appelé
        for (int i = 0; i < 19; i++) {
            innKeeper.ticsPassed();
        }
        assertEquals(initialHealthP2, p2.getHealth(), "Personne ne doit être soigné avant 20 tics");

        // 20ème tic : work() doit être exécuté
        innKeeper.ticsPassed();
        assertTrue(p2.getHealth() > initialHealthP2, "La personne la plus faible doit être soignée après 20 tics");
    }

    @Test
    void testMultipleCyclesOfTicsPassed() {
        double initialHealthP2 = p2.getHealth();

        // 40 tics = 2 cycles
        for (int i = 0; i < 40; i++) {
            innKeeper.ticsPassed();
        }

        // Le PV de la personne la plus faible doit avoir été augmenté deux fois
        assertTrue(p2.getHealth() > initialHealthP2, "Le travail doit être exécuté deux fois après 40 tics");
    }

}
