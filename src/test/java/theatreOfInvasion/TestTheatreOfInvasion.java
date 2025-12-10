package theatreOfInvasion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import person.*;
import person.Person;
import place.Place;
import place.types.*;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
import java.util.List;

public class TestTheatreOfInvasion {
    private TheatreOfInvasion theatre;

    Place place1 = new RomanFortifiedCamp();
    Place place2 = new RomanCity();
    Place place3 = new GallicVillage();

    @Mock
    Person gallic;
    @Mock
    Person blacksmith;

    @BeforeEach
    void init(){
        ArrayList<Place> lplaces = new ArrayList();
        lplaces.add(place1);
        lplaces.add(place2);
        lplaces.add(place3);
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
        place1.addPerson(blacksmith);
        place2.addPerson(gallic);

        int result = theatre.howManyPeople();
        Assertions.assertEquals(2,result);
    }

    @Test
    void testPeopleByPlace(){
        List result = theatre.PeopleByPlace(place2);
        Assertions.assertEquals(result.size(), 2);
    }
}
