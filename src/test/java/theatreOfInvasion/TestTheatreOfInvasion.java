package theatreOfInvasion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import person.*;
import person.Person;
import person.gaulish.Gaulish;
import person.gaulish.charac.GaulishBlacksmith;
import person.roman.charac.RomanGeneral;
import place.Place;
import place.types.*;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestTheatreOfInvasion {
    private TheatreOfInvasion theatre;

    Place place1 = new RomanFortifiedCamp();
    Place place2 = new RomanCity();
    Place place3 = new GallicVillage();

    @Mock
    Person blacksmith;
    @Mock
    Person romanGeneral;

    @BeforeEach
    void init(){
        ArrayList<Place> lplaces = new ArrayList();
        lplaces.add(place1);
        lplaces.add(place2);
        lplaces.add(place3);
        blacksmith = mock(GaulishBlacksmith.class);
        romanGeneral = mock(RomanGeneral.class);
        theatre = new TheatreOfInvasion("Testing", lplaces);
    }

    // Test la méthode ShowPlaces
    @Test
    void testShowPlaceOfTheatreOfInvasion(){
        ArrayList<Place> result = null;
        Assertions.assertEquals(result,null);
        result = theatre.showPlaces();
        Assertions.assertEquals(result.size(), 3);

    }

    @Test
    void testHowManyPeople(){
        place3.addPerson(blacksmith);
        place1.addPerson(romanGeneral);

        int result = theatre.howManyPeople();
        Assertions.assertEquals(2,result);
    }

    @Test
    void testPeopleByPlace(){
        place3.addPerson(blacksmith);
        List result = theatre.PeopleByPlace(place3);
        Assertions.assertEquals(1,result.size());
    }
}
