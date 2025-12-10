package person.gaulish.charac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;
import place.Place;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GaulishBlacksmithTest {

    private GaulishBlacksmith blacksmith;
    private Place mockPlace;
    private Person mockPerson;

    @BeforeEach
    void setUp() {
        blacksmith = new GaulishBlacksmith("Asterix", 'M', 1.75, 30, 10, 15);
        mockPlace = mock(Place.class);
        mockPerson = mock(Person.class);
    }

    /**
     * Vérifie que work() ne fait rien si le forgeron n'a pas de place assignée.
     */
    @Test
    void testWorkWithoutPlace() {
        blacksmith.setPlace(null);

        assertDoesNotThrow(() -> blacksmith.work());
    }

    /**
     * Vérifie que work() ne fait rien si la place ne contient aucun habitant.
     */
    @Test
    void testWorkWithEmptyPlace() {
        when(mockPlace.getPeople()).thenReturn(new ArrayList<>());
        blacksmith.setPlace(mockPlace);

        blacksmith.work();

        // Vérifier qu'aucune personne n'a été modifiée
        verify(mockPerson, never()).setStrength(anyInt());
    }

    /**
     * Vérifie que work() augmente bien la force d'un habitant de 1 point.
     */
    @Test
    void testWorkIncreasesStrength() {
        List<Person> people = new ArrayList<>();
        people.add(mockPerson);

        when(mockPlace.getPeople()).thenReturn(people);
        when(mockPerson.getStrength()).thenReturn(10).thenReturn(11);
        when(mockPerson.getName()).thenReturn("Obelix");

        blacksmith.setPlace(mockPlace);
        blacksmith.work();

        // Vérifier que la force a été augmentée de 1
        verify(mockPerson).setStrength(11);
        verify(mockPerson, times(2)).getStrength();
    }

    /**
     * Vérifie que work() sélectionne exactement un habitant parmi plusieurs.
     */
    @Test
    void testWorkWithMultiplePeople() {
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);
        Person person3 = mock(Person.class);

        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);

        when(mockPlace.getPeople()).thenReturn(people);
        when(person1.getStrength()).thenReturn(10);
        when(person2.getStrength()).thenReturn(12);
        when(person3.getStrength()).thenReturn(8);
        when(person1.getName()).thenReturn("Person1");
        when(person2.getName()).thenReturn("Person2");
        when(person3.getName()).thenReturn("Person3");

        blacksmith.setPlace(mockPlace);
        blacksmith.work();

        // Vérifier qu'exactement une personne a eu sa force augmentée
        int modificationsCount = 0;
        try {
            verify(person1).setStrength(anyInt());
            modificationsCount++;
        } catch (AssertionError e) {
            // Person1 n'a pas été modifié
        }

        try {
            verify(person2).setStrength(anyInt());
            modificationsCount++;
        } catch (AssertionError e) {
            // Person2 n'a pas été modifié
        }

        try {
            verify(person3).setStrength(anyInt());
            modificationsCount++;
        } catch (AssertionError e) {
            // Person3 n'a pas été modifié
        }

        assertEquals(1, modificationsCount, "Exactement une personne devrait avoir sa force augmentée");
    }

    /**
     * Vérifie que work() peut renforcer le forgeron lui-même s'il est présent dans la liste des habitants.
     */
    @Test
    void testWorkCanTargetSelf() {
        List<Person> people = new ArrayList<>();
        people.add(blacksmith);

        when(mockPlace.getPeople()).thenReturn(people);
        blacksmith.setPlace(mockPlace);

        int initialStrength = blacksmith.getStrength();
        blacksmith.work();

        // Le forgeron peut se renforcer lui-même
        assertEquals(initialStrength + 1, blacksmith.getStrength());
    }
}