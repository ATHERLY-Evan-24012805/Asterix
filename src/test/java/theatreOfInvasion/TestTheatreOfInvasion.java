package theatreOfInvasion;

import Theatres.TheatreOfInvasion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import person.Person;
import place.Place;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class TestTheatreOfInvasion {
    private TheatreOfInvasion theatre;

    @Mock
    Place galicVillage, romanFortifiedCamp, romancity ;

    @Mock
    Person gallic, blacksmith;

    @BeforeEach
    void init(){
        ArrayList<Place> lplaces = new ArrayList();
        lplaces.add(galicVillage);
        lplaces.add(romanFortifiedCamp);
        lplaces.add(romancity);
        theatre = new TheatreOfInvasion("scene1", lplaces);
    }

    // Test la méthodes ShowPlaces
    @Test
    void testShowPlaceOfTheatreOfInvasion(){
        ArrayList<Place> result = null;
        Assertions.assertEquals(result,null);
        result = theatre.showPlaces();
        Assertions.assertEquals(result.size(), 3);

    }

    @Test
    void testHowManyPeople(){
        when(galicVillage.getNbpersons()).thenReturn(15);
        when(romancity.getNbpersons()).thenReturn(15);
        when(romanFortifiedCamp.getNbpersons()).thenReturn(15);

        int result = theatre.howManyPeople();
        Assertions.assertEquals(45,result);
    }

    @Test
    void testPeopleByPlace(){
        List p = new ArrayList();
        p.add(gallic);
        p.add(blacksmith);
        when(romancity.getListOfPersonnage()).thenReturn(p);
        List result = theatre.PeopleByPlace(romancity);
        Assertions.assertEquals(result.size(), 2);
    }
}
