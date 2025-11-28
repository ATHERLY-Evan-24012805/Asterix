package test.places;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.persons.Person;
import org.places.Place;
import org.places.RomanCity;


import java.util.List;

public class PlaceTest {

    @Test
    void testHealSomeone() {
        Place place = new RomanCity();
        Person personMock = mock(Person.class);

        place.healSomeone(personMock);
        verify(personMock).heal();
    }

    @Test
    void testFeedPeople() {
        Place place = new RomanCity();
        Person personMock = mock (Person.class);

        place.feedSomeone(personMock);
        verify(personMock).eat();
    }

    @Test
    void testAddPerson() {
        Place place = new RomanCity();
        Person personMock = mock (Person.class);
        place.addPerson(personMock);

        List<Person> people = place.getPeople();

        assertTrue(people.contains(personMock));
        assertEquals(1, people.size());
    }

    @Test
    void testRemovePerson() {
        Place place = new RomanCity();
        Person personMock = mock (Person.class);
        place.addPerson(personMock);
        place.removePerson(personMock);

        List<Person> people = place.getPeople();

        assertFalse(people.contains(personMock));
        assertEquals(0, people.size());

    }
}