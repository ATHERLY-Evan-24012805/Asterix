package clanLeader;

import MagicPotion.MagicPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persons.Gaulish.charac.Druide;
import persons.Person;
import places.Place;

import java.util.List;

import static org.mockito.Mockito.*;

public class ClanLeaderTest {

    ClanLeader chief;
    Place placeMock;
    Place placeMock2;

    Person personMock;
    MagicPotion potionMock;
    Druide druidMock;

    @BeforeEach
    void setup() {
        placeMock = mock(Place.class);
        placeMock2 = mock(Place.class);
        personMock = mock(Person.class);
        potionMock = mock(MagicPotion.class);
        druidMock = mock(Druide.class);

        chief = new ClanLeader("Abraracourcix", "male", 30, placeMock);
    }

    @Test
    void testCreatePerson() {
        chief.createPerson(personMock);
        verify(placeMock).addPerson(personMock);
    }

    @Test
    void testHealSomeone() {
        chief.healSomeone(personMock);
        verify(placeMock).healSomeone(personMock);
    }

    @Test
    void testHealAll() {
        when(placeMock.getPeople()).thenReturn(List.of(personMock, druidMock));

        chief.healAll();

        verify(personMock).heal();
        verify(druidMock).heal();
    }

    @Test
    void testFeedSomeone() {
        chief.feedSomeone(personMock);
        verify(placeMock).feedSomeone(personMock);
    }

    @Test
    void testFeedAll() {
        when(placeMock.getPeople()).thenReturn(List.of(personMock, druidMock));

        chief.feedAll();

        verify(personMock).eat();
        verify(druidMock).eat();
    }

    @Test
    void testTransferPersonAllowed() {
        when(placeMock.getPeople()).thenReturn(List.of(personMock));
        when(placeMock2.canAddPerson(personMock)).thenReturn(true);

        chief.transferPerson(personMock, placeMock2);

        verify(placeMock).removePerson(personMock);
        verify(placeMock2).addPerson(personMock);
    }

    @Test
    void testTransferPersonNotAllowed() {
        when(placeMock.getPeople()).thenReturn(List.of(personMock));
        when(placeMock2.canAddPerson(personMock)).thenReturn(false);

        chief.transferPerson(personMock, placeMock2);

        verify(placeMock, never()).removePerson(any());
        verify(placeMock2, never()).addPerson(any());
    }

    @Test
    void testTransferPersonNotInPlace() {
        when(placeMock.getPeople()).thenReturn(List.of());

        chief.transferPerson(personMock, placeMock2);

        verify(placeMock, never()).removePerson(any());
        verify(placeMock2, never()).addPerson(any());
    }

}
