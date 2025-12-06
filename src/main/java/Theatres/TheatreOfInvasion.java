package Theatres;

import clock.Clock;
import place.Place;

import java.util.ArrayList;
import java.util.List;

public class TheatreOfInvasion {
    private String name;
    private int numberOfPlaces;
    private ArrayList<Place> listOfPlaces;
    private List listOfChief;

    //Constructeur
    public TheatreOfInvasion(String nameOfTheGame, ArrayList<Place> listOfPlaces) {

        this.name = name;
        this.listOfPlaces = listOfPlaces;
    }


    public ArrayList<Place> showPlaces() {
        return listOfPlaces;
    }

    public int howManyPeople() {
        int compt = 0;
        for (Place p : listOfPlaces) {
            compt+=p.getNbpersons();
        }
        return compt;
    }

    public List PeopleByPlace(Place place){
        List people = new ArrayList();
        people = place.getListOfPersonnage();
        return null;
    }
    public static void main (String[] args) {
        System.out.println("Bienvenue dans le théatre d'envahisement !" ); // combien de joueur êtes-vous ?

        Clock clock = new Clock();

    }

}
