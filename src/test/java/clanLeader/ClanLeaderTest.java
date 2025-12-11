package clanLeader;

import MagicPotion.MagicPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.gaulish.charac.Druid;
import person.Person;
import place.Place;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Classe de tests unitaires pour la classe ClanLeader
 */
public class ClanLeaderTest {

    ClanLeader chief;
    Place placeMock;
    Place placeMock2;

    Person personMock;
    MagicPotion potionMock;
    Druid druidMock;

    /**
     * Initialise les mocks et crée un chef de clan avant chaque test.
     *
     * <p>Cette méthode permet d’isoler le comportement de ClanLeader en simulant toutes les dépendances.
     */
    @BeforeEach
    void setup() {
        placeMock = mock(Place.class);
        placeMock2 = mock(Place.class);
        personMock = mock(Person.class);
        potionMock = mock(MagicPotion.class);
        druidMock = mock(Druid.class);

        chief = new ClanLeader("Abraracourcix", 'm', 30, placeMock);
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
        when(placeMock.getListOfPersons()).thenReturn(List.of(personMock, druidMock));

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
        when(placeMock.getListOfPersons()).thenReturn(List.of(personMock, druidMock));

        chief.feedAll();

        verify(personMock).eat();
        verify(druidMock).eat();
    }

    /**
     * Vérifie que le transfert fonctionne lorsque la personne est dans le lieu
     * et que la destination autorise son arrivée.
     */
    @Test
    void testTransferPersonAllowed() {
        when(placeMock.getListOfPersons()).thenReturn(List.of(personMock));
        when(placeMock2.canAddPerson(personMock)).thenReturn(true);

        chief.transferPerson(personMock, placeMock2);

        verify(placeMock).removePerson(personMock);
        verify(placeMock2).addPerson(personMock);
    }

    /**
     * Vérifie que le transfert n’a pas lieu lorsque la destination refuse la personne.
     */
    @Test
    void testTransferPersonNotAllowed() {
        when(placeMock.getListOfPersons()).thenReturn(List.of(personMock));
        when(placeMock2.canAddPerson(personMock)).thenReturn(false);

        chief.transferPerson(personMock, placeMock2);

        verify(placeMock, never()).removePerson(any());
        verify(placeMock2, never()).addPerson(any());
    }

    /**
     * Vérifie que le transfert n’a pas lieu lorsque la personne n’est pas présente dans le lieu d’origine.
     */
    @Test
    void testTransferPersonNotInPlace() {
        when(placeMock.getListOfPersons()).thenReturn(List.of());

        chief.transferPerson(personMock, placeMock2);

        verify(placeMock, never()).removePerson(any());
        verify(placeMock2, never()).addPerson(any());
    }
}
